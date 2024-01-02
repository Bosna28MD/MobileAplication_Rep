package com.example.finalproject_android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.finalproject_android1.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSharedPreferences("UserLogIn",MODE_PRIVATE).getString("logged","false").equals("true")){
            Intent i=new Intent(MainActivity.this,MainActivity2.class);
            startActivity(i);
            finish();
        }

        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main);

        binding.bottomNavigationView.setBackground(null);
        replaceFragment(new LogInFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.login_bottom){
                replaceFragment(new LogInFragment());
            }else if(item.getItemId()==R.id.register_bottom){
                replaceFragment(new RegisterFragment());
            }

            return true;
        });



    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

}