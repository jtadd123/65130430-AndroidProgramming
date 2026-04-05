package thi.dat65130430.onthi_bottomnavigationmenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cau1Fragment extends Fragment {
    EditText editText_MET;
    EditText editText_KiloMET;
    Button btnChuyen;


    public Cau1Fragment() {
        // Required empty public constructor
    }

    public static Cau1Fragment newInstance(String param1, String param2) {
        Cau1Fragment fragment = new Cau1Fragment();
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
        // Inflate the layout for this fragment
        View viewCau1 = inflater.inflate(R.layout.fragment_cau1, container, false);
        // Tìm điều khiển trong view này
        editText_MET =      viewCau1.findViewById(R.id.editSoMet);
        editText_KiloMET =   viewCau1.findViewById(R.id.editSoKiloM);
        btnChuyen  =   viewCau1.findViewById(R.id.btnDoi);
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duLieuMet = editText_MET.getText().toString();
                String duLieuKiloMet = editText_KiloMET.getText().toString();
                //
                if (!duLieuMet.isEmpty()) // user có nhập dữ liệu met, cần đổi sang km
                {
                    Toast.makeText(viewCau1.getContext(),"m->km", Toast.LENGTH_SHORT).show();
                }
                else //đổi từ km sang met
                {
                    Toast.makeText(viewCau1.getContext(),"km->m", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return viewCau1;
    }
}