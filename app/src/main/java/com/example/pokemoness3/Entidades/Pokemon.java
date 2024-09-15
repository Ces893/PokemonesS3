package com.example.pokemoness3.Entidades;
import java.io.Serializable;

public class Pokemon{
    public int id;
    public String name;
    public String tipo;
    public String latitud;
    public String longitud;

    public Pokemon(String name, String tipo, String latitud, String longitud) {
        this.name = name;
        this.tipo = tipo;
        this.latitud = latitud;
        this.longitud = longitud;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
