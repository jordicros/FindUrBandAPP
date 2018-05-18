package com.jordicros997.findurband.Adapters;



public class LlistaGrups {
    private String titol;
    private String estil;
    public LlistaGrups(){super();}
    public LlistaGrups(String titol, String estil)
    {
        this.titol = titol;
        this.estil = estil;
    }

    public String getTitol() {
        return titol;
    }

    public String getEstil() {
        return estil;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public void setEstil(String estil) {
        this.estil = estil;
    }
}
