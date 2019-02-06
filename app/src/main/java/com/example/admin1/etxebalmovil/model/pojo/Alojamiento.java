package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

public class Alojamiento implements DatabaseObject, Serializable {

    private UUID mMyID;

    private String mFirma;
    private String mNombre;
    private String mDescripcion;
    private String mDescripcionEuskera;
    private String mTelefono;
    private String mDireccion;
    private String mEmail;
    private String mWeb;
    private boolean mClub;
    private boolean mRestaurante;
    private boolean mAutocaravana;
    private boolean mTienda;
    private int mCapacidad;
    private boolean mGastronomico;
    private boolean mSurf;
    private String mCoordenadas;
    private String mTipo;
    private String mTipoEuskera;
    private String mProvincia;
    private String mMunicipio;
    private int mCp;

    public static Comparator<Alojamiento> COMPARE_BY_NAME = new Comparator<Alojamiento>() {
        @Override
        public int compare(Alojamiento o1, Alojamiento o2) {
            return o1.getNombre().compareTo(o2.getNombre());
        }
    };

    public Alojamiento(String firma, UUID uuid) {

        this.mFirma = firma;
        mMyID = uuid;
    }

    public Alojamiento() {
        mMyID = UUID.randomUUID();
        mFirma = mMyID.toString();
    }

    public Alojamiento(String nombre, String descripcion, String descripcionEuskera, String telefono, String direccion, String email, String web, boolean club, boolean restaurante, boolean autocaravana, boolean tienda, int capacidad, boolean gastronomico, boolean surf, String coordenadas, String tipo, String tipoEuskera, String provincia, String municipio, int cp) {
        mMyID = UUID.randomUUID();
        mFirma = mMyID.toString();
        mNombre = nombre;
        mDescripcion = descripcion;
        mDescripcionEuskera = descripcionEuskera;
        mTelefono = telefono;
        mDireccion = direccion;
        mEmail = email;
        mWeb = web;
        mClub = club;
        mRestaurante = restaurante;
        mAutocaravana = autocaravana;
        mTienda = tienda;
        mCapacidad = capacidad;
        mGastronomico = gastronomico;
        mSurf = surf;
        mCoordenadas = coordenadas;
        mTipo = tipo;
        mTipoEuskera = tipoEuskera;
        mProvincia = provincia;
        mMunicipio = municipio;
        mCp = cp;
    }

    public UUID getMyID() {
        return mMyID;
    }

    public void setMyID(UUID myID) {
        mMyID = myID;
    }

    public String getFirma() {
        return mFirma;
    }

    public void setFirma(String firma) {
        mFirma = firma;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    public String getDescripcionEuskera() {
        return mDescripcionEuskera;
    }

    public void setDescripcionEuskera(String descripcionEuskera) {
        mDescripcionEuskera = descripcionEuskera;
    }

    public String getTelefono() {
        return mTelefono;
    }

    public void setTelefono(String telefono) {
        mTelefono = telefono;
    }

    public String getDireccion() {
        return mDireccion;
    }

    public void setDireccion(String direccion) {
        mDireccion = direccion;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getWeb() {
        return mWeb;
    }

    public void setWeb(String web) {
        mWeb = web;
    }

    public boolean isClub() {
        return mClub;
    }

    public void setClub(boolean club) {
        mClub = club;
    }

    public boolean isRestaurante() {
        return mRestaurante;
    }

    public void setRestaurante(boolean restaurante) {
        mRestaurante = restaurante;
    }

    public boolean isAutocaravana() {
        return mAutocaravana;
    }

    public void setAutocaravana(boolean autocaravana) {
        mAutocaravana = autocaravana;
    }

    public boolean isTienda() {
        return mTienda;
    }

    public void setTienda(boolean tienda) {
        mTienda = tienda;
    }

    public int getCapacidad() {
        return mCapacidad;
    }

    public void setCapacidad(int capacidad) {
        mCapacidad = capacidad;
    }

    public boolean isGastronomico() {
        return mGastronomico;
    }

    public void setGastronomico(boolean gastronomico) {
        mGastronomico = gastronomico;
    }

    public boolean isSurf() {
        return mSurf;
    }

    public void setSurf(boolean surf) {
        mSurf = surf;
    }

    public String getCoordenadas() {
        return mCoordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        mCoordenadas = coordenadas;
    }

    public String getTipo() {
        return mTipo;
    }

    public void setTipo(String tipo) {
        mTipo = tipo;
    }

    public String getTipoEuskera() {
        return mTipoEuskera;
    }

    public void setTipoEuskera(String tipoEuskera) {
        mTipoEuskera = tipoEuskera;
    }

    public String getProvincia() {
        return mProvincia;
    }

    public void setProvincia(String provincia) {
        mProvincia = provincia;
    }

    public String getMunicipio() {
        return mMunicipio;
    }

    public void setMunicipio(String municipio) {
        mMunicipio = municipio;
    }

    public int getCp() {
        return mCp;
    }

    public void setCp(int cp) {
        mCp = cp;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        return this;
    }
}
