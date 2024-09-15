package com.example.pokemoness3.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemoness3.Activity.DetallePokemon;
import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.R;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

        private List<Pokemon> data;


        public PokemonAdapter(List<Pokemon> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.item_pokemon, parent, false);
            return new PokemonViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
            View view= holder.itemView;
            Pokemon item = data.get(position);

            TextView Name = view.findViewById(R.id.Name);
            TextView ca = view.findViewById(R.id.Categoria);
            TextView la = view.findViewById(R.id.Latitud);
            TextView lo = view.findViewById(R.id.Longitud);

            Name.setText(item.getName());
            ca.setText(item.getTipo());
            la.setText(item.getLatitud());
            lo.setText(item.getLongitud());

            Name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetallePokemon.class);
                    intent.putExtra("POKEMON_ID",item.getId());
                    Log.d("POKEMON_ADAPTER", "Enviando ID: " + item.getId());
                    view.getContext().startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class PokemonViewHolder extends RecyclerView.ViewHolder {

            public PokemonViewHolder(@NonNull View itemView) {
                super(itemView);
            }

        }
}


