package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.List;

public interface IVehiculos {
    List<Vehiculo> get();

    void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    Vehiculo buscar(Vehiculo vehiculo);

    void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion;
}
