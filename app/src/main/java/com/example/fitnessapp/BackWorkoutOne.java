package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BackWorkoutOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_workout_one);
    }
    //method for the back button
    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, BackWorkoutPage.class);
        startActivity(intent);
    }

}