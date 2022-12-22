package com.example.project_x.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.BD.Categories;
import com.example.project_x.BD.DBClient;
import com.example.project_x.BD.RegularTransactions;
import com.example.project_x.R;

import java.util.List;

public class RegularAdapter extends RecyclerView.Adapter<RegularAdapter.RegularViewHolder> {

    private static Context context;
    private static List<RegularTransactions> RegularTransactionsList;

    public RegularAdapter(Context mCtx, List<RegularTransactions> RegularTransactionsList) {
        this.context = mCtx;
        this.RegularTransactionsList = RegularTransactionsList;
    }

    @NonNull
    @Override
    public RegularAdapter.RegularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.db_categories, parent, false);
        return new RegularAdapter.RegularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RegularAdapter.RegularViewHolder holder, int position) {
        RegularTransactions r = RegularTransactionsList.get(position);
        holder.textViewName.setText(r.getName());
        holder.textViewFrequency.setText(r.getFrequency());
        holder.textViewSum.setText(r.getSum());
        holder.textViewDate.setText(r.getTime());
        holder.textViewComment.setText(r.getComment());
    }

    @Override
    public int getItemCount() {
        return RegularTransactionsList.size();
    }

    static class RegularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewFrequency, textViewSum, textViewDate, textViewComment;

        public RegularViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewRegName);
            textViewFrequency = itemView.findViewById(R.id.textViewRegFreq);
            textViewSum = itemView.findViewById(R.id.textViewRegSum);
            textViewDate = itemView.findViewById(R.id.textViewRegOst);
            textViewComment = itemView.findViewById(R.id.textViewRegCom);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            new Thread(()->{
                RegularTransactions RegularTransactions = RegularTransactionsList.get(getAdapterPosition());
                DBClient.getInstance(context.getApplicationContext()).getAppDatabase().RegularDao().delete(RegularTransactions);
            }).start();
        }
    }
}
