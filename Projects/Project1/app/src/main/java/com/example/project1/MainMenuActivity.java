package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    Button btn_LogIn,btn_Register,btn_viewUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btn_Register=findViewById(R.id.btn_Register);
        btn_viewUsers=findViewById(R.id.viewUsers_id);

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainMenuActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

        btn_viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainMenuActivity.this,ViewAllUsersActivity.class);
                startActivity(i);
            }
        });
    }
}