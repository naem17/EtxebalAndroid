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

public class ReservasPagerActivity extends AppCompatActivity {
    private static final String EXTRA_RESERVA_NOMBRE = ReservasPagerActivity.class.getName() + ".nombre_reserva";
    private static final String EXTRA_RESERVA_FIRMA = ReservasPagerActivity.class.getName() + ".firma_alojamiento";
    private ViewPager viewPager;
    private ArrayList<Reservas> reservas;

    private Button primero;
    private Button ultimo;

    public static Intent newIntent(Context packageContect, String firmaAlojamiento, String nombreReserva) {
        Intent intent = new Intent(packageContect, ReservasPagerActivity.class);
        intent.putExtra(EXTRA_RESERVA_NOMBRE, nombreReserva);
        intent.putExtra(EXTRA_RESERVA_FIRMA,firmaAlojamiento);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserva_detalle_pager_layout);

        primero=findViewById(R.id.buttonPrimeroReservas);
        ultimo=findViewById(R.id.buttonUltimoReservas);

        String nombreReserva=getIntent().getStringExtra(EXTRA_RESERVA_NOMBRE);
        String firmaAlojamiento=getIntent().getStringExtra(EXTRA_RESERVA_FIRMA);

        viewPager = findViewById(R.id.viewPagerReservas);
        reservas = ReservasLab.get(this).getReservas();

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                Reservas reserva = reservas.get(i);
                return FragmentoReservaDetalle.newInstance(reserva.getFirmaAlojamiento(), reserva.getNombreReserva());
            }

            @Override
            public int getCount() {
                return reservas.size();
            }
        });

        int j=ReservasLab.get(this).getPosicion(nombreReserva,firmaAlojamiento);
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
