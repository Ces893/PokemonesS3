package com.example.pokemoness3.Services;

import com.example.pokemoness3.Entidades.Pokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/Pokemon")
    Call<List<Pokemon>> getAll();

    @GET("/Pokemon/{id}")
    Call<Pokemon> find(@Path("id") int id);

    @POST("/Pokemon")
    Call<Pokemon> create(@Body Pokemon pokemon);
}
