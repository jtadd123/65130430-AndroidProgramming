package thigk2.nguyenvandat.baithi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ChucNang2Activity extends AppCompatActivity {

    // Khai bao thanh phan giao dien
    ListView lvThanhPho;
    Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chuc_nang2);

        // Xu ly padding cho system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Anh xa thanh phan giao dien
        lvThanhPho = findViewById(R.id.lvThanhPho);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        // Su kien click nut Quay lai
        btnQuayLai.setOnClickListener(v -> {
            finish(); // Dong Activity hien tai, quay lai man hinh chinh
        });

        // Tao danh sach 10 tinh/thanh pho Viet Nam
        ArrayList<String> danhSachThanhPho = new ArrayList<>();
        danhSachThanhPho.add("Hà Nội");
        danhSachThanhPho.add("Hồ Chí Minh");
        danhSachThanhPho.add("Đà Nẵng");
        danhSachThanhPho.add("Hải Phòng");
        danhSachThanhPho.add("Cần Thơ");
        danhSachThanhPho.add("Nha Trang");
        danhSachThanhPho.add("Huế");
        danhSachThanhPho.add("Phú Yên");
        danhSachThanhPho.add("Đà Lạt");
        // Thanh pho dac biet: Ho va Ten cua sinh vien
        danhSachThanhPho.add("Nguyễn Văn Đạt");

        // Tao adapter tuy chinh de hien thi danh sach dep hon
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return danhSachThanhPho.size();
            }

            @Override
            public Object getItem(int position) {
                return danhSachThanhPho.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Tao view tu layout item_thanh_pho
                if (convertView == null) {
                    convertView = LayoutInflater.from(ChucNang2Activity.this)
                            .inflate(R.layout.item_thanh_pho, parent, false);
                }

                // Anh xa cac thanh phan trong item
                TextView txtSoThuTu = convertView.findViewById(R.id.txtSoThuTu);
                TextView txtTenThanhPho = convertView.findViewById(R.id.txtTenThanhPho);

                // Gan du lieu
                txtSoThuTu.setText(String.valueOf(position + 1));
                txtTenThanhPho.setText(danhSachThanhPho.get(position));

                // Thanh pho dac biet (ten sinh vien) thi doi mau
                if (position == danhSachThanhPho.size() - 1) {
                    txtTenThanhPho.setTextColor(0xFF2E7D32); // Mau xanh la dam
                    txtTenThanhPho.setTextSize(18);
                    txtSoThuTu.setBackgroundColor(0xFFE65100); // Mau cam
                } else {
                    txtTenThanhPho.setTextColor(0xFF333333);
                    txtTenThanhPho.setTextSize(17);
                    txtSoThuTu.setBackgroundColor(0xFF2E7D32);
                }

                return convertView;
            }
        };

        // Gan adapter cho ListView
        lvThanhPho.setAdapter(adapter);
    }
}
