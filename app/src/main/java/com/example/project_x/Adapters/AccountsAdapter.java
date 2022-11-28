package com.example.project_x.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.BD.Accounts;
import com.example.project_x.BD.Categories;
import com.example.project_x.R;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder>{
    private final Context context;
    private final List<Accounts> AccountsList;

    public AccountsAdapter(Context mCtx, List<Accounts> AccountsList) {
        this.context = mCtx;
        this.AccountsList = AccountsList;
    }

    @NonNull
    @Override
    public AccountsAdapter.AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.db_accounts, parent, false);
        return new AccountsAdapter.AccountsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountsAdapter.AccountsViewHolder holder, int position) {
        Accounts a = AccountsList.get(position);
        holder.textViewAccount.setText(a.getAccount_name());
    }

    @Override
    public int getItemCount() {
        return AccountsList.size();
    }

    static class AccountsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewAccount;

        public AccountsViewHolder(View itemView) {
            super(itemView);
            textViewAccount = itemView.findViewById(R.id.textViewAccounts);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
