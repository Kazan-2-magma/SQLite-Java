package com.example.sqliteproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;



public class RecyclerViewCustom extends RecyclerView.Adapter<RecyclerViewCustom.ViewHolder> {
    ArrayList<HashMap<String, Object>> list = new ArrayList<>();
    DatabaseManager db;
    Activity context;



    public RecyclerViewCustom(ArrayList<HashMap<String, Object>> list, Activity context) {
        this.list = list;
        this.context = context;
        db = new DatabaseManager(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.user_data_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).get("name").toString());
        holder.age.setText(list.get(position).get("age").toString());
        holder.layout.setOnClickListener(v -> {
        String nam = list.get(position).get("name").toString();
        String age = String.valueOf(list.get(position).get("age"));
        Cursor cursor = db.getId(nam, age);
            if (cursor.moveToFirst()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
                Toast.makeText(context, String.valueOf(id), Toast.LENGTH_SHORT).show();
                Log.e("MY TAG", "id: " + id);
                Bundle b = new Bundle();
                Intent intent = new Intent(context, UpdateActivity.class);
                b.putString("id",String.valueOf(id));
                intent.putExtras(b);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "no data found !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, age;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            age = itemView.findViewById(R.id.textView2);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
