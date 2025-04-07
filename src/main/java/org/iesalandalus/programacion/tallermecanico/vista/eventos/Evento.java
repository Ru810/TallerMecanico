
package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.Map;
import java.util.TreeMap;

public enum Evento {
    INSERTAR_CLIENTE (11, "Insertar cliente."),
    BUSCAR_CLIENTE(12, "Buscar cliente."),
    BORRAR_CLIENTE(13, "Borrar cliente."),
    LISTAR_CLIENTES(14, "Listar clientes."),
    MODIFICAR_CLIENTE(15, "Mostrar cliente."),
    INSERTAR_VEHICULO(21, "Insertar vehículo."),
    BUSCAR_VEHICULO(22, "Buscar vehículo."),
    BORRAR_VEHICULO(23, "Borrar vehículo."),
    LISTAR_VEHICULOS(24, "Listar vehículos."),
    INSERTAR_REVISION(31, "Insertar revisión."),
    INSERTAR_MECANICO(32, "Insertar mecánico."),
    BUSCAR_TRABAJO(33, "Buscar revisión."),
    BORRAR_TRABAJO(34, "Borrar revisión."),
    LISTAR_TRABAJOS(35, "Listar revisiones."),
    LISTAR_TRABAJOS_CLIENTE(36, "Listar revisiones de clientes."),
    LISTAR_TRABAJOS_VEHICULO(37, "Listar revisiones de vehículos."),
    ANADIR_HORAS_TRABAJO(38, "Añadir las horas a la revisión."),
    ANADIR_PRECIO_MATERIAL_TRABAJO(39, "Añadir el precio del material de la revisión."),
    CERRAR_TRABAJO(40, "Cerrar revisión."),
    SALIR(0, "Salir.");

    private int codigo;
    private String texto;
    private static Map<Integer, Evento> eventos = new TreeMap<>();

    static {
        for (Evento evento : values()) {
            eventos.put(evento.codigo, evento);
        }
    }

    private Evento(int codigo, String texto) {
        this.codigo = codigo;
        this.texto = texto;
    }

    public static boolean esValido(int codigo) {
        return eventos.containsKey(codigo);
    }

    public static Evento get(int codigo) {
        if(!esValido(codigo)) {
            throw new IllegalArgumentException("El número de la opción no es correcto.");
        }
        return eventos.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("%d, %s", codigo, texto);
    }
}
