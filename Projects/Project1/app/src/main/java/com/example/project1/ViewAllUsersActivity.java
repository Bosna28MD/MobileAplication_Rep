package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ViewAllUsersActivity extends AppCompatActivity {

    Button btn_MainMenu;
    RecyclerView recycleView_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        recycleView_list=findViewById(R.id.recycleView_list);
        btn_MainMenu=findViewById(R.id.btnMainMenu);

        DataBaseHelper db=new DataBaseHelper(this);
        ArrayList<UserClass> arr_users=db.Select_All();

        RecycleViewUsers adapter=new RecycleViewUsers(this);
        adapter.setArrUsers(arr_users);

        recycleView_list.setAdapter(adapter);
        recycleView_list.setLayoutManager(new LinearLayoutManager(this));

        btn_MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ViewAllUsersActivity.this,MainMenuActivity.class);
                startActivity(i);
            }
        });

    }


    public RecyclerView getRecycleView_list() {
        return recycleView_list;
    }
}