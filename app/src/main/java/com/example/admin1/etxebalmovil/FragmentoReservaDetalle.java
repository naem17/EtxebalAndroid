package com.example.admin1.etxebalmovil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.json.JSONBuilder;
import com.example.admin1.etxebalmovil.model.json.JSONController;

import java.sql.Date;
import java.util.ArrayList;
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
    private Button guardar;
    private SessionDataController mSession;

    private Reservas reserva;
    private Reservas reservaOld;

    private static final String EXTRA_RESERVA_NOMBRE = ReservasPagerActivity.class.getName() + ".nombre_reserva";
    private static final String EXTRA_RESERVA_FIRMA = ReservasPagerActivity.class.getName() + ".firma_alojamiento";

    public static FragmentoReservaDetalle newInstance(String firmaAlojamiento, String nombreReserva) {
        Bundle argumentos = new Bundle();
        argumentos.putString(EXTRA_RESERVA_NOMBRE, nombreReserva);
        argumentos.putString(EXTRA_RESERVA_FIRMA, firmaAlojamiento);

        FragmentoReservaDetalle fragmentoReservaDetalle = new FragmentoReservaDetalle();
        fragmentoReservaDetalle.setArguments(argumentos);
        return fragmentoReservaDetalle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombre=getArguments().getString(EXTRA_RESERVA_NOMBRE);
        String firma=getArguments().getString(EXTRA_RESERVA_FIRMA);
        reservaOld=ReservasLab.get(getActivity()).getReserva(nombre, firma);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.reserva_detalle_layout, container, false);

        nombreAlojamiento = vista.findViewById(R.id.textViewNombreAlojamientoDetalle);
        nombreReserva = vista.findViewById(R.id.textViewNombreReserva);
        direccion = vista.findViewById(R.id.textViewDireccion);
        telefono = vista.findViewById(R.id.textViewTelefono);
        email = vista.findViewById(R.id.textViewEmail);
        fechaInicio = vista.findViewById(R.id.editTextFechaInicio);
        fechaFin = vista.findViewById(R.id.editTextFechaFin);
        cantidad = vista.findViewById(R.id.editTextCantidad);
        eliminar = vista.findViewById(R.id.buttonEliminar);
        editar = vista.findViewById(R.id.buttonEditar);
        guardar=vista.findViewById(R.id.buttonGuardar);
        guardar.setVisibility(View.INVISIBLE);


        nombreAlojamiento.setText(reservaOld.getNombreAlojamiento());
        nombreReserva.setText(reservaOld.getNombreReserva());
        direccion.setText(reservaOld.getDireccion());
        telefono.setText(reservaOld.getTelefono());
        email.setText(reservaOld.getEmail());
        fechaInicio.setText(reservaOld.getFechaInicio().toString());
        fechaFin.setText(reservaOld.getFechaFin().toString());
        cantidad.setText(String.valueOf(reservaOld.getCantidad()));

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Eliminar Reserva");

                builder.setMessage("¿Desea Eliminar la reserva?")
                        .setCancelable(false)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mSession = SessionDataController.getInstance();
                                ArrayList<Reservas> reservas=ReservasLab.get(getActivity()).getReservas();
                                reservas.remove(reservaOld);
                                mSession.borrarReserva(reservaOld.toReservaJSON());
                                Intent intent = FragmentoListarReservasActivity.newIntent(getContext());
                                startActivity(intent);
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar.setVisibility(View.VISIBLE);
                editar.setVisibility(View.INVISIBLE);
                eliminar.setVisibility(View.INVISIBLE);
                fechaInicio.setEnabled(true);
                fechaFin.setEnabled(true);
                cantidad.setEnabled(true);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                        builder.setTitle("Guardar Cambios");

                        builder.setMessage("¿Desea guardar los cambios?")
                                .setCancelable(true)
                                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                       boolean correcto=true;
                                       //TODO DatePicker
                                       int capacidad=Integer.valueOf(cantidad.getText().toString());

                                       if(capacidad<=0 ||
                                               capacidad>AlojamientosLab.get(getContext()).getAlojamiento(reservaOld.getFirmaAlojamiento()).getCapacidad())
                                           correcto=false;
                                       Date aux1=Date.valueOf(fechaInicio.getText().toString());
                                       Date aux2=Date.valueOf(fechaFin.getText().toString());
                                       correcto=aux1.before(aux2);
                                       if(correcto)
                                       {
                                           reserva=new Reservas();
                                           reserva.setNombreReserva(reservaOld.getNombreReserva());
                                           reserva.setMyID(reservaOld.getMyID());
                                           reserva.setTelefono(reservaOld.getTelefono());
                                           reserva.setEmail(reservaOld.getEmail());
                                           reserva.setDireccion(reservaOld.getDireccion());
                                           reserva.setNombreAlojamiento(reservaOld.getNombreAlojamiento());
                                           reserva.setNombreCliente(reservaOld.getNombreCliente());
                                           reserva.setFirmaAlojamiento(reservaOld.getFirmaAlojamiento());
                                           reserva.setCantidad(capacidad);
                                           reserva.setFechaInicio(aux1);
                                           reserva.setFechaFin(aux2);
                                           guardar.setVisibility(View.INVISIBLE);
                                           editar.setVisibility(View.VISIBLE);
                                           eliminar.setVisibility(View.VISIBLE);
                                           fechaInicio.setEnabled(false);
                                           fechaFin.setEnabled(false);
                                           cantidad.setEnabled(false);
                                           mSession = SessionDataController.getInstance();
                                           mSession.borrarReserva(reservaOld.toReservaJSON());
                                           mSession.addReserva(reserva.toReservaJSON());
                                           dialog.cancel();
                                       }else
                                       {
                                           Toast.makeText(getActivity(), "Error al intorducir los datos.",
                                                   Toast.LENGTH_LONG).show();
                                       }
                                    }
                                })
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        guardar.setVisibility(View.INVISIBLE);
                                        editar.setVisibility(View.VISIBLE);
                                        eliminar.setVisibility(View.VISIBLE);
                                        fechaInicio.setEnabled(false);
                                        fechaFin.setEnabled(false);
                                        cantidad.setEnabled(false);
                                        fechaFin.setText(reserva.getFechaFin().toString());
                                        fechaInicio.setText(reserva.getFechaInicio().toString());
                                        cantidad.setText(String.valueOf(reserva.getCantidad()));
                                    }

                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                });

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
