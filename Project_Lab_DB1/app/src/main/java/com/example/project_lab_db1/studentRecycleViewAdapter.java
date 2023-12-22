package com.example.project_lab_db1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studentRecycleViewAdapter extends RecyclerView.Adapter<studentRecycleViewAdapter.ViewHolder> {

    private ArrayList<Student> student_arr=new ArrayList<>();
    private Context context;

    public studentRecycleViewAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(student_arr.get(position).getId()));
        holder.txtName.setText(String.valueOf(student_arr.get(position).getName()));
        holder.txtEmail.setText(String.valueOf(student_arr.get(position).getEmail()));
        holder.txtGrade.setText(String.valueOf(student_arr.get(position).getGrade()));

        SQLiteDatabase db=this.context.openOrCreateDatabase("StudentDB1", Context.MODE_PRIVATE,null);
        //db.execSQL("Create Table if not exists student(id integer primary key,name_student text,email_student text,grade_student integer);");

        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAllActivity.Delete_Student_DB(student_arr.get(position).getId(),db);   //Delete From DataBase
                studentRecycleViewAdapter.this.setArrStudents(ViewAllActivity.Select_All_DB(db));
                ((ViewAllActivity)(studentRecycleViewAdapter.this.context)).getRecycleView_AllStudents().setAdapter(studentRecycleViewAdapter.this);
            }
        });


        holder.imgBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ViewAllActivity.(studentRecycleViewAdapter.this.context)=null;
                Intent i=new Intent(studentRecycleViewAdapter.this.context,UpdateActivity.class);
                i.putExtra("idStudent",String.valueOf(student_arr.get(position).getId()) );
                i.putExtra("nameStudent",String.valueOf(student_arr.get(position).getName()) );
                i.putExtra("emailStudent",String.valueOf(student_arr.get(position).getEmail()) );
                i.putExtra("gradeStudent",String.valueOf(student_arr.get(position).getGrade()));
                studentRecycleViewAdapter.this.context.startActivity(i);
                ((ViewAllActivity)studentRecycleViewAdapter.this.context).finish();

            }
        });


    }

    @Override
    public int getItemCount() {
        return student_arr.size();
    }

    public void setArrStudents(ArrayList<Student> student_arr){
        this.student_arr=student_arr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtId;
        private TextView txtName;
        private TextView txtEmail;
        private TextView txtGrade;
        private ImageButton imgBtnEdit,imgBtnDelete;

        private LinearLayout parent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            txtId=itemView.findViewById(R.id.txtId);
            txtName=itemView.findViewById(R.id.txtName);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtGrade=itemView.findViewById(R.id.txtGrade);

            imgBtnEdit=itemView.findViewById(R.id.btnIcEdit);
            imgBtnDelete=itemView.findViewById(R.id.btnIcDelete);
        }
    }
}
