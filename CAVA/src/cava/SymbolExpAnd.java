/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;

/**
 *
 * @author Jim
 */
public class SymbolExpAnd extends Expressio{
    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;

    //AND
    public SymbolExpAnd(Parser p, SymbolExpAnd a, SymbolExpRel b, M m)throws Exception{
        super("SymbolExpAnd",p);
        this.tipus = b.tipus; //id_nula
        //Ambdós TSB han de concordar, sinos serà error
        if (!b.tsb.equals(TSB.BOOLEAN)) {
            throw new TSBsINVALIDEXPR();
        }
        if (!a.tsb.equals(TSB.BOOLEAN)) {
            throw new TSBsINVALIDEXPR();
        }
        this.tsb = TSB.BOOLEAN;
        this.valor = b.valor;
        this.m = b.m;
        //
        this.afegirFill(a);
        this.afegirFill("AND");
        this.afegirFill(b);
        this.pintarArbre();
        
        Expressio.backpatch(m.e, a.ecert);
        this.efals = concatena(a.efals, b.efals);
        this.ecert = b.ecert;
    }
    
    public SymbolExpAnd(Parser p, SymbolExpRel b){
        super("SymbolExpAnd",p);
        this.tipus = b.tipus;
        this.tsb = b.tsb;
        this.m = b.m;
        switch (b.tsb) {
            case INTEGER:
                this.numero = b.numero;
                break;
            default: 
                this.valor = b.valor; // string, true o false
        }
        //
        this.afegirFill(b);
        this.pintarArbre();
        
        this.ecert = b.ecert;
        this.efals = b.efals;
        this.var = b.var;
    }
    
}
