package com.test.dsm_taller2.services;

import android.content.Context;

import com.google.gson.Gson;
import com.test.dsm_taller2.models.JSONRestaurantResponse;
import com.test.dsm_taller2.models.Restaurant;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RestaurantService {
    private final Gson gson = new Gson();

    @Inject RestaurantService() {}

    @Provides
    public String getJsonFromAssets(Context context) {
        try {
            InputStream is = context.getAssets().open("restaurants.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Provides
    public Restaurant getRestaurantById(Context context, int restaurantId) {
        Restaurant restaurant = null;
        String data = getJsonFromAssets(context);
        JSONRestaurantResponse response = getGsonInstance().fromJson(data, JSONRestaurantResponse.class);
        ArrayList<Restaurant> restaurants = response.data;

        for (Restaurant current : restaurants) {
            if (current.getId() == restaurantId) {
                restaurant = current;
            }
        }

        return restaurant;
    }

    @Provides
    public Gson getGsonInstance() {
        return gson;
    }
}
