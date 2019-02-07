package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
