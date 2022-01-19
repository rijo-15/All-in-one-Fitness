package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EZBarBicepsBottom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_z_bar_biceps_bottom);
    }

    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, ArmWorkout.class);
        startActivity(intent);
    }
}