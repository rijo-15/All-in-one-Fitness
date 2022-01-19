package com.example.fitnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#00636b"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //animated backgorund referenced this link: https://codinginflow.com/tutorials/android/animated-gradient-background
        ConstraintLayout constraintLayout = findViewById(R.id.workoutlayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();
    }

    //back button to go to home page.

    public void goBackToHomePage(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void gotoArmsActivity(View v){
        Intent intent = new Intent(this, ArmWorkout.class);
        startActivity(intent);
    }

    public void gotoBackWorkoutActivity(View v){
        Intent intent = new Intent(this, BackWorkoutPage.class);
        startActivity(intent);
    }

    public void gotoCardioActivity(View v){
        Intent intent = new Intent(this, CardioWorkout.class);
        startActivity(intent);
    }

    public void gotoChestActivity(View v){
        Intent intent = new Intent(this, ChestWorkout.class);
        startActivity(intent);
    }
    public void gotoOverViewPage(View v){
        Intent overviewIntent = new Intent(this, OverviewActivity.class);
        startActivity(overviewIntent);
    }
}
