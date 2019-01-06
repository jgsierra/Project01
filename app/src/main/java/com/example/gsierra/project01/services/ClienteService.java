package com.example.gsierra.project01.services;

import com.example.gsierra.project01.entidades.Clientes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ClienteService {

    @GET("Clientes")
    Call<List<Clientes>> getAll();

    @GET("Clientes/{id}")
    Call<Clientes> find(@Path("id") int id);

    @POST("PostClientes")
    Call<Void> create(@Body Clientes cliente);

    @GET("ClientesFilter/{keyword}")
    Call<List<Clientes>> getAllFilter();

    @DELETE("Clientes/{id}")
    Call<Void> delete(@Path("id") int id);

}
