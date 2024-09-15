package com.example.pokemoness3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.R;
import com.example.pokemoness3.Services.ApiService;
import com.google.android.gms.common.providers.PooledExecutorsProvider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetallePokemon extends AppCompatActivity {

    TextView nombrePokemon, tipoPokemon, Latitud, longitud;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);

        nombrePokemon = findViewById(R.id.NameD);
        tipoPokemon = findViewById(R.id.TipoD);
        Latitud = findViewById(R.id.LatitudD);
        longitud = findViewById(R.id.LongitudD);
        button = findViewById(R.id.btnMapaD);

        int idPokemon = getIntent().getIntExtra("POKEMON_ID", -1);

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://66e3a31ad2405277ed11616c.mockapi.io/api/pokv1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        ApiService service = retrofit.create(ApiService.class);

        service.find(idPokemon).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokemon = response.body();
                    nombrePokemon.setText(pokemon.getName());
                    tipoPokemon.setText(pokemon.getTipo());
                    Latitud.setText(String.valueOf(pokemon.getLatitud()));
                    longitud.setText(String.valueOf(pokemon.getLongitud()));

                    if (button == null) {
                        Log.e("DETALLE_POKEMON", "El botón es null");
                    } else {
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                                double lat = Double.parseDouble(pokemon.getLatitud());
                                double log = Double.parseDouble(pokemon.getLongitud());
                                intent.putExtra("POKEMON_LAT", lat);
                                intent.putExtra("POKEMON_LOG", log);
                                Log.d("DETALLE_POKEMON", "Enviando Latitud: " + lat + ", Longitud: " + log);
                                view.getContext().startActivity(intent);
                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable throwable) {
                Log.e("DETALLE_POKEMON", "Error en la llamada a la API: " + throwable.getMessage());
            }
        });
    }
}