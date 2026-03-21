package com.dat.danhsachdoibong;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewDoiBong;
    private DoiBongAdapter adapter;
    private List<DoiBong> danhSachDoiBong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewDoiBong = findViewById(R.id.listViewDoiBong);

        khoiTaoDuLieu();

        adapter = new DoiBongAdapter(this, danhSachDoiBong);
        listViewDoiBong.setAdapter(adapter);

        // Xử lý sự kiện OnItemClick
        listViewDoiBong.setOnItemClickListener((parent, view, position, id) -> {
            DoiBong doiBong = danhSachDoiBong.get(position);
            hienThiChiTiet(doiBong, position + 1);
        });
    }

    private void khoiTaoDuLieu() {
        danhSachDoiBong = new ArrayList<>();

        // Danh sách các đội bóng vô địch World Cup (từ gần nhất đến xa nhất)
        danhSachDoiBong.add(new DoiBong("Argentina",   "2022 - Qatar",          "🇦🇷", 3));
        danhSachDoiBong.add(new DoiBong("France",      "2018 - Russia",         "🇫🇷", 2));
        danhSachDoiBong.add(new DoiBong("Germany",     "2014 - Brazil",         "🇩🇪", 4));
        danhSachDoiBong.add(new DoiBong("Spain",       "2010 - South Africa",   "🇪🇸", 1));
        danhSachDoiBong.add(new DoiBong("Italy",       "2006 - Germany",        "🇮🇹", 4));
        danhSachDoiBong.add(new DoiBong("Brazil",      "2002 - South Korea/Japan","🇧🇷", 5));
        danhSachDoiBong.add(new DoiBong("France",      "1998 - France",         "🇫🇷", 2));
        danhSachDoiBong.add(new DoiBong("Brazil",      "1994 - United States",  "🇧🇷", 5));
        danhSachDoiBong.add(new DoiBong("Germany",     "1990 - Italy",          "🇩🇪", 4));
        danhSachDoiBong.add(new DoiBong("Argentina",   "1986 - Mexico",         "🇦🇷", 3));
    }

    private void hienThiChiTiet(DoiBong doiBong, int stt) {
        String thongTin =
                doiBong.getCo() + "  " + doiBong.getTenDoi() + "\n\n" +
                "🗓  Năm vô địch : " + doiBong.getNamVoDich() + "\n" +
                "🏆  Số lần vô địch: " + doiBong.getSoLanVoDich() + " lần\n" +
                "📋  Xếp hạng    : #" + stt + " (danh sách gần nhất)";

        new AlertDialog.Builder(this)
                .setTitle("Chi tiết đội bóng")
                .setMessage(thongTin)
                .setPositiveButton("Đóng", null)
                .show();

        Toast.makeText(this,
                "Bạn đã chọn: " + doiBong.getTenDoi(),
                Toast.LENGTH_SHORT).show();
    }
}