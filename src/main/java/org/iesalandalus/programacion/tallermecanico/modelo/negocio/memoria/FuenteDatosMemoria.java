package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;



public class FuenteDatosMemoria implements IFuenteDatos {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Trabajos trabajos;


    public FuenteDatosMemoria() {
        this.clientes = new Clientes();
        this.vehiculos = new Vehiculos();
        this.trabajos = new Trabajos();
    }

    @Override
    public IClientes crearClientes() {
        return clientes;
    }

    @Override
    public IVehiculos crearVehiculos() {
        return vehiculos;
    }

    @Override
    public ITrabajos crearTrabajos() {
        return trabajos;
    }
}
