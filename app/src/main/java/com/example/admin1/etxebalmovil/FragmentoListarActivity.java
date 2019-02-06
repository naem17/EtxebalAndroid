package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.UUID;

public class FragmentoListarActivity extends FragmentoActivity {
    private static final String EXTRA_ALOJAMIENTOS_FILTRADOS = FragmentoListarActivity.class.getName() + ".alojamiento_filtrado";

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect, FragmentoListarActivity.class);
        return intent;
    }
    public static Intent newIntent (Context packageContect, Boolean filtrados){
        Intent intent = new Intent(packageContect, FragmentoListarActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTOS_FILTRADOS, filtrados);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return new FragmentoListarAlojamientos(false);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
