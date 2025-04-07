package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public interface ITrabajos {
    default List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    default List<Trabajo> get(Cliente cliente) {
        List<Trabajo>trabajosCliente = new ArrayList<>();

        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().getDni().equals(cliente.getDni())) {
                trabajosCliente.add(trabajo);
            }
        }
        return trabajosCliente;
    }

    default List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo>trabajosVehiculo = new ArrayList<>();

        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().matricula().equals(vehiculo.matricula())) {
                trabajosVehiculo.add(trabajo);
            }
        }
        return trabajosVehiculo;
    }

    default List<Revision> get(LocalDate fechaInicio, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return List.of();
    }

    default float getCosteTotal() {
        return 0;
    }

    default int getHorasTotales() {
        return 0;
    }

    default boolean comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) {
        return false;
    }

    default void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    default void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {

    }

    default void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {

    }

    default void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {

    }

    default void borrar(Revision revision) throws TallerMecanicoExcepcion {

    }

    default Revision buscar(Revision revision) {
        return null;
    }

    void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) throws TallerMecanicoExcepcion;

    Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion;

    Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws TallerMecanicoExcepcion;

    Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion;

    Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion;

    Trabajo buscar(Trabajo trabajo);

    default void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo,"No se puede borrar un trabajo nulo.");
        if (coleccionTrabajos.contains(trabajo)) {
            coleccionTrabajos.remove(trabajo);
        } else {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo igual.");
        }
    }
}
