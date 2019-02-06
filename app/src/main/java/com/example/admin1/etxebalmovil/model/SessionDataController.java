package com.example.admin1.etxebalmovil.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;

import com.example.admin1.etxebalmovil.model.json.JSONBuilder;
import com.example.admin1.etxebalmovil.model.json.JSONController;
import com.example.admin1.etxebalmovil.model.pojo.Alojamiento;
import com.example.admin1.etxebalmovil.model.pojo.CodigoPostal;
import com.example.admin1.etxebalmovil.model.pojo.Filter;
import com.example.admin1.etxebalmovil.model.pojo.Municipio;
import com.example.admin1.etxebalmovil.model.pojo.Provincia;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de almacenar los datos de la sesion.
 * Permite cambiar el usuario y modificar la lista de reservas.
 */
public class SessionDataController {
    private static SessionDataController ourInstance;
    private Usuario mUsuario;
    private Util.Position mPosicionActual;
    private List<Alojamiento> mAlojamientos;
    private List<Reserva> mReservas;
    private List<CodigoPostal> mCodigosPostales;
    private List<Provincia> mProvincias;
    private List<Municipio> mMunicipios;

    public static SessionDataController getInstance() {
        if (null == ourInstance) {
            ourInstance = new SessionDataController();
        }
        return ourInstance;
    }

    private SessionDataController() {
        mAlojamientos = new ArrayList<>();
        mReservas = new ArrayList<>();
        mProvincias = new ArrayList<>();
        mMunicipios = new ArrayList<>();
        mCodigosPostales = new ArrayList<>();
        mUsuario = new Usuario();
    }

    public Alojamiento getAlojamiento(String id) {
        for (Alojamiento alojamiento : mAlojamientos) {
            if (alojamiento.getFirma().equals(id))
                return alojamiento;
        }
        return null;
    }

    public List<Alojamiento> getAlojamientos() {
        return mAlojamientos;
    }

    public List<Alojamiento> getAlojamientos(Filter filter) {
        List<Alojamiento> alojamientos = new ArrayList<>();

        for (Alojamiento alojamiento : mAlojamientos) {
            if (filter.check(alojamiento)) {
                alojamientos.add(alojamiento);
            }
        }

        return alojamientos;
    }

    public boolean registarUsuario(Usuario usuario) {
        byte error = JSONController.setData(JSONBuilder.build(usuario));
        if (error == JSONController.NO_ERROR) {
            this.mUsuario = usuario;
            return true;
        }
        return false;
    }

    public boolean addReserva(Reserva reserva) {
        if (JSONController.setData(JSONBuilder.build(JSONBuilder.INSERT, reserva)) == JSONController.OTHER_ERROR) {
            return false;
        }
        mReservas.add(reserva);
        return true;
    }

    public boolean borrarReserva(Reserva reserva) {
        if (JSONController.setData(JSONBuilder.build(JSONBuilder.DELETE, reserva)) == JSONController.OTHER_ERROR) {
            return false;
        }
        mReservas.remove(reserva);
        return true;
    }

    public boolean isReservado(String id) {
        for (Reserva reserva : mReservas) {
            if (reserva.getmFirmaAlojamiento().equals(id))
                return true;
        }
        return false;
    }

    public Reserva getReserva(String lodgingId) {
        for (Reserva r : mReservas) {
            if (r.getmFirmaAlojamiento().equals(lodgingId))
                return r;
        }
        return null;
    }

    public CodigoPostal getCodigoPostal(String codigoPostal) {
        for (CodigoPostal p : mCodigosPostales) {
            if (codigoPostal.equals(p.getmCodigoPostal())) {
                return p;
            }
        }
        return null;
    }

    public void setReservas(List<Reserva> reservas) {
        Date today = new Date(new java.util.Date().getTime());

        for (Reserva reserve : reservas) {
            if (reserve.getmFechaFin().before(today)) {
                mReservas.remove(reserve);
            } else {
                mReservas.add(reserve);
            }
        }
    }

    public void setAlojamientos(List<Alojamiento> alojamientos) {
        mAlojamientos = alojamientos;
        mAlojamientos.sort(Alojamiento.COMPARE_BY_NAME);
    }

    public void setCodigosPostales(List<CodigoPostal> codigosPostales) {
        mCodigosPostales = codigosPostales;
    }

    public void setPosicionActual(Context context) {
        if (context == null) {
            mPosicionActual = null;
            return;
        }

        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        List<String> probiders = lm.getProviders(true);
        Location currentLocation = null;
        Util.Position currentPosition = new Util.Position();

        // Check permisions
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mPosicionActual = null;
            return;
        }
        for (String probider : probiders) {
            currentLocation = lm.getLastKnownLocation(probider);
            if (currentLocation != null)
                break;
        }
        if (currentLocation != null) {
            currentPosition.latitude = currentLocation.getLatitude();
            currentPosition.longitude = currentLocation.getLongitude();
        }

        mPosicionActual = currentPosition;
    }

    public Util.Position getPosicionActual() {
        return mPosicionActual;
    }

    public List<CodigoPostal> getPostCodes() {
        return mCodigosPostales;
    }

    public List<Reserva> getReserves() {
        return mReservas;
    }

    public Usuario getUsuario() {
        return mUsuario;
    }

    public void setUsuario(Usuario usuario) {
        mUsuario = usuario;
    }
}
