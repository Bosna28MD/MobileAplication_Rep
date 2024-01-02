package com.example.finalproject_android1;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    RecyclerView recyclerViewNotification;

    public RecyclerView getRecyclerViewNotification(){
        return this.recyclerViewNotification;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewNotification=view.findViewById(R.id.parent_recycle_view_notification);
        ArrayList<UserData> userAddArr=new ArrayList<>();
        SharedPreferences sharedPreferences=getContext().getSharedPreferences("UserLogIn",MODE_PRIVATE);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://192.168.0.53/FinalProject_Android/notificationList.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            String status=jsonObject.getString("result");
                            if(status.equals("success")){
                                JSONArray jsonArray = new JSONArray(jsonObject.getString("array") );
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject jsonObject_arr = jsonArray.getJSONObject(i);
                                    userAddArr.add(new UserData(jsonObject_arr.getString("email"),jsonObject_arr.getString("name")));
                                }
                                notificationRecycleViewAdapter adapter=new notificationRecycleViewAdapter(getContext(),NotificationFragment.this);
                                adapter.setNotificationArr(userAddArr);
                                recyclerViewNotification.setAdapter(adapter);
                                recyclerViewNotification.setLayoutManager(new LinearLayoutManager(getContext()));

                            }
                            else{
                                Toast.makeText(getContext(), status, Toast.LENGTH_LONG).show();
                                //errLogIn.setText(message);
                                //errLogIn.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Some error appeared", Toast.LENGTH_LONG).show();
                Log.d("error_notification123",error.getMessage());
                //errLogIn.setVisibility(View.VISIBLE);
                //errLogIn.setText("Some Error appeared");
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("email",sharedPreferences.getString("email","") );
                paramV.put("id",sharedPreferences.getString("id","") );
                return paramV;
            }
        };
        queue.add(stringRequest);


    }
}