package com.dat.docbaotonghop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ArrayBaiBaoAdapter extends RecyclerView.Adapter<ArrayBaiBaoAdapter.ItemBBViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(ItemBaiBao item);
    }

    private final Context context;
    private final ArrayList<ItemBaiBao> dsBaiBao;
    private OnItemClickListener listener;

    public ArrayBaiBaoAdapter(Context context, ArrayList<ItemBaiBao> dsBaiBao) {
        this.context = context;
        this.dsBaiBao = dsBaiBao;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemBBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_bao, parent, false);
        return new ItemBBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBBViewHolder holder, int position) {
        ItemBaiBao baiBao = dsBaiBao.get(position);

        holder.tvTieuDeBaiBao.setText(baiBao.getTieuDeBaiBao());
        holder.tvNgayDangBB.setText(baiBao.getThoiDiemDangTin());

        // Load ảnh bằng Glide
        Glide.with(context)
                .load(baiBao.getUrlAnhDaiDien())
                .placeholder(R.drawable.ic_launcher_foreground)
                .centerCrop()
                .into(holder.anhBB);

        // Xử lý click
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(baiBao);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dsBaiBao.size();
    }

    static final class ItemBBViewHolder extends RecyclerView.ViewHolder {
        ImageView anhBB;
        TextView tvTieuDeBaiBao;
        TextView tvNgayDangBB;

        public ItemBBViewHolder(@NonNull View itemView) {
            super(itemView);
            anhBB = itemView.findViewById(R.id.imvAnhDaiDien);
            tvTieuDeBaiBao = itemView.findViewById(R.id.tvTieuDe);
            tvNgayDangBB = itemView.findViewById(R.id.tvNgayDang);
        }
    }
}
