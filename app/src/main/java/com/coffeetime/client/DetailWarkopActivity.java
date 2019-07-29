package com.coffeetime.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.adapter.MenuClientAdapter;
import com.coffeetime.model.Kopi;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWarkopActivity extends Activity {

    private Bundle bundle;
    TextView namawarkop, namapemilik, cpwarkop, alamatwarkop, waktubuka, txquantity, harga_kopi, harga_total, namakopi, jeniskopi;
    private RecyclerView recyclerView;
    private MenuClientAdapter adapter;
    private List<Kopi> kopiArrayList;
    int quantity = 0;

    Endpoints endpoints;
    Kopi kopi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_warkop);

        //warkop
        namawarkop = findViewById(R.id.nama_warkop);
        namapemilik = findViewById(R.id.nama_pemilik);
        cpwarkop = findViewById(R.id.cp_warkop);
        alamatwarkop = findViewById(R.id.alamat_warkop);
        waktubuka = findViewById(R.id.waktu_buka);

        //kopi
        namakopi = findViewById(R.id.txnama_kopi);
        jeniskopi = findViewById(R.id.txjenis_kopi);

        txquantity = findViewById(R.id.tx_quantity);
        harga_kopi = findViewById(R.id.txharga_kopi);
        harga_total = findViewById(R.id.txharga_total);

        recyclerView = findViewById(R.id.recyclerview);

        tampildata();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailWarkopActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        bundle = getIntent().getExtras();
    }

    void tampildata(){

        endpoints = Connection.getEndpoints(DetailWarkopActivity.this);
        endpoints.getKopi().enqueue(new Callback<List<Kopi>>() {
            @Override
            public void onResponse(Call<List<Kopi>> call, Response<List<Kopi>> response) {
                kopiArrayList = new ArrayList<>(response.body());
                adapter = new MenuClientAdapter(kopiArrayList);
                recyclerView.setAdapter(adapter);

                adapter.setOnQuantityChangedListener(new MenuClientAdapter.OnQuantityChangedListener() {
                    int totalakhir = 0;

                    @Override
                    public void onQuantityChanged(int total) {
                        totalakhir += total;
                        harga_total.setText(String.valueOf(totalakhir));
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Kopi>> call, Throwable t) {

            }
        });
    }

    public void Pesan(View view) {
        startActivity (new Intent(DetailWarkopActivity.this, PesanActivity.class));
    }

    /*public void tambah(View view) {
        int harga = Integer.parseInt(harga_kopi.getText().toString());
        int quantity = Integer.parseInt(txquantity.getText().toString());
        int total = harga*quantity;
        harga_total.setText(total+"");

        if (quantity==100){
            return;
        }
        quantity = quantity+1;
        txquantity.setText(""+quantity);
    }

    public void kurang(View view) {
        int harga = Integer.parseInt(harga_kopi.getText().toString());
        int quantity = Integer.parseInt(txquantity.getText().toString());
        int total = harga-quantity;
        harga_total.setText(total+"");

        if (quantity==1){
            return;
        }
        quantity = quantity - 1;
        txquantity.setText(quantity);
    }*/
}
