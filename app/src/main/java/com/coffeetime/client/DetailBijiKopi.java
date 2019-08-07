package com.coffeetime.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.adapter.MenuClientAdapter;
import com.coffeetime.model.Kopi;
import com.coffeetime.model.User;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Endpoints;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DetailBijiKopi extends AppCompatActivity {

    private List<Kopi> dataList;
    private Context context;

    private TextView namawarkop, alamatwarkop, waktubuka, txquantity, harga_kopi, harga_total, namakopi, jeniskopi;
    private RecyclerView recyclerView;
    private MenuClientAdapter adapter;
    private List<Warkop> warkopArrayList;

    private ImageView decrement, increment;
    private Button pesan;

    int quantity = 0;

    Endpoints endpoints;
    Kopi kopi;
    Warkop warkop;
    User user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    List<Integer> totalitem = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_biji_kopi);

        pesan=findViewById(R.id.btnPesanBiji);

        //warkop
        namawarkop = findViewById(R.id.txWarkopPsnBiji);
        alamatwarkop = findViewById(R.id.txAlmtWrkpBiji);
        waktubuka = findViewById(R.id.txWktuBukaBiji);

        //kopi
        namakopi = findViewById(R.id.txnama_kopi);
        jeniskopi = findViewById(R.id.txjenis_kopi);

        txquantity = findViewById(R.id.tx_quantityBiji);
        harga_kopi = findViewById(R.id.txHrgBiji);
        harga_total = findViewById(R.id.txharga_total);

        txquantity.setText(String.valueOf(quantity));
        decrement = findViewById(R.id.btn_decrement);
        increment = findViewById(R.id.btn_increment);

        sharedPreferences = DetailBijiKopi.this.getSharedPreferences("coffee",0);
        editor = sharedPreferences.edit();
        editor.apply();

        gson = new Gson();
        String json = sharedPreferences.getString("user","");

        user = gson.fromJson(json, new TypeToken<User>(){

        }.getType());

        kopi = (Kopi) getIntent().getSerializableExtra("kopi");

        tampildata();

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int harga = Integer.parseInt(harga_kopi.getText().toString());
                int quantity = Integer.parseInt(txquantity.getText().toString());

                //harga_total.setText(total+"");

                //int total = harga * quantity;

                quantity = quantity + 1;
                txquantity.setText(String.valueOf(quantity));

                int total = harga * quantity;

                harga_total.setText(String.valueOf(total));
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int harga_awal = Integer.parseInt(harga_kopi.getText().toString());
                int harga = harga_awal;
                int quantity = Integer.parseInt(txquantity.getText().toString());
                //int total = harga * quantity;

                //harga_total.setText(total+"");


                quantity = quantity - 1;
                if (quantity < 0) {
                    quantity = 0;
                }
                txquantity.setText(String.valueOf(quantity));

                int total = 0;

                if(harga==0) {
                    harga=0;
                    quantity=0;
                }else if(quantity==0) {
                    quantity=0;
                    harga=0;
                } else {

                    total=(harga-quantity)*quantity;
                }

                harga_total.setText(String.valueOf(total));
            }
        });

    }


    void tampildata() {

        namakopi.setText(kopi.getNamaKopi());
        jeniskopi.setText(kopi.getJenisKopi());
        harga_kopi.setText(kopi.getHargaKopi());

//        warkop = new Warkop();
//        warkop.setIdKopi(kopi.getIdKopi());
//        namawarkop.setText(warkop.getNamaWarkop());
//        alamatwarkop.setText(warkop.getAlamatWarkop());
//        waktubuka.setText(warkop.getWaktuBuka());

    }

    public void Pesan(View view) {

        String namaKopi = namakopi.getText().toString();
        String jenisKopi = jeniskopi.getText().toString();
        String qty = txquantity.getText().toString();
        String hrga = harga_kopi.getText().toString();
        String hrga_total = harga_total.getText().toString();

        Intent ii = new Intent(DetailBijiKopi.this, Pemesanan.class);
        ii.putExtra("namaKopi", namaKopi);
        ii.putExtra("jenisKopi", jenisKopi);
        ii.putExtra("qtyKopi", qty);
        ii.putExtra("hrgaKopi", hrga);
        ii.putExtra("hrga_total", hrga_total);
        startActivity(ii);
    }


}
