package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Date;
import java.util.Comparator;

public class Reserva implements Serializable, DatabaseObject {
    private String mId;
    private String mNombreReserva;
    private String mNombreCliente;
    private String mFirmaAlojamiento;
    private Date mFechaInicio;
    private Date mFechaFin;
    private int mCantidadPersonas;

    public static Comparator<DatabaseObject> COMPARE_BY_USER = new Comparator<DatabaseObject>() {
        @Override
        public int compare(DatabaseObject o1, DatabaseObject o2) {
            return ((Reserva)o1).getmNombreCliente().compareTo(((Reserva)o2).getmNombreCliente());
        }
    };

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmNombreReserva() {
        return mNombreReserva;
    }

    public void setmNombreReserva(String mNombreReserva) {
        this.mNombreReserva = mNombreReserva;
    }

    public String getmNombreCliente() {
        return mNombreCliente;
    }

    public void setmNombreCliente(String mNombreCliente) {
        this.mNombreCliente = mNombreCliente;
    }

    public String getmFirmaAlojamiento() {
        return mFirmaAlojamiento;
    }

    public void setmFirmaAlojamiento(String mFirmaAlojamiento) {
        this.mFirmaAlojamiento = mFirmaAlojamiento;
    }

    public Date getmFechaInicio() {
        return mFechaInicio;
    }

    public void setmFechaInicio(Date mFechaInicio) {
        this.mFechaInicio = mFechaInicio;
    }

    public Date getmFechaFin() {
        return mFechaFin;
    }

    public void setmFechaFin(Date mFechaFin) {
        this.mFechaFin = mFechaFin;
    }

    public int getmCantidadPersonas() {
        return mCantidadPersonas;
    }

    public void setmCantidadPersonas(int mCantidadPersonas) {
        this.mCantidadPersonas = mCantidadPersonas;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setmId(json.getString((JSONTag.Reserva.TAG_ID)));
        this.setmNombreReserva(json.getString(JSONTag.Reserva.TAG_NOMBRE_RESERVA));
        this.setmNombreCliente(json.getString(JSONTag.Reserva.TAG_NOMBRE_CLIENTE));
        this.setmFirmaAlojamiento(json.getString(JSONTag.Reserva.TAG_FIRMA_ALOJAMIENTO));
        //TODO cargar fechas java
        /*
        this.setmFechaInicio(Date.valueOf(json.getString(JSONTag.Reserva.TAG_FECHA_INICIO)));
        this.setmFechaFin(Date.valueOf(json.getString(JSONTag.Reserva.TAG_FECHA_FIN)));
        */
        this.setmCantidadPersonas(json.getInt(JSONTag.Reserva.TAG_CANTIDAD_PERSONAS));
        return this;
    }

}
