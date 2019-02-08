package com.example.admin1.etxebalmovil;

import android.content.Context;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.json.JSONController;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;

import com.example.admin1.etxebalmovil.model.SessionDataController;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ReservasLab {
    private static ReservasLab reservasLab;
    public ArrayList<Reservas> reservas;
    public ArrayList<Reserva> mReservas;
    private SessionDataController mSessionDataController;

    private ReservasLab(Context context) {
        reservas = new ArrayList<>();
        //TODO añadir lo del JSON
    }

    public static ReservasLab get(Context context) {
        if (reservasLab == null)
            return new ReservasLab(context);
        return reservasLab;
    }

    public ArrayList<Reservas> getReservas() {

        return reservas;
    }

    public List<Reserva> getmReservas() {
        mSessionDataController = SessionDataController.getInstance();
        return mSessionDataController.getReservas();
    }


    public void setReservas(ArrayList<Reservas> reservas) {
        this.reservas = reservas;
    }

    public Reservas getReserva(UUID id) {
        int i = 0;
        while (i < reservas.size() && !reservas.get(i).getMyID().equals(id))
            i++;
        return i >= reservas.size() ? null : reservas.get(i);
    }

    public int getPosicion(UUID id) {
        int i = 0;
        while (i < reservas.size() && !reservas.get(i).getMyID().equals(id))
            i++;
        return i >= reservas.size() ? -1 : i;
    }

    public int getPosicion(String nombreReserva, String firmaAlojamiento) {
        int i = 0;
        while (i < reservas.size() && reservas.get(i).getNombreReserva().compareToIgnoreCase(nombreReserva) != 0 && reservas.get(i).getFirmaAlojamiento().compareToIgnoreCase(firmaAlojamiento) != 0)
            i++;
        return i >= reservas.size() ? -1 : i;
    }

    public Reservas getReserva(String nombreReserva, String firmaAlojamiento) {
        int i = 0;
        while (i < reservas.size() && reservas.get(i).getNombreReserva().compareToIgnoreCase(nombreReserva) != 0 && reservas.get(i).getFirmaAlojamiento().compareToIgnoreCase(firmaAlojamiento) != 0) {
            i++;
        }
        return i >= reservas.size() ? null : reservas.get(i);
    }
}
