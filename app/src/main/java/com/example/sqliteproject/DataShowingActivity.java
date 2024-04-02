package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataShowingActivity extends AppCompatActivity{
    Toolbar toolbarW;
    RecyclerView recyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_showing);
        toolbarW = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarW);
        recyclerView = findViewById(R.id.recycler);
        DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
        Cursor cursor = databaseManager.getAllData();
        ArrayList<HashMap<String, Object>> dataList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressLint("Range") int age = cursor.getInt(cursor.getColumnIndex("age"));
                HashMap<String, Object> dataMap = new HashMap<>();
                dataMap.put("name", name);
                dataMap.put("age", age);
                dataList.add(dataMap);
            } while (cursor.moveToNext());
        }
        RecyclerViewCustom recyclerViewCustom = new RecyclerViewCustom(dataList,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(recyclerViewCustom);
    }


}
