package com.coffeetime.warkop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeetime.R;
import com.coffeetime.adapter.MenuWarkopAdapter;
import com.coffeetime.model.Kopi;
import com.coffeetime.networkmanager.Connection;
import com.coffeetime.networkmanager.Endpoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuFragment extends Fragment {

    private RecyclerView recyclerView;
    private MenuWarkopAdapter adapter;
    private ArrayList<Kopi> kopiArrayList;

    private FloatingActionButton tambah_menu;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText nama_kopi, harga_kopi;
    Spinner jenis_kopi;

    Endpoints endpoints;
    Kopi kopi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_warkop, container, false);

        tambah_menu = view.findViewById(R.id.tambah_menu);
        recyclerView = view.findViewById(R.id.recyclerview);

        adapter = new MenuWarkopAdapter(kopiArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        tambah_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogForm();
            }
        });

        return view;

    }

    private void DialogForm() {
        dialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_menu, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Form Tambah Menu");

        nama_kopi = dialogView.findViewById(R.id.nama_kopi);
        harga_kopi = dialogView.findViewById(R.id.harga_kopi);
        jenis_kopi = dialogView.findViewById(R.id.listJenisKopi);


        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                kopi = new Kopi();
                kopi.setNamaKopi(nama_kopi.getText().toString());
                kopi.setHargaKopi(harga_kopi.getText().toString());
                kopi.setJenisKopi(jenis_kopi.getSelectedItem().toString());

                endpoints = Connection.getEndpoints(getActivity());
                endpoints.aadKopi(kopi).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body().string());
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
                dialog.dismiss();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
