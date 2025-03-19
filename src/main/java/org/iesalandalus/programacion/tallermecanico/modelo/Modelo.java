package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Clientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Revisiones;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.Vehiculos;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private Clientes clientes;
    private Vehiculos vehiculos;
    private Revisiones revisiones;

    public Modelo() {
        clientes = new Clientes();
        vehiculos = new Vehiculos();
        revisiones = new Revisiones();
    }

    public void comenzar() {
        System.out.println("El modelo ha comenzado.");
    }

    public void terminar() {
        System.out.println("El modelo ha terminado.");
    }

    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    public void insertar(Revision revision) throws TallerMecanicoExcepcion {
        Cliente clienteEncontrado = clientes.buscar(revision.getCliente());
        Vehiculo vehiculoEncontrado = vehiculos.buscar(revision.getVehiculo());

        if (clienteEncontrado != null && vehiculoEncontrado != null) {
            revisiones.insertar(new Revision(revision));
        } else {
            throw new IllegalArgumentException("Cliente o vehículo no encontrado.");
        }
    }

    public Cliente buscar(Cliente cliente) {
        Cliente c = clientes.buscar(cliente);
        return (c == null) ? null : new Cliente(c);
    }

    public Vehiculo buscar(Vehiculo vehiculo) {
        return vehiculos.buscar(vehiculo);
    }

    public Revision buscar(Revision revision) {
        Revision r = revisiones.buscar(revision);
        return (r == null) ? null : new Revision(r);
    }

    public void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        clientes.modificar(cliente, nombre, telefono);
    }

    public void anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        revisiones.anadirHoras(revision, horas);
    }

    public void anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion {
        revisiones.anadirPrecioMaterial(revision, precioMaterial);
    }

    public void cerrar(Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        revisiones.cerrar(revision, fechaFin);
    }

    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Revision> revisionesCliente = revisiones.get(cliente);
        for (Revision revision : revisionesCliente) {
            revisiones.borrar(revision);
        }
        clientes.borrar(cliente);
    }

    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Revision> revisionesVehiculo = revisiones.get(vehiculo);
        for (Revision revision : revisionesVehiculo) {
            revisiones.borrar(revision);
        }
        vehiculos.borrar(vehiculo);
    }

    public void borrar(Revision revision) throws TallerMecanicoExcepcion {
        revisiones.borrar(revision);
    }

    public List<Cliente> getClientes() {
        List<Cliente> lista = clientes.get();
        List<Cliente> copia = new ArrayList<>();
        for (Cliente c : lista) {
            copia.add(new Cliente(c));
        }
        return copia;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    public List<Revision> getRevisiones() {
        List<Revision> lista = revisiones.get();
        List<Revision> copia = new ArrayList<>();
        for (Revision r : lista) {
            copia.add(new Revision(r));
        }
        return copia;
    }

    public List<Revision> getRevisiones(Cliente cliente) {
        List<Revision> lista = revisiones.get(cliente);
        List<Revision> copia = new ArrayList<>();
        for (Revision r : lista) {
            copia.add(new Revision(r));
        }
        return copia;
    }

    public List<Revision> getRevisiones(Vehiculo vehiculo) {
        List<Revision> lista = revisiones.get(vehiculo);
        List<Revision> copia = new ArrayList<>();
        for (Revision r : lista) {
            copia.add(new Revision(r));
        }
        return copia;
    }
}