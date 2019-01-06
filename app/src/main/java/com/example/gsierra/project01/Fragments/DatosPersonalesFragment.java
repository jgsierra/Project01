package com.example.gsierra.project01.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsierra.project01.MainActivity;
import com.example.gsierra.project01.R;

import java.io.BufferedReader;
import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DatosPersonalesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DatosPersonalesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DatosPersonalesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String CARPETA_PRINCIPAL ="misImagenesCRUDRETROFIT";
    private static final String CARPETA_IMAGEN ="imagenes";
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL+CARPETA_IMAGEN;
    private String path;

    File fileImagen;
    Bitmap bitmap;
    EditText etNombre;
    TextView tvNombre;
    private static final int COD_SELECCIONADO=10;
    private static final int COD_FOTO=20;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView ivUser;

    private OnFragmentInteractionListener mListener;

    public DatosPersonalesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DatosPersonalesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DatosPersonalesFragment newInstance(String param1, String param2) {
        DatosPersonalesFragment fragment = new DatosPersonalesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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

        final View vista = inflater.inflate(R.layout.fragment_datos_personales, container, false);
        ivUser = vista.findViewById(R.id.imageView3);
        etNombre = vista.findViewById(R.id.etNombrel);
        tvNombre = vista.findViewById(R.id.tvNombrec);
        etNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvNombre.setTextColor(Color.RED);
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarDialogoOpciones();
            }
        });
        return  vista;
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
    public void onResume() {
        super.onResume();
        if (!getUserVisibleHint()) {
            return;
        }

        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {

            mainActivity.hideFloatingActionButton();
//            mainActivity.getSupportActionBar().setTitle("Datos Personales");
            mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
//            mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mainActivity.getSupportActionBar().show();

        }
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

    private void mostrarDialogoOpciones()
    {
        final CharSequence[] opciones = {"Tomar Foto","Elegir desde Galeria","Cancelar"};
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Elija una opción");
        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (opciones[which].equals("Tomar Foto"))
                {
                    abrirCamara();
                }
                else if (opciones[which].equals("Elegir desde Galeria"))
                {
                    Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    //intent.setType("image/");
//                    startActivityForResult(intent.createChooser(intent,"Seleccione"),COD_SELECCIONADO);
                    startActivityForResult(intent,COD_SELECCIONADO);
                }else
                {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void abrirCamara() {
        File miFile = new File(Environment.getExternalStorageDirectory(),DIRECTORIO_IMAGEN);
        boolean isCreada = miFile.exists();
        if(isCreada==false)
        {
            isCreada = miFile.mkdirs();
        }
        if(isCreada==true)
        {
            Long consecutivo = System.currentTimeMillis()/1000;
            String nombre = consecutivo.toString()+".jpg";

            path = Environment.getExternalStorageDirectory()+File.separator+DIRECTORIO_IMAGEN
                    +File.separator+nombre;
            fileImagen = new File(path);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(fileImagen));

            startActivityForResult(intent,COD_FOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK)
        {
            switch (requestCode)
            {
                case COD_SELECCIONADO:
                    Uri miPath = data.getData();
                    ivUser.setImageURI(miPath);
                    break;

                case COD_FOTO:
                    MediaScannerConnection.scanFile(getContext(), new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                                @Override
                                public void onScanCompleted(String path, Uri uri) {
                                    Log.i("Path",""+path);
                                }
                            });
                    bitmap = BitmapFactory.decodeFile(path);
                    ivUser.setImageBitmap(bitmap);
                    break;
            }
        }
    }

}
