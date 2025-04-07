package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import java.util.List;

public interface IClientes {
    List<Cliente> get();

    void insertar(Cliente cliente) throws TallerMecanicoExcepcion;

    Cliente buscar(Cliente cliente);

    void borrar(Cliente cliente) throws TallerMecanicoExcepcion;

    void modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion;
}
