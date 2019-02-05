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

public class FragmentoReservaDetalle extends Fragment {
    private TextView nombreReserva;
    private TextView nombreAlojamiento;
    private TextView direccion;
    private TextView telefono;
    private TextView email;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText cantidad;
    private Button eliminar;
    private Button editar;

    private Reservas reserva;

    private static final String ARG_RESERVA_ID="reserva_id";

    public static FragmentoReservaDetalle newInstance(UUID reservaID)
    {
        Bundle argumentos=new Bundle();
        argumentos.putSerializable(ARG_RESERVA_ID, reservaID);

        FragmentoReservaDetalle fragmentoReservaDetalle=new FragmentoReservaDetalle();
        fragmentoReservaDetalle.setArguments(argumentos);
        return fragmentoReservaDetalle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID reservaID=(UUID)getArguments().getSerializable(ARG_RESERVA_ID);
        reserva=ReservasLab.get(getActivity()).getReserva(reservaID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.reserva_detalle_layout,container,false);

        nombreAlojamiento= vista.findViewById(R.id.textViewNombreAlojamientoDetalle);
        nombreReserva= vista.findViewById(R.id.textViewNombreReserva);
        direccion= vista.findViewById(R.id.textViewDireccion);
        telefono= vista.findViewById(R.id.textViewTelefono);
        email= vista.findViewById(R.id.textViewEmail);
        fechaInicio= vista.findViewById(R.id.editTextFechaInicio);
        fechaFin= vista.findViewById(R.id.editTextFechaFin);
        cantidad= vista.findViewById(R.id.editTextCantidad);
        eliminar=vista.findViewById(R.id.buttonEliminar);
        editar=vista.findViewById(R.id.buttonEditar);

        nombreAlojamiento.setText(reserva.getFirmaAlojamiento());
        nombreReserva.setText(reserva.getNombreReserva());
        direccion.setText(reserva.getDireccion());
        telefono.setText(reserva.getTelefono());
        email.setText(reserva.getEmail());
        fechaInicio.setText(reserva.getFechaInicio().toString());
        fechaFin.setText(reserva.getFechaFin().toString());
        cantidad.setText(reserva.getCantidad());

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO eliminar de las reservas
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Habilitar campos de edición
            }
        });

        nombreAlojamiento.setEnabled(false);
        nombreReserva.setEnabled(false);
        direccion.setEnabled(false);
        telefono.setEnabled(false);
        email.setEnabled(false);
        fechaInicio.setEnabled(false);
        fechaFin.setEnabled(false);
        cantidad.setEnabled(false);

        return vista;
    }

}
