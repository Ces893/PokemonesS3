package com.example.pokemoness3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemoness3.Activity.DetallePokemon;
import com.example.pokemoness3.Adapter.PokemonAdapter;
import com.example.pokemoness3.Entidades.Pokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    List<Pokemon> pokemonList = new ArrayList<>();
    PokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpRecyclerView();
    }


    private void setUpRecyclerView() {
        RecyclerView rvPokemones = findViewById(R.id.rvPokemones);
        rvPokemones.setLayoutManager(new LinearLayoutManager(this));

        pokemonList.add(new Pokemon("Pikachu", "Electrico", "Agua"));
        pokemonList.add(new Pokemon("Aron", "Acero", "Agua"));
        pokemonList.add(new Pokemon("Squirtle", "Agua", "Electrico"));
        pokemonList.add(new Pokemon("Charmander", "Fuego", "Agua"));
        pokemonList.add(new Pokemon("Bulbasaur", "Planta", "Fuego"));

        adapter = new PokemonAdapter(pokemonList, new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                Intent intent = new Intent(MainActivity.this, DetallePokemon.class);
                intent.putExtra("POKEMON_DETALLES", pokemon);
                startActivity(intent);
            }
        });

        rvPokemones.setAdapter(adapter);
    }
}