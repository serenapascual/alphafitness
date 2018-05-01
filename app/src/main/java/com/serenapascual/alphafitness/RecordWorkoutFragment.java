package com.serenapascual.alphafitness;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class RecordWorkoutFragment extends Fragment {

    public RecordWorkoutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record_workout, container, false);
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

    public void startWorkout(View view) {

    }

}
