package com.example.globalitsupport.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalitsupport.R;

public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_layout);

        EditText name, address, phone, email, username, password;
        Button signup;
        TextView login;

        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRegisteredValues();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    public void getRegisteredValues() {
        String nameValue = name.getText().toString();
        String addressValue = address.getText().toString();
        String phoneValue = phone.getText().toString();
        String emailValue = email.getText().toString();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();

        Toast.makeText(this, "Name" + nameValue
                + "Address" + addressValue
                + "Phone" + phoneValue
                + "Email" + emailValue
                + "Username" + usernameValue
                + "Password" + passwordValue, Toast.LENGTH_SHORT).show();
    }
}