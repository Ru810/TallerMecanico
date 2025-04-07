
package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VistaTexto implements Vista {
    private Controlador controlador;
    private GestorEventos gestorEventos;

    public VistaTexto(){
        gestorEventos = new GestorEventos(Evento.values());
    }

    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }

    @Override
    public void comenzar() throws TallerMecanicoExcepcion {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
        controlador.terminar();
    }

    @Override
    public void terminar(){
        System.out.println("Me doy el piro, vampiro!");
    }

    private void ejecutar(Evento evento) throws TallerMecanicoExcepcion {

        switch(evento){
            case INSERTAR_CLIENTE -> gestorEventos.notificar(Evento.INSERTAR_CLIENTE);
            case INSERTAR_VEHICULO -> gestorEventos.notificar(Evento.INSERTAR_VEHICULO);
            case INSERTAR_REVISION -> gestorEventos.notificar(Evento.INSERTAR_REVISION);
            case INSERTAR_MECANICO -> gestorEventos.notificar(Evento.INSERTAR_MECANICO);
            case BUSCAR_CLIENTE -> gestorEventos.notificar(Evento.BUSCAR_CLIENTE);
            case BUSCAR_VEHICULO -> gestorEventos.notificar(Evento.BUSCAR_VEHICULO);
            case BUSCAR_TRABAJO -> gestorEventos.notificar(Evento.BUSCAR_TRABAJO);
            case MODIFICAR_CLIENTE -> gestorEventos.notificar(Evento.MODIFICAR_CLIENTE);
            case ANADIR_HORAS_TRABAJO -> gestorEventos.notificar(Evento.ANADIR_HORAS_TRABAJO);
            case ANADIR_PRECIO_MATERIAL_TRABAJO -> gestorEventos.notificar(Evento.ANADIR_PRECIO_MATERIAL_TRABAJO);
            case BORRAR_CLIENTE -> gestorEventos.notificar(Evento.BORRAR_CLIENTE);
            case BORRAR_TRABAJO -> gestorEventos.notificar(Evento.BORRAR_TRABAJO);
            case CERRAR_TRABAJO -> gestorEventos.notificar(Evento.CERRAR_TRABAJO);
            case BORRAR_VEHICULO -> gestorEventos.notificar(Evento.BORRAR_VEHICULO);
            case LISTAR_CLIENTES -> gestorEventos.notificar(Evento.LISTAR_CLIENTES);
            case LISTAR_TRABAJOS -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS);
            case LISTAR_TRABAJOS_CLIENTE -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS_CLIENTE);
            case LISTAR_TRABAJOS_VEHICULO -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS_VEHICULO);
            case LISTAR_VEHICULOS -> gestorEventos.notificar(Evento.LISTAR_VEHICULOS);
            case SALIR -> gestorEventos.notificar(Evento.SALIR);

        }
    }

    @Override
    public Cliente leerCliente(){
        Cliente cliente = new Cliente(leerClienteDNI());
        cliente.setNombre(leerNuevoNombre());
        cliente.setTelefono(leerNuevoTelefono());
        return cliente;

    }
    public float leerPrecioMaterial(){
        System.out.print("Introduzca el precio del material:");
        return Entrada.real();
    }

    public int leerHoras(){
        System.out.print("Introduzca la cantidad de horas.");
        return Entrada.entero();
    }

    @Override
    public Cliente leerClienteDNI(){
        System.out.print("Escriba el DNI del cliente: ");
        String dniCliente = Entrada.cadena();
        return Cliente.get(dniCliente);
    }

    @Override
    public String leerNuevoNombre(){
        return Consola.leerCadena("Introduzca un nombre para el cliente: ");
    }

    @Override
    public String leerNuevoTelefono(){
        return Consola.leerCadena("Introduzca un nuevo teléfono para el cliente.");
    }

    @Override
    public Vehiculo leerVehiculo(){
        String marca = Consola.leerCadena("Introduzca el marca del vehiculo: ");
        String modelo = Consola.leerCadena("Introduzca el modelo del vehiculo: ");
        String matricula = Consola.leerCadena("Introduzca la matricula del vehiculo: ");
        return new Vehiculo(marca,modelo,matricula);

    }

    @Override
    public Vehiculo leerVehiculoMatricula(){
        return Vehiculo.get(Consola.leerCadena("Introduzca la matrícula del vehiculo: "));
    }

    @Override
    public Revision leerRevision(){
        return new Revision(leerClienteDNI(),leerVehiculoMatricula(),Consola.leerFecha("Introduzca la fecha de la revisión: "));
    }

    @Override
    public Mecanico leerMecanico(){
        return new Mecanico(leerCliente(),leerVehiculoMatricula(),Consola.leerFecha("Introduzca la fecha de la revisión: "));
    }

    @Override
    public Trabajo leerTrabajoVehiculo(){
        Vehiculo vehiculo = leerVehiculo();
        return Trabajo.copiar(Trabajo.get(vehiculo));
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if (exito){
            System.out.println(texto);
        }else {
            System.out.printf("ERROR: %s%n", texto);
        }

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes){
        Consola.mostrarCabecera("Listado de clientes");
        if (!clientes.isEmpty()){
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    public LocalDate leerFechaCierre(){
        return Consola.leerFecha("Introduzca la fecha de cierre.");
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos){
        Objects.requireNonNull(vehiculos,"La lista no puede ser nula.");
        Consola.mostrarCabecera("Listado de vehículos");
        if (!vehiculos.isEmpty()){
            for (Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos){
        Objects.requireNonNull(trabajos,"La lista no pude ser nula.");
        Consola.mostrarCabecera("Listado de revisiones");
        if (!trabajos.isEmpty()){
            for (Trabajo trabajo : trabajos){
                System.out.println(trabajo);
            }

        }else {
            System.out.println("La lista esta vacía.");
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente){
        Objects.requireNonNull(cliente,"El cliente no puede ser nulo.");
        System.out.println(cliente);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo){
        Objects.requireNonNull(trabajo,"El trabajo no puede ser nulo.");
        System.out.println(trabajo);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo,"El vehiculo no puede ser nulo.");
        System.out.println(vehiculo);
    }
}
