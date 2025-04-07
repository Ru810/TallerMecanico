
package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public enum FabricaVista {
    TEXTO {
        @Override
        public VistaTexto crear() {
            return new VistaTexto();
        }
    };

    public abstract VistaTexto crear();
}
