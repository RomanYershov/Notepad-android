package com.example.notepad.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notepad.R;
import com.example.notepad.models.Category;

import java.util.List;


public class CategoriesViewAdapter extends RecyclerView.Adapter<CategoriesViewAdapter.ViewHolderCategory> {

    private List<Category> categories;

    public CategoriesViewAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public ViewHolderCategory onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.categories, viewGroup, false);
        return new ViewHolderCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCategory viewHolderCategory, int i) {
        Category category = categories.get(i);
        viewHolderCategory.categoryName.setText(category.getName());
        viewHolderCategory.categoryName.setTag(category.getId());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class ViewHolderCategory extends RecyclerView.ViewHolder {
        private TextView categoryName;

        public ViewHolderCategory(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name_tv);
        }
    }
}
