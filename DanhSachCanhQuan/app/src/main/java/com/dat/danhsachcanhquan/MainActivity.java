package com.dat.danhsachcanhquan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCanhQuan;
    private CanhQuanAdapter adapter;
    private List<CanhQuan> danhSachCanhQuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCanhQuan = findViewById(R.id.recyclerViewCanhQuan);

        // Khởi tạo dữ liệu
        danhSachCanhQuan = taoDanhSach();

        // Thiết lập LinearLayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewCanhQuan.setLayoutManager(layoutManager);

        // Tạo adapter với lambda cho sự kiện click
        adapter = new CanhQuanAdapter(danhSachCanhQuan, canhQuan ->
                Toast.makeText(this,
                        "Bạn đã chọn: " + canhQuan.getTen(),
                        Toast.LENGTH_SHORT).show()
        );

        recyclerViewCanhQuan.setAdapter(adapter);
    }

    private List<CanhQuan> taoDanhSach() {
        List<CanhQuan> list = new ArrayList<>();

        list.add(new CanhQuan(
                "Vịnh Vũng Rô",
                "Huyện Đông Hòa, Phú Yên",
                "Vịnh Vũng Rô là một trong những vịnh đẹp nhất Việt Nam, nước trong xanh và được bao bọc bởi rừng núi xanh mát.",
                R.drawable.vinh_vung_ro_phu_yen_gonatour
        ));
        list.add(new CanhQuan(
                "Mũi Điện (Đại Lãnh)",
                "Huyện Vạn Ninh, Phú Yên",
                "Nơi đón ánh bình minh sớm nhất Việt Nam, với ngọn hải đăng cổ kính sừng sững trên đỉnh núi nhìn ra biển khơi.",
                R.drawable.mui_dien_phu_yen_gonatour
        ));
        list.add(new CanhQuan(
                "Gành Đá Đĩa",
                "Huyện Tuy An, Phú Yên",
                "Thắng cảnh thiên nhiên độc đáo với những cột đá bazan hình lục giác xếp thành từng tảng như đĩa xếp chồng lên nhau.",
                R.drawable.ganhdadia
        ));
        list.add(new CanhQuan(
                "Bãi Reu Xóm Rộ",
                "Huyện Tuy An, Phú Yên",
                "Bãi rêu xanh mướt trải dài bên bờ đá, một khung cảnh thiên nhiên độc đáo và thơ mộng của vùng biển Phú Yên.",
                R.drawable.bai_reu_xom_ro_gonatour
        ));
        list.add(new CanhQuan(
                "Bãi Xép",
                "Thành phố Tuy Hòa, Phú Yên",
                "Bãi biển nhỏ xinh, yên bình nổi tiếng qua bộ phim 'Tôi thấy hoa vàng trên cỏ xanh', với cát trắng và sóng êm.",
                R.drawable.baixep
        ));
        list.add(new CanhQuan(
                "Tháp Nhạn",
                "Thành phố Tuy Hòa, Phú Yên",
                "Tháp Chăm cổ xây từ thế kỷ XI – XII, tọa lạc trên đỉnh núi Nhạn bên bờ sông Đà Rằng, biểu tượng của Phú Yên.",
                R.drawable.thap_nhan_phu_yen_gonatour
        ));
        list.add(new CanhQuan(
                "Hòn Yến",
                "Huyện Tuy An, Phú Yên",
                "Rạn san hô và hòn đảo nhỏ xinh với bãi cát trắng mịn, nơi cư trú của đàn chim yến, nổi tiếng với vẻ đẹp hoang sơ.",
                R.drawable.hon_yen_phu_yen_gonatour
        ));
        list.add(new CanhQuan(
                "Nhà Thờ Mằng Lăng",
                "Huyện Tuy An, Phú Yên",
                "Nhà thờ Công giáo cổ nhất Việt Nam được xây dựng năm 1892, lưu giữ cuốn sách chữ Quốc ngữ đầu tiên của nước ta.",
                R.drawable.nha_tho_mang_lang_gonatour
        ));
        list.add(new CanhQuan(
                "Vẻ Đẹp Hòn Nưa",
                "Huyện Tuy An, Phú Yên",
                "Hòn đảo hoang sơ với làn nước trong vắt như pha lê, bãi cát trắng phau và rạn san hô đầy màu sắc.",
                R.drawable.ve_dep_hon_nua_phu_yen_gonatour
        ));
        list.add(new CanhQuan(
                "Bãi Môn",
                "Huyện Đông Hòa, Phú Yên",
                "Bãi biển tuyệt đẹp nằm cạnh Mũi Điện, với làn nước trong xanh, cát trắng mịn và không khí trong lành.",
                R.drawable.baimon
        ));

        return list;
    }
}