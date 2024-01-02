package com.example.myapplication_stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager=null;
    private Sensor stepSensor;
    private int totalSteps=0;
    private int previewsTotalSteps=0;
    private ProgressBar progressBar;
    private TextView txtSteps;

    int appStart=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar=findViewById(R.id.ProgressBar);
        txtSteps=findViewById(R.id.txtSteps);

        progressBar.setMax(8000);

        restartStep();
        //saveData();
        //loadData();

        //appStartSet();
        if(!appStartGet()){
            Toast.makeText(this, "First Time", Toast.LENGTH_SHORT).show();
            appStartSet();
            appStart=0;
        }
        else {
            Toast.makeText(this, "Another Time", Toast.LENGTH_SHORT).show();
            appStart=1;
            loadData();
        }

        mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        stepSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


    }

    protected void onResume(){
        super.onResume();
        if(stepSensor==null){
            Toast.makeText(this, "This device doesn't have Sensory", Toast.LENGTH_SHORT).show();
            return;
        }

        mSensorManager.registerListener(this,stepSensor,SensorManager.SENSOR_DELAY_NORMAL);


    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER){

            if(appStart==0){
                saveData((int)sensorEvent.values[0]);
                loadData();
                appStart=1;
            }

            totalSteps=(int)sensorEvent.values[0];
            int currentSteps=totalSteps-previewsTotalSteps;
            /*runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    txtSteps.setText("Steps: " + currentSteps);
                    progressBar.setProgress(currentSteps);
                }
            });*/
            txtSteps.setText("Steps: "+currentSteps);
            progressBar.setProgress(currentSteps);


        }
    }

    private void restartStep(){
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Long press to reset steps", Toast.LENGTH_SHORT).show();
            }
        });


        progressBar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                previewsTotalSteps=totalSteps;
                txtSteps.setText("Steps: "+0);
                progressBar.setProgress(0);
                appStart=0;
                saveData(previewsTotalSteps);
                //appStartReset();

                return true;
            }
        });
    }


    private void appStartSet(){
        SharedPreferences sharePref=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharePref.edit();
        editor.putBoolean("key2",true);
        editor.apply();
    }

    private boolean appStartGet(){
        SharedPreferences sharePref=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor=sharePref.edit();
        boolean savedNumber = sharePref.getBoolean("key2", false);
        return savedNumber;
    }

    private void appStartReset(){
        SharedPreferences settings = MainActivity.this.getSharedPreferences("myPref", Context.MODE_PRIVATE);
        settings.edit().remove("key2").commit();
    }

    private void saveData(int value){
        SharedPreferences sharePref=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharePref.edit();
        editor.putInt("key1",value);
        editor.apply();

    }


    /*private void loadData(){
        SharedPreferences sharePref=getSharedPreferences("myPref", Context.MODE_PRIVATE);
        //sharePref.getInt("key1",0);
        int savedNumber=sharePref.getInt("key1",0);
        //Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        //previewsTotalSteps=savedNumber;
    }*/


    private void loadData(){
        SharedPreferences sharePref = getSharedPreferences("myPref", Context.MODE_PRIVATE);
        int savedNumber = sharePref.getInt("key1", '0');
        // Use the retrieved int value appropriately
        previewsTotalSteps = savedNumber;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}