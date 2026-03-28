package com.dat.docbaotonghop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class TieuDeBaiBaoMainActivity extends AppCompatActivity {

    // ─── View ──────────────────────────────────────────────────────────────
    RecyclerView      lvDSBaiBao;
    ProgressBar       progressBar;
    TextView          tvTrangThai;
    TextView          tvSoKetQua;
    EditText          etTimKiem;
    Spinner           spBoLoc;

    // ─── Data ──────────────────────────────────────────────────────────────
    ArrayBaiBaoAdapter arrayBaiBaoAdapter;
    ArrayList<ItemBaiBao> dsBaiBaoGoc   = new ArrayList<>();  // Toàn bộ bài từ RSS
    ArrayList<ItemBaiBao> dsBaiBaoHienThi = new ArrayList<>(); // Kết quả sau lọc (cho adapter)

    // ─── Filter state ──────────────────────────────────────────────────────
    String tuKhoaHienTai  = "";
    int    boLocThoiGian  = 0; // 0=Tất cả, 1=Hôm nay, 2=Hôm qua, 3=7 ngày, 4=30 ngày

    // ─── Danh sách lựa chọn Spinner ────────────────────────────────────────
    static final String[] FILTER_LABELS = {
            "📋  Tất cả thời gian",
            "☀️  Hôm nay",
            "🌙  Hôm qua",
            "📆  7 ngày qua",
            "🗓️  30 ngày qua"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tieu_de_bai_bao);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("🌐 Thế giới - VnExpress");
        }

        // Ánh xạ view
        lvDSBaiBao  = findViewById(R.id.lvDSBaiBao);
        progressBar = findViewById(R.id.progressBar);
        tvTrangThai = findViewById(R.id.tvTrangThai);
        tvSoKetQua  = findViewById(R.id.tvSoKetQua);
        etTimKiem   = findViewById(R.id.etTimKiem);
        spBoLoc     = findViewById(R.id.spBoLoc);

        // Adapter & RecyclerView
        arrayBaiBaoAdapter = new ArrayBaiBaoAdapter(this, dsBaiBaoHienThi);
        arrayBaiBaoAdapter.setOnItemClickListener(item -> {
            String url = item.getDuongDan();
            if (url != null && !url.isEmpty()) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
        lvDSBaiBao.setLayoutManager(new LinearLayoutManager(this));
        lvDSBaiBao.setAdapter(arrayBaiBaoAdapter);

        // Khởi tạo Spinner bộ lọc thời gian
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, FILTER_LABELS);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spBoLoc.setAdapter(spinnerAdapter);

        // Lắng nghe thay đổi ô tìm kiếm
        etTimKiem.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int st, int c, int a) {}
            @Override public void onTextChanged(CharSequence s, int st, int b, int c) {}
            @Override
            public void afterTextChanged(Editable s) {
                tuKhoaHienTai = s.toString().trim();
                apDungBoLoc();
            }
        });

        // Lắng nghe thay đổi bộ lọc thời gian
        spBoLoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                boLocThoiGian = position;
                apDungBoLoc();
            }
            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Tải dữ liệu RSS
        taiDuLieuRSS();
    }

    // ─── Tải RSS ───────────────────────────────────────────────────────────

    private void taiDuLieuRSS() {
        progressBar.setVisibility(View.VISIBLE);
        tvTrangThai.setVisibility(View.VISIBLE);
        tvTrangThai.setText("Đang tải tin tức...");
        lvDSBaiBao.setVisibility(View.GONE);
        tvSoKetQua.setText("");

        new RssParser().fetch(new RssParser.RssCallback() {
            @Override
            public void onSuccess(ArrayList<ItemBaiBao> dsDaDoc) {
                progressBar.setVisibility(View.GONE);
                tvTrangThai.setVisibility(View.GONE);
                lvDSBaiBao.setVisibility(View.VISIBLE);

                dsBaiBaoGoc.clear();
                dsBaiBaoGoc.addAll(dsDaDoc);
                apDungBoLoc(); // áp bộ lọc hiện tại (mặc định: tất cả)
            }

            @Override
            public void onError(String errorMessage) {
                progressBar.setVisibility(View.GONE);
                tvTrangThai.setVisibility(View.VISIBLE);
                tvTrangThai.setText("⚠️ Không thể tải tin tức.\nKiểm tra kết nối mạng và thử lại.");
                Toast.makeText(TieuDeBaiBaoMainActivity.this,
                        "Lỗi: " + errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    // ─── Lọc dữ liệu ───────────────────────────────────────────────────────

    private void apDungBoLoc() {
        long ngưỡngThoiGian = tinhNguongThoiGian(boLocThoiGian);
        long ngưỡngHetThoiGian = tinhNguongHetThoiGian(boLocThoiGian);

        dsBaiBaoHienThi.clear();

        for (ItemBaiBao item : dsBaiBaoGoc) {
            // Lọc theo từ khóa tiêu đề
            boolean khopTuKhoa = tuKhoaHienTai.isEmpty()
                    || item.getTieuDeBaiBao().toLowerCase().contains(tuKhoaHienTai.toLowerCase());

            // Lọc theo thời gian
            boolean khopThoiGian;
            if (boLocThoiGian == 0) {
                khopThoiGian = true;
            } else {
                long millis = item.getPubDateMillis();
                // Trường hợp "Hôm qua": cần cả giới hạn trên và dưới
                if (boLocThoiGian == 2) {
                    khopThoiGian = millis >= ngưỡngThoiGian && millis < ngưỡngHetThoiGian;
                } else {
                    khopThoiGian = millis >= ngưỡngThoiGian;
                }
            }

            if (khopTuKhoa && khopThoiGian) {
                dsBaiBaoHienThi.add(item);
            }
        }

        arrayBaiBaoAdapter.notifyDataSetChanged();

        // Cập nhật số kết quả
        int soKQ = dsBaiBaoHienThi.size();
        if (!dsBaiBaoGoc.isEmpty()) {
            tvSoKetQua.setText(soKQ + " bài");
        }

        // Trạng thái rỗng
        if (dsBaiBaoGoc.isEmpty()) {
            // Đang chờ tải → không làm gì
        } else if (soKQ == 0) {
            tvTrangThai.setVisibility(View.VISIBLE);
            lvDSBaiBao.setVisibility(View.VISIBLE);
            tvTrangThai.setText("Không tìm thấy bài báo phù hợp 🙁");
        } else {
            tvTrangThai.setVisibility(View.GONE);
        }
    }

    /**
     * Trả về mốc thời gian BẮT ĐẦU (tính từ đây trở về sau mới hợp lệ).
     * -1 = không có giới hạn dưới.
     */
    private long tinhNguongThoiGian(int boLoc) {
        Calendar cal = Calendar.getInstance();
        switch (boLoc) {
            case 1: // Hôm nay: từ 00:00 hôm nay
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return cal.getTimeInMillis();
            case 2: // Hôm qua: từ 00:00 hôm qua
                cal.add(Calendar.DAY_OF_YEAR, -1);
                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
                return cal.getTimeInMillis();
            case 3: // 7 ngày qua
                return System.currentTimeMillis() - 7L * 24 * 60 * 60 * 1000;
            case 4: // 30 ngày qua
                return System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000;
            default: return 0;
        }
    }

    /**
     * Trả về mốc thời gian KẾT THÚC (bài phải có timestamp TRƯỚC mốc này).
     * -1 = không có giới hạn trên.
     */
    private long tinhNguongHetThoiGian(int boLoc) {
        if (boLoc == 2) {
            // Hôm qua: kết thúc lúc 00:00 hôm nay
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return cal.getTimeInMillis();
        }
        return -1; // Không giới hạn trên cho các trường hợp khác
    }
}
