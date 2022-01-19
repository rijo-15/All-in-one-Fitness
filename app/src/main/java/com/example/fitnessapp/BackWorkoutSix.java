package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BackWorkoutSix extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_workout_six);


    }

    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, BackWorkoutPage.class);
        startActivity(intent);
    }
}
