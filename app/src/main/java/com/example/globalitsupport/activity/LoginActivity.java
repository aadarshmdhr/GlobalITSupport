package com.example.globalitsupport.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalitsupport.R;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    CheckBox rememberme;
    Button login;
    TextView signup;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_layout);

        sharedPreferences = getSharedPreferences("Userinfo", 0);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        rememberme = findViewById(R.id.rememberme);
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);

        if (sharedPreferences.getBoolean("rememberme", false)) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameValue = username.getText().toString();
                String passwordValue = password.getText().toString();
                String registeredUsername = sharedPreferences.getString("username", "");
                String registeredPassword = sharedPreferences.getString("password", "");
                if (registeredPassword.equals(passwordValue) && registeredUsername.equals(usernameValue)) {
                    if (rememberme.isChecked()) {
                        sharedPreferences.edit().putBoolean("rememberme", true).apply();
                    }
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });
    }
}