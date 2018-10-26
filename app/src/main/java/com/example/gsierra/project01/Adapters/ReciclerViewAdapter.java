package com.example.gsierra.project01.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsierra.project01.R;


import com.example.gsierra.project01.entidades.Clientes;

import java.util.List;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cliente,email,movil,opcion;
        ImageView imagenCantante;

        public ViewHolder(View itemView) {
            super(itemView);
            cliente = (TextView)itemView.findViewById(R.id.tvCliente);
            email = (TextView)itemView.findViewById(R.id.tvMail2);
            movil = (TextView)itemView.findViewById(R.id.tvcelular2);
            opcion = (TextView)itemView.findViewById(R.id.txtOptionDigit);
            imagenCantante  = (ImageView)itemView.findViewById(R.id.imgCliente);
        }
    }

    public List<Clientes> clienteLista;

    public ReciclerViewAdapter(List<Clientes> clienteLista) {
        this.clienteLista = clienteLista;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cliente.setText(clienteLista.get(position).getApellido() +" " + clienteLista.get(position).getNombre() );
        holder.email.setText(clienteLista.get(position).getEmail());
        holder.movil.setText(clienteLista.get(position).getTelef_movil());
        holder.opcion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v_1){
                final View v = v_1;

                PopupMenu popupmenu = new PopupMenu(v.getContext(),holder.opcion);
                popupmenu.inflate(R.menu.option_menu);
                popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId())
                        {
                            case R.id.mnu_item_edit:

                                //Toast.makeText(v.getContext(),"Editar", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.mnu_item_delete:
                                notifyDataSetChanged();
                                Toast.makeText(v.getContext(),"deleted", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        return false;                    }
                });
                popupmenu.show();
            }
        });

//        holder.cliente.setText(clienteLista.get(position).getApellido());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente,parent,false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;


    }


    @Override
    public int getItemCount() {
        return clienteLista.size();
    }
}

