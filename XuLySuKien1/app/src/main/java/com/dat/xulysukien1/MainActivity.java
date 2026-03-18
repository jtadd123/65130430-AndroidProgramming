package com.dat.xulysukien1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editTextSo1, editTextSo2, editTextKQ;
    Button nutCong, nutTru, nutNhan, nutChia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Ánh xạ View
        editTextSo1 = findViewById(R.id.editTextSo1);
        editTextSo2 = findViewById(R.id.editTextSo2);
        editTextKQ = findViewById(R.id.editTextKQ);
        nutCong = findViewById(R.id.nutCong);
        nutTru = findViewById(R.id.nutTru);
        nutNhan = findViewById(R.id.nutNhan);
        nutChia = findViewById(R.id.nutChia);

        // Thiết lập sự kiện Click
        nutCong.setOnClickListener(v -> handleCalculation('+'));
        nutTru.setOnClickListener(v -> handleCalculation('-'));
        nutNhan.setOnClickListener(v -> handleCalculation('*'));
        nutChia.setOnClickListener(v -> handleCalculation('/'));
    }

    // Hàm xử lý chung để tránh lặp code
    private void handleCalculation(char operator) {
        String s1 = editTextSo1.getText().toString();
        String s2 = editTextSo2.getText().toString();

        // 1. Kiểm tra trống
        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ 2 số", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            // 2. Ép kiểu an toàn trong try-catch
            float num1 = Float.parseFloat(s1);
            float num2 = Float.parseFloat(s2);
            float result = 0;

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        editTextKQ.setText("Lỗi: Chia cho 0");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            // Hiển thị kết quả
            editTextKQ.setText(String.valueOf(result));

        } catch (NumberFormatException e) {
            // 3. Xử lý nếu người dùng nhập ký tự không hợp lệ
            editTextKQ.setText("Lỗi định dạng số");
        }
    }
}