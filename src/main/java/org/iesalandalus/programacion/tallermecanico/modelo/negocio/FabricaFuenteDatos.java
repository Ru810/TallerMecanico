package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.FuenteDatosMemoria;


public enum FabricaFuenteDatos {
    MEMORIA {
        @Override
        public FuenteDatosMemoria crear() {
            return new FuenteDatosMemoria();
        }
    },


    public IFuenteDatos; crear();

    public abstract IFuenteDatos crear();
}
