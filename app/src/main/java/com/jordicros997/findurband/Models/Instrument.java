package com.jordicros997.findurband.Models;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by jordi on 10/03/2018.
 */

public class Instrument{
    public String tipus;
    List<Usuari> usuarisInstr = new ArrayList<Usuari>();
    List<Grup> grupsInstr = new ArrayList<Grup>();
}
