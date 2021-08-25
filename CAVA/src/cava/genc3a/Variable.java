/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;

/**
 *
 * @author Jim
 */
public class Variable {
    
    private static int n = 0;
    int num = 0;
    TSB tsb;
    String nom;
    Subprograma sub;
    public int offset;
    boolean parametre;
    public int ocupacioString; //En el cas de ser un string, ocupara un espai extra
                        //Sera l'espai de memoria per l'string. La variable tendra
                        //el punter d'aquest espai de memoria.
    int nAssignacions;
    String valorString;
    long valorEnter;
    boolean constantPermanent;
    
    public Variable(TSB t, Subprograma s, boolean parametre){
        this.n++;
        this.num = n;
        this.tsb = t;
        this.nom= "t_"+n;
        this.sub = s;
        this.parametre = parametre; 
        this.ocupacioString = 0;
        this.nAssignacions=0;
        constantPermanent = false;
    }
    public Variable(TSB t,String nom, Subprograma s, boolean parametre){
        this.n++;
        this.num = n;
        this.tsb = t;
        this.nom = nom;
        this.sub = s;
        this.parametre = parametre; 
        this.ocupacioString = 0;
        this.nAssignacions=0;
        constantPermanent = false;
    }
    

    @Override
    public String toString() {
        if(this.nom == null){
            return "t_"+num;
        }
        return this.nom;
    }
}
