package com.dat.appmonan;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Tìm listview
        ListView lvDSMonAn = (ListView) findViewById(R.id.lvDSMonAn);
        insert();
        MonAnAdapter adapter = new MonAnAdapter(this, dsMonAn);
        lvDSMonAn.setAdapter(adapter);
        // Xử lý sự kiện
        lvDSMonAn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy phần tử được chọn
                MonAn monAnHienTai = dsMonAn.get(position);
                Snackbar.make(view, "Bạn vừa chọn: "+ monAnHienTai.getTenMonAn(), Snackbar.LENGTH_LONG)
                        .setAction("Đóng", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Code xử lý
                            }
                        }).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ArrayList<MonAn> dsMonAn = new ArrayList<>();
    public void insert(){
        dsMonAn.add(new MonAn("Cơm tắm sườn", 27000,"Đặc biệt thơm ngon",
                R.drawable.comsuon));
        dsMonAn.add(new MonAn("Cơm vịt", 25000,"Rất nhiều topping",
                R.drawable.comvit));
        dsMonAn.add(new MonAn("Cơm gà", 25000,"Đặc sản Nha Trang",
                R.drawable.comga));
        dsMonAn.add(new MonAn("Cơm chiên trứng", 15000,"Giống cơm mẹ nấu",
                R.drawable.comtrung));
        dsMonAn.add(new MonAn("Cơm chiên muối é", 15000,"Đặc biệt ngon",
                R.drawable.muoie));
        dsMonAn.add(new MonAn("Cơm chiên hải sản", 35000,"Phần ăn dành 2 người",
                R.drawable.comhaisan));
    }
}