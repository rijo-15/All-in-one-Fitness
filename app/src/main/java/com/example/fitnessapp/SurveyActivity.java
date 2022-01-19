package com.example.fitnessapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SurveyActivity extends AppCompatActivity {

    public static class SurveyViewHolder extends RecyclerView.ViewHolder {
        EditText name;

        public SurveyViewHolder(View v) {
            super(v);
            name = (EditText) itemView.findViewById(R.id.profileName);

        }
    }

    private Button submitButton;
    private EditText mprofileName;
    private RadioGroup mgender;
    private RadioButton mMaleGender;
    private RadioButton mFemaleGender;
    private Spinner mheight;
    private Spinner mweight;
    private EditText mgoals;
    Profile profile;
    DatabaseReference mFirebaseDatabase;
    private FirebaseRecyclerAdapter<Profile, SurveyViewHolder>
            mFirebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        // Define ActionBar object
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#2368AA"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //animated backgorund referenced this link: https://codinginflow.com/tutorials/android/animated-gradient-background
        ScrollView scrollView = findViewById(R.id.surveylayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) scrollView.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        //Height drop down menu
        Spinner dropdown = findViewById(R.id.heightSpinner);
        //create a list of items for the spinner.
        String[] height = new String[]{"", "4ft 0in", "4ft 1in", "4ft 2in", "4ft 3in", "4ft 4in", "4ft 5in", "4ft 6in", "4ft 7in", "4ft 8in", "4ft 9in", "4ft 10in", "4ft 11in",
                "5ft 0in", "5ft 1in", "5ft 2in", "5ft 3in", "5ft 4in", "5ft 5in", "5ft 6in", "5ft 7in", "5ft 8in", "5ft 9in", "5ft 10in", "5ft 11in",
                "6ft 0in", "6ft 1in", "6ft 2in", "6ft 3in", "6ft 4in", "6ft 5in", "6ft 6in", "6ft 7in", "6ft 8in", "6ft 9in", "6ft 10in", "6ft 11in"};
        //Create an array adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, height);
        //set the spinners adapter to the adapter we just created
        dropdown.setAdapter(adapter);

        //Weight drop down menu
        Spinner dropdownWeight = findViewById(R.id.weightSpinner);
        //create a list of weights in pounds 0-500 (heaviest person weighted 440
        String[] weight = new String[1001];
        weight[0] = "";
        for(int i = 1; i <= 1000; i++){
            weight[i] = String.valueOf(i + " lbs");
            final ArrayAdapter<String> adapterWeight = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, weight);
            //set the spinners adapter to the adapter we just created
            dropdownWeight.setAdapter(adapterWeight);
        }

        mprofileName=(EditText)findViewById(R.id.profileName);
        mheight=(Spinner)findViewById(R.id.heightSpinner);
        mweight=(Spinner)findViewById(R.id.weightSpinner);

        mgender=(RadioGroup)findViewById(R.id.radioGroup);
        mMaleGender=(RadioButton)findViewById(R.id.radioButtonMale);
        mFemaleGender=(RadioButton)findViewById(R.id.radioButtonFemale);

        mgoals=(EditText)findViewById(R.id.goals_edit);
        submitButton=(Button)findViewById(R.id.submit_button);
        profile = new Profile();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Profile");

        //Saving values to database
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile.setName(mprofileName.getText().toString().trim());
                if(mMaleGender.isChecked()){
                    profile.setGender(mMaleGender.getText().toString());
                }else{
                    profile.setGender(mFemaleGender.getText().toString());
                }
                profile.setHeight(mheight.getSelectedItem().toString());
                profile.setWeight(mweight.getSelectedItem().toString());
                profile.setGoals(mgoals.getText().toString());
                //validation checks to make sure user has answered all fields
                int validationFail = 0;
                if((mprofileName.getText().toString().trim()).matches("")){
                    Toast.makeText(SurveyActivity.this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                    validationFail++;
                }
                if(mgender.getCheckedRadioButtonId() == -1){
                    Toast.makeText(SurveyActivity.this, "You did not select a gender", Toast.LENGTH_SHORT).show();
                    validationFail++;
                }
                if((mheight.getSelectedItem().toString()).matches("")){
                    Toast.makeText(SurveyActivity.this, "You did not select your height", Toast.LENGTH_SHORT).show();
                    validationFail++;
                }
                if((mheight.getSelectedItem().toString()).matches("")){
                    Toast.makeText(SurveyActivity.this, "You did not select your weight", Toast.LENGTH_SHORT).show();
                    validationFail++;
                }
                if((mgoals.getText().toString()).matches("")){
                    Toast.makeText(SurveyActivity.this, "You did not enter a goal", Toast.LENGTH_SHORT).show();
                    validationFail++;
                }
               if(validationFail == 0) {
                   DatabaseReference newRef = mFirebaseDatabase.child("profile").push();
                   newRef.setValue(profile);
                   //mFirebaseDatabase.child("profile!").setValue(profile);
                   Toast.makeText(SurveyActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();
                   Intent backToMain = new Intent(SurveyActivity.this, MainActivity.class);
                   startActivity(backToMain);
               }else{
                   Toast.makeText(SurveyActivity.this, "Make sure to fill out all fields", Toast.LENGTH_LONG).show();
               }
            }
        });


    }

}
