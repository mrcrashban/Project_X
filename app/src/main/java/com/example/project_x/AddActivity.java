package com.example.project_x;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.Transactions;

import java.util.List;

public class AddActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Context context;
    private ImageButton go_back;
    private Button add_button;
    private EditText add_sum_insert, add_date_insert, add_comment_insert;
    private String type, add_acc_insert, add_cat_insert;
    private Spinner spinner_account, spinner_category;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        add_sum_insert = findViewById(R.id.add_sum_insert);
        add_date_insert = findViewById(R.id.add_date_insert_sub);
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
        spinner_account = findViewById(R.id.spinner_account);
        spinner_account.setOnItemSelectedListener(this);
        runThreadAcc();
        spinner_category = findViewById(R.id.spinner_category);
        spinner_category.setOnItemSelectedListener(this);
        runThreadCat();
    }

    private void saveTask() {
        final String sAcc = add_acc_insert;
        final String sCat = add_cat_insert;
        final String sSum = add_sum_insert.getText().toString();
        final String sDate = add_date_insert.getText().toString();
        final String sComment = add_comment_insert.getText().toString();

        if (sAcc.isEmpty()){
            return;
        }

        if (sCat.isEmpty()){
            return;
        }

        if (sSum.isEmpty()) {
            add_sum_insert.setError(getResources().getString(R.string.required));
            add_sum_insert.requestFocus();
            return;
        }

        if (sDate.isEmpty()) {
            add_date_insert.setError(getResources().getString(R.string.required));
            add_date_insert.requestFocus();
            return;
        }


        new Thread(() -> {
            DBClient.getInstance(context).getAppDatabase()
                    .MainDao()
                    .insert(new Transactions(sAcc, sCat, type, sSum, sDate, sComment));
            }).start();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }



    @SuppressLint("ResourceType")
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == 2131362358) {
            add_cat_insert = adapterView.getSelectedItem().toString();
        } else {
            add_acc_insert = adapterView.getSelectedItem().toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void runThreadAcc() {

        new Thread() {
            public void run() {
                int i = 0;
                List<String> list = DBClient
                            .getInstance(getApplicationContext())
                            .getAppDatabase()
                            .AccountsDao()
                            .getAccountsName();
                ArrayAdapter<String> adapter_acc = new ArrayAdapter<>(AddActivity.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner_account.setAdapter(adapter_acc);
            }
        }.start();
    }
    private void runThreadCat() {

        new Thread() {
            public void run() {
                int i = 0;
                List<String> list = DBClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .CategoriesDao()
                        .getCategoriesName();
                ArrayAdapter<String> adapter_cat = new ArrayAdapter<>(AddActivity.this,
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                spinner_category.setAdapter(adapter_cat);
            }
        }.start();
    }
}
