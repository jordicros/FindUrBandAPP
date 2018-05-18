package com.jordicros997.findurband.Data;

import com.jordicros997.findurband.Models.Grup;
import com.jordicros997.findurband.Models.Usuari;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 15/05/2018.
 */

public class UserData {
    private static UserData ourInstance;
    private Usuari user;
    private List<Grup> grups;
    public static UserData getInstance() {
        if(ourInstance==null)
        {
            ourInstance = new UserData();
            return ourInstance;
        }

        else return ourInstance;

    }
    private UserData()
    {
        this.user = new Usuari();
        this.grups = new ArrayList<Grup>();
    }
    public Usuari getUser(){
        return this.user;
    }

    public List<Grup> getGrups(){
        return this.grups;
    }

    public void setUser(Usuari user) {
        this.user = user;
    }

    public void setGrups(List<Grup> grups) {
        this.grups = grups;
    }
}
