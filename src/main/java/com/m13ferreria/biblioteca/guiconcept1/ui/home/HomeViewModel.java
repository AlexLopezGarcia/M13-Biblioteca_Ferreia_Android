package com.m13ferreria.biblioteca.guiconcept1.ui.home;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<String>> mList;
    private RequestQueue requestQueue;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Este texto está escrito en el HomeViewModel.java");

        mList = new MutableLiveData<>();
        requestQueue = Volley.newRequestQueue(application);
        fetchLibros();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getList() {
        return mList;
    }

    private void fetchLibros() {
        String url = "http://localhost:9090/libros"; // Ajusta según tu entorno

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<String> libros = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject libro = response.getJSONObject(i);
                                String titulo = libro.getString("titulo");
                                String autor = libro.getString("autor");
                                libros.add(titulo + " - " + autor);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mList.setValue(libros);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        mList.setValue(new ArrayList<>()); // Lista vacía en caso de error
                    }
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}