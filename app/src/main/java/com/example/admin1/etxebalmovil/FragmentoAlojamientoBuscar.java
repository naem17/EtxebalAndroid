package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class FragmentoAlojamientoBuscar extends Fragment {

    private EditText palabra;
    private AutoCompleteTextView nombreAlojamiento;
    private ImageButton buscar;

    private Spinner provincia;
    private Spinner municipio;
    private Spinner cp;
    private Spinner tipos;

    private EditText cantidad;

    private CheckBox autocaravana;
    private CheckBox restaurante;
    private CheckBox gastronomico;
    private CheckBox surfing;
    private CheckBox club;
    private CheckBox tienda;

    public static FragmentoAlojamientoBuscar newInstance()
    {
        Bundle argumentos=new Bundle();
        FragmentoAlojamientoBuscar fragmentoAlojamientoBuscar=new FragmentoAlojamientoBuscar();
        fragmentoAlojamientoBuscar.setArguments(argumentos);
        return fragmentoAlojamientoBuscar;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.filtro_layout,container,false);
       palabra=view.findViewById(R.id.editTextPalabra);
       nombreAlojamiento=view.findViewById(R.id.autoCompleteTextViewNombreAlojamiento);
       buscar=view.findViewById(R.id.imageButtonBuscar);
       provincia=view.findViewById(R.id.spinnerProvincia);
       tipos=view.findViewById(R.id.spinnerTipos);
       cantidad=view.findViewById(R.id.editTextCantidad);
       autocaravana=view.findViewById(R.id.checkBoxAutocaravana);
       restaurante=view.findViewById(R.id.checkBoxRestaurante);
       gastronomico=view.findViewById(R.id.checkBoxGastronomico);
       surfing=view.findViewById(R.id.checkBoxSurfing);
       club=view.findViewById(R.id.checkBoxSurfing);
       tienda=view.findViewById(R.id.checkBoxTienda);

       buscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //TODO filtrar y mostrar los resultados y mirar que los parámetros no sean null
               ArrayList<Alojamientos> filtrados=new ArrayList<Alojamientos>();
               ArrayList<Alojamientos> total=AlojamientosLab.get(getActivity()).getAlojamientos();
               for (Alojamientos alojamiento:total) {
                   boolean concuerda=true;
                   if(!alojamiento.getNombre().contains(nombreAlojamiento.getText()))
                       concuerda=false;
                   if(alojamiento.getProvincia().compareToIgnoreCase((String) provincia.getSelectedItem())!=0)
                       concuerda=false;
                   if(alojamiento.getMunicipio().compareToIgnoreCase((String) municipio.getSelectedItem())!=0)
                       concuerda=false;
                   if(alojamiento.getCp()!=(int) cp.getSelectedItem())
                       concuerda=false;
                   if(alojamiento.getTipo().compareToIgnoreCase((String) tipos.getSelectedItem())!=0)
                       concuerda=false;
                   if(String.valueOf(alojamiento.getCapacidad()).compareToIgnoreCase(String.valueOf(cantidad.getText()))!=0)
                       concuerda=false;
                   if(alojamiento.isAutocaravana()!=autocaravana.isChecked())
                       concuerda=false;
                   if(alojamiento.isRestaurante()!=restaurante.isChecked())
                       concuerda=false;
                   if(alojamiento.isGastronomico()!=gastronomico.isChecked())
                       concuerda=false;
                   if(alojamiento.isSurf()!=surfing.isChecked())
                       concuerda=false;
                   if(alojamiento.isClub()!=club.isChecked())
                       concuerda=false;
                   if(alojamiento.isTienda()!=tienda.isChecked())
                       concuerda=false;
                   if(concuerda)
                       filtrados.add(alojamiento);
               }
               AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);
               Intent intent=FragmentoListarActivity.newIntent(getContext(),true);
               startActivity(intent);
               getActivity().finish();
           }
       });


       return view;
    }
}
