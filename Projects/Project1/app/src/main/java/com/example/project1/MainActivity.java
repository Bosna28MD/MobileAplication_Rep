package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txt_Register,txtErrName,txtErrEmail,txtErrPwd,txtErrRePwd;
    EditText edName,edEmail,edPwd,edRePwd;
    Button btnRegister,btn_Menu;
    ConstraintLayout parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize_ViewId();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set_Invisible_Err();
                Register_User();
            }
        });

        btn_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,MainMenuActivity.class);
                startActivity(i);
                //Toast.makeText(MainActivity.this, "CLicked Main Menu", Toast.LENGTH_SHORT).show();
            }
        });


    }


    void Initialize_ViewId(){
        txt_Register=findViewById(R.id.title_Register);

        txtErrName=findViewById(R.id.errorName_id);
        txtErrEmail=findViewById(R.id.errorEmail_id);
        txtErrPwd=findViewById(R.id.errorPwd_id);
        txtErrRePwd=findViewById(R.id.errorRePwd_id);

        edName=findViewById(R.id.edName_id);
        edEmail=findViewById(R.id.edEmail_id);
        edPwd=findViewById(R.id.edPwd_id);
        edRePwd=findViewById(R.id.edRePwd_id);

        btnRegister=findViewById(R.id.btnRegister_id);
        btn_Menu=findViewById(R.id.btn_Menu);

        parent=findViewById(R.id.parent);

    }

    void Register_User(){
        if(Validate_Register_User()){
            DataBaseHelper db=new DataBaseHelper(this);

            String name= edName.getText().toString();
            String email=edEmail.getText().toString();
            String pwd=edPwd.getText().toString();

            boolean result=db.add_Row(new UserClass(-1,name,email,pwd));
            if(result){
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "User Not Registered :(", Toast.LENGTH_SHORT).show();




        }
    }

    boolean Validate_Register_User(){
        boolean check_valid=true;
        if(edName.getText().toString().equals("")){
            txtErrName.setVisibility(View.VISIBLE);
            txtErrName.setText("Empty Field");
            check_valid=false;
        }

        if(edEmail.getText().toString().equals("")){
            txtErrEmail.setVisibility(View.VISIBLE);
            txtErrEmail.setText("Empty Field");
            check_valid=false;
        }

        if(edPwd.getText().toString().equals("")){
            txtErrPwd.setVisibility(View.VISIBLE);
            txtErrPwd.setText("Empty Field");
            check_valid=false;
        }

        if(edRePwd.getText().toString().equals("")){
            txtErrRePwd.setVisibility(View.VISIBLE);
            txtErrRePwd.setText("Empty Field");
            check_valid=false;
        }

        if(!edPwd.getText().toString().equals("") &&  !(edPwd.getText().toString().equals(edRePwd.getText().toString()) ) ){
            txtErrRePwd.setVisibility(View.VISIBLE);
            txtErrRePwd.setText("Confirmation Password Incorrect");
            check_valid=false;
        }

        return check_valid;
    }


    void Set_Invisible_Err(){
        txtErrName.setVisibility(View.GONE);
        txtErrEmail.setVisibility(View.GONE);
        txtErrPwd.setVisibility(View.GONE);
        txtErrRePwd.setVisibility(View.GONE);
    }

}