package com.coffeetime.warkop;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeetime.R;
import com.coffeetime.adapter.PesananWarkopAdapter;
import com.coffeetime.model.PesananWarkop;

import java.util.ArrayList;

public class PesananFragment extends Fragment {

    private RecyclerView recyclerView;
    private PesananWarkopAdapter adapter;
    private ArrayList<PesananWarkop> pesananWarkopArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pesanan_warkop, container, false);

        recyclerView = view.findViewById(R.id.recyclerview);

        addData();

        adapter = new PesananWarkopAdapter(pesananWarkopArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    void addData(){
        pesananWarkopArrayList = new ArrayList<>();
        pesananWarkopArrayList.add(new PesananWarkop("Wisa", "Sanger", "Kopi jadi", "x 2", "10.000"));
        pesananWarkopArrayList.add(new PesananWarkop("Nanda", "Arabica Gayo", "Bubuk", "x 1", "30.000"));
    }
}
