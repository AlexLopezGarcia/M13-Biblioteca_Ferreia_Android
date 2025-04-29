package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

public class Libro {
    private final String titulo;
    private final String autor;
    private final String categoria;

    public Libro(String titulo, String autor, String categoria) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
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
}
