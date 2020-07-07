package com.example.employeeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    FloatingActionButton add_button ;

    MyDatabaseHelper empDB ;
    ArrayList<String> e_id, e_name, e_age, e_gender ;
    CustomAdapter customAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//permission
        recyclerView = findViewById(R.id.recyclerview) ;
        add_button = findViewById(R.id.add_button) ;
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        empDB = new MyDatabaseHelper(MainActivity.this) ;
        e_id = new ArrayList<>();
        e_name = new ArrayList<>() ;
        e_age = new ArrayList<>() ;
        e_gender = new ArrayList<>() ;
        storeDataInArrays() ;

        customAdapter  = new CustomAdapter(MainActivity.this, e_id, e_name,e_age,e_gender);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



    }
    //Display Data
    void storeDataInArrays(){
        Cursor cursor = empDB.readAllData();
        if(cursor.getCount()==0){
            Toast.makeText(this, "ERROR! NO Data.", Toast.LENGTH_SHORT).show();
            Log.e("DataLoading ","Suceess");
        }else{
            while(cursor.moveToNext()){
                e_id.add(cursor.getString(0));
                e_name.add(cursor.getString(1));
                e_age.add(cursor.getString(2));
                e_gender.add(cursor.getString(3)) ;
            }
        }
    }


}