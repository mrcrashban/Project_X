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

import com.example.project_x.BD.Categories;
import com.example.project_x.BD.DBClient;

public class CategoriesDialogFragment extends DialogFragment {
    private Context context;
    public static final String TAG_DIALOG_PIECE_SAVE = "category_save";

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
        View view = getActivity().getLayoutInflater().inflate(R.layout.category_add_layout, null);
        final EditText categories = view.findViewById(R.id.category_add);

        alertDialogBuilder.setView(view)
                .setTitle(getString(R.string.add_cat_alert))
                .setPositiveButton(R.string.save, (dialog, which) -> saveCategory(categories.getText().toString()))
                .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.cancel());

        return alertDialogBuilder.create();
    }

    private void saveCategory(String category) {
        if (TextUtils.isEmpty(category)) {
            return;
        }

        new Thread(() -> {
            DBClient.getInstance(context).getAppDatabase()
                    .MainDao()
                    .insert_cat(new Categories(category));
        }).start();

    }

}
