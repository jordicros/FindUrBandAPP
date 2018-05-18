package com.jordicros997.findurband.Models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 10/03/2018.
 */

public class Quedades {
    public String nom;
    public String descripcio;
    public float latitud;
    public float longitud;
    List<Usuari> usuaris = new ArrayList<Usuari>();
}
