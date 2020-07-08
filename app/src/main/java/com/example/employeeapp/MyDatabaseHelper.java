package com.example.employeeapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//FIXME Update Image Issue

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "Employee.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "Employee_List";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "emp_name";
    private static final String COLUMN_AGE = "emp_age";
    private static final String COLUMN_GENDER = "emp_gender";
    private static final String COLUMN_IMAGE = "emp_image";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    // Img Col Update here check
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_AGE + " INTEGER, " +
                        COLUMN_GENDER + " TEXT, " +
                        COLUMN_IMAGE + " TEXT);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addEmployee(String name, int age, String gender, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_IMAGE, image);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            MainActivity.reference.storeDataInArrays();
            Toast.makeText(context, "Added Successfully!!", Toast.LENGTH_SHORT).show();
        }
    }

    // Reading Data From DB Below Function
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String id, String name, String age, String gender, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_AGE, age);
        cv.put(COLUMN_GENDER, gender);
        cv.put(COLUMN_IMAGE, image);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            MainActivity.reference.storeDataInArrays();
        }

    }

    void deleteOneRow(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
            MainActivity.reference.storeDataInArrays();
        }
    }
}
