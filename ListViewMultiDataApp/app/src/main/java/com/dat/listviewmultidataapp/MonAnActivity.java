package com.dat.listviewmultidataapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MonAnActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mon_an);

		ListView lvMonAn = findViewById(R.id.lvMonAn);
		Button btnBackMonAn = findViewById(R.id.btnBackMonAn);
		String[] monAns = {
				"Phở bò",
				"Bún bò Huế",
				"Bánh mì",
				"Cơm tấm",
				"Bún chả",
				"Gỏi cuốn",
				"Chả cá Lã Vọng",
				"Bánh xèo"
		};

		ArrayAdapter<String> adapter = new ArrayAdapter<>(
				this,
				android.R.layout.simple_list_item_1,
				monAns
		);
		lvMonAn.setAdapter(adapter);

		btnBackMonAn.setOnClickListener(v -> finish());
	}
}
