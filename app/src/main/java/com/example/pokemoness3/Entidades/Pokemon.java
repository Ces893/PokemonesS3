package com.example.pokemoness3.Entidades;
import java.io.Serializable;

public class Pokemon implements Serializable{
    public int id;
    public String nombre;
    public String categoria;
    public String debilidades;

    public Pokemon(String nombre, String categoria,String debilidades) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.debilidades = debilidades;
    }

}
