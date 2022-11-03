package com.example.project_x;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class SubActivity extends AppCompatActivity {

    ImageButton go_back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        go_back = findViewById(R.id.button_sub_back);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );
    }
}