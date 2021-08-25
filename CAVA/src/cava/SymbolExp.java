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
public class SymbolExp extends Expressio{

    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;

    //OR
    public SymbolExp(Parser p, SymbolExp a, SymbolExpAnd b, M m) throws Exception {
        super("SymbolExp", p);
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
        this.afegirFill("OR");
        this.afegirFill(b);
        this.pintarArbre();
        
        Expressio.backpatch(m.e, a.efals);
        this.ecert = concatena(a.ecert, b.ecert);
        this.efals = b.efals;

    }

    public SymbolExp(Parser p, SymbolExpAnd a) {
        super("SymbolExp", p);
        this.tipus = a.tipus;
        this.tsb = a.tsb;
        this.m = a.m;
        switch (a.tsb) {
            case INTEGER:
                this.numero = a.numero;
                break;
            default:
                this.valor = a.valor; // string, true o false
        }
        //
        this.afegirFill(a);
        this.pintarArbre();

        this.ecert = a.ecert;
        this.efals = a.efals;
        this.var = a.var;
    }
}
