package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    TextView txt_title,txt_errEmail,txt_errPwd;
    EditText edEmail,edPwd;
    Button btn_MainMenu,btn_LogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        Initialize_ViewId();

    }


    void Initialize_ViewId(){
        txt_title=findViewById(R.id.title_LogIn);
        txt_errEmail=findViewById(R.id.errUserEmail_id);
        txt_errPwd=findViewById(R.id.errPwdUser_id);

        edEmail=findViewById(R.id.edUserEmail_id);
        edPwd=findViewById(R.id.edPwdUser_id);

        btn_MainMenu=findViewById(R.id.btn_MainMenu);
        btn_LogIn=findViewById(R.id.btnLogIn_id);
    }

    boolean Validate_LogIn_User(){
        boolean validate_check=true;

        if(edEmail.getText().toString().equals("")){
            txt_errEmail.setVisibility(View.VISIBLE);
            txt_errEmail.setText("Empty Field");
            validate_check=false;
        }

        if(edPwd.getText().toString().equals("")){
            txt_errPwd.setVisibility(View.VISIBLE);
            txt_errPwd.setText("Empty Field");
            validate_check=false;
        }


        


        return  validate_check;
    }
}