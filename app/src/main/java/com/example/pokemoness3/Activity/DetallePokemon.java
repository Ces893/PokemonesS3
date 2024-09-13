package com.example.pokemoness3.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.R;

public class DetallePokemon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pokemon);

        Pokemon pokemon = (Pokemon) getIntent().getSerializableExtra("POKEMON_DETALLES");

        TextView name =findViewById(R.id.Name);
        TextView cate = findViewById(R.id.Categoria);
        TextView de = findViewById(R.id.Debilidad);

        name.setText(pokemon.nombre);
        cate.setText(pokemon.categoria);
        de.setText(pokemon.debilidades);
    }
}