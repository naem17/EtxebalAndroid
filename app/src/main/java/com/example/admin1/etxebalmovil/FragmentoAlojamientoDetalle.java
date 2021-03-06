package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class FragmentoAlojamientoDetalle extends Fragment {

    private TextView nombreAlojamiento;
    private TextView tipoAlojamiento;
    private ImageView imagenAlojamiento;
    private TextView descripcion;
    private TextView email;
    private TextView telefono;
    private TextView web;
    private TextView direccion;
    private TextView provincia;
    private TextView municipio;
    private TextView cp;
    private CheckBox restaurante;
    private CheckBox autocaravana;
    private CheckBox tienda;
    private CheckBox surf;
    private CheckBox gastronomico;
    private CheckBox club;

    private ImageButton mapa;
    private Button  reserva;

    private Alojamientos alojamiento;

    private static final String ARG_ALOJAMIENTO_ID="alojamiento_id";
    private static final String EXTRA_ALOJAMIENTOS=AlojamientosLab.class.getName() + ".alojamiento_id";

    public static FragmentoAlojamientoDetalle newInstance(UUID alojamientoID)
    {
        Bundle argumentos=new Bundle();
        argumentos.putSerializable(ARG_ALOJAMIENTO_ID, alojamientoID);

        FragmentoAlojamientoDetalle fragmentoAlojamientoDetalle=new FragmentoAlojamientoDetalle();
        fragmentoAlojamientoDetalle.setArguments(argumentos);
        return fragmentoAlojamientoDetalle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID alojamientoID=(UUID)getArguments().getSerializable(ARG_ALOJAMIENTO_ID);
        alojamiento=AlojamientosLab.get(getActivity()).getAlojamiento(alojamientoID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista=inflater.inflate(R.layout.alojamiento_detalle_layout,container,false);

        nombreAlojamiento= vista.findViewById(R.id.textViewAlojamientoNombre);
        tipoAlojamiento= vista.findViewById(R.id.textViewAlojamientoTipo);
        imagenAlojamiento= vista.findViewById(R.id.imageViewImagenAlojamiento);
        /*PROBAR SI SALEN LOS ICONOS*/
        if (alojamiento.getTipo().equalsIgnoreCase("Albergues") || alojamiento.getTipo().equalsIgnoreCase("Aterpetxeak")){
            imagenAlojamiento.setImageResource(R.drawable.ic_alberguess);
        }else if (alojamiento.getTipo().equalsIgnoreCase("Campings")  || alojamiento.getTipo().equalsIgnoreCase("Kanpinak")){
            imagenAlojamiento.setImageResource(R.drawable.ic_campings);
        }else  if (alojamiento.getTipo().equalsIgnoreCase("Agroturismos")  || alojamiento.getTipo().equalsIgnoreCase("Nekazaritza-turismoak")){
            imagenAlojamiento.setImageResource(R.drawable.ic_agroturismo);
        }else {
            imagenAlojamiento.setImageResource(R.drawable.ic_casasrurales);
        }
        /*----------------------------------------------------------------------*/
        descripcion= vista.findViewById(R.id.textViewAlojamientoDesc);
        email= vista.findViewById(R.id.textViewAlojamientoEmail);
        telefono= vista.findViewById(R.id.textViewAlojamientoTel);
        web= vista.findViewById(R.id.textViewAlojamientoWeb);
        direccion= vista.findViewById(R.id.textViewAlojamientoDireccion);
        provincia= vista.findViewById(R.id.textViewAlojamientoProvincia);
        municipio= vista.findViewById(R.id.textViewAlojamientoMunicipio);
        cp= vista.findViewById(R.id.textViewAlojamientoCP);
        restaurante= vista.findViewById(R.id.checkBoxRestaurante);
        autocaravana= vista.findViewById(R.id.checkBoxAutocaravana);
        tienda= vista.findViewById(R.id.checkBoxTienda);
        surf= vista.findViewById(R.id.checkBoxSurf);
        gastronomico= vista.findViewById(R.id.checkBoxGastronomico);
        club=vista.findViewById(R.id.checkBoxClub);

        nombreAlojamiento.setText(alojamiento.getNombre());
        tipoAlojamiento.setText(alojamiento.getTipo());
        descripcion.setText(alojamiento.getDescripcion());
        email.setText(alojamiento.getEmail());
        telefono.setText(alojamiento.getTelefono());
        web.setText(alojamiento.getWeb());
        direccion.setText(alojamiento.getDireccion());
        provincia.setText(alojamiento.getProvincia());
        municipio.setText(alojamiento.getMunicipio());
        cp.setText(alojamiento.getCp()==0?"":String.valueOf(alojamiento.getCp()));
        restaurante.setChecked(alojamiento.isRestaurante());
        autocaravana.setChecked(alojamiento.isAutocaravana());
        tienda.setChecked(alojamiento.isTienda());
        surf.setChecked(alojamiento.isSurf());
        gastronomico.setChecked(alojamiento.isGastronomico());
        club.setChecked(alojamiento.isClub());

        nombreAlojamiento.setEnabled(false);
        tipoAlojamiento.setEnabled(false);
        descripcion.setEnabled(false);
        email.setEnabled(false);
        telefono.setEnabled(false);
        web.setEnabled(false);
        direccion.setEnabled(false);
        provincia.setEnabled(false);
        municipio.setEnabled(false);
        cp.setEnabled(false);
        restaurante.setEnabled(false);
        autocaravana.setEnabled(false);
        tienda.setEnabled(false);
        surf.setEnabled(false);
        gastronomico.setEnabled(false);
        club.setEnabled(false);

        mapa=vista.findViewById(R.id.imageButtonMapa);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapita = MapsActivity.newIntent(getContext(), alojamiento.getMyID());
                startActivity(mapita);
            }
        });

        reserva=vista.findViewById(R.id.buttonReservar);
        reserva.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=AlojamientosHacerReservaActivity.newIntent(getActivity(), alojamiento.getMyID());
                startActivity(intent);
            }
        });

        return vista;
    }
}
