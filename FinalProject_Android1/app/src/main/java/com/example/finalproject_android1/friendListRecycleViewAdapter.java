package com.example.finalproject_android1;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class friendListRecycleViewAdapter extends   RecyclerView.Adapter<friendListRecycleViewAdapter.ViewHolder> {

    Context context;
    ArrayList<UserData> friendListArr;
    FriendListFragment fragment_friendList;

    public friendListRecycleViewAdapter(Context context,FriendListFragment fragment_friendList) {
        this.context = context;
        this.fragment_friendList=fragment_friendList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friendlist_recycleview,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtEmail.setText(String.valueOf(friendListArr.get(position).getEmail()));
        holder.txtName.setText(String.valueOf(friendListArr.get(position).getUsername()));

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, holder.txtEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                RequestQueue queue = Volley.newRequestQueue(context);
                String url ="http://192.168.0.53/FinalProject_Android/btnNotificationDecline.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("success")){
                                    Toast.makeText(context, "Request Decline", Toast.LENGTH_SHORT).show();
                                    friendListArr.remove(position);
                                    fragment_friendList.getRecyclerViewFriendList().setAdapter(friendListRecycleViewAdapter.this);
                                    //fragment_friendAdd.getRecycleViewFriendAdd().setLayoutManager(new LinearLayoutManager(context));
                                }
                                else{
                                    Toast.makeText(context, response, Toast.LENGTH_SHORT).show();
                                    //Log.d("error_btnAcceptnotification123",response);

                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Some error appeared", Toast.LENGTH_LONG).show();
                        //Log.d("error_btnAdd123",error.getMessage());
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
        return friendListArr.size();
    }

    public void setFriendListArr(ArrayList<UserData> friendListArr) {
        this.friendListArr = friendListArr;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout parent;
        TextView txtEmail,txtName;
        ImageButton btnRemove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            txtEmail=itemView.findViewById(R.id.txtEmail);
            txtName=itemView.findViewById(R.id.txtUserName);
            btnRemove=itemView.findViewById(R.id.btnRemoveFriend);
        }
    }

}
