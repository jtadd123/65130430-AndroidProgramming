package thi.dat65130430.onthi_bottomnavigationmenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Cau3Fragment extends Fragment {
    // Khai báo các biến cần thiêt

    //1. Khai báo các biến toàn cục
    LandScapeAdapter adapter;
    ArrayList<LandScape> list;
    //2. Khai báo các biến đại diện cho các view cần tương tác
    RecyclerView recyclerViewLandScape;



    public Cau3Fragment() {
        // Required empty public constructor
    }

    public static Cau3Fragment newInstance(String param1, String param2) {
        Cau3Fragment fragment = new Cau3Fragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //3. Chuẩn bị dữ liệu cho list
        list = new ArrayList<LandScape>();
        list.add(new LandScape("Cột cờ Hà Nội", "hanoitower"));
        list.add(new LandScape("Tháp Eiffel", "eiffel"));
        list.add(new LandScape("Cung điện Buckingham", "buckingham"));
        list.add(new LandScape("Tượng nữ thần tự do", "nuthantudo"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewCau3 =inflater.inflate(R.layout.fragment_cau3, container, false);
        //4. Tìm điều khiển Recycler
        recyclerViewLandScape=  viewCau3.findViewById(R.id.ryCau3);

        //5. Tạo layout manager để đặt bố cục cho Recycler
        RecyclerView.LayoutManager layoutLinear = new LinearLayoutManager(viewCau3.getContext());
        recyclerViewLandScape.setLayoutManager(layoutLinear);

        //6. Tạo adapter gắn vào nguồn dữ liệu
        adapter = new LandScapeAdapter(viewCau3.getContext(), list);
        //7, Gắn adapter vào Recycler
        recyclerViewLandScape.setAdapter(adapter);

        return viewCau3;
    }
}