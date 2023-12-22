package com.example.project_lab_db1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtName,txtEmail,txtGrade;
    Button btnAdd,btnViewAll;

    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName=findViewById(R.id.edTxtName);
        txtEmail=findViewById(R.id.edTxtEmail);
        txtGrade=findViewById(R.id.edTxtGrade);

        btnAdd=findViewById(R.id.btnAdd);
        btnViewAll=findViewById(R.id.btnViewAll);

        db=openOrCreateDatabase("StudentDB1", Context.MODE_PRIVATE,null);
        db.execSQL("Create Table if not exists student(id integer primary key,name_student text,email_student text,grade_student integer);");




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(txtName.getText().toString().trim().length()==0 || txtEmail.getText().toString().trim().length()==0
                    || txtGrade.getText().toString().trim().length()==0){
                Toast.makeText(MainActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                return;
            }
                String nameTxt=txtName.getText().toString();
                String emailTxt=txtEmail.getText().toString();
                String gradeTxt=txtGrade.getText().toString();

                db.execSQL("Insert Into student(name_student,email_student,grade_student) values('"+nameTxt+"','"+emailTxt+"','"+gradeTxt+"');");
                Toast.makeText(MainActivity.this, "Record Added", Toast.LENGTH_SHORT).show();
                txtName.setText("");
                txtEmail.setText("");
                txtGrade.setText("");


            }
        });


        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ViewAllActivity.class);
                startActivity(i);
                //MainActivity.this.finish();
                finish();
            }
        });



    }
}

