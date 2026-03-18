package com.dat.listviewmultidataapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class TinhActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tinh);

		ListView lvTinh = findViewById(R.id.lvTinh);
		Button btnBackTinh = findViewById(R.id.btnBackTinh);
		String[] tinhs = {
				"Hà Nội",
				"Hải Phòng",
				"Đà Nẵng",
				"Khánh Hòa",
				"TP Ho Chi Minh",
				"Cần Thơ",
				"Đắk Lắk",
				"Lâm Đồng"
		};

		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				tinhs
		);
		lvTinh.setAdapter(adapter);

		btnBackTinh.setOnClickListener(v -> finish());
	}
}
