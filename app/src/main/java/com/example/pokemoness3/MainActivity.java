package com.example.pokemoness3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemoness3.Activity.CrearPokemon;
import com.example.pokemoness3.Activity.DetallePokemon;
import com.example.pokemoness3.Adapter.PokemonAdapter;
import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.Services.ApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Pokemon> pokemonList = new ArrayList<>();
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://66e3a31ad2405277ed11616c.mockapi.io/api/pokv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        setUpRecyclerView();

        ApiService service = retrofit.create(ApiService.class);

        service.getAll().enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                Log.i("MAIN_APP", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    pokemonList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable throwable) {
                Log.e("MAIN_APP", throwable.getMessage());
            }
        });{

        }

        FloatingActionButton btnCreateContact = findViewById(R.id.btnCreateContact);
        btnCreateContact.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, CrearPokemon.class);
            startActivityForResult(intent, 100);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == 100) {
            String contactJson = data.getStringExtra("POKEMON");
            Pokemon contact = new Gson().fromJson(contactJson, Pokemon.class);

            pokemonList.add(contact);
            adapter.notifyDataSetChanged();
        }

    }


    private void setUpRecyclerView() {
        RecyclerView rvPokemones = findViewById(R.id.rvPokemones);
        rvPokemones.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter(pokemonList);
        rvPokemones.setAdapter(adapter);

    }
}