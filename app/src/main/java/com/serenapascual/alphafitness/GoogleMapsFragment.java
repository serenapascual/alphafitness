package com.serenapascual.alphafitness;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GoogleMapsFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MapView mapView;
    LocationManager locationManager;
    Context sContext;

    public GoogleMapsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sContext = getActivity().getApplicationContext();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_google_maps, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.googleMapsFragment);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Get the best location provider
        Criteria criteria = new Criteria();
        criteria.setAccuracy((Criteria.ACCURACY_FINE));
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String locationProvider = locationManager.getBestProvider(criteria, true);

        // Make sure that the permission is granted
        if (ActivityCompat.checkSelfPermission(sContext,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(sContext,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Request missing permissions and case where user grants permission
            return;
        }

        // Get the last known location
        Location location = locationManager.getLastKnownLocation(locationProvider);

        // Get the geocode of the given location
        String label = "Address: ";
        List<Address> addresses;

        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                    }
                    @Override
                    public void onProviderEnabled(String provider) {
                    }
                    @Override
                    public void onProviderDisabled(String provider) {
                    }
                    @Override
                    public void onLocationChanged(final Location location) {
                    }
                });

        try {
            Geocoder geocoder = new Geocoder(sContext, Locale.getDefault());
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses != null) {
                Address address = addresses.get(0);
                StringBuilder stringBuilder = new StringBuilder("");
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                   stringBuilder.append(address.getAddressLine(i)).append("/");
                   label = label + stringBuilder.toString();
                }
            }
        }
        catch (IOException e) {

        }

        // Add address and center map camera to given location
        LatLng here = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addMarker(new MarkerOptions().position(here).title(label));
        mMap.moveCamera((CameraUpdateFactory.newLatLng(here)));

        // Zoom to marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(here).zoom(17.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
