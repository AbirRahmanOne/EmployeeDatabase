package com.example.employeeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.opensooq.supernova.gligar.GligarPicker;

import java.util.Arrays;

public class UpdateActivity extends AppCompatActivity {

    private static final int PICKER_REQUEST_CODE = 30;
    private TextView emp_id ;
    private EditText emp_name,emp_age, emp_gender;
    private ImageView emp_image;
    private Button update_button, delete_button;
    private String id, name, age, gender, imgPath;

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

        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                name = emp_name.getText().toString().trim();
                age = emp_age.getText().toString().trim();
                gender = emp_gender.getText().toString().trim();
               // image = emp_image.getText().toString().trim();
                myDB.updateData(id, name, age, gender, imgPath);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        final GligarPicker picker = new GligarPicker().requestCode(PICKER_REQUEST_CODE).withActivity(this);

        emp_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                picker.show();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case PICKER_REQUEST_CODE: {
                String pathsList[] = data.getExtras().getStringArray(GligarPicker.IMAGES_RESULT); // return list of selected images paths.
                Log.d("img", Arrays.toString(pathsList));
                //FIXME: put pathList[0] to database
                imgPath = pathsList[0];
                emp_image.setImageBitmap(BitmapFactory.decodeFile(imgPath));
                break;
            }
        }
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") &&
                getIntent().hasExtra("age") && getIntent().hasExtra("gender")
                    && getIntent().hasExtra("image")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            age = getIntent().getStringExtra("age");
            gender = getIntent().getStringExtra("gender");
            imgPath = getIntent().getStringExtra("image");

            //Setting Intent Data
            emp_name.setText(name);
            emp_age.setText(age);
            emp_gender.setText(gender);
            if (imgPath != null) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgPath);
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