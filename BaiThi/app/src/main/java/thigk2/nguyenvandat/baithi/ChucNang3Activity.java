package thigk2.nguyenvandat.baithi;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChucNang3Activity extends AppCompatActivity {

    // Khai bao thanh phan giao dien
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang3);

        // Xu ly padding cho system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Anh xa thanh phan giao dien
        btnQuayLai = findViewById(R.id.btnQuayLai);

        // Su kien click nut Quay lai
        btnQuayLai.setOnClickListener(v -> {
            finish(); // Dong Activity hien tai, quay lai man hinh chinh
        });

        // Se them logic o phan sau
    }
}
