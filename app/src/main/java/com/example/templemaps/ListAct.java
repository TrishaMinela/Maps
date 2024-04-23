package com.example.templemaps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class ListAct extends AppCompatActivity {
    private List<Temple> temples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        temples = Temple.loadTemplesFromJson(this, "temples.json");

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setItemIconTintList(null);
        // Set the selected item listener
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.navigation_spiral) {
                    Log.d("Navigation", "Spiral selected");
                    // Start the SpiralActivity
                    startActivity(new Intent(ListAct.this, SpiralAct.class));
                    return true;
                } else if (id == R.id.navigation_map) {
                    Log.d("Navigation", "Map selected");
                    //Start the Map Activity
                    startActivity(new Intent(ListAct.this, MainActivity.class));
                    return true;
                } else if (id == R.id.navigation_list) {
                    Log.d("Navigation", "List selected");
                    // Already in the list activity, do nothing or handle special cases
                    return true;
                }
                return false;
            }
        });
        bottomNav.setSelectedItemId(R.id.navigation_list);

    }
}