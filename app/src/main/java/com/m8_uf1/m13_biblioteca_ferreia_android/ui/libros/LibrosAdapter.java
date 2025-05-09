package com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.m8_uf1.m13_biblioteca_ferreia_android.R;
import java.util.List;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.LibroViewHolder> {

    private final List<Libro> listaLibros;

    public LibrosAdapter(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new LibroViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libro = listaLibros.get(position);

        holder.textoTitulo.setText(libro.getTitulo());
        holder.textoAutor.setText(libro.getAutor());
        holder.textoCategoria.setText(libro.getCategoria());
        holder.textoEstadoUso.setText(libro.isEstadoUso() ? "Disponible" : "No disponible");
    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }

    static class LibroViewHolder extends RecyclerView.ViewHolder {
        TextView textoTitulo, textoAutor, textoCategoria, textoEstadoUso;

        public LibroViewHolder(@NonNull View itemView) {
            super(itemView);
            textoTitulo = itemView.findViewById(R.id.texto_titulo);
            textoAutor = itemView.findViewById(R.id.texto_autor);
            textoCategoria = itemView.findViewById(R.id.texto_categoria);
            textoEstadoUso = itemView.findViewById(R.id.texto_estado_uso);
        }
    }
}
