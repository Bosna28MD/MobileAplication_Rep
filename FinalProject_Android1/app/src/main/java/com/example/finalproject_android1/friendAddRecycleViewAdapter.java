package com.example.finalproject_android1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class friendAddRecycleViewAdapter extends   RecyclerView.Adapter<friendAddRecycleViewAdapter.ViewHolder> {

    ArrayList<UserData> friendAddArr;
    Context context;
    FriendAddFragment fragment_friendAdd;

    public friendAddRecycleViewAdapter(Context context, FriendAddFragment fragment_friendAdd) {
        this.context = context;
        this.fragment_friendAdd=fragment_friendAdd;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friendsadd_recycleview,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtEmail.setText(String.valueOf(friendAddArr.get(position).getEmail()));
        holder.txtName.setText(String.valueOf(friendAddArr.get(position).getUsername()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, holder.txtEmail.getText(), Toast.LENGTH_SHORT).show();
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="http://192.168.0.53/FinalProject_Android/btnfriendsAddList.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    friendAddArr.remove(position);
                                    fragment_friendAdd.getRecycleViewFriendAdd().setAdapter(friendAddRecycleViewAdapter.this);
                                    //fragment_friendAdd.getRecycleViewFriendAdd().setLayoutManager(new LinearLayoutManager(context));
                                }
                                else{
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                    Log.d("error_btnAdd123",response);

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Some error appeared", Toast.LENGTH_LONG).show();
                        Log.d("error_btnAdd123",error.getMessage());
                        //errLogIn.setVisibility(View.VISIBLE);
                        //errLogIn.setText("Some Error appeared");
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
                        paramV.put("email2",holder.txtEmail.getText().toString() );
                        paramV.put("id1", context.getSharedPreferences("UserLogIn",MODE_PRIVATE).getString("id","") );
                        return paramV;
                    }
                };
                queue.add(stringRequest);

            }
        });
    }

    @Override
    public int getItemCount() {
        return friendAddArr.size();
    }

    public void setFriendAddArr(ArrayList<UserData> friendAddArr) {
        this.friendAddArr = friendAddArr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parent;
        TextView txtEmail,txtName;
        Button btnAdd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtName=itemView.findViewById(R.id.txtUserName);
            btnAdd=itemView.findViewById(R.id.btnAdd);
        }
    }


}
