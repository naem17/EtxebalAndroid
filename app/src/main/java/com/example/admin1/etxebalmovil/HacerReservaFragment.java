package com.example.admin1.etxebalmovil;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;

import java.sql.Date;
import java.time.LocalDate;

public class HacerReservaFragment extends Fragment {
    private TextView nombreAlojamiento;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText cantidad;
    private Button reservar;
    private Button cancelar;
    private Alojamientos alojamiento;
    private SessionDataController mSessionDataController;

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

        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Nueva Reserva");

                builder.setMessage("¿Desea guardar los cambios?")
                        .setCancelable(true)
                        .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                boolean correcto=true;
                                //TODO DatePicker
                                int capacidad=Integer.valueOf(cantidad.getText().toString());

                                if(capacidad<=0 || capacidad>alojamiento.getCapacidad())
                                    correcto=false;
                                if(correcto)
                                {
                                    Reservas reserva = new Reservas();
                                    reserva.setCantidad(capacidad);
                                    //TODO set dates
                                    reserva.setFirmaAlojamiento(alojamiento.getFirma());
                                    reserva.setDireccion(alojamiento.getDireccion());
                                    reserva.setEmail(alojamiento.getEmail());
                                    //TODO meter los datos del cliente
                                    reserva.setTelefono(alojamiento.getTelefono());
                                    reserva.setMyID(UUID.randomUUID());
                                    reserva.setNombreReserva(alojamiento.getNombre()+" "+reserva.getMyID().toString());
                                    ReservasLab.get(getContext()).getReservas().add(reserva);
                                    //TODO guardarla en la BBDD
                                    dialog.cancel();
                                    Intent intent=FragmentoListarReservasActivity.newIntent(getContext());
                                    startActivity(intent);
                                    getActivity().finish();
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
                            }

                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

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
