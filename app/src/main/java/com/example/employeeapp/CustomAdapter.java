package com.example.employeeapp;

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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context ;
    private ArrayList emp_id, emp_name, emp_age, emp_gender ;
    //CustomAdapter Constructor 
    public CustomAdapter(Context context,  ArrayList emp_id, ArrayList emp_name,
                         ArrayList emp_age, ArrayList emp_gender   ) {

        this.context =context ;
        this.emp_id = emp_id ;
        this.emp_name = emp_name ;
        this.emp_age = emp_age ;
        this.emp_gender = emp_gender ;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context) ;
        View view = inflater.inflate(R.layout.my_row, parent,false) ;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.emp_id_text.setText(String.valueOf(emp_id.get(position)));
        holder.emp_name_txt.setText(String.valueOf(emp_name.get(position)));
        holder.emp_age_txt.setText(String.valueOf(emp_age.get(position)));
        holder.emp_gender_txt.setText(String.valueOf(emp_gender.get(position)));
    }

    @Override
    public int getItemCount() {
        return emp_age.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView emp_id_text, emp_name_txt, emp_age_txt, emp_gender_txt ;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView){
            super(itemView);
            emp_id_text  = itemView.findViewById(R.id.emp_id_text);
            emp_name_txt = itemView.findViewById(R.id.emp_name_txt);
            emp_age_txt  = itemView.findViewById(R.id.emp_age_txt);
            emp_gender_txt = itemView.findViewById(R.id.emp_gender_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout) ;

        }

    }


}
