package com.checkcode.fsrl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MenuPrincipalActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener,
            DrawerLayout.DrawerListener {

        private DrawerLayout drawerLayout;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_menu_principal);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

            drawerLayout = findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawerLayout, toolbar, R.string.nav_drawer_open,
                    R.string.nav_drawer_close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = findViewById(R.id.navigation_view);
            navigationView.setNavigationItemSelectedListener(this);

            MenuItem menuItem = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(menuItem);
            menuItem.setChecked(true);

            drawerLayout.addDrawerListener(this);

            View header = navigationView.getHeaderView(0);
            header.findViewById(R.id.header_title).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MenuPrincipalActivity.this, getString(R.string.title_click),
                            Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onBackPressed() {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        int title;
        switch (menuItem.getItemId()) {
            case R.id.nav_preventa:
                startActivity(new Intent(this, PreventaActivity.class));
                break;
            case R.id.nav_metas:
                startActivity(new Intent(this, MetasActivity.class));
                break;
            case R.id.nav_configuracion:
                startActivity(new Intent(this, ConfiguracionActivity.class));
                break;
            case R.id.nav_csesion:
                Toast.makeText(MenuPrincipalActivity.this, "Cerrar Sesión", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalArgumentException("menu option not implemented!!");
        }

        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();


            setTitle(menuItem.getTitle());


        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
    @Override
    public void onDrawerSlide(@NonNull View view, float v) {
        //cambio en la posición del drawer
    }

    @Override
    public void onDrawerOpened(@NonNull View view) {
        //el drawer se ha abierto completamente
        Toast.makeText(this, getString(R.string.nav_drawer_open),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(@NonNull View view) {
        //el drawer se ha cerrado completamente
    }

    @Override
    public void onDrawerStateChanged(int i) {
        //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
    }


}