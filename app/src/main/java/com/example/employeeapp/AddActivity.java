package com.example.employeeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.opensooq.supernova.gligar.GligarPicker;

import java.util.Arrays;

//FIXME Update Image Issue

public class AddActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    private static final int GALLERY_REQUEST = 2;
    EditText name_input, age_input, gender_input;
    Button add_button;
    private ImageView EmployeeImage;
    static final int PICKER_REQUEST_CODE = 30;
    String imgPath;
    String i1;
    String img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        age_input = findViewById(R.id.age_input);
        gender_input = findViewById(R.id.gender_input);
        add_button = findViewById(R.id.add_button);
        EmployeeImage = findViewById(R.id.iv_upload_image);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper empDB = new MyDatabaseHelper(AddActivity.this);
                empDB.addEmployee(name_input.getText().toString().trim(),
                        Integer.valueOf(age_input.getText().toString().trim()),
                        gender_input.getText().toString().trim(),
                        imgPath);
            }
        });


        final GligarPicker picker = new GligarPicker().requestCode(PICKER_REQUEST_CODE).withActivity(this);

        EmployeeImage.setOnClickListener(new View.OnClickListener() {
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
                //Log.d("img", Arrays.toString(pathsList));
                imgPath = pathsList[0];
                Bitmap myBitmap = BitmapFactory.decodeFile(imgPath);
                EmployeeImage.setImageBitmap(myBitmap);
                break;
            }
        }
    }
}