package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BackWorkoutPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_workout_page);
    }
    //method for the back button
    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }
    public void goToTheFirstBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutOne.class);
        startActivity(intent);
    }
    public void goToTheSecondBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutTwo.class);
        startActivity(intent);
    }
    public void goToTheThirdBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutThree.class);
        startActivity(intent);
    }

    public void goToTheFourthBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutFour.class);
        startActivity(intent);
    }

    public void goToTheFifthBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutFive.class);
        startActivity(intent);
    }
    public void goToTheSixthBackWorkout(View v){
        Intent intent = new Intent(this, BackWorkoutSix.class);
        startActivity(intent);
    }

}