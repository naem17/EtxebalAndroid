package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.UUID;

public class AlojamientosPagerActivity extends AppCompatActivity {
    private static final String EXTRA_ALOJAMIENTO_ID = AlojamientosPagerActivity.class.getName() + ".alojamiento_id";
    private ViewPager viewPager;
    private ArrayList<Alojamientos> alojamientos;

    private Button primero;
    private Button ultimo;

    public static Intent newIntent(Context packageContect, UUID alojamientoID) {
        Intent intent = new Intent(packageContect, AlojamientosPagerActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTO_ID, alojamientoID);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alojamientos_detalle_pager_layout);

        primero=findViewById(R.id.buttonPrimero);
        ultimo=findViewById(R.id.buttonUltimo);

        UUID alojamientoID = (UUID) getIntent().getSerializableExtra(EXTRA_ALOJAMIENTO_ID);

        viewPager = findViewById(R.id.viewPager);
        alojamientos = AlojamientosLab.get(this).getAlojamientos();

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Alojamientos alojamiento = alojamientos.get(i);
                return FragmentoAlojamientoDetalle.newInstance(alojamiento.getMyID());
            }

            @Override
            public int getCount() {
                return alojamientos.size();
            }
        });

        int j=AlojamientosLab.get(this).getPosicion(alojamientoID);
        viewPager.setCurrentItem(j);

        primero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        ultimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(alojamientos.size()-1);
            }
        });
    }
}
