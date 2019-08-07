package com.coffeetime.client;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.adapter.ListRiwayatAdapter;
import com.coffeetime.model.Riwayat;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {
    TextView namakopi, jeniskopi, hargakopi;
    private RecyclerView recyclerView;
    private ListRiwayatAdapter adapter;
    private List<Riwayat> riwayatList;
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat_client, container, false);
        
        recyclerView = view.findViewById(R.id.recyclerviewriwayat);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        tampildata();
        return view;
    }

    private void tampildata() {
        riwayatList = new ArrayList<>();
        riwayatList.add(new Riwayat("Cappucino", "Sudah dikirim", "10 Juli, 08.00"));
        riwayatList.add(new Riwayat("Sanger", "Sudah diterima", "9 Agustus, 10.00"));
        riwayatList.add(new Riwayat("Arabica", "Sudah dikirim", "10 Juli, 11.00"));
    }
}
