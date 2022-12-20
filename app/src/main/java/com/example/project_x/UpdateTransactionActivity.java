package com.example.project_x;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Context context;
    private Button btn_update, btn_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_transaction);
        EditTextAcc = findViewById(R.id.EditTextUpdateAcc);
        EditTextCat = findViewById(R.id.EditTextUpdateCat);
        EditTextSum = findViewById(R.id.EditTextUpdateSum);
        EditTextDate = findViewById(R.id.EditTextUpdateDate);
        EditTextComment = findViewById(R.id.EditTextUpdateComment);

        final Transactions transactions = (Transactions) getIntent().getSerializableExtra("transactions");

        loadTransaction(transactions);
        btn_update = findViewById(R.id.transaction_update);
        btn_update.setOnClickListener(view -> {
            updateTransaction(transactions);
        });
        btn_delete = findViewById(R.id.transaction_delete);
        btn_delete.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateTransactionActivity.this);
            builder.setTitle(R.string.sure);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    deleteTransaction(transactions);
                    startActivity(new Intent(UpdateTransactionActivity.this, MainActivity.class));
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });

            AlertDialog ad = builder.create();
            ad.show();
        });


    }
    private void loadTransaction(Transactions transactions) {
        EditTextAcc.setText(transactions.getAccount());
        EditTextCat.setText(transactions.getCategory());
        EditTextSum.setText(transactions.getSum());
        EditTextDate.setText(transactions.getDate());
        EditTextComment.setText(transactions.getComment());
    }

    private void updateTransaction(Transactions transactions){
        final String sAcc = EditTextAcc.getText().toString();
        final String sCat = EditTextCat.getText().toString();
        final String sSum = EditTextSum.getText().toString();
        final String sDate = EditTextDate.getText().toString();
        final String sCom = EditTextComment.getText().toString();
        final String sTyp = transactions.getType();

        if (sAcc.isEmpty()) {
            EditTextAcc.setError(getResources().getString(R.string.required));
            EditTextAcc.requestFocus();
            return;
        }

        if (sCat.isEmpty()) {
            EditTextCat.setError(getResources().getString(R.string.required));
            EditTextCat.requestFocus();
            return;
        }

        if (sSum.isEmpty()) {
            EditTextSum.setError(getResources().getString(R.string.required));
            EditTextSum.requestFocus();
            return;
        }

        if (sDate.isEmpty()) {
            EditTextDate.setError(getResources().getString(R.string.required));
            EditTextSum.requestFocus();
            return;
        }


        new Thread(()->{
            transactions.setAccount(sAcc);
            transactions.setCategory(sCat);
            transactions.setSum(sSum);
            transactions.setType(sTyp);
            transactions.setDate(sDate);
            transactions.setComment(sCom);
            DBClient.getInstance(getApplicationContext()).getAppDatabase().MainDao().update(transactions);
        }).start();
        startActivity(new Intent(UpdateTransactionActivity.this, MainActivity.class));
    }

    private void deleteTransaction(Transactions transactions){
        new Thread(()->{
            DBClient.getInstance(getApplicationContext()).getAppDatabase().MainDao().delete(transactions);
        }).start();
    }
}
