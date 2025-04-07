package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {

    private static final float FACTOR_HORA = 35F;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio){
        super(cliente, vehiculo, fechaInicio);
    }

    @Override
    public float getPrecio() {
        return 0;
    }

    public Revision (Revision revision){
        super(revision);
    }
    public float getPrecioEspecifico(){
        return (estaCerrado()) ? FACTOR_HORA * getHoras() : 0;

    }

    public boolean estaCerrado() {
        return false;
    }

    @Override
    public String toString() {
        String cadena;
        if(!estaCerrado()) {
            cadena = String.format("Revision -> %s - %s (%s - ): %d horas", getCliente(), getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        } else {
            cadena = String.format("Revision -> %s - %s (%s - %s): %d horas, %.2f", getVehiculo(), getFechaInicio().format(FORMATO_FECHA), getHoras());
        }
        return cadena;
    }
}