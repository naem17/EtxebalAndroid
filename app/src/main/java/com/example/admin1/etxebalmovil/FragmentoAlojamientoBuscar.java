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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Provincia;
import com.example.admin1.etxebalmovil.model.pojo.Tipo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentoAlojamientoBuscar extends Fragment {

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

       ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getContext(), R.layout.elemento_spinner);
        List<Provincia> provincias= SessionDataController.getInstance().getProvincias();
        arrayAdapter.add("Provincias:");
        for (Provincia provincia : provincias) {
            arrayAdapter.add(provincia.getmProvincua());
        }
        provincia.setAdapter(arrayAdapter);

        ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(getContext(), R.layout.elemento_spinner);
        arrayAdapter2.add("Tipos Alojamientos:");
        List<Tipo> tiposArray= SessionDataController.getInstance().getTipos();
        for (Tipo tipo : tiposArray) {
            arrayAdapter2.add(tipo.getTipo());
        }
        tipos.setAdapter(arrayAdapter2);

       ascendente.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(AlojamientosLab.get(getActivity()).getAlojamientosFiltrados().size()!=0)
               {
                   ArrayList<Alojamientos> filtrados=AlojamientosLab.get(getActivity()).getAlojamientosFiltrados();
                   Collections.sort(filtrados);

                       AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);

                       FragmentManager fragmentManager=getFragmentManager();
                       FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                       FragmentoListarAlojamientos fragmentoListarAlojamientos=new FragmentoListarAlojamientos(true);
                       fragmentTransaction.replace(R.id.resultadosLayout,fragmentoListarAlojamientos);
                       fragmentTransaction.commit();
               }
           }
       });
       descendente.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(AlojamientosLab.get(getActivity()).getAlojamientosFiltrados().size()!=0)
               {
                   ArrayList<Alojamientos> filtrados=AlojamientosLab.get(getActivity()).getAlojamientosFiltrados();
                   Collections.sort(filtrados, Alojamientos.ALOJAMIENTO_NOMBRE_DESC);

                   AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);

                   FragmentManager fragmentManager=getFragmentManager();
                   FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                   FragmentoListarAlojamientos fragmentoListarAlojamientos=new FragmentoListarAlojamientos(true);
                   fragmentTransaction.replace(R.id.resultadosLayout,fragmentoListarAlojamientos);
                   fragmentTransaction.commit();
               }
           }
       });

       buscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ArrayList<Alojamientos> filtrados=new ArrayList<Alojamientos>();
               ArrayList<Alojamientos> total=AlojamientosLab.get(getActivity()).getAlojamientos();
               for (Alojamientos alojamiento:total) {
                   boolean concuerda=true;
                   if(concuerda && nombreAlojamiento.getText().toString().compareTo("")!=0 && alojamiento.getNombre()!=null) {
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

                   if(concuerda)
                       filtrados.add(alojamiento);
               }
               if(filtrados.size()!=0) {

                   AlojamientosLab.get(getActivity()).setAlojamientosFiltrados(filtrados);

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
