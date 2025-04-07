
package org.iesalandalus.programacion.tallermecanico.modelo.cascada;


import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ModeloCascada implements Modelo {
    IClientes clientes;
    ITrabajos trabajos;
    IVehiculos vehiculos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos, "La factoría de la fuente de datos no puede ser nula.");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar() {
        System.out.println("Modelo comenzado.");
    }

    @Override
    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        Trabajo trabajo1 = null;
        if (trabajo instanceof Revision) {
            trabajo1 = new Revision(clientes.buscar(trabajo.getCliente()), vehiculos.buscar(trabajo.getVehiculo()), trabajo.getFechaInicio());
        }
        if (trabajo instanceof Mecanico) {
            trabajo1 = new Mecanico(clientes.buscar(trabajo.getCliente()), vehiculos.buscar(trabajo.getVehiculo()), trabajo.getFechaInicio());
        }
        trabajos.insertar(trabajo1);
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        return new Cliente(clientes.buscar(cliente));
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        return Trabajo.copiar(trabajos.buscar(trabajo));
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        clientes.modificar(cliente, nombre, telefono);
        return new Cliente(cliente);
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        Objects.requireNonNull(horas, "Las horas no pueden ser nulas.");
        trabajos.anadirHoras(trabajo, horas);
        return Trabajo.copiar(trabajo);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        Objects.requireNonNull(precioMaterial, "El precio del material no puede ser nulo.");
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
        return Trabajo.copiar(trabajo);
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");
        trabajos.cerrar(trabajo, fechaFin);
        return Trabajo.copiar(trabajo);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        for (Trabajo trabajoCliente : trabajos.get(cliente)) {
            vehiculos.borrar(trabajoCliente.getVehiculo());
            trabajos.borrar(trabajoCliente);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        for (Trabajo trabajoVehiculo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajoVehiculo);
        }
        vehiculos.borrar(vehiculo);
    }

    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes() {
        List<Cliente> clientesNuevos = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            clientesNuevos.add(new Cliente(cliente));
        }
        return clientesNuevos;
    }

    @Override
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> trabajosNuevos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            trabajosNuevos.add(Trabajo.copiar(trabajo));
        }
        return trabajosNuevos;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        List<Trabajo> trabajosNuevos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajosNuevos.add(Trabajo.copiar(trabajo));
        }
        return trabajosNuevos;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        List<Trabajo> trabajosNuevos = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajosNuevos.add(Trabajo.copiar(trabajo));
        }
        return trabajosNuevos;
    }
}
