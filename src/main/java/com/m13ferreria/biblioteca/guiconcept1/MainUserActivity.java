package com.m13ferreria.biblioteca.guiconcept1;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.m13ferreria.biblioteca.guiconcept1.databinding.ActivityMainUserBinding;
import com.m13ferreria.biblioteca.guiconcept1.ui.home.HomeFragment;

public class MainUserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainUserBinding binding;

    public DrawerLayout drawer;
    public NavigationView navigationView;

    public FragmentTransaction transaction;
    public FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer =findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                0,0);

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_user, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_user);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * @param item The selected item 
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        switch (item.getItemId()) {
            case R.id.nav_home:
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.nav_host_fragment_content_main_user, homeFragment);
                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_search:
                SearchFragment searchFragment = new SearchFragment();
                transaction.replace(R.id.nav_host_fragment_content_main_user, searchFragment);
                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;


            case R.id.nav_lent:
                LentFragment lentFragment = new LentFragment();
                transaction.replace(R.id.nav_host_fragment_content_main_user, lentFragment);
                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;




            case R.id.nav_settings:
                UserConfigFragment userConfigFragment = new UserConfigFragment();
                transaction.replace(R.id.nav_host_fragment_content_main_user, userConfigFragment);
                transaction.commit();
                drawer.closeDrawer(GravityCompat.START);
                return true;
            case R.id.nav_logout:
                // TODO
        }
        return false;
    }
}