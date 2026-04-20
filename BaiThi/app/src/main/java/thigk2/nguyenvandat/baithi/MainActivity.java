package thigk2.nguyenvandat.baithi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Khai bao cac nut bam
    Button btnChucNang1, btnChucNang2, btnChucNang3, btnChucNang4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Xu ly padding cho system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Anh xa cac nut bam
        btnChucNang1 = findViewById(R.id.btnChucNang1);
        btnChucNang2 = findViewById(R.id.btnChucNang2);
        btnChucNang3 = findViewById(R.id.btnChucNang3);
        btnChucNang4 = findViewById(R.id.btnChucNang4);

        // Su kien click nut Chuc nang 1 - Dung Intent de chuyen man hinh
        btnChucNang1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChucNang1Activity.class);
            startActivity(intent);
        });

        // Su kien click nut Chuc nang 2 - Dung Intent de chuyen man hinh
        btnChucNang2.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChucNang2Activity.class);
            startActivity(intent);
        });

        // Su kien click nut Chuc nang 3 - Dung Intent de chuyen man hinh
        btnChucNang3.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChucNang3Activity.class);
            startActivity(intent);
        });

        // Su kien click nut Chuc nang 4 - Dung Intent de chuyen man hinh
        btnChucNang4.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChucNang4Activity.class);
            startActivity(intent);
        });
    }
}