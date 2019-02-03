package com.example.gsierra.project01.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.gsierra.project01.MainActivity;
import com.example.gsierra.project01.R;
import com.example.gsierra.project01.Adapters.ReciclerViewCumplAdapter;
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
 * {@link EstatusFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EstatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EstatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView rvCumple;
    ReciclerViewCumplAdapter adapter;
    List<Clientes> listcumplclientes;
    ProgressBar pb;


    public EstatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EstatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EstatusFragment newInstance(String param1, String param2) {
        EstatusFragment fragment = new EstatusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {

            mainActivity.hideFloatingActionButton();

        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_estatus, container, false);

        rvCumple = vista.findViewById(R.id.reciclerCumpleanios);
        rvCumple.setLayoutManager(new LinearLayoutManager(getContext()));
        pb = vista.findViewById(R.id.pbarCumple);

        cargarDatos();

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
    private  void cargarDatos()
    {
        ClienteService clienteService = APIClient.getClient().create(ClienteService.class);
        Call call = clienteService.getCumpleaños();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                listcumplclientes = (List<Clientes>) response.body();
                adapter = new ReciclerViewCumplAdapter(listcumplclientes);
                rvCumple.setAdapter(adapter);
                rvCumple.setVisibility(View.VISIBLE);
                pb.setVisibility(View.GONE);
   //                tvCargando.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
