package com.example.project_x;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.project_x.BD.Accounts;
import com.example.project_x.BD.Categories;
import com.example.project_x.BD.DBClient;

public class AccountsDialogFragment extends DialogFragment {
    private Context context;
    public static final String TAG_DIALOG_ACCOUNT_SAVE = "account_save";

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.account_add_layout, null);
        final EditText accounts = view.findViewById(R.id.account_add);

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.add_acc_alert))
                .setPositiveButton(R.string.save, (dialog, which) -> saveAccount(accounts.getText().toString()))
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        return alertDialogBuilder.create();
    }

    private void saveAccount(String account) {
        if (TextUtils.isEmpty(account)) {
            return;
        }

        new Thread(() -> {
            DBClient.getInstance(context).getAppDatabase()
                    .MainDao()
                    .insert_acc(new Accounts(account));
        }).start();

    }

}
