package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.androidproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavView;
    private ActivityMainBinding binding;

    SplashFragment splashFragment = new SplashFragment();
    AddContactFragment addContactFragment = new AddContactFragment();
    RecyclerFragment recyclerFragment = new RecyclerFragment();
    CreditsFragment creditsFragment = new CreditsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView bottomNavView = findViewById(R.id.nav_view);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,splashFragment).commit();

        bottomNavView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_splash) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,splashFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_contacts_form) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,addContactFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_recycler_view) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,recyclerFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.nav_credits) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,creditsFragment).commit();
                    return true;
                }
                return false;
            }
        });

    }
}