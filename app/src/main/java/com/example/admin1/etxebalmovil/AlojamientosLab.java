package com.example.admin1.etxebalmovil;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class AlojamientosLab {

    private static AlojamientosLab alojamientosLab;
    private ArrayList<Alojamientos> alojamientos;
    private static ArrayList<Alojamientos> filtrados;

    private AlojamientosLab(Context context)
    {
        alojamientos=new ArrayList<>();
        //TODO AÑADIR DEL JSON LOS ALOJAMIENTOS
        for(int i=0; i<10;i++)
        {
            alojamientos.add(new Alojamientos());
            alojamientos.get(i).setNombre("Nombre "+(i+1));
            alojamientos.get(i).setTipo("A");

            alojamientos.get(i).setCapacidad(i+1);
        }
        //Coordenadas barrika surf
        alojamientos.get(0).setCoordenadas("43.4051437,-2.9657849999999826");
        alojamientos.get(0).setTipo("Albergues");
        //Coordenadas ganbarahpostel
        alojamientos.get(1).setCoordenadas("43.2583377,-2.9186009000000013");
        alojamientos.get(1).setTipo("Campings");
        //Coordenadas Ziortz
        alojamientos.get(2).setCoordenadas("43.247532247026115,-2.558726650262429");
        alojamientos.get(2).setTipo("Casas Rurales");
        //Coordenadas bbkBilbaoGoodHostel
        alojamientos.get(3).setCoordenadas("43.2467587,-2.909747100000004");
        alojamientos.get(3).setTipo("Agroturismo");

    }
    public static AlojamientosLab get(Context context)
    {
        if(alojamientosLab==null)
            alojamientosLab=new AlojamientosLab(context);
        return alojamientosLab;
    }

    public ArrayList<Alojamientos> getAlojamientos() {
        return alojamientos;
    }
    public ArrayList<Alojamientos> getAlojamientosFiltrados() {
        return filtrados;
    }
    public void setAlojamientosFiltrados(ArrayList<Alojamientos> filtrados){this.filtrados=filtrados;}
    public Alojamientos getAlojamiento(UUID id)
    {
        int i=0;
        while(i<alojamientos.size() && !alojamientos.get(i).getMyID().equals(id))
            i++;
        return i>=alojamientos.size()?null:alojamientos.get(i);
    }
    public int getPosicion(UUID id)
    {
        int i=0;
        while(i<alojamientos.size() && !alojamientos.get(i).getMyID().equals(id))
            i++;
        return i>=alojamientos.size()?-1:i;
    }
    public Alojamientos getAlojamiento(String firma)
    {
        int i=0;
        while (i<alojamientos.size() && alojamientos.get(i).getFirma().compareToIgnoreCase(firma)!=0)
        {
            i++;
        }
        return i>=alojamientos.size()?null:alojamientos.get(i);
    }
    public Alojamientos getAlojamientoFiltrado(UUID id)
    {
        int i=0;
        while(i<filtrados.size() && !filtrados.get(i).getMyID().equals(id))
            i++;
        return i>=filtrados.size()?null:filtrados.get(i);
    }
    public int getPosicionFiltrados(UUID id)
    {
        int i=0;
        while(i<filtrados.size() && !filtrados.get(i).getMyID().equals(id))
            i++;
        return i>=filtrados.size()?-1:i;
    }
    public Alojamientos getAlojamientoFiltrado(String firma)
    {
        int i=0;
        while (i<filtrados.size() && filtrados.get(i).getFirma().compareToIgnoreCase(firma)!=0)
        {
            i++;
        }
        return i>=filtrados.size()?null:filtrados.get(i);
    }
}
