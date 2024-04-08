package com.example.templemaps;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
public class Temple {
    private double latitude;
    private double longitude;
    private String name;
    private String description;
    private int iconResourceId;
    private LatLng location;

    public Temple() {
        // Default constructor
    }

    private String imageResourceId;

    public Temple(double latitude, double longitude, String name, String description, int iconResourceId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LatLng getLocation(){
        return new LatLng(latitude, longitude);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getIconResourceId() {
        return iconResourceId;
    }

    public void setIconResourceId(int iconResourceId) {
        this.iconResourceId = iconResourceId;
    }

    public MarkerOptions toMarkerOptions() {
        MarkerOptions options = new MarkerOptions();
        LatLng location = new LatLng(latitude, longitude);
        options.position(location);
        options.title(name);
        options.snippet(description);
        options.icon(BitmapDescriptorFactory.fromResource(iconResourceId));
        return options;
    }

    public static List<Temple> loadTemplesFromJson(Context context, String filename) {
        try {
            // Open the JSON file from assets
            InputStream inputStream = context.getAssets().open(filename);

            // Read the JSON data from the input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();

            // Convert JSON data to a List<Temple> using Gson
            Type listType = new TypeToken<List<Temple>>() {
            }.getType();
            return new Gson().fromJson(stringBuilder.toString(), listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getImageResourceId() {
        return imageResourceId;
    }
}