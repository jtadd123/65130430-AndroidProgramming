package com.dat.appgiaiptbac2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.card.MaterialCardView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

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

        // 1. Ánh xạ (Mapping) các view từ XML
        EditText edtA = findViewById(R.id.edt_a_coef);
        EditText edtB = findViewById(R.id.edt_b_coef);
        EditText edtC = findViewById(R.id.edt_c_coef);
        Button btnSolve = findViewById(R.id.btn_solve_equation);
        Button btnReset = findViewById(R.id.btn_reset_equation); // Nút NHẬP MỚI

        MaterialCardView cardResult = findViewById(R.id.card_result_section);
        TextView txtSolution = findViewById(R.id.txt_main_solution);
        TextView txtDelta = findViewById(R.id.txt_delta_status);

        // 2. Thiết lập bộ lắng nghe sự kiện cho nút bấm "GIẢI PHƯƠNG TRÌNH"
        btnSolve.setOnClickListener(v -> {
            String sA = edtA.getText().toString();
            String sB = edtB.getText().toString();
            String sC = edtC.getText().toString();

            // Kiểm tra dữ liệu đầu vào không được để trống
            if (sA.isEmpty() || sB.isEmpty() || sC.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ hệ số a, b, c!", Toast.LENGTH_SHORT).show();
                cardResult.setVisibility(View.GONE); // Ẩn phần kết quả cũ
                return;
            }

            double a = Double.parseDouble(sA);
            double b = Double.parseDouble(sB);
            double c = Double.parseDouble(sC);

            cardResult.setVisibility(View.VISIBLE); // Hiện phần kết quả

            // 3. Xử lý logic giải phương trình
            if (a == 0) {
                if (b == 0) {
                    txtSolution.setText(c == 0 ? "Vô số nghiệm" : "Vô nghiệm");
                    txtDelta.setText(""); // Phương trình bậc nhất, không có delta
                } else {
                    double x = -c / b;
                    txtSolution.setText(String.format(Locale.getDefault(), "Nghiệm: x = %.4f", x));
                    txtDelta.setText("Đây là phương trình bậc nhất.");
                }
            } else {
                double delta = b * b - 4 * a * c;
                txtDelta.setText(String.format(Locale.getDefault(), "Delta: %.4f", delta));

                if (delta < 0) {
                    txtSolution.setText("Vô nghiệm");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    txtSolution.setText(String.format(Locale.getDefault(), "Nghiệm kép: x = %.4f", x));
                } else {
                    double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                    double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                    txtSolution.setText(String.format(Locale.getDefault(), "x1 = %.4f\nx2 = %.4f", x1, x2));
                }
            }
        });

        // 4. Thiết lập bộ lắng nghe sự kiện cho nút bấm "NHẬP MỚI" (Reset)
        btnReset.setOnClickListener(v -> {
            // Xóa nội dung trong các ô EditText
            edtA.setText("");
            edtB.setText("");
            edtC.setText("");

            // Ẩn phần kết quả
            cardResult.setVisibility(View.GONE);

            // Thông báo
            Toast.makeText(this, "Đã làm mới, vui lòng nhập phương trình mới.", Toast.LENGTH_SHORT).show();
        });
    }
}