package com.serenapascual.alphafitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class RecordWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_workout);
    }

    public void viewProfile(View view) {
        Intent intent =  new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
