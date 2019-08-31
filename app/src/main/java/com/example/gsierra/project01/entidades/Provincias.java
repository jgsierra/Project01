package com.example.gsierra.project01.entidades;

import com.example.gsierra.project01.Fragments.ListaClientesFragment;

import java.util.ArrayList;
import java.util.List;
/**provincia */
public class Provincias {
    public int getId_provincia() {
        return id_provincia;
    }

    public Provincias(int id_provincia, String provincia) {
        this.id_provincia = id_provincia;
        this.provincia = provincia;
    }
    public Provincias() {

    }
    public void setId_provincia(int id_provincia) {
        this.id_provincia = id_provincia;
    }

    public String toString() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    private int id_provincia ;
    private String provincia ;

    public List<Provincias> getProvincias()
    {
        List<Provincias> list = new ArrayList<>();

        list.add(new Provincias(1,"Buenos Aires"));
        list.add(new Provincias(2,"CATAMARCA"));
        list.add(new Provincias(3,"CHACO"));
        list.add(new Provincias(4,"CHUBUT"));
        list.add(new Provincias(5,"CÓRDOBA"));
        list.add(new Provincias(6,"CORRIENTES"));
        list.add(new Provincias(7,"ENTRE RÍOS"));
        list.add(new Provincias(8,"FORMOSA"));
        list.add(new Provincias(9,"JUJUY"));
        list.add(new Provincias(10,"LA PAMPA"));
        list.add(new Provincias(11,"LA RIOJA"));
        list.add(new Provincias(12,"MENDOZA"));
        list.add(new Provincias(13,"MISIONES"));
        list.add(new Provincias(14,"NEUQUÉN"));
        list.add(new Provincias(15,"RÍO NEGRO"));
        list.add(new Provincias(16,"SALTA"));
        list.add(new Provincias(17,"SAN JUAN"));
        list.add(new Provincias(18,"SAN LUIS"));
        list.add(new Provincias(19,"SANTA CRUZ"));
        list.add(new Provincias(20,"SANTA FÉ"));
        list.add(new Provincias(21,"SANTIAGO DEL ESTERO"));
        list.add(new Provincias(22,"TIERRA DEL FUEGO"));
        list.add(new Provincias(23,"TUCUMÁN"));

        return list;
    }

    public List<Provincias> getCiudades()
    {
        List<Provincias> list = new ArrayList<>();

        list.add(new Provincias(1,"CAPITAL FEDERAL"));
        list.add(new Provincias(2,"SAN MARTIN"));
        list.add(new Provincias(3,"CASEROS"));
        list.add(new Provincias(4,"MORENO"));
        list.add(new Provincias(5,"VICENTE LOPEZ"));
        list.add(new Provincias(6,"SAN MIGUEL"));

        return list;
    }
}
