package com.jordicros997.findurband.Models;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 10/03/2018.
 */
public class Usuari {
    public String nickname;
    public String password;
    public String emailAddr;
    public String refPhoto;
    public String descripcio;
    public int anyNaixement;
    public String localitzacio;

    List<Grup> grupsUser = new ArrayList<Grup>();
    List<Instrument> instrumentsUser = new ArrayList<Instrument>();
    List<Quedades> quedadesUser = new ArrayList<Quedades>();
    List<Solicitud> solicitudsUser = new ArrayList<Solicitud>();
    public Usuari(String nom, String pass, String email){this.nickname=nom;this.password=pass;this.emailAddr=email;}
    public Usuari(){}
}
