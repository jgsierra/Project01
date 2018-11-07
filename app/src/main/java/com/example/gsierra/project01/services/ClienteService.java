package com.example.gsierra.project01.services;

import com.example.gsierra.project01.entidades.Clientes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClienteService {

    @GET("Clientes")
    Call<List<Clientes>> getAll();

    @GET("Clientes/{id}")
    Call<Clientes> find(@Query("id") int id);

    @GET("ClientesFilter/{keyword}")
    Call<List<Clientes>> getAllFiltered();

    @POST("Add")
    Call<Void> create(@Body Clientes cliente);


}
