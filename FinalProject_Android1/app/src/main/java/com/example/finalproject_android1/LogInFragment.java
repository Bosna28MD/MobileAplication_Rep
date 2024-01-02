package com.example.finalproject_android1;

import static android.content.Context.MODE_PRIVATE;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class LogInFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }



    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        ((TextView)view.findViewById(R.id.err_login)).setVisibility(View.GONE);

        EditText edEmail=view.findViewById(R.id.edtxt_email);
        EditText edPassword=view.findViewById(R.id.edtxt_pwd);

        TextView errLogIn=view.findViewById(R.id.err_login);
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("UserLogIn",MODE_PRIVATE);

        ((Button)view.findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "Click button", Toast.LENGTH_SHORT).show();
                errLogIn.setVisibility(View.GONE);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                String url ="http://192.168.0.53/FinalProject_Android/login.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                    String status=jsonObject.getString("status");
                                    String message=jsonObject.getString("message");
                                    if(status.equals("success")){
                                        String nameAPI=jsonObject.getString("name");
                                        String emailAPI=jsonObject.getString("email");
                                        String birthdayAPI=jsonObject.getString("date");
                                        String idAPI=jsonObject.getString("id");
                                        SharedPreferences.Editor editor=sharedPreferences.edit();
                                        editor.putString("logged","true");
                                        editor.putString("name",nameAPI);
                                        editor.putString("email",emailAPI);
                                        editor.putString("birthday",birthdayAPI);
                                        editor.putString("id",idAPI);
                                        editor.apply();

                                        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                                        Intent i=new Intent(getContext(),MainActivity2.class);
                                        startActivity(i);
                                        getActivity().finish();
                                    }
                                    else{
                                        errLogIn.setText(message);
                                        errLogIn.setVisibility(View.VISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errLogIn.setVisibility(View.VISIBLE);
                        errLogIn.setText("Some Error appeared");
                        error.printStackTrace();
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email",edEmail.getText().toString() );
                        paramV.put("pwd", edPassword.getText().toString());
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });


    }



}