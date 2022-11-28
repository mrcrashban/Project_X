package com.example.project_x;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private Button btnGoAdd, btnGoSub, btnGoCat, btnGoAcc;

    private final String accounts_names[] = {
            "main",
            "cash",
            "main",
            "seco",
            "main"
    };
    private final String categories_names[] = {
            "products",
            "car",
            "house",
            "products",
            "sport"
    };
    private final String amounts[] = {
            "1000",
            "345",
            "415",
            "674",
            "500"
    };

    private final String comments[] = {
            "a lot",
            "washing",
            "vacuum",
            "",
            "buy personal train"
    };

    static class Block {
        private String accounts;
        private String categories;
        private String comments;
        private String amounts;

        public String getAccounts() {
            return accounts;
        }

        public void setAccounts(String accounts) {
            this.accounts = accounts;
        }

        public String getCategories() {
            return categories;
        }

        public void setCategories(String categories) { this.categories = categories; }

        public String getAmounts() { return amounts; }

        public void setAmounts(String amounts) { this.amounts = amounts; }

        public String getComments() { return comments; }

        public void setComments(String comments) { this.comments = comments; }

        public Block(String accounts, String categories, String amounts, String comments){
            this.accounts = accounts;
            this.categories = categories;
            this.amounts = amounts;
            this.comments = comments;
        }
    }

    private MyAdapter myAdapter;
    private TextView ResultView;

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

        RecyclerView recyclerView = findViewById(R.id.RecyclerView_main);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(genData());
        recyclerView.setAdapter(myAdapter);
        ResultView = findViewById(R.id.balance_view);
        genSum();

    }


    public void genSum() {
        int sum = 0;
        ArrayList<Block> list = new ArrayList<>();
        for(int i=0;i<amounts.length;i++){
            sum = sum + Integer.parseInt(amounts[i]);
        }
        ResultView.setText(String.valueOf(sum));
    }

    public ArrayList<Block> genData() {
        ArrayList<Block> list = new ArrayList<>();
        for(int i=0;i<accounts_names.length;i++) {
            list.add(new Block(accounts_names[i],categories_names[i],amounts[i],comments[i]));
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}