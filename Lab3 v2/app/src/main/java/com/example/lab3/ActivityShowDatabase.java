package com.example.lab3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ActivityShowDatabase extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_database);

        RecyclerView listStudents = findViewById(R.id.rv_listStudents);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        listStudents.setLayoutManager(layoutManager);
        listStudents.setHasFixedSize(true);

        AdapterListStudents adapter = new AdapterListStudents(this);
        listStudents.setAdapter(adapter);
    }
}