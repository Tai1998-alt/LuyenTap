package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class FormPerson extends AppCompatActivity {

    Person person;
    EditText editName, editAge, editClass;
    Button btnUpdate;
    String url ="https://60b4f2bbfe923b0017c833fa.mockapi.io/api/persons";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_person);
        getSupportActionBar().hide();
        editAge = findViewById(R.id.editAge);
        editName = findViewById(R.id.editName);
        editClass = findViewById(R.id.editClass);
        btnUpdate = findViewById(R.id.btnUpdate);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            person = (Person) bundle.getSerializable("person");
            editName.setText(person.getName().toString());
            editAge.setText(String.valueOf(person.getAge()));
            editClass.setText(person.getLop().toString());
        }
        if( person.getId()==0)
        {
            btnUpdate.setText("ADD");
        } else btnUpdate.setText("UPDATE");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(person.getId()==0)
                {
                    addPerson();
                } else {updatePerson();}
            }
        });
    }

    private void updatePerson()
    {
        StringRequest stringRequest =new StringRequest(Request.Method.PUT, url+"/"+person.getId(), response ->
        {
                startActivity(new Intent(FormPerson.this, MainActivity.class));

        }, error ->
        {
                error.printStackTrace();
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("name",editName.getText().toString().trim());
                hashMap.put("age", editAge.getText().toString().trim());
                hashMap.put("lop",editClass.getText().toString().trim());
                return hashMap;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,1,1));
        RequestQueue requestQueue = Volley.newRequestQueue(FormPerson.this);
        requestQueue.add(stringRequest);
    }
    private void addPerson()
    {
        //abc
        btnUpdate.setText("ADD");
    }
}