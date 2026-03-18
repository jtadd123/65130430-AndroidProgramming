package com.dat.listviewmultidataapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class VatLieuActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vat_lieu);

		ListView lvVatLieu = findViewById(R.id.lvVatLieu);
		Button btnBackVatLieu = findViewById(R.id.btnBackVatLieu);
		String[] vatLieus = {
				"Xi măng",
				"Cát xây",
				"Đá 1x2",
				"Thép cuộn",
				"Gạch ống",
				"Sơn nước",
				"Ống nhựa PVC",
				"Tôn lạnh"
		};

		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				vatLieus
		);
		lvVatLieu.setAdapter(adapter);

		btnBackVatLieu.setOnClickListener(v -> finish());
	}
}
