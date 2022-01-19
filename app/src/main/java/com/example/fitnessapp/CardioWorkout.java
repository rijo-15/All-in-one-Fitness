package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CardioWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_workout);
    }
    //back button to go to workout home page.
    public void goBackToWorkoutHomePage(View v){
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }
    //method for the first cardio workout
    public void goToTheFirstCardioWorkout(View v){
        Intent intent = new Intent(this, CardioWorkoutOne.class);
        startActivity(intent);
    }
    public void goToTheSecondCardioWorkout(View v){
        Intent intent = new Intent(this, CardioWorkoutTwo.class);
        startActivity(intent);
    }
    public void goToTheThirdCardioWorkout(View v){
        Intent intent = new Intent(this, CardioWorkoutThree.class);
        startActivity(intent);
    }

    public void goToTheFourthCardioWorkout(View v){
        Intent intent = new Intent(this, CardioWorkoutFour.class);
        startActivity(intent);
    }
}