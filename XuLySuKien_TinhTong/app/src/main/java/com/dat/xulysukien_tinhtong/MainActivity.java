package com.dat.xulysukien_tinhtong;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Khai báo biến toàn cục để dùng chung trong toàn class
    EditText edtA, edtB, edtKQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Ánh xạ View một lần duy nhất khi ứng dụng khởi chạy
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Hàm xử lý khi nhấn nút "Tính Tổng" (đã được gán trong XML)
    public void XuLyCong(View view) {
        String a = edtA.getText().toString();
        String b = edtB.getText().toString();

        // 1. Kiểm tra nếu người dùng để trống ô nhập
        if (a.isEmpty() || b.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ số a và b", Toast.LENGTH_SHORT).show();
            edtKQ.setText("Chưa nhập đủ số");
            return;
        }

        try {
            // 2. Chuyển kiểu dữ liệu và tính toán
            int soA = Integer.parseInt(a);
            int soB = Integer.parseInt(b);
            int tong = soA + soB;

            // 3. Hiển thị kết quả
            edtKQ.setText(String.valueOf(tong));

        } catch (NumberFormatException e) {
            // Phòng trường hợp người dùng nhập số quá lớn vượt quá kiểu int
            edtKQ.setText("Số quá lớn hoặc không hợp lệ");
        }
    }
}