package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

public class Libro {
    private final String titulo;
    private final String autor;
    private final String categoria;
    private final boolean estadoUso; // 🔥 añadido

    public Libro(String titulo, String autor, String categoria, boolean estadoUso) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.estadoUso = estadoUso;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public boolean isEstadoUso() { // 🔥 añadido
        return estadoUso;
    }
}
