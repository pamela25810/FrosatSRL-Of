package com.checkcode.fsrl;


import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Objects;


public class MapaUbiActivity extends AppCompatActivity {

    private SupportMapFragment mMapFragment;


    private FusedLocationProviderClient fusedLocationClient;
    private static GoogleApiClient mGoogleApiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_ubi);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.contenedor_mapa, new MapaUbiActivity.FragmentoMapas());
        ft.commit();

        agregarToolbar();


    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class FragmentoMapas extends PreferenceFragment {

        public FragmentoMapas() {
            // Constructor Por Defecto
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }
    }
    /*
    public static class FragmentoMapas extends PreferenceFragment
            implements
            OnMapReadyCallback,
            GoogleMap.OnMapClickListener,
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
            LocationListener {
        private GoogleMap mapa;
        private LatLng latLng; //Nos ubicamos en un punto de La Paz
        private LocationRequest mLocationRequest;
        private Location mLocation;
        private Marker mCurrLocationMarker, markerClient;
        private BitmapDescriptor iconUser, iconClient;
        public FragmentoMapas() {
            // Constructor Por Defecto
        }

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mapa = googleMap;
            mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mapa.setMyLocationEnabled(true);
            buildGoogleApiClient();


            //mapa.setOnMapClickListener(this);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    buildGoogleApiClient();
                    mapa.setMyLocationEnabled(true);
                }
            }
            else {
                buildGoogleApiClient();
                mapa.setMyLocationEnabled(true);
            }



        }
        public void moveCamera(View view) {

            mapa.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        public void animateCamera(View view) {
            mapa.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        }

        public void addMarker(View view) {
            mapa.addMarker(new MarkerOptions().position(
                    mapa.getCameraPosition().target));
        }
        @Override
        public void onMapClick(LatLng puntoPulsado) {
            mapa.addMarker(new MarkerOptions().position(puntoPulsado)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
        }

        @Override
        public void onConnected( Bundle bundle) {
            Log.e("TAG", "onConnected");

            mLocationRequest = new LocationRequest();
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            mLocationRequest.setInterval(1000);
            mLocationRequest.setFastestInterval(1000);
            try {
                if (ContextCompat.checkSelfPermission(Objects.requireNonNull(this),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest,
                            this);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private void initGoogleAPIClient() {
            mGoogleApiClient = new GoogleApiClient.Builder(MapaUbiActivity.this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.e("TAG", "onConnectionSuspended");
            mGoogleApiClient.connect(i);
        }

        @Override
        public void onConnectionFailed( ConnectionResult connectionResult) {
            Log.e("TAG", "onConnectionSuspended");
        }

        @Override
        public void onLocationChanged(Location location) {
            Log.e("TAG", "onLocationChanged");
            mLocation = location;
            System.out.println("lat:"+ mLocation.getLatitude() + "log:"+mLocation.getLongitude());
            latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Posición Actual");
            markerOptions.icon(iconUser);
            mCurrLocationMarker = mapa.addMarker(markerOptions);
            mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        }




        @Override
        public void onStart() {
            super.onStart();
            mGoogleApiClient.connect();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected synchronized void buildGoogleApiClient() {
        Log.e("TAG", "buildGoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    public void checkGPS() {
        LocationManager locationManager = (LocationManager)this
                .getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            System.out.println("habiliotado");
        }
    }*/


}