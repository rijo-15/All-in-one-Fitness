package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ArmWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arm_workout);
    }

    //back button to go to workout home page.
    public void goBackToWorkoutHomePage(View v){
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }


    public void gotoArmsFirstWorkout(View v){
        Intent intent = new Intent(this, DumbbellBiceps.class);
        startActivity(intent);
    }
    public void gotoArmsSecondWorkout(View v){
        Intent intent = new Intent(this, EZBarCUNG.class);
        startActivity(intent);
    }

    public void gotoArmsThirdWorkout(View v){
        Intent intent = new Intent(this, EZBarCurlUG.class);
        startActivity(intent);
    }

    public void gotoArmsFourthWorkout(View v){
        Intent intent = new Intent(this, EzBarCurlWG.class);
        startActivity(intent);
    }

    public void gotoArmsFifthWorkout(View v){
        Intent intent = new Intent(this, EZBarBicepsBottom.class);
        startActivity(intent);
    }

    public void gotoArmsSixthWorkout(View v){
        Intent intent = new Intent(this, EZBarBicepTopHalf.class);
        startActivity(intent);
    }

}