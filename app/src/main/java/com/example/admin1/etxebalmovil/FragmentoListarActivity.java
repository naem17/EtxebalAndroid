package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

public class FragmentoListarActivity extends FragmentoActivity {

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect, FragmentoListarActivity.class);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return new FragmentoListarAlojamientos();
    }
}
