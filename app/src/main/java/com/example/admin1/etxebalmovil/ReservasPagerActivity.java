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

public class ReservasPagerActivity extends AppCompatActivity {
    private static final String EXTRA_RESERVA_ID = ReservasPagerActivity.class.getName() + ".reservaID";
    private ViewPager viewPager;
    private ArrayList<Reservas> reservas;

    private Button primero;
    private Button ultimo;

    public static Intent newIntent(Context packageContect, UUID reservaID) {
        Intent intent = new Intent(packageContect, ReservasPagerActivity.class);
        intent.putExtra(EXTRA_RESERVA_ID, reservaID);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alojamientos_detalle_pager_layout);

        primero=findViewById(R.id.buttonPrimero);
        ultimo=findViewById(R.id.buttonUltimo);

        UUID reservaID = (UUID) getIntent().getSerializableExtra(EXTRA_RESERVA_ID);

        viewPager = findViewById(R.id.viewPager);
        reservas = ReservasLab.get(this).getReservas();

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Reservas reserva = reservas.get(i);
                return FragmentoReservaDetalle.newInstance(reserva.getMyID());
            }

            @Override
            public int getCount() {
                return reservas.size();
            }
        });

        int j=ReservasLab.get(this).getPosicion(reservaID);
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
                viewPager.setCurrentItem(reservas.size()-1);
            }
        });
    }
}
