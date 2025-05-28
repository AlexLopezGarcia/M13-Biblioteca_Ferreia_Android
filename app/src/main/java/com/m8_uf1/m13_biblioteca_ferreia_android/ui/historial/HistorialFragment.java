package com.m8_uf1.m13_biblioteca_ferreia_android.ui.historial;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistorialFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistorialAdapter adapter;
    private final List<Historial> listaHistorial = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_historial, container, false);

        recyclerView = vista.findViewById(R.id.recycler_historial);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistorialAdapter(listaHistorial); // aquí usas la lista correcta
        recyclerView.setAdapter(adapter);

        cargarHistorial();

        return vista;
    }

    private void cargarHistorial() {
        String url = "http://192.168.17.223:9090/public/historial";

        RequestQueue queue = Volley.newRequestQueue(requireContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET, url, null,
                response -> {
                    listaHistorial.clear();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject item = response.getJSONObject(i);
                            long libroId = item.getLong("libroId");
                            String prestamo = item.optString("fechaPrestamo", "N/A");
                            String devolucion = item.optString("fechaDevolucion", "No devuelto");

                            listaHistorial.add(new Historial(libroId, prestamo, devolucion));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    adapter.notifyDataSetChanged();
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(getContext(), "Error al cargar historial", Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(request);

    }
}
