package com.example.templemaps;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Temple {
    private LatLng location;
    private String name;
    private String description;
    private int iconResourceId;

    public Temple() {
        // Default constructor
    }

    public Temple(LatLng location, String name, String description, int iconResourceId) {
        this.location = location;
        this.name = name;
        this.description = description;
        this.iconResourceId = iconResourceId;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
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
        options.position(location);
        options.title(name);
        options.snippet(description);
        options.icon(BitmapDescriptorFactory.fromResource(iconResourceId));
        return options;
    }
}
