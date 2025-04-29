package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.m8_uf1.m13_biblioteca_ferreia_android.R;

import java.util.ArrayList;
import java.util.List;

public class LibrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LibrosAdapter adapter;
    private final List<Libro> listaLibros = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_libros, container, false);

        // Configuramos el RecyclerView
        recyclerView = vista.findViewById(R.id.recycler_libros);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LibrosAdapter(listaLibros);
        recyclerView.setAdapter(adapter);

        // Llenamos la lista de prueba
        cargarLibrosDePrueba();

        return vista;
    }

    private void cargarLibrosDePrueba() {
        listaLibros.add(new Libro("Don Quijote", "Miguel de Cervantes", "Novela clásica"));
        listaLibros.add(new Libro("Cien Años de Soledad", "Gabriel García Márquez", "Realismo mágico"));
        listaLibros.add(new Libro("El Hobbit", "J.R.R. Tolkien", "Fantasía"));
        listaLibros.add(new Libro("1984", "George Orwell", "Distopía"));
        listaLibros.add(new Libro("La Sombra del Viento", "Carlos Ruiz Zafón", "Misterio"));
        adapter.notifyDataSetChanged();
    }
}
