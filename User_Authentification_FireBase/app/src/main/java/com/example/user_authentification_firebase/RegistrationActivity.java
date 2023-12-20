package com.example.user_authentification_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    Button btn_register;
    TextInputEditText txt_email,txt_name,txt_age,txt_pwd;
    TextView change_login;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        btn_register=findViewById(R.id.btn_Register);
        txt_email=findViewById(R.id.txt_email_register);
        txt_name=findViewById(R.id.txt_name_register);
        txt_age=findViewById(R.id.txt_age_register);
        txt_pwd=findViewById(R.id.txt_pass_register);

        change_login=findViewById(R.id.change_login);

        change_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(RegistrationActivity.this,LogIn.class);
                startActivity(i);
                finish();
            }
        });


        txt_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);

                /*
                picker=new DatePicker(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view,int year,int month,int dayOfMonth){

                    }
                },year,month,day);
                 */
                picker=new DatePickerDialog(RegistrationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        txt_age.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },year,month,day);
                picker.show();
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txt_email.getText().toString().length()==0 || txt_name.getText().toString().length()==0
                        || txt_age.getText().toString().length()==0 || txt_pwd.getText().toString().length()==0){
                    Toast.makeText(RegistrationActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(txt_email.getText().toString()).matches()){
                    txt_email.setError("Valid Email is required");
                    txt_email.requestFocus();
                    return;
                }

                register_User(txt_email.getText().toString(),txt_pwd.getText().toString(),txt_name.getText().toString(),txt_age.getText().toString());

            }
        });


    }


    private void register_User(String txtEmail,String txtPwd,String name,String age){
        auth=FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(txtEmail,txtPwd).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegistrationActivity.this, "User has been Created", Toast.LENGTH_SHORT).show();
                    firebaseUser= auth.getCurrentUser();

                    //UserProfileChangeRequest profileChangeRequest=new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                    //firebaseUser.updateProfile(profileChangeRequest);

                    String userId=firebaseUser.getUid();
                    UserRestData userRestData=new UserRestData(name,age);
                    DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(userId).setValue(userRestData).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "All user data added", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(RegistrationActivity.this,LogIn.class);
                                startActivity(i);
                                finish();
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Some Error Appeared all-data..", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    //firebaseUser.sendEmailVerification();


                }
                else{
                    //Toast.makeText(RegistrationActivity.this, "Some Error Apperead..", Toast.LENGTH_SHORT).show();
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(RegistrationActivity.this, "Email invalid or already in use", Toast.LENGTH_SHORT).show();
                    }catch (FirebaseAuthUserCollisionException e){
                        Toast.makeText(RegistrationActivity.this, "User is already registered with this email", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}