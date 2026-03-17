package com.dat.averagescoreapp;

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

        // Xử lý Insets (tràn viền) khớp với code mẫu của bạn
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. Ánh xạ các thành phần giao diện
        TextInputEditText edtMath = findViewById(R.id.edt_math);
        TextInputEditText edtPhysics = findViewById(R.id.edt_physics);
        TextInputEditText edtChemistry = findViewById(R.id.edt_chemistry);
        Button btnCalc = findViewById(R.id.btn_calc);
        Button btnReset = findViewById(R.id.btn_reset);
        MaterialCardView cardResult = findViewById(R.id.card_result);
        TextView txtResult = findViewById(R.id.txt_result);

        // 2. Xử lý nút Tính kết quả
        btnCalc.setOnClickListener(v -> {
            // Lấy chuỗi từ EditText
            String sMath = edtMath.getText().toString().trim();
            String sPhys = edtPhysics.getText().toString().trim();
            String sChem = edtChemistry.getText().toString().trim();

            // Kiểm tra rỗng
            if (sMath.isEmpty() || sPhys.isEmpty() || sChem.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ điểm!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Chuyển sang số thực (Hỗ trợ số thập phân)
                double math = Double.parseDouble(sMath);
                double phys = Double.parseDouble(sPhys);
                double chem = Double.parseDouble(sChem);

                // KIỂM TRA LOGIC ĐIỂM (0 - 10)
                if (math < 0 || math > 10) {
                    edtMath.setError("Điểm phải từ 0 - 10");
                    edtMath.requestFocus();
                    return;
                }
                if (phys < 0 || phys > 10) {
                    edtPhysics.setError("Điểm phải từ 0 - 10");
                    edtPhysics.requestFocus();
                    return;
                }
                if (chem < 0 || chem > 10) {
                    edtChemistry.setError("Điểm phải từ 0 - 10");
                    edtChemistry.requestFocus();
                    return;
                }

                // Xóa các lỗi cũ nếu có
                edtMath.setError(null);
                edtPhysics.setError(null);
                edtChemistry.setError(null);

                // Tính toán
                double average = (math + phys + chem) / 3;

                // Xếp loại
                String rank;
                if (average >= 8.0) rank = "Giỏi";
                else if (average >= 6.5) rank = "Khá";
                else if (average >= 5.0) rank = "Trung bình";
                else rank = "Yếu";

                // Hiển thị kết quả (Làm tròn 2 chữ số thập phân)
                txtResult.setText(String.format(Locale.getDefault(),
                        "Điểm trung bình: %.2f\nXếp loại: %s", average, rank));
                cardResult.setVisibility(View.VISIBLE);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng chỉ nhập số!", Toast.LENGTH_SHORT).show();
            }
        });

        // 3. Xử lý nút Nhập mới
        btnReset.setOnClickListener(v -> {
            edtMath.setText("");
            edtPhysics.setText("");
            edtChemistry.setText("");

            // Xóa dấu báo lỗi
            edtMath.setError(null);
            edtPhysics.setError(null);
            edtChemistry.setError(null);

            cardResult.setVisibility(View.GONE);
            edtMath.requestFocus();
        });
    }
}