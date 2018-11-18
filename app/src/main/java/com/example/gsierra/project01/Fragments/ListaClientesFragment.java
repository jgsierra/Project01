package com.example.gsierra.project01.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.support.v7.widget.SearchView;
//android.widget.SearchView;

import com.example.gsierra.project01.Adapters.ReciclerViewAdapter;
import com.example.gsierra.project01.Helper.Utilidades;
import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;
import com.example.gsierra.project01.services.APIClient;
import com.example.gsierra.project01.services.ClienteService;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaClientesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaClientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaClientesFragment extends Fragment implements  SearchView.OnQueryTextListener {

   // public class ListaClientesFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerClientes;
    ReciclerViewAdapter adaptadorCliente;
    ProgressBar pb;
    List<Clientes> listoriginalclientes;
    ArrayList<Clientes> listcopyclientes = new ArrayList<>() ;
    public ListaClientesFragment() {
        // Required empty public constructor
    }


    public static ListaClientesFragment newInstance(String param1, String param2) {
        ListaClientesFragment fragment = new ListaClientesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_lista_clientes, container, false);

        recyclerClientes = vista.findViewById(R.id.reciclerCliente);
        recyclerClientes.setLayoutManager(new LinearLayoutManager(getContext()));
        pb = vista.findViewById(R.id.pbar);
        //como no estamos en una activity se pone getContext() en lugar de this

            ClienteService clienteService = APIClient.getClient().create(ClienteService.class);
        Call call = clienteService.getAll();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                listoriginalclientes = (List<Clientes>) response.body();
                //listcopyclientes.addAll(listoriginalclientes);
                adaptadorCliente = new ReciclerViewAdapter(listoriginalclientes,getFragmentManager());
                recyclerClientes.setAdapter(adaptadorCliente);
                recyclerClientes.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
        return vista;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        try {
            //List<Clientes> listafiltrado = filter(listcopyclientes,newText);

            List<Clientes> listafiltrado = filter(listoriginalclientes,newText);
            adaptadorCliente.setFilter(listafiltrado);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

    private List<Clientes> filter(List<Clientes> lclientes,String filtro)
    {
        List<Clientes> listaFiltrada = new ArrayList<>();
        try
        {
            filtro = filtro.toLowerCase();
            for(Clientes cliente: lclientes)
            {
                String cliente2=cliente.getNombre().toLowerCase();
                if(cliente2.contains(filtro))
                {
                    listaFiltrada.add(cliente);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return  listaFiltrada;

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Add your menu entries here


        try {
            inflater.inflate(R.menu.menu_buscador, menu);
            MenuItem item = menu.findItem(R.id.buscador);
            SearchView searchView = (SearchView)MenuItemCompat.getActionView(item);

            searchView.setOnQueryTextListener(this);

            MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem menuItem) {
                    return true;
                }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                    //adaptadorCliente.setFilter(listcopyclientes);
                    adaptadorCliente.setFilter(listoriginalclientes);

                    return true;
                }
            });

        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
