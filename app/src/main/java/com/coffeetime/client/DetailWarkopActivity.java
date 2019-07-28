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

import java.util.ArrayList;

public class DetailWarkopActivity extends Activity {

    private Bundle bundle;
    TextView namawarkop, namapemilik, cpwarkop, alamatwarkop, waktubuka, menu;

    private RecyclerView recyclerView;
    private MenuClientAdapter adapter;
    private ArrayList<Kopi> kopiArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_warkop);

        namawarkop = findViewById(R.id.nama_warkop);
        namapemilik = findViewById(R.id.nama_pemilik);
        cpwarkop = findViewById(R.id.cp_warkop);
        alamatwarkop = findViewById(R.id.alamat_warkop);
        waktubuka = findViewById(R.id.waktu_buka);
        //menu = findViewById(R.id.menu);

        recyclerView = findViewById(R.id.recyclerview);

        //addData();

        adapter = new MenuClientAdapter(kopiArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailWarkopActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        bundle = getIntent().getExtras();
        //reference = FirebaseDatabase.getInstance().getReference().child("warkop");

        /*if(bundle!=null)
        {
            String key = bundle.getString("warkop_key");

            reference.orderByKey().equalTo(key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    for (DataSnapshot data : dataSnapshot.getChildren())
                    {
                        namawarkop.setText(data.child("nama_warkop").getValue().toString());
                        //namapemilik.setText(data.child("nama_pemilik").getValue().toString());
                        //cpwarkop.setText(data.child("cp_warkop").getValue().toString());
                        alamatwarkop.setText(data.child("alamat_warkop").getValue().toString());
                        waktubuka.setText(data.child("waktu_buka").getValue().toString());
                        //menu.setText(data.child("menu").getValue().toString());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }*/
    }

//    void addData(){
//        kopiArrayList = new ArrayList<>();
//        kopiArrayList.add(new Kopi("Sanger", "Kopi jadi", "Rp. 5.000"));
//        kopiArrayList.add(new Kopi("Arabica Gayo", "Bubuk kopi", "Rp. 30.000"));
//        kopiArrayList.add(new Kopi("Espresso", "Kopi jadi", "Rp. 10.000"));
//        kopiArrayList.add(new Kopi("Sanger", "Kopi jadi", "Rp. 5.000"));
//        kopiArrayList.add(new Kopi("Arabica Gayo", "Bubuk kopi", "Rp. 30.000"));
//        kopiArrayList.add(new Kopi("Espresso", "Kopi jadi", "Rp. 10.000"));
//    }

    public void Pesan(View view) {
        startActivity (new Intent(DetailWarkopActivity.this, PesanActivity.class));
    }
}
