package com.example.user_authentification_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LogIn extends AppCompatActivity {

    Button btn_LogIn;
    TextInputEditText txt_Email,txt_pwd;
    TextView change_register;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        auth=FirebaseAuth.getInstance();

        change_register=findViewById(R.id.change_register);
        btn_LogIn=findViewById(R.id.btn_LogIn);

        txt_Email=findViewById(R.id.txt_email_logIn);
        txt_pwd=findViewById(R.id.txt_pass_logIn);

        change_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogIn.this,RegistrationActivity.class);
                startActivity(i);
                finish();
            }
        });


        btn_LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txt_Email.getText().toString().length()==0 || txt_pwd.getText().toString().length()==0){
                    Toast.makeText(LogIn.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(txt_Email.getText().toString()).matches()){
                    txt_Email.setError("Valid Email is required");
                    txt_Email.requestFocus();
                    return;
                }



                LogIn_User(txt_Email.getText().toString(),txt_pwd.getText().toString());



            }
        });



        //btn_Register=findViewById(R.id.btn_LogIn);

        /*btn_Register_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LogIn.this,RegistrationActivity.class);
                startActivity(i);
            }
        });*/

    }


    private void LogIn_User(String email,String pwd){
        auth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(LogIn.this, "Successfully LogIn", Toast.LENGTH_SHORT).show();
                    FirebaseUser user = auth.getCurrentUser();

                }else{


                    Log.d("LogIn555",task.getException().getMessage());
                    if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                        // The provided email does not exist in the authentication database
                        Toast.makeText(LogIn.this, "Email not registered", Toast.LENGTH_SHORT).show();
                        // Handle this case or prompt the user to register
                    } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // The provided password is incorrect
                        Toast.makeText(LogIn.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        // Handle this case or prompt the user to reset their password
                    } else {
                        // Other authentication failures
                        Toast.makeText(LogIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });



    }


}