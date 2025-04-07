
package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores = new HashMap<>();

    public GestorEventos(Evento ... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento ... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            usuarios.add(receptor);
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento ... eventos) {
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            usuarios.remove(receptor);
        }
    }

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "Los eventos no pueden ser nulos.");
        List<ReceptorEventos> usuarios = receptores.get(evento);
        for (ReceptorEventos receptor : usuarios) {
            receptor.actualizar(evento);
        }
    }
}
