package com.example.project_x;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewRow>{

    private ArrayList<MainActivity.Block> arrayList;

    public MyAdapter(ArrayList<MainActivity.Block> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewRow onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        return new ViewRow(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewRow holder, int position) {
        holder.accountsView.setText(arrayList.get(position).getAccounts());
        holder.categoriesView.setText(arrayList.get(position).getCategories());
        holder.amountView.setText(arrayList.get(position).getAmounts());
        holder.commentsView.setText(arrayList.get(position).getComments());
    }

    @Override
    public int getItemCount() { return arrayList.size(); }

    class ViewRow extends RecyclerView.ViewHolder {
        private TextView categoriesView;
        private TextView accountsView;
        private TextView amountView;
        private TextView commentsView;

        public ViewRow(@NonNull View itemView) {
            super(itemView);
            accountsView = itemView.findViewById(R.id.accounts_view);
            categoriesView = itemView.findViewById(R.id.categories_view);
            amountView = itemView.findViewById(R.id.amount_view);
            commentsView = itemView.findViewById(R.id.comments_view);
        }

    }
}
