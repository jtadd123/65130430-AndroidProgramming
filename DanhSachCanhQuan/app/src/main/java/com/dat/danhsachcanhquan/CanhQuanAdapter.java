package com.dat.danhsachcanhquan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CanhQuanAdapter extends RecyclerView.Adapter<CanhQuanAdapter.CanhQuanViewHolder> {

    // Interface pattern (lambda-compatible) for click events
    public interface OnItemClickListener {
        void onItemClick(CanhQuan canhQuan);
    }

    private List<CanhQuan> danhSach;
    private OnItemClickListener listener;

    public CanhQuanAdapter(List<CanhQuan> danhSach, OnItemClickListener listener) {
        this.danhSach = danhSach;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CanhQuanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_canh_quan, parent, false);
        return new CanhQuanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CanhQuanViewHolder holder, int position) {
        CanhQuan canhQuan = danhSach.get(position);
        holder.imgCanhQuan.setImageResource(canhQuan.getHinhAnh());
        holder.tvTen.setText(canhQuan.getTen());
        holder.tvDiaDiem.setText(canhQuan.getDiaDiem());
        holder.tvMoTa.setText(canhQuan.getMoTa());

        // Lambda-compatible click handler via interface
        holder.itemView.setOnClickListener(v -> listener.onItemClick(canhQuan));
    }

    @Override
    public int getItemCount() {
        return danhSach.size();
    }

    // ViewHolder inner class
    public static class CanhQuanViewHolder extends RecyclerView.ViewHolder {
        ImageView imgCanhQuan;
        TextView tvTen;
        TextView tvDiaDiem;
        TextView tvMoTa;

        public CanhQuanViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCanhQuan = itemView.findViewById(R.id.imgCanhQuan);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvDiaDiem = itemView.findViewById(R.id.tvDiaDiem);
            tvMoTa = itemView.findViewById(R.id.tvMoTa);
        }
    }
}
