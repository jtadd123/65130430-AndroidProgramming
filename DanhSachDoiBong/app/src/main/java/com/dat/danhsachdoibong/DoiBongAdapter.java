package com.dat.danhsachdoibong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class DoiBongAdapter extends ArrayAdapter<DoiBong> {

    private Context context;
    private List<DoiBong> danhSach;

    public DoiBongAdapter(Context context, List<DoiBong> danhSach) {
        super(context, R.layout.item_doi_bong, danhSach);
        this.context = context;
        this.danhSach = danhSach;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_doi_bong, parent, false);
        }

        DoiBong doiBong = danhSach.get(position);

        TextView tvStt     = convertView.findViewById(R.id.tvStt);
        TextView tvTenDoi  = convertView.findViewById(R.id.tvTenDoi);
        TextView tvNam     = convertView.findViewById(R.id.tvNamVoDich);
        TextView tvCo      = convertView.findViewById(R.id.tvCo);

        tvStt.setText(String.valueOf(position + 1));
        tvTenDoi.setText(doiBong.getTenDoi());
        tvNam.setText("🗓 " + doiBong.getNamVoDich());
        tvCo.setText(doiBong.getCo());

        return convertView;
    }
}
