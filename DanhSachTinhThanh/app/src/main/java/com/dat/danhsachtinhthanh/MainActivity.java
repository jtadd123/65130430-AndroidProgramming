package com.dat.danhsachtinhthanh;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ListView lvTinhThanh;

    // Danh sách 34 tỉnh/thành phố sau sáp nhập (hiệu lực từ 1/7/2025)
    private final String[] danhSachTinhThanh = {
            // ── Miền Bắc ──
            "1. Hà Nội",
            "2. Tuyên Quang (+ Hà Giang)",
            "3. Lào Cai (+ Yên Bái)",
            "4. Điện Biên",
            "5. Lai Châu",
            "6. Sơn La",
            "7. Cao Bằng",
            "8. Lạng Sơn",
            "9. Thái Nguyên (+ Bắc Kạn)",
            "10. Quảng Ninh",
            "11. Phú Thọ (+ Hòa Bình, Vĩnh Phúc)",
            "12. Bắc Ninh (+ Bắc Giang)",
            "13. Hải Phòng (+ Hải Dương)",
            "14. Hưng Yên (+ Thái Bình)",
            "15. Ninh Bình (+ Hà Nam, Nam Định)",
            // ── Miền Trung ──
            "16. Thanh Hóa",
            "17. Nghệ An",
            "18. Hà Tĩnh",
            "19. Quảng Trị (+ Quảng Bình)",
            "20. Huế",
            "21. Đà Nẵng (+ Quảng Nam)",
            "22. Quảng Ngãi (+ Kon Tum)",
            "23. Gia Lai (+ Bình Định)",
            "24. Đắk Lắk (+ Phú Yên)",
            "25. Khánh Hòa (+ Ninh Thuận)",
            "26. Lâm Đồng (+ Đắk Nông, Bình Thuận)",
            // ── Miền Nam ──
            "27. TP. Hồ Chí Minh (+ Bình Dương, Bà Rịa-Vũng Tàu)",
            "28. Đồng Nai (+ Bình Phước)",
            "29. Tây Ninh (+ Long An)",
            "30. Cần Thơ (+ Tiền Giang)",
            "31. Đồng Tháp (+ An Giang)",
            "32. Vĩnh Long (+ Bến Tre, Trà Vinh)",
            "33. An Giang (+ Kiên Giang)",
            "34. Cà Mau (+ Bạc Liêu)"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTinhThanh = findViewById(R.id.lvTinhThanh);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                danhSachTinhThanh
        );

        lvTinhThanh.setAdapter(adapter);

        lvTinhThanh.setOnItemClickListener((parent, view, position, id) -> {
            String tinhDuocChon = danhSachTinhThanh[position];
            android.widget.Toast.makeText(this, "Bạn chọn: " + tinhDuocChon, android.widget.Toast.LENGTH_SHORT).show();
        });
    }
}