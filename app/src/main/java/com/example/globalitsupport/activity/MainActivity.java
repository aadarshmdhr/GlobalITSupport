package com.example.globalitsupport.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.globalitsupport.R;
import com.example.globalitsupport.fragments.HomeFragment;
import com.example.globalitsupport.fragments.DetailFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.HomeFragmentInteraction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        startFragment(new HomeFragment(), true);
    }

    private void startFragment(Fragment fragment, boolean shouldReplace) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (shouldReplace)
            transaction.replace(R.id.mainActivityContainer, fragment);
        else
            transaction.add(R.id.mainActivityContainer, fragment);
        transaction.addToBackStack("MainBackStack");
        transaction.commit();
    }

    @Override
    public void onLaptopClicked() {
        startFragment(new DetailFragment(), true);
    }
}