package com.example.admin1.etxebalmovil;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class FragmentoListarActivity extends FragmentoActivity {
    @Override
    protected Fragment createFragment() {
        return new FragmentoListarAlojamientos();
    }
}
