package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.m8_uf1.m13_biblioteca_ferreia_android.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LibrosFragment extends Fragment {

    private RecyclerView recyclerView;
    private LibrosAdapter adapter;
    private final List<Libro> listaLibros = new ArrayList<>();

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

    private void cargarLibrosDesdeAPI() {
        String url = "http://192.168.17.223:9090/public/libros";

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
                            boolean estadoUso = libroJson.getBoolean("estadoUso"); // 🔥 nuevo

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
