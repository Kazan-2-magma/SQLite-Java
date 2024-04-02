package com.example.sqliteproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name,age;
    Toolbar toolbarW;
    Button confirme,show;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarW = findViewById(R.id.toolbar);
        setSupportActionBar(toolbarW);
        name = findViewById(R.id.editTextText);
        show = findViewById(R.id.button2);
        age = findViewById(R.id.age);
        confirme = findViewById(R.id.button);
        confirme.setOnClickListener(v->{
            String nameStr = name.getText().toString();
            String ageStr = age.getText().toString();
            if(nameStr.isEmpty()){
                name.setError("Name is Required !!");
            }else if(ageStr.isEmpty()){
                age.setError("Age is Requierd !!");
            } else{
                DatabaseManager databaseManager = new DatabaseManager(getApplicationContext());
                databaseManager.addData(nameStr,Integer.parseInt(ageStr));
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        Intent intent = new Intent(MainActivity.this,DataShowingActivity.class);
                        startActivity(intent);
                    }
                });
                thread.start();
            }
        });
        show.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this,DataShowingActivity.class);
            startActivity(intent);
        });

    }

}