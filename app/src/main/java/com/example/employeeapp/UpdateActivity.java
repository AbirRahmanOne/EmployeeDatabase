package com.example.employeeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private TextView emp_id ;
    private EditText emp_name,emp_age, emp_gender;
    private ImageView emp_image;
    private Button update_button, delete_button;
    private String id, name, age, gender,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        emp_id = findViewById(R.id.emp_txt_id) ;
        emp_name = findViewById(R.id.name_update);
        emp_age = findViewById(R.id.age_update);
        emp_gender = findViewById(R.id.gender_update);
        emp_image = findViewById(R.id.e_img_update);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();
        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = emp_name.getText().toString().trim();
                age = emp_age.getText().toString().trim();
                gender = emp_gender.getText().toString().trim();
               // image = emp_image.getText().toString().trim();
                myDB.updateData(id, name, age, gender,image);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("age") && getIntent().hasExtra("gender")
                    && getIntent().hasExtra("image")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            age = getIntent().getStringExtra("age");
            gender = getIntent().getStringExtra("gender");
            image = getIntent().getStringExtra("image");

            //Setting Intent Data
            emp_name.setText(name);
            emp_age.setText(age);
            emp_gender.setText(gender);
            if (image != null) {
                Bitmap myBitmap = BitmapFactory.decodeFile(image);
                emp_image.setImageBitmap(myBitmap);
            } else {
                //TODO
            }

            Log.d("stev", name +" "+ age +" "+ gender);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}