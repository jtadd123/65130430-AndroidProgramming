package thi.dat65130430.onthi_bottomnavigationmenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.cardview.widget.CardView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Cau2Fragment extends Fragment {

    EditText editCelsius;
    EditText editFahrenheit;
    Button btnDoi;
    CardView cardKetQua;
    TextView tvKetQua;

    public Cau2Fragment() {
        // Required empty public constructor
    }

    public static Cau2Fragment newInstance(String param1, String param2) {
        Cau2Fragment fragment = new Cau2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau2, container, false);

        // Tìm các điều khiển
        editCelsius = view.findViewById(R.id.editCelsius);
        editFahrenheit = view.findViewById(R.id.editFahrenheit);
        btnDoi = view.findViewById(R.id.btnDoiNhietDo);
        cardKetQua = view.findViewById(R.id.cardKetQua);
        tvKetQua = view.findViewById(R.id.tvKetQua);

        btnDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strCelsius = editCelsius.getText().toString().trim();
                String strFahrenheit = editFahrenheit.getText().toString().trim();

                if (!strCelsius.isEmpty()) {
                    // Đổi từ °C sang °F
                    try {
                        double celsius = Double.parseDouble(strCelsius);
                        double fahrenheit = celsius * 9.0 / 5.0 + 32;
                        editFahrenheit.setText(String.format("%.2f", fahrenheit));

                        // Hiển thị kết quả
                        cardKetQua.setVisibility(View.VISIBLE);
                        tvKetQua.setText(celsius + " °C  =  " + String.format("%.2f", fahrenheit) + " °F");
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else if (!strFahrenheit.isEmpty()) {
                    // Đổi từ °F sang °C
                    try {
                        double fahrenheit = Double.parseDouble(strFahrenheit);
                        double celsius = (fahrenheit - 32) * 5.0 / 9.0;
                        editCelsius.setText(String.format("%.2f", celsius));

                        // Hiển thị kết quả
                        cardKetQua.setVisibility(View.VISIBLE);
                        tvKetQua.setText(fahrenheit + " °F  =  " + String.format("%.2f", celsius) + " °C");
                    } catch (NumberFormatException e) {
                        Toast.makeText(getContext(), "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập giá trị vào một trong hai ô!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}