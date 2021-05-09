package com.example.globalitsupport.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.globalitsupport.DatabaseHelper;
import com.example.globalitsupport.R;
import com.example.globalitsupport.UserInfo;

public class ProfileActivity extends AppCompatActivity {

    TextView name, address, phone, email, username;
    Button update, delete, back;

    String id;

    SharedPreferences sharedPreferences;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.profile_layout);

        sharedPreferences = getSharedPreferences("Userinfo", 0);

        databaseHelper = new DatabaseHelper(this);

        id = getIntent().getStringExtra("id");
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);

        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        displayUserInfo();
    }

    public void displayUserInfo() {
        final UserInfo info = databaseHelper.getUserInfo(id);
        name.setText(info.name);
        address.setText(info.address);
        phone.setText(info.phone);
        email.setText(info.email);
        username.setText(info.username);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, SignupActivity.class);
                intent.putExtra("id", Integer.parseInt(id));
                startActivity(intent);
            }
        });

    }
}