package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos  {

    private List<Vehiculo> vehiculos;

    public Vehiculos() {
        vehiculos = new ArrayList<>();
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(vehiculos);
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (buscar(vehiculo) != null) {
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        vehiculos.add(vehiculo);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        for (Vehiculo vehiculo1 : vehiculos) {
            if (vehiculo1.matricula().equals(vehiculo.matricula())) {
                return vehiculo1;
            }
        }
        return null;
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        Vehiculo encontrado = buscar(vehiculo);
        if (encontrado == null) {
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        vehiculos.remove(encontrado);
    }
}