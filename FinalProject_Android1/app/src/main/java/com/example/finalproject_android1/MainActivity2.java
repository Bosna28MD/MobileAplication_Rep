package com.example.finalproject_android1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.finalproject_android1.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {


    //ActivityMainBinding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(getSharedPreferences("UserLogIn",MODE_PRIVATE).getString("logged","true").equals("false")){
            Intent i=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(i);
            finish();
        }
        //Toast.makeText(this, "User IS LOGIN", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //binding2=ActivityMainBinding.inflate(getLayoutInflater());
        //setContentView(binding2.getRoot());
        //setContentView(R.layout.activity_main2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setSelectedItemId(R.id.home_bottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            if(item.getItemId()==R.id.home_bottom){
                replaceFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.friends_bottom){
                replaceFragment(new FriendListFragment());
            }else if(item.getItemId()==R.id.friendsAdd_bottom){
                replaceFragment(new FriendAddFragment());
            }else if(item.getItemId()==R.id.notification_bottom){
                replaceFragment(new NotificationFragment());
            }else if(item.getItemId()==R.id.logout_bottom){
                replaceFragment(new LogOutFragment());
            }

            return true;
        });

        //binding2.bottomNavigationView

        /*
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout2, new HomeFragment())
                .commit();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home_bottom){
                    replaceFragment(new HomeFragment());
                }else if(item.getItemId()==R.id.friends_bottom){
                    replaceFragment(new FriendListFragment());
                }else if(item.getItemId()==R.id.friendsAdd_bottom){
                    replaceFragment(new FriendAddFragment());
                }else if(item.getItemId()==R.id.notification_bottom){
                    replaceFragment(new NotificationFragment());
                }else if(item.getItemId()==R.id.logout_bottom){
                    replaceFragment(new LogOutFragment());
                }

                return false;
            }
        });

        */

        //binding2.bottomNavigationView.setBackground(null);
        //replaceFragment(new HomeFragment());
        /*binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.home_bottom){
                replaceFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.friends_bottom){
                replaceFragment(new FriendListFragment());
            }else if(item.getItemId()==R.id.friendsAdd_bottom){
                replaceFragment(new FriendAddFragment());
            }else if(item.getItemId()==R.id.notification_bottom){
                replaceFragment(new NotificationFragment());
            }else if(item.getItemId()==R.id.logout_bottom){
                replaceFragment(new LogOutFragment());
            }

            return true;
        });*/

        /*((Button)findViewById(R.id.btnlogout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=getSharedPreferences("UserLogIn",MODE_PRIVATE).edit();
                editor.putString("logged","false");
                editor.putString("name","");
                editor.putString("email","");
                editor.putString("birthday","");
                editor.apply();

                Intent i=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });*/
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout2,fragment);
        fragmentTransaction.commit();

        /*BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView2);

        if(fragment.getClass().equals( (new HomeFragment()).getClass() )){
            //Toast.makeText(getContext(), "Fragment Equals", Toast.LENGTH_SHORT).show();
            bottomNavigationView.setSelectedItemId(R.id.home_bottom);
        }else if( fragment.getClass().equals( (new FriendListFragment()).getClass() ) ){
            bottomNavigationView.setSelectedItemId(R.id.friends_bottom);
        }else if( fragment.getClass().equals( (new FriendAddFragment()).getClass() ) ){
            bottomNavigationView.setSelectedItemId(R.id.friendsAdd_bottom);
        }else if( fragment.getClass().equals( (new NotificationFragment()).getClass() ) ){
            bottomNavigationView.setSelectedItemId(R.id.notification_bottom);
        }

         */
    }



}