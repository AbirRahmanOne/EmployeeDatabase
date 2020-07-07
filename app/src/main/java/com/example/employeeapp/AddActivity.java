package com.example.employeeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {
    private static final int RESULT_LOAD_IMG = 1;
    private static final int GALLERY_REQUEST = 2;
    EditText name_input, age_input, gender_input;
    Button add_button;
    private ImageButton imageButton;
    //Database database = new Database(this);
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
        imageButton = findViewById(R.id.iv_upload_image);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper empDB = new MyDatabaseHelper(AddActivity.this);
                empDB.addEmployee(name_input.getText().toString().trim(),
                        Integer.valueOf(age_input.getText().toString().trim()),
                        gender_input.getText().toString().trim());


            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 0:
                if (resultCode == RESULT_OK) {
                    Uri targetUri = data.getData();
                    //             textTargetUri.setText(targetUri.toString());
                    Bitmap bitmap;
                     try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                        imageButton.setImageBitmap(bitmap);

                        i1 = bitmap.toString();
                        Log.i("firstimage........", "" + i1);
                        // imageButton.setVisibility(0);

                        // SQLiteDatabase db = database.getWritableDatabase();
                        ///  db.execSQL("INSERT INTO UPLOAD VALUES('" + i1 + "');");

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                break;


        }

    }
}