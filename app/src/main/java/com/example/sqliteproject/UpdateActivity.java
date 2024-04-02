package com.example.sqliteproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Currency;

public class UpdateActivity extends AppCompatActivity{
    TextInputEditText updateName,updateAge;
    Button update;
    private static final String TAG = "HEREEE";
    String idd ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        Bundle b = getIntent().getExtras();
        assert b != null;
        idd =b.getString("id");
            Toast.makeText(this,"id is " + idd,Toast.LENGTH_SHORT).show();
        DatabaseManager db = new DatabaseManager(getApplicationContext());
       Cursor c = db.getDataWithId(idd);
        updateName = findViewById(R.id.update_name);
        updateAge = findViewById(R.id.update_age);
        update = findViewById(R.id.update);
        if(c!=null && c.moveToFirst()){
            @SuppressLint("Range") String currentName = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String currentAge = c.getString(c.getColumnIndex("age"));
            if(currentAge != null){
                Toast.makeText(getApplicationContext(), currentAge + " " + idd, Toast.LENGTH_SHORT).show();
                updateAge.setText(currentAge);
            }
            //Toast.makeText(getApplicationContext(),currentAge+" "+id,Toast.LENGTH_SHORT).show();
            updateName.setText(currentName);

        }
    }
}