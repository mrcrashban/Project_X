package com.example.project_x;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.Trans_Acc_Cat;
import com.example.project_x.BD.Transactions;

public class UpdateTransactionActivity extends AppCompatActivity {

    private EditText EditTextAcc, EditTextCat, EditTextSum, EditTextDate, EditTextComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);
    }
}
