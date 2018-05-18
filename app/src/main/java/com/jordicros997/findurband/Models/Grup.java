package com.jordicros997.findurband.Models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 10/03/2018.
 */

public class Grup{
    public String nom;
    public String estilMusical;
    public String comarca;
    public int complet;
    public int numIntegrants;
    public String contacte;
    List<Instrument> instrumentsGrup = new ArrayList<Instrument>();
    List<Usuari> usuarisGrup = new ArrayList<Usuari>();
    List<Solicitud> solicitudsGrup = new ArrayList<Solicitud>();

}
