package com.example.admin1.etxebalmovil;

import java.io.Serializable;
import java.util.UUID;

public class Alojamientos {

    private UUID myID;

    private String firma;
    private String nombre;
    private String descripcion;
    private String descripcionEuskera;
    private String Telefono;
    private String direccion;
    private String email;
    private String web;
    private boolean club;
    private boolean restaurante;
    private boolean autocaravana;
    private boolean tienda;
    private int capacidad;
    private boolean gastronomico;
    private boolean surf;
    private String coordenadas;
    private String tipo;
    private String tipoEuskera;
    private String provincia;
    private String municipio;
    private int cp;

    public Alojamientos(String firma, UUID uuid) {

        this.firma = firma;
        myID=uuid;
    }
    public Alojamientos()
    {
        myID=UUID.randomUUID();
        firma=myID.toString();
    }

    public Alojamientos(String firma, String nombre, String descripcion, String descripcionEuskera, String telefono, String direccion, String email, String web, boolean club, boolean restaurante, boolean autocaravana, boolean tienda, int capacidad, boolean gastronomico, boolean sufring, String coordenadas, String tipo, String tipoEuskera, String provincia, String municipio, int cp) {
        this.firma = firma;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionEuskera = descripcionEuskera;
        Telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.web = web;
        this.club = club;
        this.restaurante = restaurante;
        this.autocaravana = autocaravana;
        this.tienda = tienda;
        this.capacidad = capacidad;
        this.gastronomico = gastronomico;
        this.surf = sufring;
        this.coordenadas = coordenadas;
        this.tipo = tipo;
        this.tipoEuskera = tipoEuskera;
        this.provincia = provincia;
        this.municipio = municipio;
        this.cp = cp;
    }

    public UUID getMyID() {
        return myID;
    }

    public void setMyID(UUID myID) {
        this.myID = myID;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionEuskera() {
        return descripcionEuskera;
    }

    public void setDescripcionEuskera(String descripcionEuskera) {
        this.descripcionEuskera = descripcionEuskera;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public boolean isClub() {
        return club;
    }

    public void setClub(boolean club) {
        this.club = club;
    }

    public boolean isRestaurante() {
        return restaurante;
    }

    public void setRestaurante(boolean restaurante) {
        this.restaurante = restaurante;
    }

    public boolean isAutocaravana() {
        return autocaravana;
    }

    public void setAutocaravana(boolean autocaravana) {
        this.autocaravana = autocaravana;
    }

    public boolean isTienda() {
        return tienda;
    }

    public void setTienda(boolean tienda) {
        this.tienda = tienda;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isGastronomico() {
        return gastronomico;
    }

    public void setGastronomico(boolean gastronomico) {
        this.gastronomico = gastronomico;
    }

    public boolean isSurf() {
        return surf;
    }

    public void setSufring(boolean sufring) {
        this.surf = sufring;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoEuskera() {
        return tipoEuskera;
    }

    public void setTipoEuskera(String tipoEuskera) {
        this.tipoEuskera = tipoEuskera;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }
}
