package com.example.globalitsupport.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.globalitsupport.DatabaseHelper;
import com.example.globalitsupport.R;
import com.example.globalitsupport.UserInfo;

import java.util.ArrayList;

public class UserlistActivity extends AppCompatActivity {

    LinearLayout container;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.userlist_layout);

        container = findViewById(R.id.container);
        databaseHelper = new DatabaseHelper(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        showUserList();
    }

    public void showUserList() {
        ArrayList<UserInfo> list = databaseHelper.getUserList();
        container.removeAllViews();

        for (UserInfo info : list) {

            View view = LayoutInflater.from(this).inflate(R.layout.item_layout, null);

            ImageView imageView = view.findViewById(R.id.profileimage);
            TextView name = view.findViewById(R.id.name);
            TextView phone = view.findViewById(R.id.phone);
            imageView.setImageBitmap(SignupActivity.getBitmap(info.image));
            name.setText(info.name);
            phone.setText(info.phone);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserlistActivity.this, ProfileActivity.class);
                    intent.putExtra("id", info.id);
                    startActivity(intent);
                }
            });

            container.addView(view);
        }
    }
}