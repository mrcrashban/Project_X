package com.example.project_x.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.Transactions;
import com.example.project_x.R;
import com.example.project_x.UpdateTransactionActivity;

import java.util.Arrays;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>{
    private static Context context;
    private static List<Transactions> TransactionsList;

    public TransactionsAdapter(Context mCtx, List<Transactions> TransactionsList) {
        this.context = mCtx;
        this.TransactionsList = TransactionsList;
    }

    @NonNull
    @Override
    public TransactionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.transactions_db, parent, false);
        return new TransactionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionsViewHolder holder, int position) {
        Transactions t = TransactionsList.get(position);
        holder.textViewAccount.setText(t.getAccount());
        holder.textViewCategory.setText(t.getCategory());
        holder.textViewType.setText(t.getType());
        holder.textViewSum.setText(t.getSum());
        holder.textViewDate.setText(t.getDate());
        holder.textViewComment.setText(t.getComment());

    }

    @Override
    public int getItemCount() {
        return TransactionsList.size();
    }

    static class TransactionsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewAccount, textViewCategory, textViewType, textViewSum, textViewDate, textViewComment, textViewSummary;

        public TransactionsViewHolder(View itemView) {
            super(itemView);

            textViewAccount = itemView.findViewById(R.id.textViewAccount);
            textViewCategory = itemView.findViewById(R.id.textViewCategories);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewSum = itemView.findViewById(R.id.textViewSum);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewComment = itemView.findViewById(R.id.textViewComment);
            textViewSummary = itemView.findViewById(R.id.textViewSummary);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            new Thread(()->{
            Transactions transactions = TransactionsList.get(getAdapterPosition());
            Intent intent = new Intent(context, UpdateTransactionActivity.class);
                intent.putExtra("transactions", transactions);
            context.startActivity(intent);
            }).start();
        }
    }


}


