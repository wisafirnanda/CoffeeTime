package com.coffeetime.client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.coffeetime.LoginActivity;
import com.coffeetime.R;
import com.coffeetime.RegisterActivity;
import com.coffeetime.adapter.PesananClientAdapter;
import com.coffeetime.model.Kopi;
import com.coffeetime.model.PesananClient;
import com.coffeetime.model.User;
import com.coffeetime.model.Warkop;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pemesanan extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PesananClientAdapter adapter;
    private ArrayList<PesananClient> pesananClientArrayList;

    private TextView namawarkop, alamatwarkop, waktubuka, txquantity, harga_kopi, harga_pesanan, namakopi,
            jeniskopi,harga_antar,kode_unik,harga_total;

    int quantity = 0;

    Endpoints endpoints;
    Kopi kopi;
    Warkop warkop;
    User user;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Gson gson;

    List<Integer> totalitem = new ArrayList<>();

    private TextView alamat_antarText;
    String stxtaddresplace;

    // konstanta untuk mendeteksi hasil balikan dari place picker
    private int Place_Picker_Request = 1;
    private Place place_Picker;
    private static int MY_PERMISSION_FINE_LOCATION = 1;
    LatLng latLng ;
    float latitude;
    float longitude;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);

        //warkop
        namawarkop = findViewById(R.id.txWarkopPsnBiji);
        alamatwarkop = findViewById(R.id.txAlmtWrkpBiji);
        waktubuka = findViewById(R.id.txWktuBukaBiji);

        //kopi
        namakopi = findViewById(R.id.txNamaPesan_kopi);
        jeniskopi = findViewById(R.id.txJenisPesan_kopi);

        txquantity = findViewById(R.id.tx_quantityPesan);
        harga_kopi = findViewById(R.id.txHrgPesan);

        harga_pesanan = findViewById(R.id.harga_pesanan);
        harga_antar = findViewById(R.id.harga_antar);
        kode_unik = findViewById(R.id.kode_unik);
        harga_total = findViewById(R.id.harga_total);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String namaKopi =(String) b.get("namaKopi");
            namakopi.setText(namaKopi);

            String jenisKopi =(String) b.get("jenisKopi");
            jeniskopi.setText(jenisKopi);

            String qtyKopi =(String) b.get("qtyKopi");
            txquantity.setText(qtyKopi);

            String hrgaKopi =(String) b.get("hrgaKopi");
            harga_kopi.setText(hrgaKopi);

            String hrga_total =(String) b.get("hrga_total");
            harga_pesanan.setText(hrga_total);
        }

        int harga = Integer.parseInt(harga_pesanan.getText().toString());

        int kode = 121;
        int total = harga+kode;

        kode_unik.setText(String.valueOf(kode));

        harga_total.setText(String.valueOf(total));
    }

    private void bayar(View view){
//        user = new User();
//        user.setNama(namaText.getText().toString());
//        user.setEmail(emailText.getText().toString());
//        user.setNoHp(phoneText.getText().toString());
//        user.setPassword(passwordText.getText().toString());
//        user.setTipeUser(tipe_user);

        //request connection
        endpoints = Connection.getEndpoints(this);
        endpoints.aadUser(user).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    if (jsonObject.getString("status").equals("sukses")) {

                        startActivity(new Intent(Pemesanan.this, ListKopiBijiActivity.class));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });



    }
}
