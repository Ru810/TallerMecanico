package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Revision {
    private static final float PRECIO_HORA = 30F;
    private static final float PRECIO_DIA = 10F;
    private static final float PRECIO_MATERIAL = 1.5F;
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int horas;
    private float precioMaterial;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        this.cliente = Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        this.vehiculo = Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        this.fechaInicio = Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser futura.");
        }
        this.horas = 0;
        this.precioMaterial = 0;
    }

    public Revision(Revision revisionCopia) {
        Objects.requireNonNull(revisionCopia, "La revisión no puede ser nula.");
        this.cliente = new Cliente(revisionCopia.cliente);
        this.vehiculo = revisionCopia.vehiculo;
        this.fechaInicio = revisionCopia.fechaInicio;
        this.fechaFin = revisionCopia.fechaFin;
        this.horas = revisionCopia.horas;
        this.precioMaterial = revisionCopia.precioMaterial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public int getHoras() {
        return horas;
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public double getPrecio() {
        return (horas * PRECIO_HORA) + (getDias() * PRECIO_DIA) + (precioMaterial * PRECIO_MATERIAL);
    }

    public void anadirHoras(int horas) throws TallerMecanicoExcepcion {
        if (horas <= 0) {
            throw new IllegalArgumentException("Las horas a añadir deben ser mayores que cero.");
        }
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir horas, ya que la revisión está cerrada.");
        }
        this.horas += horas;
    }

    public void anadirPrecioMaterial(float precio) throws TallerMecanicoExcepcion {
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que la revisión está cerrada.");
        }
        this.precioMaterial += precio;
    }

    public void cerrar(LocalDate fechaFin) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");

        if (fechaFin.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fin no puede ser futura.");
        }
        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser anterior a la fecha de inicio.");
        }
        if (estaCerrada()) {
            throw new TallerMecanicoExcepcion("La revisión ya está cerrada.");
        }
        this.fechaFin = fechaFin;
    }

    public boolean estaCerrada() {
        return fechaFin != null;
    }

    private float getDias() {
        long dias = (fechaFin != null) ? fechaInicio.until(fechaFin).getDays() : 0;
        return (float) dias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Revision revision = (Revision) o;
        return horas == revision.horas &&
                Float.compare(revision.precioMaterial, precioMaterial) == 0 &&
                cliente.equals(revision.cliente) &&
                vehiculo.equals(revision.vehiculo) &&
                fechaInicio.equals(revision.fechaInicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, vehiculo, fechaInicio, horas, precioMaterial);
    }

    @Override
    public String toString() {
        if (estaCerrada() == true) {
            String cadenaFechaFin = (fechaFin != null) ? fechaFin.format(FORMATO_FECHA) : "";
            return String.format("%s - %s: (%s - %s), %d horas, %.2f € en material, %.2f € total",
                    cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), cadenaFechaFin, horas, precioMaterial, getPrecio());
        } else {
            String cadenaFechaFin = (fechaFin != null) ? fechaFin.format(FORMATO_FECHA) : "";
            return String.format("%s - %s: (%s - ), 0 horas, 0,00 € en material",
                    cliente, vehiculo, fechaInicio.format(FORMATO_FECHA), cadenaFechaFin, horas, precioMaterial, getPrecio());
        }
    }
}
