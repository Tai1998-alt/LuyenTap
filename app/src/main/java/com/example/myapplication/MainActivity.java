package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnFinish;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    String url ="https://60b4f2bbfe923b0017c833fa.mockapi.io/api/persons";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView =findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        btnFinish = findViewById(R.id.btnEdit);

        myAdapter = new MyAdapter(MainActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));
        recyclerView.setAdapter(myAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("person", new Person());
                Intent intent = new Intent(MainActivity.this, FormPerson.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


    }


}