package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewUsers extends RecyclerView.Adapter<RecycleViewUsers.ViewHolder>  {

    private ArrayList<UserClass> user_arr=new ArrayList<>();
    private Context context;


    public RecycleViewUsers(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allusers_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtId.setText(String.valueOf(user_arr.get(position).getId()));
        holder.txtName.setText(user_arr.get(position).getName());
        holder.txtEmail.setText(user_arr.get(position).getEmail());
        holder.txtPwd.setText(user_arr.get(position).getPwd());

        holder.imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, user_arr.get(position).getId().toString(), Toast.LENGTH_SHORT).show();
                DataBaseHelper db=new DataBaseHelper(context);
                db.delete_Row(user_arr.get(position)); //Delete from database
                RecycleViewUsers.this.setArrUsers(db.Select_All()); //Reset the array
                ((ViewAllUsersActivity)context).getRecycleView_list().setAdapter(RecycleViewUsers.this); //Update the interface

            }
        });


    }

    @Override
    public int getItemCount() {
        return user_arr.size();
    }


    public void setArrUsers(ArrayList<UserClass> user_arr){
        this.user_arr=user_arr;
        //notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtId;
        private TextView txtName;
        private TextView txtEmail;
        private TextView txtPwd;
        private ImageButton imgBtnEdit,imgBtnDelete;

        private LinearLayout parent;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            txtId=itemView.findViewById(R.id.id_userRV);
            txtName=itemView.findViewById(R.id.name_userRV);
            txtEmail=itemView.findViewById(R.id.email_userRV);
            txtPwd=itemView.findViewById(R.id.pwd_userRV);

            imgBtnEdit=itemView.findViewById(R.id.edit_btn);
            imgBtnDelete=itemView.findViewById(R.id.delete_btn);
        }
    }
}
