package com.example.gsierra.project01.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;

import java.util.List;

public class ClienteListAdapter extends ArrayAdapter<Clientes> {

    private List<Clientes> clientes;
    private Context context;

    public ClienteListAdapter(List<Clientes> clientes, Context context){
        super(context,R.layout.cliente_row_layout,clientes);
        this.context = context;
        this.clientes = clientes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.cliente_row_layout,parent,false);

        Clientes cliente = clientes.get(position);

        TextView tvNombre = (TextView)convertView.findViewById(R.id.tvNombre);
        tvNombre.setText(cliente.getNombre());
        TextView tvApellido = (TextView)convertView.findViewById(R.id.tvApellido);
        tvNombre.setText(cliente.getApellido());

        return convertView;


    }
}
