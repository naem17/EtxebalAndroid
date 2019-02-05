package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class AlojamientoBuscarActivity extends FragmentoActivity {

    public static Intent newIntent(Context packageContect) {
        Intent intent = new Intent(packageContect, AlojamientoBuscarActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new FragmentoAlojamientoBuscar();
    }
}
