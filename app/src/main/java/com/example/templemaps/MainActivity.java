package com.example.templemaps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.admin.SystemUpdatePolicy;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

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

        temples.add(new Temple(new LatLng(10.698185265854661, 122.99053061022767), "Bacolod Philippines Temple",
                "Announcement: 5 October 2019\n" +
                        "Groundbreaking: 11 December 2021\n", R.drawable.bacolod_philippines_temple));

        temples.add(new Temple(new LatLng(-38.69058407842174, -62.2338538556748), "Bahia Blanca Argentina Temple",
                "Announcement: 5 April 2020\n" +
                        "Groundbreaking: 9 April 2022\n", R.drawable.bahia_blanca_argentina_temple));

        temples.add(new Temple(new LatLng(35.3549833280961, -119.13774044326361), "Bakersfield California Temple",
                "Announcement: 2 April 2023\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(13.749762600411321, 100.56312662191793), "Bangkok Thailand Temple",
                "Announcement: 5 April 2015\n" +
                        "Groundbreaking: 26 January 2019\n", R.drawable.bangkok_thailand_temple));

        temples.add(new Temple(new LatLng(41.49045056053769, 2.0808519369518863), "Barcelona Spain Temple",
                "Announced: 3 April 2022\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(11.017085400180319, -74.86159093631201), "Barranquilla Colombia Temple",
                "Announcement: 1 October 2011\n" +
                        "Groundbreaking: 20 February 2016\n" +
                        "Dedication: 9 December 2018\n", R.drawable.barranquilla_colombia_temple));

        temples.add(new Temple(new LatLng(30.362943248495572, -91.10773717037753), "Baton Rouge Louisiana Temple",
                "Announcement: 14 October 1998\n" +
                        "Groundbreaking: 8 May 1999\n" +
                        "Dedication: 16 July 2000\n" +
                        "Rededication: 17 November 2019\n", R.drawable.baton_rouge_louisiana_temple));

        temples.add(new Temple(new LatLng(-19.844339015620616, 34.862248817922136), "Beira Mozambique Temple",
                "Announcement: 4 April 2021\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(-1.3863675590049538, -48.45907762048797), "Belem Brazil Temple",
                "Announcement: 3 April 2016\n" +
                        "Groundbreaking: 17 August 2019\n" +
                        "Dedication: 20 November 2022\n", R.drawable.belem_brazil_temple));

        temples.add(new Temple(new LatLng(-19.87999579756787, -43.977119362653205), "Belo Horizonte Brazil Temple",
                "Announcement: 4 April 2021\n", R.drawable.belo_horizonte_brazil_temple));

        temples.add(new Temple(new LatLng(12.992872987157396, 77.70531129492537), "Bengaluru India Temple",
                "Announcement: 1 April 2018\n" +
                        "Groundbreaking: 2 December 2020\n", R.drawable.bengaluru_india_temple));

        temples.add(new Temple(new LatLng(6.323228922940358, 5.632757337787359), "Benin City Nigeria Temple",
                "Announcement: 5 April 2020\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(36.385177705134815, -94.18187541439885), "Bentonville Arkansas Temple",
                "Announcement: 5 October 2019\n" +
                        "Groundbreaking: 7 November 2020\n", R.drawable.bentonville_arkansas_temple));

        temples.add(new Temple(new LatLng(47.002087939348925, 7.458710009219409), "Bern Switzerland Temple",
                "Announcement: 1 July 1952\n" +
                        "Groundbreaking: 5 August 1953\n" +
                        "Dedication: 11–15 September 1955\n" +
                        "Rededication: 23–25 October 1992\n", R.drawable.bern_switzerland_temple));

        temples.add(new Temple(new LatLng(45.80071532556247, -108.6383691466424), "Billings Montana Temple",
                "Announcement: 30 August 1996\n" +
                        "Groundbreaking: 30 March 1998\n" +
                        "Dedication: 20–21 November 1999\n", R.drawable.billings_montana_temple));

        temples.add(new Temple(new LatLng(33.674690958298065, -86.82087432373186), "Birmingham Alabama Temple",
                "Announcement: 11 September 1998\n" +
                        "Groundbreaking: 9 October 1999\n" +
                        "Dedication: 3 September 2000\n", R.drawable.birmingham_alabama_temple));

        /*temples.add(new Temple(new LatLng(unknownLat, unknownLng), "Birmingham United Kingdom Temple",
                "Announced: 3 April 2022\n", R.drawable.no_image));*/

        temples.add(new Temple(new LatLng(46.83906773055011, -100.81340594475473), "Bismarck North Dakota Temple",
                "Announcement: 29 July 1998\n" +
                        "Groundbreaking: 17 October 1998\n" +
                        "Dedication: 19 September 1999\n", R.drawable.bismarck_north_dakota_temple));

        temples.add(new Temple(new LatLng(4.708148311143708, -74.0554108284164), "Bogota Colombia Temple",
                "Announcement: 7 April 1984\n" +
                        "Groundbreaking: 26 June 1993\n" + "Dedication: 24–26 April 1999\n", R.drawable.bogota_colombia_temple));

        temples.add(new Temple(new LatLng(43.593848047829475, -116.27477132158418), "Boise Idaho Temple",
                "Announcement: 31 March 1982\n" +
                        "Groundbreaking: 18 December 1982\n" +
                        "Dedication: 25–30 May 1984\n" +
                        "Rededication: 29 May 1987\n" +
                        "Rededication: 18 November 2012\n", R.drawable.boise_idaho_temple));

        temples.add(new Temple(new LatLng(42.41230929550218, -71.18712912030117), "Boston Massachusetts Temple",
                "Announcement: 30 September 1995\n" +
                        "Groundbreaking: 13 June 1997\n" +
                        "Dedication: 1 October 2000\n", R.drawable.boston_massachusetts_temple));

        temples.add(new Temple(new LatLng(40.88315626676571, -111.84626619098371), "Bountiful Utah Temple",
                "Announcement: 2 February 1990\n" +
                        "Groundbreaking: 2 May 1992\n" +
                        "Dedication: 8–14 January 1995\n", R.drawable.bountiful_utah_temple));

        temples.add(new Temple(new LatLng(-15.747313557115652, -47.88058059393178), "Brasilia Brazil Temple",
                "Announcement: 2 April 2017\n" +
                        "GroundBreaking: 26 September 2020\n", R.drawable.brasilia_brazil_temple));

        temples.add(new Temple(new LatLng(-4.321288799827434, 15.261529244298705), "Brazzaville Republic of the Congo Temple",
                "Announced: 3 April 2022\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(41.505800439057964, -112.01608320815957), "Brigham City Utah Temple",
                "Announcement: 3 October 2009\n" +
                        "Groundbreaking: 31 July 2010\n" +
                        "Dedication: 23 September 2012\n", R.drawable.brigham_city_utah_temple));

        temples.add(new Temple(new LatLng(-27.48069511113349, 153.03416020680515), "Brisbane Australia Temple",
                "Announcement: 20 July 1998\n" +
                        "Groundbreaking: 26 May 2001\n" +
                        "Dedication: 15 June 2003\n", R.drawable.brisbane_australia_temple));

        /*temples.add(new Temple(new LatLng(0, 0), "Brussels Belgium Temple",
                "Announcement: 4 April 2021\n", R.drawable.no_image));*/

        temples.add(new Temple(new LatLng(47.57033771832978, 19.006803265059634), "Budapest Hungary Temple",
                "Announcement: 7 April 2019\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(-34.72642570380932, -58.5022034706948), "Buenos Aires Argentina Temple",
                "Announcement: 2 April 1980\n" +
                        "Groundbreaking: 20 April 1983\n" +
                        "Dedication: 17–19 January 1986\n" +
                        "Rededication: 9 September 2012\n", R.drawable.buenos_aires_argentina_temple));

        temples.add(new Temple(new LatLng(-34.72882409172169, -58.517492062858196), "Buenos Aires City Center Argentina Temple",
                "Announcement: 2 October 2022\n", R.drawable.no_image));

        temples.add(new Temple(new LatLng(42.5273076046985, -113.76360103881454), "Burley Idaho Temple",
                "Announcement: 4 April 2021\n" +
                        "Groundbreaking: 4 June 2022\n", R.drawable.burley_idaho_temple));

        /*temples.add(new Temple(new LatLng(unknownLat, unknownLng), "Busan Korea Temple",
                "Announcement: 2 October 2022\n", R.drawable.no_image));*/


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


    //Maps and Markers
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;

        //Markers for each Temple in the List
        for (Temple temple : temples) {
            MarkerOptions markerOptions = temple.toMarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                    .flat(true);
            markerOptions.anchor(0.5f, 0.5f);
            Marker marker = googleMap.addMarker(markerOptions);
            markerList.add(marker);
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
                for (Marker marker : markerList) {
                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marker));
                    marker.setAnchor(0.5f / zoom, 0.5f / zoom);
                }
            }
        });

        // click Listener for Markers
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // Close previous popup if open
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
                // Find the corresponding temple for the clicked marker
                Temple clickedTemple = null;
                for (Temple temple : temples) {
                    if (temple.getLocation().equals(marker.getPosition())) {
                        clickedTemple = temple;
                        break;
                    }
                }

                // Display popup with temple's name and description
                if (clickedTemple != null) {
                    //Box with Temple information
                    showPopupWindow(clickedTemple);
                }

                // Return true to consume the event
                return true;
            }
        });
        // Click listener for the map to close the popup
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    private PopupWindow popupWindow;

    private void showPopupWindow(Temple temple) {
        if (popupWindow != null && popupWindow.isShowing()) {
            // Popup window is already showing, update its content and position
            updatePopupContent(temple);
        } else {
            // Popup window is not showing, create and show it
            createAndShowPopup(temple);
        }
    }

    private void createAndShowPopup(Temple temple) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_layout, null);

        // Set temple information in the popup layout
        TextView templeName = popupView.findViewById(R.id.templeNameTextView);
        TextView templeInfo = popupView.findViewById(R.id.templeDescriptionTextView);
        ImageView templeImage = popupView.findViewById(R.id.templeImageView);
        templeName.setText(temple.getName());
        templeInfo.setText(temple.getDescription());
        templeImage.setImageResource(temple.getIconResourceId());

        // Create the popup window
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        popupWindow = new PopupWindow(popupView, width, height, true);

        // Set the following properties to prevent dismissal when clicking outside the popup window
        popupWindow.setOutsideTouchable(false);
        popupWindow.setFocusable(false);

        // Measure the view to calculate its height
        popupView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupHeight = popupView.getMeasuredHeight();

        // Get screen height
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;

        // Calculate the Y position for the popup window to appear above the center of the screen
        int spacing = getResources().getDimensionPixelSize(R.dimen.popup_spacing);
        int popupYPosition = (screenHeight - popupHeight - spacing); // Adjust this value as needed

        // Show the popup window centered horizontally and above the center of the screen
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, popupYPosition);

        // Set a dismiss listener to nullify the popup window reference
        popupWindow.setOnDismissListener(null);
    }

    private void updatePopupContent(Temple temple) {
        // Update temple information in the existing popup layout
        TextView templeName = popupWindow.getContentView().findViewById(R.id.templeNameTextView);
        TextView templeInfo = popupWindow.getContentView().findViewById(R.id.templeDescriptionTextView);
        ImageView templeImage = popupWindow.getContentView().findViewById(R.id.templeImageView);
        templeName.setText(temple.getName());
        templeInfo.setText(temple.getDescription());
        templeImage.setImageResource(temple.getIconResourceId());
    }
}