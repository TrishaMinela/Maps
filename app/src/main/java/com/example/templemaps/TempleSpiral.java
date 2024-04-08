package com.example.templemaps;


import android.graphics.Bitmap;

public class TempleSpiral {
    Bitmap image;
    Float size;
    Float x;
    Float y;
    String role;
    static String link;
    Boolean hasImage;

    public TempleSpiral(Bitmap imageP, Float sizeP, Float xP, Float yP, Boolean hi) {
        image = imageP;
        size = sizeP;
        x = xP;
        y = yP;
        role = "";
        link = "";
        hasImage = hi;
    }

    public TempleSpiral(Bitmap imageP, Float sizeP, Float xP, Float yP) {
        image = imageP;
        size = sizeP;
        x = xP;
        y = yP;
        role = "";
        link = "";
    }
    public void setRole(String r) {
        role = r;
    }

    public static void setLink(String l) {
        link = l;
    }

    public void changeImage(Bitmap b) {
        image = b;
    }


}
