package com.test.dsm_taller2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.test.dsm_taller2.adapters.RestaurantAdapter;
import com.test.dsm_taller2.models.JSONRestaurantResponse;
import com.test.dsm_taller2.services.RestaurantService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    @Inject
    RestaurantService service;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.restaurantsRecyclerView);

        final JSONRestaurantResponse restaurants = getJsonData();
        loadRestaurantData(restaurants);
    }

    private JSONRestaurantResponse getJsonData() {
        String data = service.getJsonFromAssets(this);
        return service.getGsonInstance().fromJson(data, JSONRestaurantResponse.class);
    }

    private void loadRestaurantData(JSONRestaurantResponse restaurants) {
        RestaurantAdapter adapter = new RestaurantAdapter(restaurants.data, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
