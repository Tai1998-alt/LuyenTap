package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {

    private List<Person> persons;
    private Context context;
    String url ="https://60b4f2bbfe923b0017c833fa.mockapi.io/api/persons";
    public MyAdapter(Context context) {
     this.context = context;
     update();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        Person person = persons.get(position);
        holder.txtName.setText(person.getName());
        holder.txtAge.setText(String.valueOf(person.getAge()));
        holder.txtClass.setText(person.getLop());

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(context)
                       .setTitle("Confirm")
                       .setMessage("Are you want delete")
                       .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url+"/"+person.getId(),
                                       response ->
                                       {
                                           Toast.makeText(context,"Thanh Cong",Toast.LENGTH_SHORT).show();
                                           update();

                                       },
                                       error ->
                                       {
                                           error.printStackTrace();
                                       });
                               stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
                               RequestQueue requestQueue = Volley.newRequestQueue(context);
                               requestQueue.add(stringRequest);
                           }
                       }).setNegativeButton("NO",null).show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", person);
                Intent intent =new Intent(context,FormPerson.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView txtName, txtAge, txtClass;
        Button btnEdit, btnDelete;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtClass = itemView.findViewById(R.id.txtClass);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
    private void update(){
        persons = new ArrayList<>();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, response ->
        {
            try
            {
                for(int i=0; i< response.length(); i++)
                {
                    JSONObject jsonObject = (JSONObject) response.get(i);
                    int id = jsonObject.getInt("id");
                    String name = jsonObject.getString("name");
                    int age = jsonObject.getInt("age");
                    String lop = jsonObject.getString("lop");
                    Person person = new Person(id,name,age,lop);
                    persons.add(person);
                }
                notifyDataSetChanged();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }, error ->
        {
            error.printStackTrace();
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }
}
