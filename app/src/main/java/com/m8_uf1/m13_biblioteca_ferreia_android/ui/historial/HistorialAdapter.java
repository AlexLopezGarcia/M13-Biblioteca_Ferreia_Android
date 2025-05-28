package com.m8_uf1.m13_biblioteca_ferreia_android.ui.historial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.m8_uf1.m13_biblioteca_ferreia_android.R;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder> {

    private final List<Historial> listaHistorial;

    public HistorialAdapter(List<Historial> listaHistorial) {
        this.listaHistorial = listaHistorial;
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new HistorialViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        Historial historial = listaHistorial.get(position);
        holder.textoTitulo.setText("Libro ID: " + historial.getLibroId());
        holder.textoPrestamo.setText("Prestado: " + historial.getFechaPrestamo());
        holder.textoDevolucion.setText("Devuelto: " + historial.getFechaDevolucion());
    }

    @Override
    public int getItemCount() {
        return listaHistorial.size();
    }

    public static class HistorialViewHolder extends RecyclerView.ViewHolder {
        TextView textoTitulo, textoPrestamo, textoDevolucion;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            textoTitulo = itemView.findViewById(R.id.texto_historial_titulo);
            textoPrestamo = itemView.findViewById(R.id.texto_historial_prestamo);
            textoDevolucion = itemView.findViewById(R.id.texto_historial_devolucion);
        }
    }
}
