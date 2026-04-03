package com.dat.fragmentex_static_dynamic_replace;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class FooterFragment extends Fragment {

    public interface OnFooterButtonClickListener {
        void onFooterButtonSelected(int index);
    }

    private OnFooterButtonClickListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFooterButtonClickListener) {
            listener = (OnFooterButtonClickListener) context;
        } else {
            throw new IllegalStateException("Host activity must implement FooterFragment.OnFooterButtonClickListener");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_footer, container, false);

        MaterialButton btnOne = view.findViewById(R.id.btnOne);
        MaterialButton btnTwo = view.findViewById(R.id.btnTwo);
        MaterialButton btnThree = view.findViewById(R.id.btnThree);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onFooterButtonSelected(1);
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onFooterButtonSelected(2);
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onFooterButtonSelected(3);
            }
        });

        return view;
    }
}