package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class AlojamientosHacerReservaActivity extends FragmentoActivity {
    private static final String EXTRA_ALOJAMIENTO_ID = AlojamientosPagerActivity.class.getName() + ".alojamiento_id";

    public static Intent newIntent(Context packageContect, UUID alojamiento_id) {
        Intent intent = new Intent(packageContect, AlojamientosHacerReservaActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTO_ID,alojamiento_id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID alojamientoID = (UUID) getIntent().getSerializableExtra(EXTRA_ALOJAMIENTO_ID);
        return new HacerReservaFragment(AlojamientosLab.get(this).getAlojamiento(alojamientoID));
    }

}
