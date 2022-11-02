package com.example.project_x;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AddActivity extends AppCompatActivity {

    ImageButton go_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        go_back = findViewById(R.id.go_back_add);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );
    }
}