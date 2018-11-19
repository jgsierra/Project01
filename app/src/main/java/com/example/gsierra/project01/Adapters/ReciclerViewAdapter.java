package com.example.gsierra.project01.Adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gsierra.project01.Fragments.EditClienteFragment;
import com.example.gsierra.project01.R;
import com.example.gsierra.project01.entidades.Clientes;

import java.util.ArrayList;
import java.util.List;

public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.ViewHolder>
{

    public List<Clientes> clienteLista;
    public FragmentManager FM;

       public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cliente,email,movil,opcion;
        ImageView imagenCantante;
        private LinearLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            cliente = (TextView)itemView.findViewById(R.id.tvCliente);
            email = (TextView)itemView.findViewById(R.id.tvMail2);
            movil = (TextView)itemView.findViewById(R.id.tvcelular2);
            opcion = (TextView)itemView.findViewById(R.id.txtOptionDigit);
            imagenCantante  = (ImageView)itemView.findViewById(R.id.imgCliente);
            parent = (LinearLayout)itemView.findViewById(R.id.parentLayout);
        }
    }


    public ReciclerViewAdapter(List<Clientes> clienteLista, FragmentManager f_manager) {
        this.clienteLista = clienteLista;
        this.FM = f_manager;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
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

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Seleccion :" + clienteLista.get(position).getCodigo(),Toast.LENGTH_SHORT).show();

                EditClienteFragment frg = new EditClienteFragment();
                Bundle parametro = new Bundle();
                parametro.putInt("pIdCliente",clienteLista.get(position).getCodigo());

                frg.setArguments(parametro);
                FM.beginTransaction().replace(R.id.content_main,frg).addToBackStack(null).commit();
            }
        });
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

    public void setFilter(List<Clientes> listafiltrada)
    {
        this.clienteLista  = new ArrayList<>();
        this.clienteLista.addAll(listafiltrada);
        notifyDataSetChanged();
    }

}

