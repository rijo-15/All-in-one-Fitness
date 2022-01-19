package com.example.fitnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CardioWorkoutFour extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_workout_four);

        mTextView = (TextView) findViewById(R.id.text);
    }
    //method for the back button
    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, CardioWorkout.class);
        startActivity(intent);
    }
}