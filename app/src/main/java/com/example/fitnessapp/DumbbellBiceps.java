package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DumbbellBiceps extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dumbbell_biceps);
    }

    public void goBackPage(View v){
        Intent intent = new Intent(this, ArmWorkout.class);
        startActivity(intent);
    }

    public void doneWithFirstWork(View v){
        Intent intent = new Intent(this, ArmWorkout.class);
        startActivity(intent);
    }
}