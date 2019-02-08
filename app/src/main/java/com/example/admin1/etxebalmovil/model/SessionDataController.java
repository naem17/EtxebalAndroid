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
import com.example.admin1.etxebalmovil.model.pojo.Categoria;
import com.example.admin1.etxebalmovil.model.pojo.CodigoPostal;
import com.example.admin1.etxebalmovil.model.pojo.Filter;
import com.example.admin1.etxebalmovil.model.pojo.Municipio;
import com.example.admin1.etxebalmovil.model.pojo.Provincia;
import com.example.admin1.etxebalmovil.model.pojo.Relacion;
import com.example.admin1.etxebalmovil.model.pojo.Reserva;
import com.example.admin1.etxebalmovil.model.pojo.TipoEuskera;
import com.example.admin1.etxebalmovil.model.pojo.Usuario;
import com.example.admin1.etxebalmovil.model.pojo.Tipo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<Categoria> mCategorias;
    private List<CodigoPostal> mCodigosPostales;
    private List<Municipio> mMunicipios;
    private List<Provincia> mProvincias;
    private List<Relacion> mRelaciones;
    private List<Reserva> mReservas;
    private List<Tipo> mTipos;
    private List<TipoEuskera> mTiposEuskera;

    public static SessionDataController getInstance() {
        if (null == ourInstance) {
            ourInstance = new SessionDataController();
        }
        return ourInstance;
    }

    private SessionDataController() {
        mAlojamientos = new ArrayList<>();
        mCategorias = new ArrayList<>();
        mRelaciones = new ArrayList<>();
        mTipos = new ArrayList<>();
        mTiposEuskera = new ArrayList<>();
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

    public boolean updateReserva(Reserva reserva){
        mReservas.remove(reserva);
        if (JSONController.setData(JSONBuilder.build(JSONBuilder.UPDATE, reserva)) == JSONController.OTHER_ERROR) {
            return false;
        }
        mReservas.add(reserva);
        return true;
    }

    public boolean updateReserva2(Reserva reserva, Reserva reservaOld){
        mReservas.remove(reserva);
        if (JSONController.setData(JSONBuilder.buildU(JSONBuilder.UPDATE, reserva, reservaOld)) == JSONController.OTHER_ERROR) {
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

    public Reserva getReserva(String firma) {
        for (Reserva r : mReservas) {
            if (r.getmFirmaAlojamiento().equals(firma))
                return r;
        }
        return null;
    }

    public Categoria getCategoria(String categoria) {
        for (Categoria c : mCategorias) {
            if (c.getmCategoria().equals(categoria))
                return c;
        }
        return null;
    }

    public Municipio getMunicipio(String municipio) {
        for (Municipio m : mMunicipios) {
            if (m.getmMunicipio().equals(municipio))
                return m;
        }
        return null;
    }

    public Provincia getProvincia(String provincia) {
        for (Provincia p : mProvincias) {
            if (p.getmProvincua().equals(provincia))
                return p;
        }
        return null;
    }

    public Relacion getRelacion(String codigoPostal, String codigoProvincia, String indiceMunicipio) {
        for (Relacion r : mRelaciones) {
            if (r.getCodigoPostal().equals(codigoPostal)
                    && r.getCodigoProvincia().equals(codigoProvincia)
                    && r.getIndiceMunicipio().equals(indiceMunicipio)) {
                return r;
            }
        }
        return null;
    }

    public Relacion getRelaById(int id){
        for (Relacion r : mRelaciones){
            if (r.getId() == id){
                return r;
            }
        }
        return null;
    }

    public Municipio getMunicipioByInd(int indice){
        for (Municipio m : mMunicipios){
            if (m.getmIndice() == indice){
                return m;
            }
        }
        return null;
    }

    public Provincia getProvinciaByCod(int codigo){
        for (Provincia p : mProvincias){
            if (p.getmCodigo() == codigo){
                return p;
            }
        }
        return null;
    }

    public CodigoPostal getCodigoPostal(String codigoPostal) {
        for (CodigoPostal p : mCodigosPostales) {
            if (p.getmCodigoPostal() == Integer.valueOf(codigoPostal)) {
                return p;
            }
        }
        return null;
    }

    public Tipo getTipo(String tipo) {
        for (Tipo t : mTipos) {
            if (t.getTipo().equals(tipo)) {
                return t;
            }
        }
        return null;
    }

    public TipoEuskera getTipoEuskera(String tipoEuskera) {
        for (TipoEuskera t : mTiposEuskera) {
            if (t.getTipoEuskera().equals(tipoEuskera)) {
                return t;
            }
        }
        return null;
    }

    public void setReservas(List<Reserva> reservas) {
        Date today = new Date(new java.util.Date().getTime());

        for (Reserva reserve : reservas) {
            //TODO incluir fechas
            /*
            if (reserve.getmFechaFin().before(today)) {
                mReservas.remove(reserve);
            } else {*/
                mReservas.add(reserve);
           // }
        }
    }

    public void setAlojamientos(List<Alojamiento> alojamientos) {
        mAlojamientos = alojamientos;
        Collections.sort(mAlojamientos,Alojamiento.COMPARE_BY_NAME);
    }

    public void setCodigosPostales(List<CodigoPostal> codigosPostales) {
        mCodigosPostales = codigosPostales;
    }

    public void setCategorias(List<Categoria> categorias) {
        mCategorias = categorias;
    }

    public void setMunicipios(List<Municipio> municipios) {
        mMunicipios = municipios;
    }

    public void setProvincias(List<Provincia> provincias) {
        mProvincias = provincias;
    }

    public void setRelaciones(List<Relacion> relaciones) {
        mRelaciones = relaciones;
    }

    public void setTipos(List<Tipo> tipos) {
        mTipos = tipos;
    }

    public void setTiposEuskera(List<TipoEuskera> tiposEuskera) {
        mTiposEuskera = tiposEuskera;
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

    public List<CodigoPostal> getCodigosPostales() {
        return mCodigosPostales;
    }

    public List<Reserva> getReservas() {
        return mReservas;
    }

    public List<Categoria> getCategorias() {
        return mCategorias;
    }

    public List<Municipio> getMunicipios() {
        return mMunicipios;
    }

    public List<Provincia> getProvincias() {
        return mProvincias;
    }

    public List<Relacion> getRelaciones() {
        return mRelaciones;
    }

    public List<Tipo> getTipos() {
        return mTipos;
    }

    public List<TipoEuskera> getTiposEuskera() {
        return mTiposEuskera;
    }

    public Usuario getUsuario() {
        return mUsuario;
    }

    public void setUsuario(Usuario usuario) {
        mUsuario = usuario;
    }
}
