package com.coffeetime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.model.Kopi;

import java.util.ArrayList;

public class MenuWarkopAdapter extends RecyclerView.Adapter<MenuWarkopAdapter.MenuKopiViewHolder> {

    private ArrayList<Kopi> dataList;

    public MenuWarkopAdapter(ArrayList<Kopi> dataList) {
        this.dataList = dataList;
    }

    @Override
    public MenuKopiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list_menu, parent, false);
        return new MenuKopiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuKopiViewHolder holder, int position) {
        //holder.id_kopi.setText(dataList.get(position).getId_kopi());
        holder.nama_kopi.setText(dataList.get(position).getNamaKopi());
        holder.jenis_kopi.setText(dataList.get(position).getJenisKopi());
        holder.harga_kopi.setText(dataList.get(position).getHargaKopi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MenuKopiViewHolder extends RecyclerView.ViewHolder{
        private TextView id_kopi, nama_kopi, jenis_kopi, harga_kopi;

        public MenuKopiViewHolder(View itemView) {
            super(itemView);
            //id_kopi = itemView.findViewById(R.id.txt_nama_mahasiswa);
            nama_kopi = itemView.findViewById(R.id.nama_kopi);
            jenis_kopi = itemView.findViewById(R.id.jenis_kopi);
            harga_kopi = itemView.findViewById(R.id.harga_kopi);
        }
    }

}
