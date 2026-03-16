package com.dat.appbmi;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến tương ứng với ID trong XML
    private EditText edtHeight, edtWeight;
    private Button btnCalculate;
    private TextView txtResult, txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ (Mapping) các view từ XML vào Java
        initViews();

        // 2. Thiết lập bộ lắng nghe sự kiện (Listener) cho nút bấm
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void initViews() {
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.txtResult);
        txtStatus = findViewById(R.id.txtStatus);
    }

    private void calculateBMI() {
        String heightStr = edtHeight.getText().toString();
        String weightStr = edtWeight.getText().toString();

        // Kiểm tra dữ liệu đầu vào không được để trống
        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển đổi dữ liệu sang kiểu số
        float heightCm = Float.parseFloat(heightStr);
        float weightKg = Float.parseFloat(weightStr);

        // Đổi cm sang m để tính toán
        float heightM = heightCm / 100;
        float bmi = weightKg / (heightM * heightM);

        // Hiển thị kết quả (lấy 1 chữ số thập phân)
        txtResult.setText(String.format(Locale.getDefault(), "Kết quả BMI: %.1f", bmi));

        // Phân loại trạng thái sức khỏe
        String status;
        if (bmi < 18.5) {
            status = "Gầy";
        } else if (bmi < 24.9) {
            status = "Bình thường";
        } else if (bmi < 29.9) {
            status = "Tiền béo phì";
        } else {
            status = "Béo phì";
        }
        txtStatus.setText("Trạng thái: " + status);
    }
}