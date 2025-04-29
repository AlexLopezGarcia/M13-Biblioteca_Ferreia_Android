package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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
    private List<Libro> listaLibros = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_libros, container, false);
        recyclerView = vista.findViewById(R.id.recycler_libros);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LibrosAdapter(listaLibros);
        recyclerView.setAdapter(adapter);

        cargarLibros();

        return vista;
    }

    private void cargarLibros() {
        String url = "https://mocki.io/v1/f8b35b74-c72f-4ba0-a5c8-34f8578f263d"; // Cambiar luego a tu API real

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest peticion = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listaLibros.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject objeto = response.getJSONObject(i);
                                listaLibros.add(new Libro(
                                        objeto.getString("titulo"),
                                        objeto.getString("autor"),
                                        objeto.getString("categoria")
                                ));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(peticion);
    }
}
