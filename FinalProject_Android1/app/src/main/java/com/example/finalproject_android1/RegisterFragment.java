package com.example.finalproject_android1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        //((TextView)view.findViewById(R.id.err_register)).setVisibility(View.VISIBLE);

        TextView errMess=view.findViewById(R.id.err_register);
        EditText edEmail=view.findViewById(R.id.edtxt_email_reg);
        EditText edUsername=view.findViewById(R.id.edtxt_name_reg);
        EditText edBirthday=view.findViewById(R.id.edtxt_birthday_reg);
        EditText edPassword=view.findViewById(R.id.edtxt_pwd_reg);

        errMess.setVisibility(View.GONE);

        ((Button)view.findViewById(R.id.btn_register)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                errMess.setVisibility(View.GONE);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url ="http://192.168.0.53/FinalProject_Android/register.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //textView.setText("Response is: "+ response);
                                if(response.equals("Success Registration")){
                                    Toast.makeText(getContext(), "Success Registration", Toast.LENGTH_SHORT).show();
                                    replaceFragment(new LogInFragment());
                                    //Intent i=new Intent(RegisterActivity.this,LogInActivity.class);
                                    //startActivity(new Intent(getContext(),MainActivity2.class));
                                    //getActivity().finish();
                                }
                                else{
                                    errMess.setVisibility(View.VISIBLE);
                                    errMess.setText(response);
                                    //((TextView)view.findViewById(R.id.err_register)).setVisibility(View.VISIBLE);
                                    //((TextView)view.findViewById(R.id.err_register)).setText(response);

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //((TextView)view.findViewById(R.id.err_register)).setVisibility(View.VISIBLE);
                        //((TextView)view.findViewById(R.id.err_register)).setText("Some error appeared");
                        errMess.setText("Some Error appeared");
                        errMess.setVisibility(View.VISIBLE);
                        //Log.d("Error REGISTRATION-->", error.getLocalizedMessage());
                        //textView.setText("That didn't work!");
                        //error_message.setVisibility(View.VISIBLE);
                        //error_message.setText(error.getLocalizedMessage());
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("name",edUsername.getText().toString() );
                        paramV.put("email",edEmail.getText().toString() );
                        paramV.put("birthday",edBirthday.getText().toString() );
                        paramV.put("pwd", edPassword.getText().toString());
                        return paramV;
                    }
                };
                queue.add(stringRequest);



            }
        });

        ((EditText)view.findViewById(R.id.edtxt_birthday_reg)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);
                int year=calendar.get(Calendar.YEAR);

                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        ((EditText)view.findViewById(R.id.edtxt_birthday_reg)).setText(i+"-"+(i1+1)+"-"+i2);
                    }
                },year,month,day).show();
            }
        });

    }



    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getParentFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        if(fragment.getClass().equals( (new LogInFragment()).getClass() )){
            //Toast.makeText(getContext(), "Fragment Equals", Toast.LENGTH_SHORT).show();
            bottomNavigationView.setSelectedItemId(R.id.login_bottom);
        }else if( fragment.getClass().equals( (new RegisterFragment()).getClass() ) ){
            bottomNavigationView.setSelectedItemId(R.id.register_bottom);
        }
        //Toast.makeText(getContext(), fragment.la(), Toast.LENGTH_SHORT).show();

        //BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        //Toast.makeText(getContext(), fragment.getId(), Toast.LENGTH_SHORT).show();
        //bottomNavigationView.setSelectedItemId(fragment.getId());
    }

}