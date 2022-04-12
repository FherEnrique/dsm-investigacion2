package com.test.dsm_taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.test.dsm_taller2.adapters.MenuAdapter;
import com.test.dsm_taller2.models.Restaurant;
import com.test.dsm_taller2.services.RestaurantService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FoodActivity extends AppCompatActivity {
    @Inject
    RestaurantService service;
    RecyclerView breakfastRecyclerView;
    RecyclerView lunchRecyclerView;
    RecyclerView dinnerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        breakfastRecyclerView = findViewById(R.id.breakfastRecyclerView);
        lunchRecyclerView = findViewById(R.id.lunchRecyclerView);
        dinnerRecyclerView = findViewById(R.id.dinnerRecyclerView);

        resolveAdapters(getRestaurantId());
    }

    private int getRestaurantId() {
        Intent intent = getIntent();
        return intent.getIntExtra("restaurantId", 0);
    }

    private void resolveAdapters(int restaurantId) {
        Restaurant restaurant = service.getRestaurantById(this, restaurantId);

        breakfastRecyclerView.setAdapter(new MenuAdapter(restaurant.getMenu().getBreakfast()));
        breakfastRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        lunchRecyclerView.setAdapter(new MenuAdapter(restaurant.getMenu().getLunch()));
        lunchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        dinnerRecyclerView.setAdapter(new MenuAdapter(restaurant.getMenu().getDinner()));
        dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}