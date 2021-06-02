package com.example.globalitsupport.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.globalitsupport.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    ImageView gitlogo, profile, popupmenu;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home_layout);

        BottomNavigationView bottomnav = findViewById(R.id.bottom_navagation);
        navListener.onNavigationItemSelected((MenuItem) navListener);

        sharedPreferences = getSharedPreferences("Userinfo", 0);

        gitlogo = findViewById(R.id.gitlogo);
        gitlogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, WebviewActivity.class));
            }
        });

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, UserlistActivity.class));
                finish();
            }
        });

        popupmenu = findViewById(R.id.popupmenu);
        popupmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpMenu(v);
            }
        });
    }

    public void showPopUpMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        getMenuInflater().inflate(R.menu.popupmenu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logout) {
                    sharedPreferences.edit().putBoolean("rememberme", false).apply();
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                    finish();
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
            Fragment selectFragment = null;
            return false;
        }
    };
}