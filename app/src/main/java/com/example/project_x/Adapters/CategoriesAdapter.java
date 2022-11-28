package com.example.project_x.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_x.BD.Categories;
import com.example.project_x.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    private final Context context;
    private final List<Categories> CategoriesList;

    public CategoriesAdapter(Context mCtx, List<Categories> CategoriesList) {
        this.context = mCtx;
        this.CategoriesList = CategoriesList;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.db_categories, parent, false);
        return new CategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoriesViewHolder holder, int position) {
        Categories p = CategoriesList.get(position);
        holder.textViewCategory.setText(p.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return CategoriesList.size();
    }

    static class CategoriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView  textViewCategory;

        public CategoriesViewHolder(View itemView) {
            super(itemView);
            textViewCategory = itemView.findViewById(R.id.textViewCategory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
}


