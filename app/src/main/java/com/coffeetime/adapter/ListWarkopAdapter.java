package com.coffeetime.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.model.Warkop;

import java.util.ArrayList;

public class ListWarkopAdapter extends RecyclerView.Adapter<ListWarkopAdapter.ListWarkopViewHolder>{

    private ArrayList<Warkop> dataList;

    public ListWarkopAdapter(ArrayList<Warkop> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ListWarkopAdapter.ListWarkopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list_warkop, parent, false);
        return new ListWarkopAdapter.ListWarkopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListWarkopAdapter.ListWarkopViewHolder holder, int position) {
        //holder.id_kopi.setText(dataList.get(position).getId_kopi());
        //holder.nama_user.setText(dataList.get(position).getNama_user());
        holder.nama_warkop.setText(dataList.get(position).getNamaWarkop());
        holder.alamat_warkop.setText(dataList.get(position).getAlamatWarkop());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListWarkopViewHolder extends RecyclerView.ViewHolder{
        private TextView nama_warkop, alamat_warkop;

        public ListWarkopViewHolder(View itemView) {
            super(itemView);
            //id_kopi = itemView.findViewById(R.id.id_kopi);
            nama_warkop = itemView.findViewById(R.id.nama_warkop);
            alamat_warkop = itemView.findViewById(R.id.alamat_warkop);
        }
    }
}

