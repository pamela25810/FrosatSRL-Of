package com.checkcode.fsrl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.checkcode.fsrl.fragments.PreventaFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private TextView textViewUser, textViewPass;
    private View header;
    private Button btnAceptar,btnCancel;


    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cargarLayout();
        agregarToolbar();
        accionarCabecera();
        verificarLogueo();
        accionarNavigation();

    }


    private void cargarLayout() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
    }

    private void agregarToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void accionarCabecera() {
        header = navigationView.getHeaderView(0);
        header.findViewById(R.id.header_title);
        sharedPreferences = getSharedPreferences("miLogueo",MODE_PRIVATE);
    }

    private void verificarLogueo() {
        textViewUser=header.findViewById(R.id.usuario);
        textViewPass=header.findViewById(R.id.password);
        if (sharedPreferences == null){
            sharedPreferences = getSharedPreferences("miLogueo", MODE_PRIVATE);
        }
        String usuario = sharedPreferences.getString("user", "");
        String contrasenia = sharedPreferences.getString("password", "");
        if (usuario != null && !usuario.equals("")){
            if (contrasenia != null&& !contrasenia.equals("")) {
                textViewUser.setText("Bienvenid@: "+ usuario);
                textViewPass.setText(contrasenia);
            }
        }
        else {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    }

    private void accionarNavigation() {
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }
    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.nav_preventa:
                fragmentoGenerico = new PreventaFragment();
                //startActivity(new Intent(this, PreventaActivity.class));
                break;
            case R.id.nav_metas:
                startActivity(new Intent(this, MetasActivity.class));
                break;
            case R.id.nav_configuracion:
                startActivity(new Intent(this, ConfiguracionActivity.class));
                break;
            case R.id.nav_csesion:
                mostrarDialogoPersonal();
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    private void mostrarDialogoPersonal() {

        AlertDialog.Builder builder =new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater=getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog, null);
        builder.setView(view);
        AlertDialog dialog =builder.create();
        dialog.show();
        TextView txt= view.findViewById(R.id.contenido);
        txt.setText(R.string.aviso3_c);

        btnAceptar = view.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Cerrando Sesión", Toast.LENGTH_SHORT).show();
                cerrarSesion();
                dialog.dismiss();
            }
        });
        btnCancel = view.findViewById(R.id.btnCancelar);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void cerrarSesion() {
        try {
            if (sharedPreferences == null)
                sharedPreferences = getSharedPreferences("miLogueo", MODE_PRIVATE);

            sharedEditor = sharedPreferences.edit();
            sharedEditor.putString("user", "");
            sharedEditor.putString("password","");
            sharedEditor.commit();

            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            finish();

        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
