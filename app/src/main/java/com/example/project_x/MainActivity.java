package com.example.project_x;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_x.Adapters.TransactionsAdapter;
import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.Transactions;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Button btnGoAdd, btnGoSub, btnGoCat, btnGoAcc;
    private RecyclerView TransactionsView;
    private int i, sum;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        btnGoAdd = findViewById(R.id.button_go_to_add);
        btnGoSub = findViewById(R.id.button_go_to_sub);
        btnGoCat = findViewById(R.id.button_go_to_cat);
        btnGoAcc = findViewById(R.id.button_go_to_acc);
        textView = findViewById(R.id.textViewSummary);
        btnGoAdd.setOnClickListener(
                v -> {
                    Intent intent = new Intent("com.example.project_x.AddActivity");
                    startActivity(intent);
                }
        );
        btnGoSub.setOnClickListener(
                v -> {
                    Intent intent = new Intent("com.example.project_x.SubActivity");
                    startActivity(intent);
                }
        );
        btnGoCat = findViewById(R.id.button_go_to_cat);
        btnGoCat.setOnClickListener(
                v -> {
                    Intent intent = new Intent("com.example.project_x.CatActivity");
                    startActivity(intent);
                }
        );
        btnGoAcc.setOnClickListener(
                v -> {
                    Intent intent = new Intent("com.example.project_x.activity_acc");
                    startActivity(intent);
                }
        );

        TransactionsView = findViewById(R.id.RecyclerView_main);
        TransactionsView.setLayoutManager(new LinearLayoutManager(this));
        Disposable disposable = DBClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .MainDao()
                .getAll()
                // поток интерфейса UI - наблюдает за изменениями Flowable данных
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(transactions -> {
                    TransactionsAdapter adapter = new TransactionsAdapter(MainActivity.this, transactions);
                    TransactionsView.setAdapter(adapter);
                });
        runThread();
    }

    private void runThread() {

        new Thread() {
            public void run() {
                sum = 0;
                while (i++ < 5) {
                    runOnUiThread(() -> {
                        sum = sum + 1;
                        textView.setText(String.valueOf(sum));
                    });
                }
            }
        }.start();
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}