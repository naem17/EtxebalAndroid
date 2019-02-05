package com.example.admin1.etxebalmovil;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ReservasLab {
    private static ReservasLab reservasLab;
    public ArrayList<Reservas> reservas;

    private ReservasLab(Context context)
    {
        reservas=new ArrayList<>();
        //TODO Quitar las reservas por defecto
        for(int i=0; i<10;i++)
        {
            reservas.add(new Reservas());
            reservas.get(i).setNombreReserva(String.valueOf(i+1));
            reservas.get(i).setCantidad(i+1);
            Date fechaInicio=new Date();
            fechaInicio.setYear(2019);
            fechaInicio.setMonth(i);
            Date fechaFin=new Date();
            fechaFin.setYear(2019);
            fechaFin.setMonth(i+1);
            reservas.get(i).setFechaInicio(fechaInicio);
            reservas.get(i).setFechaFin(fechaFin);
        }
    }
    public static ReservasLab get(Context context)
    {
        if(reservasLab==null)
            return new ReservasLab(context);
        return reservasLab;
    }

    public ArrayList<Reservas> getReservas() {

        return reservas;
    }

    public void setReservas(ArrayList<Reservas> reservas) {
        this.reservas = reservas;
    }
    public Reservas getReserva(UUID id)
    {
        int i=0;
        while(i<reservas.size() && !reservas.get(i).getMyID().equals(id))
            i++;
        return i>=reservas.size()?null:reservas.get(i);
    }
    public int getPosicion(UUID id)
    {
        int i=0;
        while(i<reservas.size() && !reservas.get(i).getMyID().equals(id))
            i++;
        return i>=reservas.size()?-1:i;
    }
    public Reservas getReserva(String firma)
    {
        int i=0;
        while (i<reservas.size() && reservas.get(i).getNombreReserva().compareToIgnoreCase(firma)!=0)
        {
            i++;
        }
        return i>=reservas.size()?null:reservas.get(i);
    }
}
