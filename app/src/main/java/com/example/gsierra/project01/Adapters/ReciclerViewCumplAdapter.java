package com.example.gsierra.project01.Adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class ReciclerViewCumplAdapter extends RecyclerView.Adapter<ReciclerViewCumplAdapter.ViewHolder> {
    public List<Clientes> clienteLista;
    //public FragmentManager FM;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cliente,fecha;
        private LinearLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            cliente = (TextView)itemView.findViewById(R.id.tvNombreClienteCumple);
            fecha = (TextView)itemView.findViewById(R.id.FechaCumpleCliente);
            parent = (LinearLayout)itemView.findViewById(R.id.parentLayout);
        }
    }

    public ReciclerViewCumplAdapter(List<Clientes> clienteLista) {
        this.clienteLista = clienteLista;
        //this.FM = f_manager;
    }

    @Override
    @NonNull
    public ReciclerViewCumplAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cumpleanios,viewGroup,false);

        ReciclerViewCumplAdapter.ViewHolder viewHolder = new ReciclerViewCumplAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.cliente.setText(clienteLista.get(position).getApellido() +" " + clienteLista.get(position).getNombre() );
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-mm-yyyy");
        String f = clienteLista.get(position).getFecha_Nac().toString();

        try{
            DateFormat _inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat _outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            //conversion
            _inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date parsed = _inputFormat.parse(f);
           String FechaParsed =  _outputFormat.format(parsed);
            holder.fecha.setText(FechaParsed);
        }
        catch (Exception ex) {
           Log.w("fecha","#ERROR",ex);
            ex.printStackTrace();
        }
//        holder.parent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(v.getContext(),
////                        "Seleccion :" + clienteLista.get(position).getCodigo(),Toast.LENGTH_SHORT).show();
//
//                EditClienteFragment frg = new EditClienteFragment();
//                Bundle parametro = new Bundle();
//                parametro.putInt("pIdCliente",clienteLista.get(position).getCodigo());
//
//                frg.setArguments(parametro);
//                FM.beginTransaction().replace(R.id.content_main,frg).addToBackStack(null).commit();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return clienteLista.size();
    }
}
