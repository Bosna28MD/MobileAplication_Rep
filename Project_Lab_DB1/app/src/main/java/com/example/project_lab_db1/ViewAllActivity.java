package com.example.project_lab_db1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewAllActivity extends AppCompatActivity {

    Button btn_BackAddMenu,btnViewStudents;



    RecyclerView recycleView_AllStudents;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        recycleView_AllStudents=findViewById(R.id.recycleView_list);

        //db=MainActivity.db;
        db=openOrCreateDatabase("StudentDB1", Context.MODE_PRIVATE,null);
        db.execSQL("Create Table if not exists student(id integer primary key,name_student text,email_student text,grade_student integer);");

        ArrayList<Student> student_arr=Select_All_DB(db);
        studentRecycleViewAdapter adapter=new studentRecycleViewAdapter(this);
        adapter.setArrStudents(student_arr);
        recycleView_AllStudents.setAdapter(adapter);
        recycleView_AllStudents.setLayoutManager(new LinearLayoutManager(this));



        btn_BackAddMenu=findViewById(R.id.btnBackAddMenu);
        //btnViewStudents=findViewById(R.id.btnViewStudents);

        btn_BackAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewAllActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        /*
        btnViewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.rawQuery("Select * from student where id=3 ",null);
                if(c==null){
                    Toast.makeText(ViewAllActivity.this,"Invalid..." , Toast.LENGTH_SHORT).show();
                    return;
                }

                if(c.moveToFirst()){
                    Toast.makeText(ViewAllActivity.this,c.getString(0)+" "+c.getString(1) , Toast.LENGTH_SHORT).show();
                }

            }
        });
        */

    }


    public static ArrayList<Student> Select_All_DB(SQLiteDatabase db){
        ArrayList<Student> student_arr=new ArrayList<>();

        Cursor c=db.rawQuery("Select * from student",null);
        if(c.moveToFirst()){
            do{
                student_arr.add(new Student(c.getInt(0),c.getInt(3),c.getString(1),c.getString(2)));
            }while (c.moveToNext());
        }

        c.close();
        return  student_arr;
    }

    public static boolean Delete_Student_DB(int id,SQLiteDatabase db){

        Cursor c=db.rawQuery("Delete from student where id="+id,null);
        if(c.moveToFirst()){
            return true;
        }


        return false;
    }


    public static void Update_Student_Db(Student student,SQLiteDatabase db){
        Cursor c=db.rawQuery("Update student set name_student='"+student.getName()+"' , email_student='"+student.getEmail()+"' , grade_student='"+student.getGrade()+"' where  id="+student.getId()+" ;",null);
        if(c.moveToFirst()){

        }
    }

    public RecyclerView getRecycleView_AllStudents() {
        return recycleView_AllStudents;
    }
    public ViewAllActivity getAdressViewAllActivity(){ return  this; }

}