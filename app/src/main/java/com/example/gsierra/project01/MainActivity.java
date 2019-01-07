package com.example.gsierra.project01;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.gsierra.project01.Fragments.BlueFragment;
import com.example.gsierra.project01.Fragments.ConfiguracionActivity;
import com.example.gsierra.project01.Fragments.ContenedorFragment;
import com.example.gsierra.project01.Fragments.DatosPersonalesFragment;
import com.example.gsierra.project01.Fragments.EditClienteFragment;
import com.example.gsierra.project01.Fragments.EstatusFragment;
import com.example.gsierra.project01.Fragments.FormularioFragment;
import com.example.gsierra.project01.Fragments.GreenFragment;
import com.example.gsierra.project01.Fragments.ListaClientesFragment;
import com.example.gsierra.project01.Helper.Utilidades;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BlueFragment.OnFragmentInteractionListener,
        GreenFragment.OnFragmentInteractionListener,FormularioFragment.OnFragmentInteractionListener,
        ContenedorFragment.OnFragmentInteractionListener,ListaClientesFragment.OnFragmentInteractionListener,
        EditClienteFragment.OnFragmentInteractionListener,
        DatosPersonalesFragment.OnFragmentInteractionListener,EstatusFragment.OnFragmentInteractionListener{
    private FloatingActionButton fab;
    private PendingIntent pendingIntent;
    private final static String CHANNEL_ID="NOTIFICACION";
    private final static int NOTIFICACION_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicio coneste fragment
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_main,new EstatusFragment()).addToBackStack(null).commit();


        fab = (FloatingActionButton) findViewById(R.id.fab);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        ImageView imUser = (ImageView)headerView.findViewById(R.id.ivUser);

        imUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment miFrag = new DatosPersonalesFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFrag).addToBackStack(null).commit();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);

            }
        });

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fm = getSupportFragmentManager();
        Fragment currentFragment = fm.findFragmentById(R.id.content_main);
        if (currentFragment instanceof EstatusFragment)
        {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            }else
            {
                salir();
            }
        }
        else if (currentFragment instanceof FormularioFragment) {
            View vista = currentFragment.getView();
            WebView webView = vista.findViewById(R.id.wbUfopolis);
            if (webView.canGoBack()) {
                webView.goBack();
            } else
            {
                fm.beginTransaction().replace(R.id.content_main,new EstatusFragment()).commit();

            }
        }
        else if (currentFragment instanceof EditClienteFragment) {
            if(fm.getBackStackEntryCount()> 0)
            {
                getSupportFragmentManager().popBackStack();
            }
        }
        else if(fm.getBackStackEntryCount()> 0)
        {
            fm.beginTransaction().replace(R.id.content_main,new EstatusFragment()).commit();

            //getSupportFragmentManager().popBackStack();
        }
        else if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
          else
              {
            salir();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
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
            CrearNotificacion();
            fSeleccionado = false;

        } else if (id == R.id.nav_gallery) {
            miFrag = new BlueFragment();
            getSupportActionBar().setTitle("Azul");
            fSeleccionado = true;
        } else if (id == R.id.nav_slideshow) {
            miFrag = new GreenFragment();
            getSupportActionBar().setTitle("Verde");
            fSeleccionado = true;
        } else if (id == R.id.nav_manage) {
            miFrag = new FormularioFragment();
            getSupportActionBar().setTitle("Web");
            fSeleccionado = true;
        } else if (id == R.id.nav_share) {
            miFrag = new ContenedorFragment();
            getSupportActionBar().setTitle("Pestañas");
            fSeleccionado = true;
        }
        else if (id == R.id.nav_config) {
            //miFrag = new ConfiguracionActivity();
            startActivity(new Intent(MainActivity.this,ConfiguracionActivity.class));
           /// getSupportActionBar().setTitle("Configuraciones");
            fSeleccionado = false;

        } else if (id == R.id.nav_send) {

            miFrag = new ListaClientesFragment();

            if (showSnackIfOffline(findViewById(R.id.content_main)))
            {
                fSeleccionado = true;
                getSupportActionBar().setTitle("Clientes");
            }
            else
            { fSeleccionado = false; }

        } else if (id == R.id.salir){

        salir();

        fSeleccionado = false;
        } else if (id == R.id.ivUser){
            miFrag = new DatosPersonalesFragment();
            getSupportActionBar().setTitle("Datos Personales");

            fSeleccionado = true;
        }


        if (fSeleccionado==true)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main,miFrag).addToBackStack(null).commit();
            item.setChecked(true);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void salir() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.MyDialogTheme);

        builder.setTitle("Estás por salir de la aplicación");
        builder.setMessage("¿Confirmas la operación?");

        builder.setPositiveButton("salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();}

        });

        builder.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private boolean showSnackIfOffline(View vista) {
        final boolean online = Utilidades.isOnline();

        if (!online)
            //Snackbar.make(vista,R.string.No_Network_Connection, Snackbar.LENGTH_LONG).show();
        {
            Intent intent = new Intent(this,NoConnectionActivity.class);
            startActivity(intent);

        }
        else
            Snackbar.make(vista, R.string.Connection, Snackbar.LENGTH_LONG).show();

        return online;
    }


    public void showFloatingActionButton() {
        fab.show();
    }

    public void hideFloatingActionButton() {
        fab.hide();
    }

    public void CrearNotificacion()
    {
        //esto ess para versiones anteriore a android o
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_person_black_24dp);
        builder.setContentTitle("Cliente agregado");
        builder.setContentText("Se han agregado nuevos clientes");
        //builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long[]{1000,1000,2000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent2);

        builder.setAutoCancel(true);//al clickear la notificacion se borra el icono
                NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

                notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());


    }
}
