package com.coffeetime.client;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.coffeetime.R;
import com.coffeetime.adapter.ListWarkopAdapter;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListWarkopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListWarkopAdapter adapter;
    private List<Warkop> warkopArrayList;

    Endpoints endpoints;
    Warkop warkop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_warkop);

        tampildata();

        recyclerView = findViewById(R.id.recyclerview);
        //adapter = new ListWarkopAdapter(warkopArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListWarkopActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        //recyclerView.setAdapter(adapter);
    }

    void tampildata() {
        endpoints = Connection.getEndpoints(ListWarkopActivity.this);
        endpoints.getWarkops().enqueue(new Callback<List<Warkop>>() {
            @Override
            public void onResponse(Call<List<Warkop>> call, Response<List<Warkop>> response) {
                warkopArrayList = new ArrayList<>(response.body());
                adapter = new ListWarkopAdapter(ListWarkopActivity.this,warkopArrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Warkop>> call, Throwable t) {

            }
        });
    }
}
