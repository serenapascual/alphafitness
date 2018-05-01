package com.serenapascual.alphafitness;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check configuration
        Configuration config = getResources().getConfiguration();
        FragmentManager fragmentManager = getFragmentManager();
        // Start series of edit operations on the Fragments associated with
        // this FragmentManager
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            WorkoutDetailsFragment workoutDetailsFragment = new WorkoutDetailsFragment();
            fragmentTransaction.replace(android.R.id.content, workoutDetailsFragment);
        }
        else {
            RecordWorkoutFragment recordWorkoutFragment = new RecordWorkoutFragment();
            fragmentTransaction.replace(android.R.id.content, recordWorkoutFragment);
        }
        fragmentTransaction.commit();
       }

    public void viewProfile(View view) {
        Intent intent =  new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}
