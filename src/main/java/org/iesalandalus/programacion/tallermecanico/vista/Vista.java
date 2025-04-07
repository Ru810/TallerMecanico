
package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Consola;

import java.time.LocalDate;
import java.util.List;

public interface Vista {
    Cliente leerCliente();

    Cliente leerClienteDNI();

    String leerNuevoNombre();

    String leerNuevoTelefono();

    Vehiculo leerVehiculo();

    Vehiculo leerVehiculoMatricula();

    Trabajo leerTrabajoVehiculo();

    int leerHoras();

    float leerPrecioMaterial();

    LocalDate leerFechaCierre();

    GestorEventos getGestorEventos();

    void comenzar() throws TallerMecanicoExcepcion;

    void terminar();

    Revision leerRevision();

    Mecanico leerMecanico();

    void notificarResultado(Evento evento, String texto, boolean exito);

    void mostrarCliente(Cliente cliente);

    void mostrarVehiculo(Vehiculo vehiculo);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarClientes(List<Cliente> clientes);

    void mostrarVehiculos(List<Vehiculo> vehiculos);

    void mostrarTrabajos(List<Trabajo> trabajos);

}
