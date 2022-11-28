package com.example.project_x;

import static com.example.project_x.CategoriesDialogFragment.TAG_DIALOG_PIECE_SAVE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.project_x.BD.DBClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class CatActivity extends AppCompatActivity {

    private Button add_cat;
    private ImageButton go_back;
    private RecyclerView recyclerView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        recyclerView = findViewById(R.id.categories_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        go_back = findViewById(R.id.go_back_cat);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );
        add_cat = findViewById(R.id.add_category);
        add_cat.setOnClickListener(view -> showDialog());
        Disposable disposable = DBClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .CategoriesDao()
                .getCategories()
                // поток интерфейса UI - наблюдает за изменениями Flowable данных
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pieces -> {
                    CategoriesAdapter adapter = new CategoriesAdapter(CatActivity.this, pieces);
                    recyclerView.setAdapter(adapter);
                });
    }
    private void showDialog() {
        DialogFragment dialogFragment = new CategoriesDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), TAG_DIALOG_PIECE_SAVE);
    }
}