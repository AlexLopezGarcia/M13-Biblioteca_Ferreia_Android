package com.m8_uf1.m13_biblioteca_ferreia_android.ui.historial;

public class Historial {
    private final Long libroId;
    private final String fechaPrestamo;
    private final String fechaDevolucion;

    public Historial(Long libroId, String fechaPrestamo, String fechaDevolucion) {
        this.libroId = libroId;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Long getLibroId() {
        return libroId;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }
}
