package com.example.gsierra.project01.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.gsierra.project01.Adapters.ReciclerViewAdapter;
import com.example.gsierra.project01.Helper.Utilidades;
import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;
import com.example.gsierra.project01.services.APIClient;
import com.example.gsierra.project01.services.ClienteService;

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
public class ListaClientesFragment extends Fragment {

    private String mParam1;
    private String mParam2;
    private List<Clientes> clientes;
    private OnFragmentInteractionListener mListener;
    RecyclerView recyclerClientes;
    ReciclerViewAdapter adaptadorCliente;
    ProgressBar pb;
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
                 clientes = (List<Clientes>) response.body();
                adaptadorCliente = new ReciclerViewAdapter(clientes, getFragmentManager());
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


}
