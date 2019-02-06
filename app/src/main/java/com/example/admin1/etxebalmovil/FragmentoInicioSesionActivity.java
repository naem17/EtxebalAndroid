package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class FragmentoInicioSesionActivity extends FragmentoActivity {

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect, FragmentoInicioSesionActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new FragmentoInicioSesion();
    }
}
