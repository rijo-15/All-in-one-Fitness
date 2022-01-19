package com.example.fitnessapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EzBarCurlWG extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ez_bar_curl_w_g);
    }


    public void gotoWorkoutPage(View v){
        Intent intent = new Intent(this, ArmWorkout.class);
        startActivity(intent);
    }
}