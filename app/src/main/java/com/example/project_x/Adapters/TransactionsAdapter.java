package com.example.project_x.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.BD.Trans_Acc_Cat;
import com.example.project_x.R;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder>{
    private final Context context;
    private final List<Trans_Acc_Cat> TransactionsList;

    public TransactionsAdapter(Context mCtx, List<Trans_Acc_Cat> TransactionsList) {
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
        Trans_Acc_Cat t = TransactionsList.get(position);
        holder.textViewAccount.setText(t.getAccount().toString());
        holder.textViewCategory.setText(t.getCategory().toString());
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

        TextView textViewAccount, textViewCategory, textViewType, textViewSum, textViewDate, textViewComment;

        public TransactionsViewHolder(View itemView) {
            super(itemView);

            textViewAccount = itemView.findViewById(R.id.textViewAccount);
            textViewCategory = itemView.findViewById(R.id.textViewCategories);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewSum = itemView.findViewById(R.id.textViewSum);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewComment = itemView.findViewById(R.id.textViewComment);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }

    }

}

