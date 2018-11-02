package com.example.gsierra.project01;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.gsierra.project01.Fragments.BlueFragment;
import com.example.gsierra.project01.Fragments.ContenedorFragment;
import com.example.gsierra.project01.Fragments.FormularioFragment;
import com.example.gsierra.project01.Fragments.GreenFragment;
import com.example.gsierra.project01.Fragments.ListaClientesFragment;
import com.example.gsierra.project01.Helper.Utilidades;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BlueFragment.OnFragmentInteractionListener, GreenFragment.OnFragmentInteractionListener,FormularioFragment.OnFragmentInteractionListener, ContenedorFragment.OnFragmentInteractionListener,ListaClientesFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFrag = null;
        Boolean fSeleccionado=false;

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {
            miFrag = new BlueFragment();
            fSeleccionado = true;
        } else if (id == R.id.nav_slideshow) {
            miFrag = new GreenFragment();
            fSeleccionado = true;
        } else if (id == R.id.nav_manage) {
            miFrag = new FormularioFragment();
            fSeleccionado = true;
        } else if (id == R.id.nav_share) {
            miFrag = new ContenedorFragment();
            fSeleccionado = true;
        } else if (id == R.id.nav_send) {

            miFrag = new ListaClientesFragment();

            if (showSnackIfOffline())
            {fSeleccionado = true;}
            else
            { fSeleccionado = false; }

        } else if (id == R.id.salir){
        //AlertDialog.Builder  builder = new AlertDialog.Builder(this);
        AlertDialog.Builder builder =
                    new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle("Estás por salir de la aplicación");
        builder.setMessage("¿Confirmas la operación?");

        builder.setPositiveButton("salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();

        fSeleccionado = false;
        }

        if (fSeleccionado==true)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFrag).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private boolean showSnackIfOffline(){
        final boolean online = Utilidades.isOnline();

        runOnUiThread(new TimerTask() { //must run on main thread to update UI (show Snackbar), can be used only in Activity (FragmentActivity, AppCompatActivity...)
            @Override
            public void run() {
                if(!online)
                    Snackbar.make(findViewById(R.id.nav_view), "@string/No_Network_Connection", Snackbar.LENGTH_SHORT);

                else{
                    Snackbar.make(findViewById(R.id.nav_view), "@string/Connection", Snackbar.LENGTH_SHORT);

                }
            }
        });
        return   online;
    }
}
