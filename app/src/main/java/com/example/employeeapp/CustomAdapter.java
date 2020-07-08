package com.example.employeeapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.PriorityQueue;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//FIXME Working Issue :-> Update Employee Id Again
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<String> emp_id, emp_name, emp_age, emp_gender, emp_image;

    //CustomAdapter Constructor
    public CustomAdapter(Context context, ArrayList<String> emp_id, ArrayList<String> emp_name,
                         ArrayList<String> emp_age, ArrayList<String> emp_gender, ArrayList<String> emp_image) {

        this.context = context;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_age = emp_age;
        this.emp_gender = emp_gender;
        this.emp_image = emp_image;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.emp_id_text.setText(emp_id.get(position));
        holder.emp_name_txt.setText(emp_name.get(position));
        holder.emp_age_txt.setText(emp_age.get(position));
        holder.emp_gender_txt.setText(emp_gender.get(position));

        //FIXME Done Image ArrayList<String>
        if (emp_image.get(position) != null) {
            Bitmap myBitmap = BitmapFactory.decodeFile(emp_image.get(position));
            holder.emp_img_id.setImageBitmap(myBitmap);
        } else {
            //TODO
        }


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);

                intent.putExtra("id", emp_id.get(position));
                intent.putExtra("name", emp_name.get(position));
                intent.putExtra("age", emp_age.get(position));
                intent.putExtra("gender", emp_gender.get(position));
                intent.putExtra("image", emp_image.get(position));

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return emp_age.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView emp_id_text, emp_name_txt, emp_age_txt, emp_gender_txt;
        public ImageView emp_img_id;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_id_text = itemView.findViewById(R.id.emp_txt_id);
            emp_name_txt = itemView.findViewById(R.id.emp_name_txt);
            emp_age_txt = itemView.findViewById(R.id.emp_age_txt);
            emp_gender_txt = itemView.findViewById(R.id.emp_gender_txt);
            emp_img_id = itemView.findViewById(R.id.emp_img_id);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //FIXME: emp_id_text

        }

    }

}
