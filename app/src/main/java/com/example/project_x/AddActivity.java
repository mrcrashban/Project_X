package com.example.project_x;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.Transactions;

public class AddActivity extends AppCompatActivity {

    private Context context;
    private ImageButton go_back;
    private Button add_button;
    private EditText add_sum_insert, add_acc_insert, add_cat_insert, add_date_insert, add_comment_insert, test;
    private String type;

    @SuppressLint("MissingInflatedId")
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
        type = "add";
        add_button = findViewById(R.id.transaction_add);
        add_sum_insert = findViewById(R.id.add_sum_insert);
        add_acc_insert = findViewById(R.id.add_acc_insert);
        add_cat_insert = findViewById(R.id.add_cat_insert);
        add_date_insert = findViewById(R.id.add_date_insert);
        test = findViewById(R.id.test);
        add_comment_insert = findViewById(R.id.add_comment_insert);
        add_button.setOnClickListener(saveTransaction(Integer.valueOf(add_acc_insert.getText().toString()),Integer.valueOf(add_cat_insert.getText().toString()),type,add_sum_insert.getText().toString(),add_date_insert.getText().toString(),add_comment_insert.getText().toString()));
    }

    private View.OnClickListener saveTransaction(Integer account, Integer category, String type, String sum, String date, String comment){
        if (TextUtils.isEmpty(test.getText().toString())) {
            new Thread(() -> {
                DBClient.getInstance(context).getAppDatabase()
                        .MainDao()
                        .insert(new Transactions(account, category, type, sum, date, comment));
            }).start();
        }
        return null;
       }

}