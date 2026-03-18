package com.dat.listviewmultidataapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các nút
        Button btnVatLieu = findViewById(R.id.btn_vat_lieu);
        Button btnMonAn = findViewById(R.id.btn_mon_an);
        Button btnTinh = findViewById(R.id.btn_tinh);

        // Xử lý chuyển màn hình
        btnVatLieu.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, VatLieuActivity.class));
        });

        btnMonAn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MonAnActivity.class));
        });

        btnTinh.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TinhActivity.class));
        });
    }
}