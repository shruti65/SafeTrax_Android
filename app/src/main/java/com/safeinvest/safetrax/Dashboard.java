package com.safeinvest.safetrax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        DrawerLayout drawerLayout= findViewById(R.id.drawerlayout);
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        NavigationView navigationView= findViewById(R.id.navigationview);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.menu_cef:
                        loadfrag(new cef());
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });
    }
    private void loadfrag(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.replace(R.id.fragmentContainerView,fragment);
        ft.commit();
    }
}