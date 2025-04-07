
package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Trabajos implements ITrabajos {
    List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }

    @Override
    public void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente, "Cliente no nulo.");
        Objects.requireNonNull(vehiculo, "Vehiculo no nulo.");
        Objects.requireNonNull(fechaInicio, "Revisión no nulo.");

        for (Trabajo trabajoExistente : coleccionTrabajos) {
            if (!trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente)) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo en curso.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo)) {
                    throw new TallerMecanicoExcepcion("El vehículo está actualmente en el taller.");
                }
            }
            if (trabajoExistente.estaCerrado()) {
                if (trabajoExistente.getCliente().equals(cliente) && !fechaInicio.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El cliente tiene otro trabajo posterior.");
                }
                if (trabajoExistente.getVehiculo().equals(vehiculo) && !fechaInicio.isAfter(trabajoExistente.getFechaFin())) {
                    throw new TallerMecanicoExcepcion("El vehículo tiene otro trabajo posterior.");
                }
            }
        }
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "No puedo añadir horas a un trabajo nulo.");
        Objects.requireNonNull(horas, "Las horas de la revision no pueden ser nulas.");
        Trabajo trabajo1;
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo1  instanceof Revision revision) {
            if (revision.estaCerrado()) {
                throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
            }
            revision.anadirHoras(horas);
        }
        if (trabajo1 instanceof Mecanico mecanico) {
            if (mecanico.estaCerrado()) {
                throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
            }
            mecanico.anadirHoras(horas);
        }
        return trabajo1;
    }

    @Override
    public Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Trabajo trabajoAux = buscar(null);
        Iterator<Trabajo> iterador = coleccionTrabajos.iterator();
        boolean encontrado = comprobarRevision(null, null, null);
        if (iterador.hasNext() && !encontrado) {
            trabajoAux = iterador.next();
            if (trabajoAux.getVehiculo().equals(vehiculo) && !trabajoAux.estaCerrado()) {
                encontrado = true;
            }
        }
        if (trabajoAux == buscar(null)) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }
        return trabajoAux;
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "No puedo añadir precio del material a un trabajo nulo.");
        Objects.requireNonNull(precioMaterial, "El precio del material no puede ser nulo.");
        Trabajo trabajo1;
        trabajo1 = getTrabajoAbierto(trabajo.getVehiculo());
        if (trabajo1 instanceof Revision) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio al material para este tipo de trabajos.");
        }
        if (trabajo1 instanceof Mecanico mecanico) {
            mecanico.anadirPrecioMaterial(precioMaterial);
        }
        return trabajo1;
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new TallerMecanicoExcepcion("No existe ningún trabajo abierto para dicho vehículo.");
        }
        trabajo.cerrar(fechaFin);
        return trabajo;
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        if (coleccionTrabajos.contains(trabajo)) {
            int indexTrabajo = coleccionTrabajos.indexOf(trabajo);
            return coleccionTrabajos.get(indexTrabajo);
        } else {
            return buscar(null);
        }
    }

}
