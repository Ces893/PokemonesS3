package com.example.pokemoness3.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemoness3.Entidades.Pokemon;
import com.example.pokemoness3.R;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

        private List<Pokemon> data;
        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(Pokemon pokemon);
        }

        public PokemonAdapter(List<Pokemon> data, OnItemClickListener listener) {
            this.data = data;
            this.listener = listener;
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
            Pokemon item = data.get(position);

            holder.bind(item, listener);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class PokemonViewHolder extends RecyclerView.ViewHolder {

            TextView Name;
            TextView Categoria;

            public PokemonViewHolder(@NonNull View itemView) {
                super(itemView);
                Name = itemView.findViewById(R.id.Name);
                Categoria = itemView.findViewById(R.id.Categoria);
            }

            public void bind(final Pokemon item, final OnItemClickListener listener) {
                Name.setText(item.nombre);
                Categoria.setText(item.categoria);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(item);
                    }
                });
            }
        }
}


