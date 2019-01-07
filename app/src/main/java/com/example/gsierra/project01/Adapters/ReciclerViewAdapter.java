package com.example.gsierra.project01.Adapters;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
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
import com.example.gsierra.project01.services.APIClient;
import com.example.gsierra.project01.services.ClienteService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                                Integer codCliente = clienteLista.get(position).getCodigo();

                                Confirmar(v,codCliente);
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
//                Toast.makeText(v.getContext(),
//                        "Seleccion :" + clienteLista.get(position).getCodigo(),Toast.LENGTH_SHORT).show();

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


    private void Confirmar(View v, int CodCliente) {
        final View vista = v;
        final int idcliente = CodCliente;
           AlertDialog.Builder builder =
                new AlertDialog.Builder(vista.getContext(), R.style.MyDialogTheme);

        builder.setTitle("Eliminar el cliente n° " + String.valueOf(idcliente));
        builder.setMessage("¿Confirmas la operación?");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BorrarCliente(vista,idcliente);}
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public  void BorrarCliente(View v,int idCliente)
    {
        final View vista = v;
        ClienteService clienteService = APIClient.getClient().create(ClienteService.class);
        Call call = clienteService.delete(idCliente);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                Toast.makeText(vista.getContext(),response.message()+" code - "+ String.valueOf(response.code()),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

}

