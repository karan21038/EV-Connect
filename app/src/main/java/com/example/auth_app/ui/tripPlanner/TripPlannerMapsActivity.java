package com.example.auth_app.ui.tripPlanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.auth_app.R;
import com.example.auth_app.databinding.ActivityTripPlannerMapsBinding;
import com.example.auth_app.ui.maps.StationMapActivity;
import com.example.auth_app.ui.stations.StationAdapter;
import com.example.auth_app.ui.stations.StationFragment;
import com.example.auth_app.ui.stations.StationListItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TripPlannerMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private Geocoder geocoder;
    double latitude;
    double longitude;
    public ArrayList<StationListItem> stationListItems;
    public static String name;
    public static double price ;
    public static double lati ;
    public static double longi ;
    public static String address_station ;
    public static String strtTime ;
    public static String closeTime ;
    public static double rating;

    public List<Marker> markerList;

    public int current_time_hour;
    public String status;

    public double distance_from_curr_location;
    public int image_station;
    public int k=0;

    public float[] results = new float[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_planner_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map_trip);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        stationListItems = new ArrayList<>();
        markerList = new ArrayList<Marker>();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

//
//        mMap.setOnMapLongClickListener(this);
        mMap.setOnMarkerDragListener(this);

        LocationManager locationManager = (LocationManager)
                getSystemService(this.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        Date currentTime = Calendar.getInstance().getTime();
        String[] str_arr = String.valueOf(currentTime).split(" ");
        char a = str_arr[3].charAt(0);
        char b = str_arr[3].charAt(1);
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);
        String current_time_hour_str = str1+str2;
        current_time_hour = Integer.parseInt(current_time_hour_str);
        Log.i("","Current Time"+current_time_hour);


        try {
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title(address.getAddressLine(0)).draggable(true);
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] images_station = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s};

        for(int i=1;i<=20;i++) {

            int finalI = i;
            FirebaseDatabase.getInstance().getReference().child("Stations").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                private static final String TAG = "StationFragment";

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
//
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        Log.i("TAG", "map>>>>>: " + map);
                        name = (String) map.get("Name");
                        price = (double) map.get("Price");
                        lati = (double) map.get("Latitude");
                        longi = (double) map.get("Longitude");
                        address_station = (String) map.get("Address");
                        strtTime = (String) map.get("Starting Time");
                        closeTime = (String) map.get("Closing Time");
                        rating = (double) map.get("Rating");
                        //url = (String) map.get("img-url");
//               Log.i("TAG", "MAPPPPPP>>>>>: "+map);
                        Log.i("TAG", "KRKRKRKRK>>>>>: " + rating);
                        android.location.Location.distanceBetween(latitude,longitude,lati,longi,results);
                        Log.i("", "Current Location : " + latitude + ", " + longitude + ", "+ results[0]);
                        Log.i("","Station Time : "+strtTime+", "+closeTime);

                        char a = strtTime.charAt(0);
                        char b = strtTime.charAt(1);
                        String str1 = String.valueOf(a);
                        String str2 = String.valueOf(b);

                        String strt_time_hour_str = "";

                        if(!str2.equals(":"))
                            strt_time_hour_str = str1+str2;
                        else if(str2.equals(":"))
                            strt_time_hour_str = str1;

                        int strt_time_hour = Integer.parseInt(strt_time_hour_str);

                        String close_time_hour_str = "";

                        char a1 = closeTime.charAt(0);
                        char b1 = closeTime.charAt(1);
                        String str3 = String.valueOf(a1);
                        String str4 = String.valueOf(b1);

                        Random rd = new Random();
                        int temp_rk = rd.nextInt(17);

                        if(!str4.equals(":"))
                            close_time_hour_str = str3+str4;
                        else if(str4.equals(":"))
                            close_time_hour_str = str3;

                        int close_time_hour = Integer.parseInt(close_time_hour_str);

                        if(current_time_hour>=strt_time_hour && current_time_hour<=close_time_hour)
                            status = "Opened Now";
                        else
                            status = "Closed Now";

                        Log.i("","Station Time Hour"+strt_time_hour+close_time_hour);
                        if(k<17) {
                            image_station = images_station[k++];
                            Log.i("", "K value: " + k);
                        }
                        else{
                            image_station = images_station[temp_rk];
                        }
                        if(results[0]<=10000.0) {
                            distance_from_curr_location = results[0]/1000;
                            String temp = String.format("%.2f",distance_from_curr_location);
                            distance_from_curr_location = Double.parseDouble(temp);

                            //Log.i("","K value: "+finalK);
                            stationListItems.add(new StationListItem(finalI, name, lati, longi, price, strtTime, closeTime, rating, address_station, distance_from_curr_location, status,image_station));

                            try {
                                List<Address> addresses = geocoder.getFromLocation(lati,longi,1);
                                if (addresses.size() > 0) {
                                    Marker marker;
                                    Address address = addresses.get(0);
                                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                    MarkerOptions markerOptions = new MarkerOptions()
                                            .position(latLng)
                                            .title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                    marker = mMap.addMarker(markerOptions);
                                    markerList.add(marker);
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }


    @Override
    public void onMarkerDragStart(Marker marker) {
        Log.d(TAG, "onMarkerDragStart: ");
    }

    @Override
    public void onMarkerDrag(Marker marker) {


        Log.d(TAG, "onMarkerDrag: ");
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

        mMap.clear();
        markerList.clear();

        Log.d(TAG, "onMarkerDragEnd: ");
        LatLng latLng = marker.getPosition();
        latitude = latLng.latitude;
        longitude = latLng.longitude;

        Date currentTime = Calendar.getInstance().getTime();
        String[] str_arr = String.valueOf(currentTime).split(" ");
        char a = str_arr[3].charAt(0);
        char b = str_arr[3].charAt(1);
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);
        String current_time_hour_str = str1+str2;
        current_time_hour = Integer.parseInt(current_time_hour_str);
        Log.i("","Current Time"+current_time_hour);

        try {
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            if (addresses.size() > 0) {
                Address address = addresses.get(0);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(latLng)
                        .title(address.getAddressLine(0)).draggable(true);
                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] images_station = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,R.drawable.j,R.drawable.k,R.drawable.l,R.drawable.m,R.drawable.n,R.drawable.o,R.drawable.p,R.drawable.q,R.drawable.r,R.drawable.s};

        for(int i=1;i<=20;i++) {

            int finalI = i;
            FirebaseDatabase.getInstance().getReference().child("Stations").child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                private static final String TAG = "StationFragment";

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
//
                        Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                        Log.i("TAG", "map>>>>>: " + map);
                        name = (String) map.get("Name");
                        price = (double) map.get("Price");
                        lati = (double) map.get("Latitude");
                        longi = (double) map.get("Longitude");
                        address_station = (String) map.get("Address");
                        strtTime = (String) map.get("Starting Time");
                        closeTime = (String) map.get("Closing Time");
                        rating = (double) map.get("Rating");
                        //url = (String) map.get("img-url");
//               Log.i("TAG", "MAPPPPPP>>>>>: "+map);
                        Log.i("TAG", "KRKRKRKRK>>>>>: " + rating);
                        android.location.Location.distanceBetween(latitude,longitude,lati,longi,results);
                        Log.i("", "Current Location : " + latitude + ", " + longitude + ", "+ results[0]);
                        Log.i("","Station Time : "+strtTime+", "+closeTime);

                        char a = strtTime.charAt(0);
                        char b = strtTime.charAt(1);
                        String str1 = String.valueOf(a);
                        String str2 = String.valueOf(b);

                        String strt_time_hour_str = "";

                        if(!str2.equals(":"))
                            strt_time_hour_str = str1+str2;
                        else if(str2.equals(":"))
                            strt_time_hour_str = str1;

                        int strt_time_hour = Integer.parseInt(strt_time_hour_str);

                        String close_time_hour_str = "";

                        char a1 = closeTime.charAt(0);
                        char b1 = closeTime.charAt(1);
                        String str3 = String.valueOf(a1);
                        String str4 = String.valueOf(b1);

                        Random rd = new Random();
                        int temp_rk = rd.nextInt(17);

                        if(!str4.equals(":"))
                            close_time_hour_str = str3+str4;
                        else if(str4.equals(":"))
                            close_time_hour_str = str3;

                        int close_time_hour = Integer.parseInt(close_time_hour_str);

                        if(current_time_hour>=strt_time_hour && current_time_hour<=close_time_hour)
                            status = "Opened Now";
                        else
                            status = "Closed Now";

                        Log.i("","Station Time Hour"+strt_time_hour+close_time_hour);
                        if(k<17) {
                            image_station = images_station[k++];
                            Log.i("", "K value: " + k);
                        }
                        else{
                            image_station = images_station[temp_rk];
                        }
                        if(results[0]<=10000.0) {
                            distance_from_curr_location = results[0]/1000;
                            String temp = String.format("%.2f",distance_from_curr_location);
                            distance_from_curr_location = Double.parseDouble(temp);

                            //Log.i("","K value: "+finalK);
                            stationListItems.add(new StationListItem(finalI, name, lati, longi, price, strtTime, closeTime, rating, address_station, distance_from_curr_location, status,image_station));

                            try {
                                List<Address> addresses = geocoder.getFromLocation(lati,longi,1);
                                if (addresses.size() > 0) {
                                    Marker marker;
                                    Address address = addresses.get(0);
                                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                    MarkerOptions markerOptions = new MarkerOptions()
                                            .position(latLng)
                                            .title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                    marker = mMap.addMarker(markerOptions);
                                    markerList.add(marker);
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
}