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
        //TODO añadir lo del JSON
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
    public int getPosicion(String nombreReserva, String firmaAlojamiento)
    {
        int i=0;
        while(i<reservas.size() && reservas.get(i).getNombreReserva().compareToIgnoreCase(nombreReserva)!=0 && reservas.get(i).getFirmaAlojamiento().compareToIgnoreCase(firmaAlojamiento)!=0)
            i++;
        return i>=reservas.size()?-1:i;
    }
    public Reservas getReserva(String nombreReserva, String firmaAlojamiento)
    {
        int i=0;
        while (i<reservas.size() && reservas.get(i).getNombreReserva().compareToIgnoreCase(nombreReserva)!=0 && reservas.get(i).getFirmaAlojamiento().compareToIgnoreCase(firmaAlojamiento)!=0)
        {
            i++;
        }
        return i>=reservas.size()?null:reservas.get(i);
    }
}
