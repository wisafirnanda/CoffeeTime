package com.coffeetime.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.coffeetime.R;
import com.coffeetime.adapter.ListWarkopAdapter;
import com.coffeetime.model.Warkop;

import java.util.ArrayList;

public class ListWarkopActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListWarkopAdapter adapter;
    private ArrayList<Warkop> warkopArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_warkop);

        //addData();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        adapter = new ListWarkopAdapter(warkopArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListWarkopActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

//    void addData(){
//        warkopArrayList = new ArrayList<>();
//        warkopArrayList.add(new Warkop("Cut Nun", "Pango Raya"));
//        warkopArrayList.add(new Warkop("Cut Ngoh", "Jl. Prof. Ali Hasyimi, Lamteh, Ulee Kareng"));
//        warkopArrayList.add(new Warkop("Cut Nun", "Pango Raya"));
//        warkopArrayList.add(new Warkop("Cut Ngoh", "Jl. Prof. Ali Hasyimi, Lamteh, Ulee Kareng"));
//    }

    public void detailWarkop(View view) {
        startActivity (new Intent(ListWarkopActivity.this, DetailWarkopActivity.class));
    }
}
