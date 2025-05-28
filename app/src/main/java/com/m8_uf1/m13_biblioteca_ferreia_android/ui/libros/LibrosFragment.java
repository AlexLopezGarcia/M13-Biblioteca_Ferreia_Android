package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.*;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import com.m8_uf1.m13_biblioteca_ferreia_android.R;

import org.json.*;

import java.util.*;

public class LibrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LibrosAdapter adapter;
    private final List<Libro> listaLibros = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_libros, container, false);

        recyclerView = vista.findViewById(R.id.recycler_libros);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new LibrosAdapter(listaLibros);
        recyclerView.setAdapter(adapter);

        cargarLibrosDesdeAPI();

        return vista;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.inicio) {
            Toast.makeText(getContext(), "Inicio seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.perfil) {
            Toast.makeText(getContext(), "Perfil seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.historial) {
            Toast.makeText(getContext(), "Historial seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.ajustes) {
            Toast.makeText(getContext(), "Ajustes seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.cerrar_sesion) {
            Toast.makeText(getContext(), "Cerrar sesión seleccionado", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void cargarLibrosDesdeAPI() {
        String url = "http://192.168.18.44:9090/public/libros";

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest peticion = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    listaLibros.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject libroJson = response.getJSONObject(i);
                            String titulo = libroJson.getString("titulo");
                            String autor = libroJson.getString("autor");
                            String categoria = libroJson.getString("categoria");
                            boolean estadoUso = libroJson.getBoolean("estadoUso");

                            listaLibros.add(new Libro(titulo, autor, categoria, estadoUso));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(getContext(), "No se pudo conectar con el servidor", Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(peticion);
    }
}
