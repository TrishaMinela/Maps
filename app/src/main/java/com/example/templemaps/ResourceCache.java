package com.example.templemaps;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResourceCache {

    private static final Map<Integer, Bitmap> cache = new HashMap<>();
    private ArrayList<String> templeInfo = new ArrayList<>();
    private ArrayList<String> templeDrawableNames = new ArrayList<>();
    public ArrayList<Integer> templeLargeDrawableIds = new ArrayList<>();
    public ArrayList<String> templeYears = new ArrayList<>();
    public ArrayList<String> allTempleLinks = new ArrayList<>();
    public ArrayList<String> templeNames = new ArrayList<>();
    public  ArrayList<Integer> smallImageIdentifiers = new ArrayList<>();
    public ArrayList<TempleSpiral> templeObjects = new ArrayList<TempleSpiral>();
    public  ArrayList<Integer>  allTempleInfoFileIds = new ArrayList<>();

    public ResourceCache(Context context, float w2) {

        Integer noImageIdentifier = context.getResources().getIdentifier("no_image", "drawable", "edu.byuh.cis.templevis");

        readInfoFile(context);
        for (String s: templeInfo) {
            templeDrawableNames.add(s.substring(0, s.length()-6));
            templeYears.add(s.substring(s.length()-5, s.length()-1));
        }

        Log.d("temples count", templeInfo.size() + "");

        for (String s: templeDrawableNames) {
            Integer identifier = context.getResources().getIdentifier(s, "drawable", "edu.byuh.cis.templevis");
            if (identifier != 0) {
                smallImageIdentifiers.add(identifier);
            } else {
                smallImageIdentifiers.add(context.getResources().getIdentifier("no_image", "drawable", "edu.byuh.cis.templevis"));
            }

            Integer largeIdentifier = context.getResources().getIdentifier(s + "_large", "drawable", "edu.byuh.cis.templevis");
            if (largeIdentifier != 0) {
                templeLargeDrawableIds.add(largeIdentifier);
            } else {
                templeLargeDrawableIds.add(context.getResources().getIdentifier("no_image_large", "drawable", "edu.byuh.cis.templevis"));
            }

            Integer infoFileIdentifier = context.getResources().getIdentifier(s, "raw", "edu.byuh.cis.templevis");
            if (infoFileIdentifier != 0) {
                allTempleInfoFileIds.add(infoFileIdentifier);
            } else {
                allTempleInfoFileIds.add(context.getResources().getIdentifier("no_info", "raw", "edu.byuh.cis.templevis"));
            }

            Log.d(s, infoFileIdentifier + " " + (infoFileIdentifier == 0 ?  "------" + context.getResources().getIdentifier("no_info", "raw", "edu.byuh.cis.templevis") : " "));

            String[] templeNameList = s.split("_");
            String templeName = "";
            for(int i = 0; i < templeNameList.length; i++) {
                String word = templeNameList[i];
                word = word.substring(0, 1).toUpperCase() + word.substring(1);
                templeName = templeName + " " + word;
            }
            templeNames.add(templeName.substring(1, templeName.length()));

            String[] templeLinkList = s.split("_");
            String templeLink = "";
            for(int i = 0; i < templeLinkList.length; i++) {
                String word = templeLinkList[i];
                word = word.substring(0, 1) + word.substring(1);
                templeLink = templeLink + "-" + word;
            }

            templeLink = "https://www.churchofjesuschrist.org/search?lang=eng&query=" + s;



            allTempleLinks.add(templeLink);
        }

        float w = w2 / 4;

        for (int i:smallImageIdentifiers) {
            Bitmap temple = loadAndScale(context.getResources(),i, w);

            if(i == noImageIdentifier) {
                templeObjects.add(new TempleSpiral(temple, 0f, 0f, 0f, false));
            } else {
                templeObjects.add(new TempleSpiral(temple, 0f, 0f, 0f, true));
            }
        }
        Log.d("templeObjects size", templeObjects.size() + "");

        for(TempleSpiral temple: templeObjects) {
            TempleSpiral.setLink(allTempleLinks.get(templeObjects.indexOf(temple)));
        }


    }

    public void readInfoFile(Context context) {
        try {
            InputStream templeInfosFile = context.getResources().openRawResource(R.raw.temple_names);
            if (templeInfosFile != null)
            {
                InputStreamReader ir = new InputStreamReader(templeInfosFile);
                BufferedReader br = new BufferedReader(ir);
                String line;
                //read each line
                while (( line = br.readLine()) != null) {
                    templeInfo.add(line+"\n");
                }
                templeInfosFile.close();
            }
        }
        catch (FileNotFoundException e)
        {
            Log.d("File", "The File doesn't not exist.");
        }
        catch (IOException e)
        {
            Log.d("File", e.getMessage());
        }

    }


    public static Bitmap getBitmap(Resources res, int id, float newWidth) {
        Bitmap cachedBitmap = cache.get(id);
        if (cachedBitmap != null) {
            return cachedBitmap;
        } else {
            Bitmap scaledBitmap = loadAndScale(res, id, newWidth);
            if (scaledBitmap != null) {
                cache.put(id, scaledBitmap);
            }
            return scaledBitmap;
        }
    }

    private static Bitmap loadAndScale(Resources res, int id, float newWidth) {
        Bitmap original = BitmapFactory.decodeResource(res, id);
        if (original == null) {
            // Handle case where decoding fails
            Log.e("ResourceCache", "Failed to decode bitmap for resource ID: " + id);
            return null;
        } else {
            float aspectRatio = (float) original.getHeight() / (float) original.getWidth();
            float newHeight = newWidth * aspectRatio;
            return Bitmap.createScaledBitmap(original, (int) newWidth, (int) newHeight, true);
        }
    }
}