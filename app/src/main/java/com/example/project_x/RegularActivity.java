package com.example.project_x;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.Adapters.RegularAdapter;
import com.example.project_x.Adapters.TransactionsAdapter;
import com.example.project_x.BD.DBClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RegularActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton go_back;
    private Button button_add;
    private RecyclerView RegularView;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regular_activity);
        go_back = findViewById(R.id.go_back_regular);
        button_add = findViewById(R.id.RegularAdd);
        button_add.setOnClickListener(
                v -> {
            Intent intent = new Intent(this, AddRegular.class);
            startActivity(intent);
        });
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );

        RegularView = findViewById(R.id.RecyclerViewRegular);
        RegularView.setLayoutManager(new LinearLayoutManager(this));
        Disposable disposable = DBClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .RegularDao()
                .getRegularTransactions()
                // поток интерфейса UI - наблюдает за изменениями Flowable данных
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(regularTransactions -> {
                    RegularAdapter adapter = new RegularAdapter(RegularActivity.this, regularTransactions);
                    RegularView.setAdapter(adapter);
                });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
