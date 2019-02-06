package com.example.admin1.etxebalmovil;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

public class HacerReservaFragment extends Fragment {
    private TextView nombreAlojamiento;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText cantidad;
    private Button reservar;
    private Button cancelar;
    private Alojamientos alojamiento;

    private static final String EXTRA_ALOJAMIENTO_ID = AlojamientosPagerActivity.class.getName() + ".alojamiento_id";

    public HacerReservaFragment(Alojamientos alojamiento) {
        this.alojamiento = alojamiento;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.hacer_reserva_layout, container, false);
        nombreAlojamiento=view.findViewById(R.id.textViewAlojamientoNombre);
        fechaInicio=view.findViewById(R.id.editTextFechaInicio);
        fechaFin=view.findViewById(R.id.editTextFechaFin);
        cantidad=view.findViewById(R.id.editTextCantidad);
        reservar=view.findViewById(R.id.buttonReservar);
        cancelar=view.findViewById(R.id.buttonCancelar);


        nombreAlojamiento.setText(alojamiento.getNombre());

        //TODO reservar y mirar si está logueado
        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
