package com.example.admin1.etxebalmovil;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentoAlojamientoBuscar extends Fragment {

    private EditText palabra;
    private AutoCompleteTextView nombreAlojamiento;
    private ImageButton buscar;
    private ImageButton ascendente;
    private ImageButton descendente;

    private Spinner provincia;
    private Spinner tipos;

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
       ascendente=view.findViewById(R.id.imageButtonAscendente);
       descendente=view.findViewById(R.id.imageButtonDescendente);
       provincia=view.findViewById(R.id.spinnerProvincia);
       tipos=view.findViewById(R.id.spinnerTipos);
       autocaravana=view.findViewById(R.id.checkBoxAutocaravana);
       restaurante=view.findViewById(R.id.checkBoxRestaurante);
       gastronomico=view.findViewById(R.id.checkBoxGastronomico);
       surfing=view.findViewById(R.id.checkBoxSurfing);
       club=view.findViewById(R.id.checkBoxSurfing);
       tienda=view.findViewById(R.id.checkBoxTienda);

       ascendente.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(AlojamientosLab.get(getActivity()).getAlojamientosFiltrados().size()!=0)
               {
                   ArrayList<Alojamientos> filtrados=AlojamientosLab.get(getActivity()).getAlojamientosFiltrados();
                   
               }
           }
       });

       buscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //TODO filtrar y mostrar los resultados y mirar que los parámetros no sean null
               ArrayList<Alojamientos> filtrados=new ArrayList<Alojamientos>();
               ArrayList<Alojamientos> total=AlojamientosLab.get(getActivity()).getAlojamientos();
               for (Alojamientos alojamiento:total) {
                   boolean concuerda=true;
                   if(concuerda && nombreAlojamiento.getText()!=null && alojamiento.getNombre()!=null) {
                       if (!alojamiento.getNombre().contains(nombreAlojamiento.getText()))
                           concuerda = false;
                   }
                   if(concuerda && provincia.getSelectedItemPosition()!=0 && alojamiento.getProvincia()!=null) {
                       if (alojamiento.getProvincia().compareToIgnoreCase(provincia.getSelectedItem().toString()) != 0)
                               concuerda = false;
                       }
                       if(concuerda && tipos.getSelectedItemPosition()!=0 && alojamiento.getTipo()!=null) {
                           if (alojamiento.getTipo().compareToIgnoreCase(tipos.getSelectedItem().toString()) != 0)
                               concuerda = false;
                       }
                       if(concuerda && !alojamiento.isAutocaravana() && autocaravana.isChecked())
                           concuerda=false;
                   if(concuerda && !alojamiento.isRestaurante() && restaurante.isChecked())
                       concuerda=false;
                   if(concuerda && !alojamiento.isGastronomico() && gastronomico.isChecked())
                       concuerda=false;
                   if(concuerda && !alojamiento.isSurf() && surfing.isChecked())
                       concuerda=false;
                   if(concuerda && !alojamiento.isClub() && club.isChecked())
                       concuerda=false;
                   if(concuerda && !alojamiento.isTienda() && tienda.isChecked())
                       concuerda=false;
                   if(concuerda && palabra.getText()!=null)
                   {
                       if(alojamiento.getNombre()!=null && !palabra.getText().toString().contains(alojamiento.getNombre()))
                           if(alojamiento.getTipo()!=null && !palabra.getText().toString().contains(alojamiento.getTipo()))
                               if(alojamiento.getTelefono()!=null && !palabra.getText().toString().contains(alojamiento.getTelefono()))
                                   if(alojamiento.getEmail()!=null && !palabra.getText().toString().contains(alojamiento.getEmail()))
                                       if(alojamiento.getWeb()!=null && !palabra.getText().toString().contains(alojamiento.getWeb()))
                                           if(alojamiento.getDireccion()!=null && !palabra.getText().toString().contains(alojamiento.getDireccion()))
                                               if(alojamiento.getProvincia()!=null && !palabra.getText().toString().contains(alojamiento.getProvincia()))
                                                   if(alojamiento.getMunicipio()!=null && !palabra.getText().toString().contains(alojamiento.getMunicipio()))
                                                       if(alojamiento.getDescripcion()!=null && !palabra.getText().toString().contains(alojamiento.getDescripcion()))
                                                            if(alojamiento.getCp()>0 && !palabra.getText().toString().contains(String.valueOf(alojamiento.getCp())))
                                                                concuerda=false;
                   }
                   if(concuerda)
                       filtrados.add(alojamiento);
               }
               if(filtrados.size()!=0) {

                   AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);
                 /*  Intent intent = FragmentoListarActivity.newIntent(getContext(), true);
                   startActivity(intent);
                   getActivity().finish();
                   */
                   FragmentManager fragmentManager=getFragmentManager();
                   FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                   FragmentoListarAlojamientos fragmentoListarAlojamientos=new FragmentoListarAlojamientos(true);
                   fragmentTransaction.replace(R.id.resultadosLayout,fragmentoListarAlojamientos);
                   fragmentTransaction.commit();
               }else
               {
                   Toast.makeText(getActivity(), "No existen alojamientos que coincidan con su búsqueda.",
                           Toast.LENGTH_LONG).show();
                   filtrados.removeAll(filtrados);
                   AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);
                   FragmentManager fragmentManager=getFragmentManager();
                   FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                   FragmentoListarAlojamientos fragmentoListarAlojamientos=new FragmentoListarAlojamientos(true);
                   fragmentTransaction.replace(R.id.resultadosLayout,fragmentoListarAlojamientos);
                   fragmentTransaction.commit();
               }
           }
       });


       return view;
    }
}
