package thigk2.nguyenvandat.baithi;

import android.os.Bundle;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ChucNang3Activity extends AppCompatActivity {

    // Khai bao thanh phan giao dien
    ListView lvDiemDuLich;
    Button btnQuayLai;

    // Mang luu ten cac diem du lich
    ArrayList<String> danhSachTen = new ArrayList<>();
    // Mang luu dia chi cac diem du lich
    ArrayList<String> danhSachDiaChi = new ArrayList<>();
    // Mang luu anh cac diem du lich
    ArrayList<Integer> danhSachAnh = new ArrayList<>();

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
        lvDiemDuLich = findViewById(R.id.lvDiemDuLich);
        btnQuayLai = findViewById(R.id.btnQuayLai);

        // Su kien click nut Quay lai
        btnQuayLai.setOnClickListener(v -> {
            finish(); // Dong Activity hien tai, quay lai man hinh chinh
        });

        // Them du lieu 5 diem du lich o Nha Trang
        themDuLieuDiemDuLich();

        // Tao adapter tuy chinh de hien thi danh sach
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return danhSachTen.size();
            }

            @Override
            public Object getItem(int position) {
                return danhSachTen.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // Tao view tu layout item_diem_du_lich
                if (convertView == null) {
                    convertView = LayoutInflater.from(ChucNang3Activity.this)
                            .inflate(R.layout.item_diem_du_lich, parent, false);
                }

                // Anh xa cac thanh phan trong item
                ImageView imgDiaDiem = convertView.findViewById(R.id.imgDiaDiem);
                TextView txtTenDiaDiem = convertView.findViewById(R.id.txtTenDiaDiem);
                TextView txtDiaChi = convertView.findViewById(R.id.txtDiaChi);

                // Gan du lieu cho tung item
                imgDiaDiem.setImageResource(danhSachAnh.get(position));
                txtTenDiaDiem.setText(danhSachTen.get(position));
                txtDiaChi.setText(danhSachDiaChi.get(position));

                return convertView;
            }
        };

        // Gan adapter cho ListView
        lvDiemDuLich.setAdapter(adapter);

        // Xu ly su kien click vao tung diem du lich trong danh sach
        lvDiemDuLich.setOnItemClickListener((parent, view, position, id) -> {
            String tenDiaDiem = danhSachTen.get(position);
            Toast.makeText(ChucNang3Activity.this,
                    "Bạn đã chọn: " + tenDiaDiem, Toast.LENGTH_SHORT).show();
        });
    }

    // Ham them du lieu 5 diem du lich Nha Trang
    private void themDuLieuDiemDuLich() {
        // Diem 1: Thap Ba Ponagar
        danhSachTen.add("Tháp Bà Ponagar");
        danhSachDiaChi.add("2 Tháng 4, Vĩnh Phước, Nha Trang, Khánh Hòa");
        danhSachAnh.add(R.drawable.thapba);

        // Diem 2: Vinpearl Nha Trang
        danhSachTen.add("Vinpearl Nha Trang");
        danhSachDiaChi.add("Đảo Hòn Tre, Vĩnh Nguyên, Nha Trang, Khánh Hòa");
        danhSachAnh.add(R.drawable.vinpeal);

        // Diem 3: Chua Long Son
        danhSachTen.add("Chùa Long Sơn");
        danhSachDiaChi.add("22 Đường 23 Tháng 10, Phương Sơn, Nha Trang, Khánh Hòa");
        danhSachAnh.add(R.drawable.longso);

        // Diem 4: Ba Ho
        danhSachTen.add("Suối Ba Hồ");
        danhSachDiaChi.add("Ninh Ích, Ninh Hòa, Khánh Hòa");
        danhSachAnh.add(R.drawable.baho);

        // Diem 5: Nha Hat Do (Nha hat Nha Trang)
        danhSachTen.add("Nhà Hát Đỏ");
        danhSachDiaChi.add("Trần Phú, Lộc Thọ, Nha Trang, Khánh Hòa");
        danhSachAnh.add(R.drawable.nhahatdo);
    }
}
