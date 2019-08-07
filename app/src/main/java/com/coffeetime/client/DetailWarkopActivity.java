package com.coffeetime.client;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.adapter.MenuClientAdapter;
import com.coffeetime.model.Kopi;
import com.coffeetime.model.User;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailWarkopActivity extends Activity {

    TextView namawarkop, alamatwarkop, waktubuka, txquantity, harga_kopi, harga_total, namakopi, jeniskopi;
    private RecyclerView recyclerView;
    private MenuClientAdapter adapter;
    private List<Kopi> kopiArrayList;
    int quantity = 0;

    Endpoints endpoints;
    Kopi kopi;
    Warkop warkop;
    User user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;
    //Warkop warkop;
    List<Integer> totalitem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_warkop);

        //warkop
        namawarkop = findViewById(R.id.txnamawarkop);
        alamatwarkop = findViewById(R.id.txalamatwarkop);
        waktubuka = findViewById(R.id.txwaktubuka);

        //kopi
        namakopi = findViewById(R.id.txnama_kopi);
        jeniskopi = findViewById(R.id.txjenis_kopi);

        txquantity = findViewById(R.id.tx_quantity);
        harga_kopi = findViewById(R.id.txharga_kopi);
        harga_total = findViewById(R.id.txharga_total);

        recyclerView = findViewById(R.id.recyclerviewmenuclient);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailWarkopActivity.this);
        recyclerView.setLayoutManager(layoutManager);

        sharedPreferences = DetailWarkopActivity.this.getSharedPreferences("coffee",0);
        editor = sharedPreferences.edit();
        editor.apply();

        gson = new Gson();
        String json = sharedPreferences.getString("user","");

        user = gson.fromJson(json, new TypeToken<User>(){

        }.getType());

        warkop = (Warkop) getIntent().getSerializableExtra("warkop");


        tampildata();
    }

    void tampildata() {
        kopi = new Kopi();
        kopi.setIdWarkop(warkop.getIdWarkop());

        namawarkop.setText(warkop.getNamaWarkop());
        alamatwarkop.setText(warkop.getAlamatWarkop());
        waktubuka.setText(warkop.getWaktuBuka());

        endpoints = Connection.getEndpoints(DetailWarkopActivity.this);
        endpoints.getKopi(kopi).enqueue(new Callback<List<Kopi>>() {
            @Override
            public void onResponse(Call<List<Kopi>> call, Response<List<Kopi>> response) {
                if (response.body() != null) {
                    kopiArrayList = new ArrayList<>(response.body());
                    adapter = new MenuClientAdapter(kopiArrayList);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnQuantityChangedListener(new MenuClientAdapter.OnQuantityChangedListener() {
                        /*int totalakhir = 0;
                        List<Integer> totalitem = new ArrayList<>();*/

                        @Override
                        public void onQuantityChanged(int harga) {
                            /*int total1=total;
                            totalakhir += total1;*/

                            totalitem.add(harga);
                            Log.i("totalitem",""+totalitem);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Kopi>> call, Throwable t) {

            }
        });
    }

    public void Pesan(View view) {
        startActivity(new Intent(DetailWarkopActivity.this, PesanActivity.class));
    }

    public void Hitung(View view) {
        int totalakhir = 0;

        for (int i = 0; i < totalitem.size(); i++) {
            totalakhir += totalitem.get(i);

        }

        //Log.i("totalakhir", ""+totalakhir);

        harga_total.setText(String.valueOf(totalakhir));
    }
}
