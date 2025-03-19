package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.util.Objects;

public class Vista {
    private Controlador controlador;

    public void setControlador(Controlador controlador) {
        Objects.requireNonNull(controlador, "ERROR. El controlador no puede ser nulo.");
        this.controlador = controlador;
    }

    public void comenzar() {
        Opcion opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion;
            ejecutar(opcion);
        } while (opcion != Opcion.SALIR);
        controlador.terminar();
    }

    public void terminar() {
        System.out.println("¡¡¡Hasta luego Lucassss!!!");
    }

    private void ejecutar(Opcion opcion) {
        try {
            switch (opcion) {
                case INSERTAR_CLIENTE -> insertarCliente();
                case INSERTAR_VEHICULO -> insertarVehiculo();
                case INSERTAR_REVISION -> insertarRevision();
                case BUSCAR_CLIENTE -> buscarCliente();
                case BUSCAR_VEHICULO -> buscarVehiculo();
                case BUSCAR_REVISION  -> buscarRevision();
                case MODIFICAR_CLIENTE -> modificarCliente();
                case ANADIR_HORAS_REVISION -> anadirHoras();
                case ANADIR_PRECIO_MATERIAL_REVISION -> anadirPrecioMaterial();
                case CERRAR_REVISION -> cerrarRevision();
                case BORRAR_CLIENTE -> borrarCliente();
                case BORRAR_VEHICULO -> borrarVehiculo();
                case BORRAR_REVISION -> borrarRevision();
                case LISTAR_CLIENTE -> listarClientes();
                case LISTAR_VEHICULOS -> listarVehiculos();
                case LISTAR_REVISIONES -> listarRevisiones();
                case LISTAR_REVISIONES_CLIENTES -> listarRevisionesClientes();
                case LISTAR_REVISIONES_VEHICULO -> listarRevisionesVehiculo();
                case SALIR -> salir();
            }
        } catch (Exception e) {
            System.out.printf("ERROR: %s%n" , e.getMessage());
        }
    }

    private void insertarCliente() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Cliente");
        controlador.insertar(Consola.leerCliente());
        System.out.println("Cliente insertado correctamente.");
    }

    private void insertarVehiculo() throws TallerMecanicoExcepcion {
        Consola.mostrarCabecera("Insertar Vehiculo");
        controlador.insertar(Consola.leerVehiculo());
        System.out.println("Vehículo insertado correctamente.");
    }

    private void insertarRevision() throws TallerMecanicoExcepcion {
        Consola.mostrarCabercera("Insertar Revisión");
        controlador.insertar(Consola.leerRevision());
        System.out.println("Revision insertada correctamente");
    }

    private void cerrarRevision() throws TallerMecanicoExcepcion

}
