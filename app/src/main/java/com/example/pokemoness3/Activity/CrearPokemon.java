package com.example.pokemoness3.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.R;
import com.example.pokemoness3.Services.ApiService;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CrearPokemon extends AppCompatActivity {

    EditText NamePok ,TipoPok , Latitud, Longitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_pokemon);

        NamePok = findViewById(R.id.PokName);
        TipoPok = findViewById(R.id.TipoPoke);
        Latitud = findViewById(R.id.PokLatitud);
        Longitud = findViewById(R.id.PokLongitud);

        Button btnGuardar = findViewById(R.id.btnGuardar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://66e3a31ad2405277ed11616c.mockapi.io/api/pokv1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        btnGuardar.setOnClickListener(view -> {
            String name = NamePok.getText().toString();
            String tipo = TipoPok.getText().toString();
            String latitud = Latitud.getText().toString();
            String logt = Longitud.getText().toString();

            Pokemon pokemon = new Pokemon(name,tipo,latitud,logt);

            service.create(pokemon).enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    Log.i("MAIN_APP", String.valueOf(response.code()));
                    if (response.isSuccessful()) {
                        Pokemon newPokemon = response.body();
                        Intent intent = getIntent();
                        intent.putExtra("POKEMON", new Gson().toJson(newPokemon));
                        setResult(100, intent);
                        finish();
                    }
                }
                @Override
                public void onFailure(Call<Pokemon> call, Throwable throwable) {
                    Log.e("MAIN_APP", throwable.getMessage());
                }
            });
        });

    }
}