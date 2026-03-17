package com.dat.temperatureconverterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Xử lý tràn viền (Insets)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- ÁNH XẠ VIEW (Cần khớp ID với XML) ---
        TextInputEditText edtTemp = findViewById(R.id.edt_temp);
        Button btnCtoF = findViewById(R.id.btn_c_to_f);
        Button btnFtoC = findViewById(R.id.btn_f_to_c);
        Button btnReset = findViewById(R.id.btn_reset);
        MaterialCardView cardResult = findViewById(R.id.card_result);
        TextView txtResult = findViewById(R.id.txt_result);

        // Tính C sang F
        btnCtoF.setOnClickListener(v -> {
            String input = edtTemp.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Nhập nhiệt độ!", Toast.LENGTH_SHORT).show();
                return;
            }
            double c = Double.parseDouble(input);
            double f = (c * 9/5) + 32;
            txtResult.setText(String.format(Locale.getDefault(), "%.2f °C = %.2f °F", c, f));
            cardResult.setVisibility(View.VISIBLE);
        });

        // Tính F sang C
        btnFtoC.setOnClickListener(v -> {
            String input = edtTemp.getText().toString();
            if (input.isEmpty()) {
                Toast.makeText(this, "Nhập nhiệt độ!", Toast.LENGTH_SHORT).show();
                return;
            }
            double f = Double.parseDouble(input);
            double c = (f - 32) * 5/9;
            txtResult.setText(String.format(Locale.getDefault(), "%.2f °F = %.2f °C", f, c));
            cardResult.setVisibility(View.VISIBLE);
        });

        // Nút Nhập mới
        btnReset.setOnClickListener(v -> {
            edtTemp.setText("");
            cardResult.setVisibility(View.GONE);
        });
    }
}