package com.example.templemaps;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Temple {
    private LatLng location;
    private String name;
    private String description;
    private  int iconResourceId;

    public Temple(LatLng location, String name, String description, int iconResourceId) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }

    public LatLng getLocation(){
        return location;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public int getIconResourceId(){

        return iconResourceId;
    }

    public MarkerOptions toMarkerOptions(){
        MarkerOptions options = new MarkerOptions();
        options.position(location);
        options.title(name);
        options.snippet(description);
        options.icon(BitmapDescriptorFactory.fromResource(iconResourceId));
        return options;
    }
}
