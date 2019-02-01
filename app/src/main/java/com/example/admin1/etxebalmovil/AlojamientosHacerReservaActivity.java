package com.example.admin1.etxebalmovil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class AlojamientosHacerReservaActivity extends AppCompatActivity {
    private static final String EXTRA_ALOJAMIENTO_ID = AlojamientosPagerActivity.class.getName() + ".alojamiento_id";

    private TextView nombreAlojamiento;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText cantidad;
    private Button reservar;
    private Button cancelar;

    private ArrayList<Alojamientos> alojamientos;

    public static Intent newIntent(Context packageContect, UUID alojamiento_id) {
        Intent intent = new Intent(packageContect, AlojamientosHacerReservaActivity.class);
        intent.putExtra(EXTRA_ALOJAMIENTO_ID,alojamiento_id);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hacer_reserva_layout);

        nombreAlojamiento=findViewById(R.id.textViewAlojamientoNombre);
        fechaInicio=findViewById(R.id.editTextFechaInicio);
        fechaFin=findViewById(R.id.editTextFechaFin);
        cantidad=findViewById(R.id.editTextCantidad);
        reservar=findViewById(R.id.buttonReservar);
        cancelar=findViewById(R.id.buttonCancelar);

        UUID alojamientoID = (UUID) getIntent().getSerializableExtra(EXTRA_ALOJAMIENTO_ID);

        nombreAlojamiento.setText(AlojamientosLab.get(this).getAlojamiento(alojamientoID).getNombre());

        //TODO reservar y mirar si está logueado
        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });
    }

}
