package com.example.project_x;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AddRegular extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton go_back;
    private EditText add_name_insert, add_frequency_insert, add_sum_insert, add_date_insert, add_comment_insert;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regular_add);
        add_name_insert = findViewById(R.id.editTextRegName);
        add_frequency_insert = findViewById(R.id.editTextRegFreq);
        add_sum_insert = findViewById(R.id.editTextRegSum);
        add_date_insert = findViewById(R.id.editTextRegDate);
        add_comment_insert = findViewById(R.id.editTextRegComment);
        go_back = findViewById(R.id.goback_regular);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, RegularActivity.class);
                    startActivity(intent);
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
