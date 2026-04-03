package com.dat.fragmentex_static_dynamic_replace;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity implements FooterFragment.OnFooterButtonClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // First launch: show Fragment1 by default. On rotation, let FragmentManager restore state.
        if (savedInstanceState == null) {
            showFragment(1);
        }
    }

    @Override
    public void onFooterButtonSelected(int index) {
        showFragment(index);
    }

    private void showFragment(int index) {
        Fragment fragment;
        switch (index) {
            case 2:
                fragment = new Fragment2();
                break;
            case 3:
                fragment = new Fragment3();
                break;
            case 1:
            default:
                fragment = new Fragment1();
                break;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
