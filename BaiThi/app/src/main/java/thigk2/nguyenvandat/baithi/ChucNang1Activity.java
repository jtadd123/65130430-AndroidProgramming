package thigk2.nguyenvandat.baithi;

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

public class ChucNang1Activity extends AppCompatActivity {

    // Khai bao cac thanh phan giao dien
    EditText edtChieuDai, edtChieuRong;
    Button btnTinhToan;
    TextView txtKetQua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang1);

        // Xu ly padding cho system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Anh xa cac thanh phan giao dien
        edtChieuDai = findViewById(R.id.edtChieuDai);
        edtChieuRong = findViewById(R.id.edtChieuRong);
        btnTinhToan = findViewById(R.id.btnTinhToan);
        txtKetQua = findViewById(R.id.txtKetQua);

        // Su kien click nut Tinh toan
        btnTinhToan.setOnClickListener(v -> {
            tinhChuViVaDienTich();
        });
    }

    // Ham tinh chu vi va dien tich hinh chu nhat
    private void tinhChuViVaDienTich() {
        // Lay du lieu tu o nhap
        String strChieuDai = edtChieuDai.getText().toString().trim();
        String strChieuRong = edtChieuRong.getText().toString().trim();

        // Kiem tra du lieu rong
        if (strChieuDai.isEmpty() || strChieuRong.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ chiều dài và chiều rộng!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyen doi sang so thuc
        double chieuDai = Double.parseDouble(strChieuDai);
        double chieuRong = Double.parseDouble(strChieuRong);

        // Kiem tra gia tri hop le
        if (chieuDai <= 0 || chieuRong <= 0) {
            Toast.makeText(this, "Chiều dài và chiều rộng phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tinh chu vi va dien tich
        double chuVi = 2 * (chieuDai + chieuRong);
        double dienTich = chieuDai * chieuRong;

        // Hien thi ket qua
        String ketQua = "📐 Kết quả:\n\n"
                + "Chu vi = 2 × (" + chieuDai + " + " + chieuRong + ") = " + chuVi + " m\n\n"
                + "Diện tích = " + chieuDai + " × " + chieuRong + " = " + dienTich + " m²";

        txtKetQua.setText(ketQua);
        txtKetQua.setVisibility(View.VISIBLE);
    }
}
