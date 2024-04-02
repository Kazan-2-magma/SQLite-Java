package com.example.sqliteproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DatabaseManager extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "USERS";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE "+TABLE_NAME +" ("
                +" id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +" name TEXT ,"
                +" age INTEGER );";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
    public void addData(String name,int age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Map<String,Object> map = new HashMap<>(Map.of(
                "name",name,"age",age
        ));
        for(Map.Entry<String,Object> i : map.entrySet()){
            String key = i.getKey();
            Object value = i.getValue();
            cv.put(key,value.toString());
        }
        long resutl = db.insert(TABLE_NAME,null,cv);
        if(resutl == -1){
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name","age"};
        return db.query(TABLE_NAME,columns,null,null,null,null,null);

    }public Cursor getDataWithId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name","age"};
        if (id != null) {
            return db.query(TABLE_NAME, columns, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        }
        return db.query(TABLE_NAME, columns, null, null, null, null, null);
    }
    public Cursor getId(String name,String age){
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "name = ? AND age >= ?";
        String [] projection = {"id"};
        Cursor cursor = db.query(TABLE_NAME,projection,selection,new String[]{name,age},null,null,null,null);;
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            Log.d("FROM getID()", "Retrieved id: " + id); // Log the retrieved id value
        }
        return cursor;
    }
}

