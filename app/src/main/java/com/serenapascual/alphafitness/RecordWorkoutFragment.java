package com.serenapascual.alphafitness;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RecordWorkoutFragment extends Fragment {
    Button workoutButton;

    public RecordWorkoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record_workout, container, false);
        workoutButton = (Button) view.findViewById(R.id.workoutButton);
        workoutButton.setTag(1);
        workoutButton.setText(R.string.start_workout_uppercase);
        workoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int status = (Integer) view.getTag();
                if (status == 1) {
                    workoutButton.setText(R.string.stop_workout_lowercase);
                    view.setTag(0); // Set to STOP
                }
                else {
                    workoutButton.setText(R.string.start_workout_uppercase);
                    view.setTag(1); // Set to START
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment googleMapsFragment = new GoogleMapsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.googleMapsFragmentContainer, googleMapsFragment).commit();
    }

    public void viewProfile(View view) {
        Intent intent =  new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }

    public void startWorkout(View view) {;
    }

}
