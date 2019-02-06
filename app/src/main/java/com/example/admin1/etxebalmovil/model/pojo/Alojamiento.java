package com.example.admin1.etxebalmovil.model.pojo;

import com.example.admin1.etxebalmovil.model.DatabaseObject;
import com.example.admin1.etxebalmovil.model.json.JSONTag;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Comparator;
import java.util.UUID;

public class Alojamiento implements DatabaseObject, Serializable {

    private UUID mMyID;

    private String mFirma;
    private String mNombre;
    private String mDescripcionAbreviada;
    private String mDescripcionAbreviadaEuskera;
    private String mDescripcion;
    private String mDescripcionEuskera;
    private String mTelefono;
    private String mDireccion;
    private boolean mCalidad;
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
    private String mCodigoTipo;
    private String mCodigoTipoEuskera;
    private int mCodigoCategorias;
    private int mIdRelaciones;

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

    public Alojamiento(String nombre, String descripcionAbreviada, String descripcionAbreviadaEuskera, String descripcion, String descripcionEuskera, String telefono, String direccion, boolean calidad, String email, String web, boolean club, boolean restaurante, boolean autocaravana, boolean tienda, int capacidad, boolean gastronomico, boolean surf, String coordenadas, String codigoTipo, String codigoTipoEuskera, int codigoCategorias, int idRelaciones) {
        mMyID = UUID.randomUUID();
        mFirma = mMyID.toString();
        mNombre = nombre;
        mDescripcionAbreviada = descripcionAbreviada;
        mDescripcionAbreviadaEuskera = descripcionAbreviadaEuskera;
        mDescripcion = descripcion;
        mDescripcionEuskera = descripcionEuskera;
        mTelefono = telefono;
        mDireccion = direccion;
        mCalidad = calidad;
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
        mCodigoTipo = codigoTipo;
        mCodigoTipoEuskera = codigoTipoEuskera;
        mCodigoCategorias = codigoCategorias;
        mIdRelaciones = idRelaciones;
    }

    public UUID getMyID() {
        return mMyID;
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
        return mCodigoTipo;
    }

    public void setTipo(String tipo) {
        mCodigoTipo = tipo;
    }

    public String getTipoEuskera() {
        return mCodigoTipoEuskera;
    }

    public void setTipoEuskera(String tipoEuskera) {
        mCodigoTipoEuskera = tipoEuskera;
    }

    public String getDescripcionAbreviada() {
        return mDescripcionAbreviada;
    }

    public void setDescripcionAbreviada(String descripcionAbreviada) {
        mDescripcionAbreviada = descripcionAbreviada;
    }

    public String getDescripcionAbreviadaEuskera() {
        return mDescripcionAbreviadaEuskera;
    }

    public void setDescripcionAbreviadaEuskera(String descripcionAbreviadaEuskera) {
        mDescripcionAbreviadaEuskera = descripcionAbreviadaEuskera;
    }

    public boolean isCalidad() {
        return mCalidad;
    }

    public void setCalidad(boolean calidad) {
        mCalidad = calidad;
    }

    public String getCodigoTipo() {
        return mCodigoTipo;
    }

    public void setCodigoTipo(String codigoTipo) {
        mCodigoTipo = codigoTipo;
    }

    public String getCodigoTipoEuskera() {
        return mCodigoTipoEuskera;
    }

    public void setCodigoTipoEuskera(String codigoTipoEuskera) {
        mCodigoTipoEuskera = codigoTipoEuskera;
    }

    public int getCodigoCategorias() {
        return mCodigoCategorias;
    }

    public void setCodigoCategorias(int codigoCategorias) {
        mCodigoCategorias = codigoCategorias;
    }

    public int getIdRelaciones() {
        return mIdRelaciones;
    }

    public void setIdRelaciones(int idRelaciones) {
        mIdRelaciones = idRelaciones;
    }

    @Override
    public DatabaseObject fromJSON(JSONObject json) throws JSONException {
        this.setFirma(json.getString(JSONTag.Alojamiento.TAG_FIRMA));
        this.setNombre(json.getString(JSONTag.Alojamiento.TAG_NAME));
        this.setDescripcionAbreviada(json.getString(JSONTag.Alojamiento.TAG_DESCRPCION_ABREVIADA));
        this.setDescripcionAbreviadaEuskera(json.getString(JSONTag.Alojamiento.TAG_DESCRPCION_ABREVIADA_EUSKERA));
        this.setDescripcion(json.getString(JSONTag.Alojamiento.TAG_DESCRIPCION));
        this.setDescripcionEuskera(json.getString(JSONTag.Alojamiento.TAG_DESCRIPCION_EUSKERA));
        this.setTelefono(json.getString(JSONTag.Alojamiento.TAG_TELEFONO));
        this.setDireccion(json.getString(JSONTag.Alojamiento.TAG_DIRECCION));
        this.setCalidad(json.getBoolean(JSONTag.Alojamiento.TAG_CALIDAD));
        this.setEmail(json.getString(JSONTag.Alojamiento.TAG_EMAIL));
        this.setWeb(json.getString(JSONTag.Alojamiento.TAG_WEB));
        this.setClub(json.getBoolean(JSONTag.Alojamiento.TAG_CLUB));
        this.setRestaurante(json.getBoolean(JSONTag.Alojamiento.TAG_RESTAURANTE));
        this.setAutocaravana(json.getBoolean(JSONTag.Alojamiento.TAG_AUTOCARAVANA));
        this.setTienda(json.getBoolean(JSONTag.Alojamiento.TAG_TIENDA));
        this.setCapacidad(json.getInt(JSONTag.Alojamiento.TAG_CAPACIDAD));
        this.setGastronomico(json.getBoolean(JSONTag.Alojamiento.TAG_GASTRONOMICO));
        this.setSurf(json.getBoolean(JSONTag.Alojamiento.TAG_SURFING));
        this.setCoordenadas(json.getString(JSONTag.Alojamiento.TAG_COORDENADAS));
        this.setCodigoTipo(json.getString(JSONTag.Alojamiento.TAG_CODIGO_TIPOS));
        this.setCodigoTipoEuskera(json.getString(JSONTag.Alojamiento.TAG_CODIGO_TIPOS_EUSKERA));
        this.setCodigoCategorias(json.getInt(JSONTag.Alojamiento.TAG_CODIGO_CATEGORIAS));
        this.setIdRelaciones(json.getInt(JSONTag.Alojamiento.TAG_ID_RELACIONES));
        return this;
    }
}
