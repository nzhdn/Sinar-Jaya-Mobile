package com.nzh.n4.sinarjaya.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.nzh.n4.sinarjaya.R;
import com.nzh.n4.sinarjaya.activities.DetailAgenActivity;
import com.nzh.n4.sinarjaya.helper.Constants;
import com.nzh.n4.sinarjaya.model.Agenbus;

import java.util.List;

public class AgenAdapter extends RecyclerView.Adapter<AgenAdapter.AgenbusViewHolder> {

    private Context context;
    private List<Agenbus> listAgenbus;

    public AgenAdapter(Context context, List<Agenbus> listAgenbus){
        this.context = context;
        this.listAgenbus = listAgenbus;
    }

    @Override
    public AgenAdapter.AgenbusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agen, parent, false);
        return new AgenbusViewHolder(itemView);
    }

    public class AgenbusViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemGambarAgen;
        TextView tvItemNamaAgen;
        TextView tvItemAlamatAgen;

        public AgenbusViewHolder(View itemView){
            super(itemView);
            ivItemGambarAgen = (ImageView) itemView
                    .findViewById(R.id.iv_item_gambar);
            tvItemNamaAgen = (TextView) itemView
                    .findViewById(R.id.tv_item_nama);
            tvItemAlamatAgen = (TextView) itemView
                    .findViewById(R.id.tv_item_alamat);

        }
    }
    @Override
    public void onBindViewHolder(AgenAdapter.AgenbusViewHolder holder, final int position){
        String linkGambar = listAgenbus.get(position).getGambarAgenbus();
        Glide.with(context)
                .load("https://drive.google.com/thumbnail?id=" + linkGambar)
                .into(holder. ivItemGambarAgen);

        holder.tvItemNamaAgen.setText(listAgenbus.get(position).getNamaAgenbus());
        holder.tvItemAlamatAgen.setText(listAgenbus.get(position).getAlamatAgenbus());
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public  void onClick(View v){
                //membuat objek bundle
                Bundle b = new Bundle();
                b.putString(Constants.NAMA_AGEN, listAgenbus.get(position).getNamaAgenbus());
                b.putString(Constants.GAMBAR_AGEN, listAgenbus.get(position).getGambarAgenbus());
                b.putString(Constants.ALAMAT_AGEN, listAgenbus.get(position).getAlamatAgenbus());
                b.putString(Constants.DESKRIPSI_AGEN, listAgenbus.get(position).getDeskripsiAgenbus());

                //mencetak Intent
                Intent i = new Intent(context, DetailAgenActivity.class);
                i.putExtras(b);
                context.startActivity(i);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listAgenbus.size();
    }
}