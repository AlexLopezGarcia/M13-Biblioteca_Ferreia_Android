package com.m8_uf1.m13_biblioteca_ferreia_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.m8_uf1.m13_biblioteca_ferreia_android.ui.historial.HistorialFragment;
import com.m8_uf1.m13_biblioteca_ferreia_android.ui.libros.LibrosFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BibliotecaFerreria");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_fragmentos, new LibrosFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        if (item.getItemId() == R.id.historial) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_fragmentos, new HistorialFragment())
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.contenedor_fragmentos);
        if (!(current instanceof LibrosFragment)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedor_fragmentos, new LibrosFragment())
                    .commit();
        } else {
            super.onBackPressed();
        }
    }

}
