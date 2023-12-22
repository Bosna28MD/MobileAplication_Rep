package com.example.project_lab_db1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    TextView txt_id;
    EditText edTxtName,edTxtEmail,edTxtGrade;
    Button btnEdit,btnAddMenu,btnViewMenu;
    SQLiteDatabase db;
    int id_Student=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        db=openOrCreateDatabase("StudentDB1", Context.MODE_PRIVATE,null);

        txt_id=findViewById(R.id.txtIdUpd);

        edTxtName=findViewById(R.id.edNameUpd);
        edTxtEmail=findViewById(R.id.edEmailUpd);
        edTxtGrade=findViewById(R.id.edGradeUpd);

        btnEdit=findViewById(R.id.btnEdit);
        btnAddMenu=findViewById(R.id.btnBackAdd);
        btnViewMenu=findViewById(R.id.btnBackView);


        Intent intent=getIntent();
        id_Student=Integer.parseInt(intent.getStringExtra("idStudent"));
        txt_id.setText(txt_id.getText()+" "+id_Student);
        edTxtName.setText(intent.getStringExtra("nameStudent"));
        edTxtEmail.setText(intent.getStringExtra("emailStudent"));
        edTxtGrade.setText(intent.getStringExtra("gradeStudent"));



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edTxtName.getText().toString().trim().length()==0 || edTxtEmail.getText().toString().trim().length()==0
                        || edTxtGrade.getText().toString().trim().length()==0){
                    Toast.makeText(UpdateActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                String name=edTxtName.getText().toString();
                String email=edTxtEmail.getText().toString();
                Integer grade=Integer.parseInt(edTxtGrade.getText().toString());
                ViewAllActivity.Update_Student_Db(new Student(id_Student,grade,name,email),UpdateActivity.this.db);

                Intent i=new Intent(UpdateActivity.this,ViewAllActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });


        btnViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UpdateActivity.this,ViewAllActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}