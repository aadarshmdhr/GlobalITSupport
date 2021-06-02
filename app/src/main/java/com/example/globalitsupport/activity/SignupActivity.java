package com.example.globalitsupport.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.globalitsupport.DatabaseHelper;
import com.example.globalitsupport.R;
import com.example.globalitsupport.models.UserInfo;

import java.io.ByteArrayOutputStream;

public class SignupActivity extends AppCompatActivity {

    ImageView imageView;
    EditText name, address, phone, email, username, password;
    Button signup;
    TextView login;

    int id;

    SharedPreferences sharedPreferences;

    DatabaseHelper databaseHelper;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_layout);

        id = getIntent().getIntExtra("id", 0);
        sharedPreferences = getSharedPreferences("Userinfo", 0);
        databaseHelper = new DatabaseHelper(this);

        imageView = findViewById(R.id.gitlogo);
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        if (id != 0) {
            final UserInfo info = databaseHelper.getUserInfo(id + "");
            name.setText(info.name);
            address.setText(info.address);
            phone.setText(info.phone);
            email.setText(info.email);
            username.setText(info.username);
            signup.setText("Update");
        }
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

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void getRegisteredValues() {
        String nameValue = name.getText().toString();
        String addressValue = address.getText().toString();
        String phoneValue = phone.getText().toString();
        String emailValue = email.getText().toString();
        String usernameValue = username.getText().toString();
        String passwordValue = password.getText().toString();

        Toast.makeText(this, "Name:" + nameValue
                + "\nAddress:" + addressValue
                + "\nPhone:" + phoneValue
                + "\nEmail:" + emailValue
                + "\nUsername:" + usernameValue
                + "\nPassword:" + passwordValue, Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "User registered", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(SignupActivity.this, MainActivity.class));

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", nameValue);
        contentValues.put("address", addressValue);
        contentValues.put("phone", phoneValue);
        contentValues.put("email", emailValue);
        contentValues.put("username", usernameValue);
        contentValues.put("password", passwordValue);
        if (bitmap != null)
            contentValues.put("image", getBlob(bitmap));
        if (id == 0) {
            databaseHelper.insertUser(contentValues);
            Toast.makeText(this, "User inserted", Toast.LENGTH_SHORT).show();
        } else {
            databaseHelper.updateUser(String.valueOf(id), contentValues);
            Toast.makeText(this, "User updated", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public static byte[] getBlob(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();
        StringBuilder sb = new StringBuilder();
        sb.append("bytearray:");
        sb.append(bArray);
        Log.i("bytearray", sb.toString());
        return bArray;
    }
}