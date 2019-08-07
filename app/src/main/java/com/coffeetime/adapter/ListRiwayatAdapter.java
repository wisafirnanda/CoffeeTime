package com.coffeetime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.model.Riwayat;

import java.util.ArrayList;

public class ListRiwayatAdapter extends RecyclerView.Adapter<ListRiwayatAdapter.ListRiwayatViewHolder> {
    private ArrayList<Riwayat> dataList;

    public ListRiwayatAdapter(ArrayList<Riwayat> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ListRiwayatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list_riwayat, parent, false);
        return new ListRiwayatViewHolder (view);
    }

    @Override
    public void onBindViewHolder(ListRiwayatViewHolder holder, int position) {
        holder.namakopi.setText(dataList.get(position).getNamakopi());
        holder.status.setText(dataList.get(position).getStatus());
        holder.waktu.setText(dataList.get(position).getWaktu());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListRiwayatViewHolder extends RecyclerView.ViewHolder{
        private TextView namakopi, status, waktu ;

        public ListRiwayatViewHolder(View itemView) {
            super(itemView);
            namakopi = itemView.findViewById(R.id.txnamakopi);
            status = itemView.findViewById(R.id.txstatus);
            waktu = itemView.findViewById(R.id.txwaktu);
        }
    }
}
