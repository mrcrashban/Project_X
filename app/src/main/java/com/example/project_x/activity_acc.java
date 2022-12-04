package com.example.project_x;

import static com.example.project_x.CategoriesDialogFragment.TAG_DIALOG_PIECE_SAVE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.project_x.Adapters.AccountsAdapter;
import com.example.project_x.Adapters.CategoriesAdapter;
import com.example.project_x.BD.DBClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class activity_acc extends AppCompatActivity {

    private ImageButton go_back;
    private Button add_acc;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);
        recyclerView = findViewById(R.id.accounts_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(
                v -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
        );
        add_acc = findViewById(R.id.account_add);
        add_acc.setOnClickListener(view -> showDialog());
        Disposable disposable = DBClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .AccountsDao()
                .getAccounts()
                // поток интерфейса UI - наблюдает за изменениями Flowable данных
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(accounts -> {
                    AccountsAdapter adapter = new AccountsAdapter(activity_acc.this, accounts);
                    recyclerView.setAdapter(adapter);
                });
    }
    private void showDialog() {
        DialogFragment dialogFragment = new AccountsDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), TAG_DIALOG_PIECE_SAVE);
    }
}
