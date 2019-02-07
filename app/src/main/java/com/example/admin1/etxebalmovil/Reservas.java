package com.example.admin1.etxebalmovil;

import com.example.admin1.etxebalmovil.model.pojo.Reserva;

import java.util.Date;
import java.util.UUID;

public class Reservas {
    private UUID myID;

    private String nombreReserva;
    private String nombreCliente;
    private String nombreAlojamiento;
    private String firmaAlojamiento;
    private String direccion;
    private String email;
    private String telefono;
    private Date fechaInicio;
    private Date fechaFin;
    private int cantidad;

    public Reservas() {

        myID=UUID.randomUUID();
    }

    public Reservas(String nombreReserva, String nombreCliente, String nombreAlojamiento, Date fechaInicio, Date fechaFin, int cantidad) {
        this.nombreReserva = nombreReserva;
        this.nombreCliente = nombreCliente;
        this.nombreAlojamiento = nombreAlojamiento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        myID=UUID.randomUUID();
    }

    public Reservas(UUID myID, String nombreReserva, String nombreCliente, String nombreAlojamiento, String direccion, String email, String telefono, Date fechaInicio, Date fechaFin, int cantidad) {
        this.myID = myID;
        this.nombreReserva = nombreReserva;
        this.nombreCliente = nombreCliente;
        this.nombreAlojamiento = nombreAlojamiento;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidad = cantidad;
        myID=UUID.randomUUID();
    }

    public UUID getMyID() {
        return myID;
    }

    public void setMyID(UUID myID) {
        this.myID = myID;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreReserva() {
        return nombreReserva;
    }

    public void setNombreReserva(String nombreReserva) {
        this.nombreReserva = nombreReserva;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreAlojamiento() {
        return nombreAlojamiento;
    }

    public void setNombreAlojamiento(String nombreAlojamiento) {
        this.nombreAlojamiento = nombreAlojamiento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFirmaAlojamiento() {
        return firmaAlojamiento;
    }

    public void setFirmaAlojamiento(String firmaAlojamiento) {
        this.firmaAlojamiento = firmaAlojamiento;
    }
    public Reserva toReservaJSON()
    {
        Reserva reserva=new Reserva();
        reserva.setmNombreReserva(this.nombreReserva);
        reserva.setmNombreCliente(this.nombreCliente);
        reserva.setmFirmaAlojamiento(this.firmaAlojamiento);
        //TRaducir a DATE Sql
        reserva.setmCantidadPersonas(this.cantidad);

        return reserva;
    }
    public void fromReservaJSON(Reserva reserva)
    {
        this.nombreCliente=reserva.getmNombreCliente();
        this.firmaAlojamiento=reserva.getmFirmaAlojamiento();
        this.cantidad=reserva.getmCantidadPersonas();
        this.nombreReserva=reserva.getmNombreReserva();
        //TODO DATES
    }
}
