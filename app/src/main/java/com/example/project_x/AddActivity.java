package com.example.project_x;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.project_x.Adapters.AccountsAdapter;
import com.example.project_x.Adapters.CategoriesAdapter;
import com.example.project_x.BD.Accounts;
import com.example.project_x.BD.AppDB;
import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.MainDao;
import com.example.project_x.BD.Transactions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class AddActivity extends AppCompatActivity {

    private Context context;
    private ImageButton go_back;
    private Button add_button;
    private EditText add_sum_insert, add_acc_insert, add_cat_insert, add_date_insert, add_comment_insert;
    private String type;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add_sum_insert = findViewById(R.id.add_sum_insert);
        add_acc_insert = findViewById(R.id.add_acc_insert);
        add_cat_insert = findViewById(R.id.add_cat_insert);
        add_date_insert = findViewById(R.id.add_date_insert);
        add_comment_insert = findViewById(R.id.add_comment_insert);
        go_back = findViewById(R.id.go_back_add);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );
        type = "add";
        add_button = findViewById(R.id.transaction_add);
        add_button.setOnClickListener(view -> saveTask());
    }

    private void saveTask() {
        final Integer sAcc = Integer.valueOf(add_acc_insert.getText().toString());
        final Integer sCat = Integer.valueOf(add_cat_insert.getText().toString());
        final String sSum = add_sum_insert.getText().toString();
        final String sDate = add_date_insert.getText().toString();
        final String sComment = add_comment_insert.getText().toString();

        if (sAcc.toString().isEmpty()){
            return;
        }

        if (sCat.toString().isEmpty()){
            return;
        }

        if (sSum.isEmpty()) {
            return;
        }

        if (sDate.isEmpty()) {
            return;
        }

        if (sComment.isEmpty()) {
            return;
        }
        new Thread(() -> {
            DBClient.getInstance(context).getAppDatabase()
                    .MainDao()
                    .insert(new Transactions(sAcc, sCat, type, sSum, sDate, sComment));
            }).start();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
    }
