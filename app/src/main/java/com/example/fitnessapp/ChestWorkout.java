package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChestWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chest_workout);
    }

    //back button to go to workout home page.
    public void goBackToWorkoutHomePage(View v){
        Intent intent = new Intent(this, WorkoutActivity.class);
        startActivity(intent);
    }
    public void goToTheFirstChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutOne.class);
        startActivity(intent);
    }
    public void goToTheSecondChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutTwo.class);
        startActivity(intent);
    }
    public void goToTheThirdChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutThree.class);
        startActivity(intent);
    }
    public void goToTheFourthChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutFour.class);
        startActivity(intent);
    }
    public void goToTheFifthChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutFive.class);
        startActivity(intent);
    }
    public void goToTheSixthChestWorkout(View v){
        Intent intent = new Intent(this, ChestWorkoutSix.class);
        startActivity(intent);
    }
}