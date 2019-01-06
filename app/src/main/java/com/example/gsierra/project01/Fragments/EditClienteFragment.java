package com.example.gsierra.project01.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsierra.project01.Adapters.ReciclerViewAdapter;
import com.example.gsierra.project01.MainActivity;
import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;
import com.example.gsierra.project01.entidades.Provincias;
import com.example.gsierra.project01.services.APIClient;
import com.example.gsierra.project01.services.ClienteService;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditClienteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditClienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditClienteFragment extends Fragment {
    private  int idcliente;
    private OnFragmentInteractionListener mListener;
    private TextView tvcodigo,tvEdad;
    private EditText etNombrel,etApellidol,etFecha,etDireccion,etTeleFijo,etTeleMovil,etEmailM;
    private Spinner sp_ciudad,sp_provi,sp_sexo,sp_ocup;
    private Button btnAceptar,btnCancelar;
    public EditClienteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditClienteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditClienteFragment newInstance(int param1, String param2) {
        EditClienteFragment fragment = new EditClienteFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_edit_cliente, container, false);

        int var = getArguments().getInt("pIdCliente");

        prepareUI(vista);

        if (var > 0)
        {
            this.idcliente =var;

            ClienteService clienteService = APIClient.getClient().create(ClienteService.class);
            Call call = clienteService.find(idcliente);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Clientes cliente = (Clientes) response.body();
                    if (cliente !=null)
                    {
                        tvcodigo.setText(String.valueOf(cliente.getCodigo()));
                        etNombrel.setText(cliente.getNombre(),TextView.BufferType.EDITABLE);
                        etApellidol.setText(cliente.getApellido(),TextView.BufferType.EDITABLE);
                        tvEdad.setText(String.valueOf(cliente.getEdad()));
                        etTeleFijo.setText(cliente.getTelef_fijo(),TextView.BufferType.EDITABLE);
                        etTeleMovil.setText(cliente.getTelef_movil(),TextView.BufferType.EDITABLE);
                        etEmailM.setText(cliente.getEmail(),TextView.BufferType.EDITABLE);
                        etDireccion.setText(cliente.getDireccion(),TextView.BufferType.EDITABLE);

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
                        Date fecha = null;
                        try {
                            fecha = formatoFecha.parse(cliente.getFecha_Nac());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        etFecha.setText(cliente.getFecha_Nac());
//                    sp_ciudad
                      sp_provi.setSelection(Integer.parseInt(cliente.getProvincia()));
//                    sp_sexo
//                    sp_ocup
                    }
                    else
                    {
                        Toast.makeText(getContext(),"Cliente no existente",Toast.LENGTH_LONG).show();
                    }
                    //recyclerClientes.setVisibility(View.VISIBLE);
                    //pb.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            });
         }

        return vista;
    }
    private void prepareUI(View vista)
    {
        etNombrel = vista.findViewById(R.id.etNombrel);
        etApellidol  = vista.findViewById(R.id.etApellidol);
        tvEdad = vista.findViewById(R.id.tvEdad);
        etTeleFijo = vista.findViewById(R.id.etTeleFijo);
        etTeleMovil = vista.findViewById(R.id.etTeleMovil);
        etDireccion = vista.findViewById(R.id.etDireccion);
        etEmailM = vista.findViewById(R.id.etEmailM);
        tvcodigo  = vista.findViewById(R.id.tvcodigo);
        etFecha = vista.findViewById(R.id.etFecha);
        sp_ciudad  = (Spinner)vista.findViewById(R.id.sp_ciudad);
        sp_provi  = (Spinner)vista.findViewById(R.id.sp_provi);
        sp_provi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Provincias provincia = (Provincias)sp_provi.getSelectedItem();
                int idProvincia  = provincia.getId_provincia();
                String nombre = provincia.toString();

                new loadSpinnerTask().execute(idProvincia);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_sexo  = vista.findViewById(R.id.sp_sexo);
        sp_ocup  = vista.findViewById(R.id.sp_ocup);

        btnAceptar = vista.findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDatos();
            }
        });

        btnCancelar = vista.findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity mac = (Activity) getContext();
               mac.onBackPressed();
            }
        });

        CargarSpinnerss();
    }

    public void CargarSpinnerss()
    {
        ArrayList<Provincias> listap = (ArrayList)new Provincias().getProvincias();

        ArrayAdapter<Provincias> adapter = new ArrayAdapter<Provincias>(getContext(),
                R.layout.support_simple_spinner_dropdown_item,listap);
        sp_provi.setAdapter(adapter);
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


    private  void saveDatos()
    {
        ClienteService clienteService = APIClient.getClient().create(ClienteService.class);
        Clientes cliente = new Clientes();
        cliente.setNombre(etNombrel.getText().toString());
        cliente.setApellido(etApellidol.getText().toString());
        //cliente.setEdad(Integer.parseInt(tvEdad.toString()));
        cliente.setEdad(44);
        cliente.setFecha_Nac("10-05-1987");
        Provincias c = (Provincias)sp_ciudad.getSelectedItem();
        cliente.setCiudad(c.toString());
        Provincias p = (Provincias)sp_provi.getSelectedItem();
        cliente.setProvincia(String.valueOf(p.getId_provincia()));
        cliente.setDireccion(etDireccion.getText().toString());
        cliente.setTelef_fijo(etTeleFijo.getText().toString());
        cliente.setTelef_movil(etTeleMovil.getText().toString());
        cliente.setSexo("F");
        cliente.setOcupacion("Sin empleo");
        cliente.setEmail(etEmailM.getText().toString());

        Call call = clienteService.create(cliente);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Toast.makeText(getContext(),response.message()+" code - "+ String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                Activity mac = (Activity) getContext();
                mac.onBackPressed();
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public class loadSpinnerTask extends AsyncTask<Integer, Integer, ArrayAdapter<Provincias>> {
        @Override
        protected ArrayAdapter<Provincias> doInBackground(Integer... integers) {
            try{
                ArrayList<Provincias> listaC = (ArrayList)new Provincias().getCiudades();

                ArrayAdapter<Provincias> adapter = new ArrayAdapter<Provincias>(getContext(),


                        R.layout.support_simple_spinner_dropdown_item,listaC);

                return adapter;
            } catch (Exception e){
                e.printStackTrace();
                return  null;

            }


        }

        @Override
        protected void onProgressUpdate(Integer... progress){

        }


        @Override
        protected void onPostExecute(ArrayAdapter<Provincias> result){
            super.onPostExecute(result);
            sp_ciudad.setAdapter(result);

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {

//            mainActivity.hideFloatingActionButton();
            mainActivity.getSupportActionBar().show();

        }
    }

    }
