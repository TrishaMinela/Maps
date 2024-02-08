package com.example.templemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.SystemUpdatePolicy;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private List<Temple> temples = new ArrayList<>();
    private List<Marker> markerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.id_map);
        mapFragment.getMapAsync(this);

        //Adding temples to the list
        temples.add(new Temple(new LatLng(5.1478, 7.3567), "Aba Nigeria Temple", "Announcement:  2 April 2000\n" +
                "Groundbreaking:  23 February 2002\n" +
                "Dedication:  7 August 2005\n", R.drawable.aba_nigeria_temple));

        temples.add(new Temple(new LatLng(5.3623, -3.9768), "Abidjan Ivory Coast Temple", "Announcement:  5 April 2015\n" +
                "Groundbreaking:  8 November 2018\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(5.3623, -3.9768), "Accra Ghana Temple", "Announcement:  16 February 1998\n" +
                "Groundbreaking:  16 November 2001\n" +
                "Dedication:  11 January 2004\n", R.drawable.accra_ghana_temple));

        temples.add(new Temple(new LatLng(-34.8923, 138.6350), "Adelaide Australia Temple", "Announcement:  17 March 1999\n" +
                "Groundbreaking:  29 May 1999\n" +
                "Dedication:  15 June 2000\n", R.drawable.adelaide_australia_temple));

        temples.add(new Temple(new LatLng(14.4130, 121.0383), "Alabang Philippines Temple", "Announcement:  2 April 2017\n" +
                "Groundbreaking:  4 June 2020\n", R.drawable.alabang_philippines_temple));

        temples.add(new Temple(new LatLng(35.1678, -106.5251), "Albuquerque New Mexico Temple", "Announcement:  4 April 1997\n" +
                "Groundbreaking:  20 June 1998\n" +
                "Dedication:  5 March 2000\n", R.drawable.albuquerque_new_mexico_temple));

        temples.add(new Temple(new LatLng(61.101627, -149.840511), "Anchorage Alaska Temple", "Announcement:  4 October 1997\n" +
                "Groundbreaking:  17 April 1998\n" +
                "Dedication:  9–10 January 1999\n" +
                "Rededication:  8 February 2004\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(-18.8539, 47.4785), "Antananarivo Madagascar Temple", "Announcement:  3 October 2021\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(-23.5719, -70.3942), "Antofagasta Chile Temple", "Announcement:  7 April 2019\n" +
                "Groundbreaking:  27 November 2020\n", R.drawable.antofagasta_chile_temple));

        temples.add(new Temple(new LatLng(-13.838344, -171.783586), "Apia Samoa Temple", "Announcement:  15 October 1977\n" +
                "Groundbreaking:  19 February 1981\n" +
                "Dedication:  5–7 August 1983\n" +
                "Rededication:  4 September 2005\n", R.drawable.apia_samoa_temple));

        temples.add(new Temple(new LatLng(-16.3754, -71.5387), "Arequipa Peru Temple", "Announcement:  6 October 2012\n" +
                "Groundbreaking:  4 March 2017\n" +
                "Dedication:  15 December 2019\n", R.drawable.arequipa_peru_temple));

        temples.add(new Temple(new LatLng(-25.287915, -57.602869), "Asunción Paraguay Temple", "Announcement:  2 April 2000\n" +
                "Groundbreaking:  3 February 2001\n" +
                "Dedication:  19 May 2002\n" +
                "Rededication:  3 November 2019\n", R.drawable.asuncion_paraguay_temple));

        temples.add(new Temple(new LatLng(33.9319, -84.3626), "Atlanta Georgia Temple", "Announcement:  2 April 1980\n" +
                "Groundbreaking:  7 March 1981\n" +
                "Dedication:  1–4 June 1983\n" +
                "Rededication:  14 November 1997\n" +
                "Rededication:  1 May 2011\n", R.drawable.atlanta_georgia_temple));

        temples.add(new Temple(new LatLng(-36.99316, 174.89015), "Auckland New Zealand Temple", "Announcement:  7 October 2018\n" +
                "Groundbreaking:  13 June 2020\n", R.drawable.auckland_new_zealand_temple));

        temples.add(new Temple(new LatLng(30.5169, -97.7971), "Austin Texas Temple", "Announced: 3 April 2022\n", R.drawable.no_image));

        //Initialization of Bottom Nav
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_spiral) {
                    // spiral view
                    return true;
                } else if (item.getItemId() == R.id.navigation_map) {
                    // map view
                    return true;
                } else if (item.getItemId() == R.id.navigation_list) {
                    // list view
                    return true;
                } else {
                    return false;
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap=googleMap;

        //Markers for each Temple in the List
        for (Temple temple : temples) {
            MarkerOptions markerOptions = temple.toMarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .flat(true);
            markerOptions.anchor(0.5f, 0.5f);
            googleMap.addMarker(markerOptions);
        }

        //Camera Position
        LatLng location = temples.get(0).getLocation();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));

        //Listener for zoom changes
        googleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                //marker size will be based on zoom level
                float zoom = googleMap.getCameraPosition().zoom;
                for (Marker marker: markerList) {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    marker.setAnchor(0.5f/zoom, 0.5f/zoom);
                }
            }
        });
    }
}