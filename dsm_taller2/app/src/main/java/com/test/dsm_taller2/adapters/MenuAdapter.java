package com.test.dsm_taller2.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.dsm_taller2.R;
import com.test.dsm_taller2.models.Food;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    ArrayList<Food> foods;

    public MenuAdapter(ArrayList<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.getFoodName().setText(food.getName());
        holder.getFoodUnitPrice().setText("$" + food.getUnitPrice() + " c/u");
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView foodName;
        private final TextView foodUnitPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            foodName = itemView.findViewById(R.id.foodName);
            foodUnitPrice = itemView.findViewById(R.id.foodUnitPrice);
        }

        public TextView getFoodName() {
            return foodName;
        }

        public TextView getFoodUnitPrice() {
            return foodUnitPrice;
        }
    }
}
