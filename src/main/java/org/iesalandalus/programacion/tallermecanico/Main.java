
package org.iesalandalus.programacion.tallermecanico;

import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public class Main {
    public static void main(String[] args) {
        Modelo modelo = FabricaModelo.CASCADA.crear(FabricaFuenteDatos.MEMORIA);
        VistaTexto vista = FabricaVista.TEXTO.crear();
        Controlador controlador = new Controlador(modelo, vista);
        try {
            controlador.comenzar();
        } catch (TallerMecanicoExcepcion e) {
            System.out.println(e.getMessage());
        }
    }
}
