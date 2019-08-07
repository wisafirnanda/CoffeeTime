package com.coffeetime.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.coffeetime.R;
import com.coffeetime.model.Kopi;

import java.util.List;

public class MenuClientAdapter extends RecyclerView.Adapter<MenuClientAdapter.MenuClientViewHolder> {

    private List<Kopi> dataList;
    private OnQuantityChangedListener listener;

    public MenuClientAdapter(List<Kopi> dataList) {
        this.dataList = dataList;
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public MenuClientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_list_menu_client, parent, false);
        return new MenuClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuClientAdapter.MenuClientViewHolder holder, int position) {
        holder.nama_kopi.setText(dataList.get(position).getNamaKopi());
        holder.jenis_kopi.setText(dataList.get(position).getJenisKopi());
        holder.harga_kopi.setText(dataList.get(position).getHargaKopi());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public interface OnQuantityChangedListener {
        void onQuantityChanged(int harga);
    }

    public class MenuClientViewHolder extends RecyclerView.ViewHolder {
        private TextView nama_kopi, jenis_kopi, harga_kopi, txquantity;
        private ImageView decrement, increment;
        private int quantity = 0;

        public MenuClientViewHolder(View itemView) {
            super(itemView);
            nama_kopi = itemView.findViewById(R.id.txnama_kopi);
            jenis_kopi = itemView.findViewById(R.id.txjenis_kopi);
            harga_kopi = itemView.findViewById(R.id.txharga_kopi);

            txquantity = itemView.findViewById(R.id.tx_quantity);
            decrement = itemView.findViewById(R.id.btn_decrement);
            increment = itemView.findViewById(R.id.btn_increment);

            increment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int harga = Integer.parseInt(harga_kopi.getText().toString());
                    int quantity = Integer.parseInt(txquantity.getText().toString());
                    //int total = harga * quantity;
                    //harga_total.setText(total+"");

                    //int total = harga * quantity;

                    listener.onQuantityChanged(harga);
                    quantity = quantity + 1;
                    txquantity.setText(String.valueOf(quantity));
                }
            });

            decrement.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int harga = Integer.parseInt(harga_kopi.getText().toString());
                    int quantity = Integer.parseInt(txquantity.getText().toString());
                    //int total = harga * quantity;

                    //harga_total.setText(total+"");

                    listener.onQuantityChanged(-harga);

                    quantity = quantity - 1;
                    if (quantity < 0) {
                        quantity = 0;
                    }
                    txquantity.setText(String.valueOf(quantity));
                }
            });
        }
    }
}
