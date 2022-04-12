package com.test.dsm_taller2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.dsm_taller2.FoodActivity;
import com.test.dsm_taller2.R;
import com.test.dsm_taller2.models.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {
    ArrayList<Restaurant> restaurants;
    Context context;

    public RestaurantAdapter(ArrayList<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.restaurant_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.getRestaurantName().setText(restaurant.getName());
        holder.getRestaurantOwner().setText(restaurant.getOwner());
        Button visit = holder.getVisitButton();

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, FoodActivity.class);
                intent.putExtra("restaurantId", restaurant.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView restaurantName;
        private final TextView restaurantOwner;
        private final Button visitButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantOwner = itemView.findViewById(R.id.restaurantOwner);
            visitButton = itemView.findViewById(R.id.visitBtn);
        }

        public TextView getRestaurantName() {
            return restaurantName;
        }

        public TextView getRestaurantOwner() {
            return restaurantOwner;
        }

        public Button getVisitButton() {
            return visitButton;
        }
    }
}
