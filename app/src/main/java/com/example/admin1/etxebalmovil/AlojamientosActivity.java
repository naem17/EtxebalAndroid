package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class AlojamientosActivity extends FragmentoActivity {

    public static final String EXTRA_ALOJAMIENTO_ID="com.example.admin1.etxebalmovil.alojamiento_id";

    public static Intent newIntent(Context context, UUID alojamientoID)
    {
        Intent intent = new Intent(context,AlojamientosActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTO_ID, alojamientoID);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID alojamientoID=(UUID)getIntent().getSerializableExtra(EXTRA_ALOJAMIENTO_ID);
        return FragmentoAlojamientoDetalle.newInstance(alojamientoID);
    }
}
