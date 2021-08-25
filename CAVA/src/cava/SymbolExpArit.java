/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Aritmetica;
import cava.genc3a.Aritmetica.Operador;
import cava.genc3a.Variable;

/**
 *
 * @author Joan Canals
 */
public class SymbolExpArit extends Expressio{

    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;

    Variable var; //Representa la variable
    
    // A aquest nivell, no ens importa quin simbolAritmetic és, simplement saber que retornarà un enter
    // Es important saber que en aquest cas, ambdós tsb han de ser del tipus ENTER ja que només
    // Permetem fer sumes entre nombres. 
    public SymbolExpArit(Parser p, SymbolExpArit a, SymbolOpArit b, SymbolTerme c) throws Exception {
        super("SymbolExpArit", p);
        this.tipus = c.tipus; //id_nula
        //Ambdós TSB han de concordar, sinos serà error
        if (!c.tsb.equals(TSB.INTEGER)) {
            throw new TSBsINVALIDEXPR();
        }
        if (!a.tsb.equals(TSB.INTEGER)) {
            throw new TSBsINVALIDEXPR();
        }
        this.tsb = TSB.INTEGER;
        switch (c.modevp) {
            case CONSTANT: // String, numero, true o false
                this.m = MODEVC.CONSTANT;
                break;
            case VARIABLE: // ID
                //El tsb va en funció del que és la variable
                //No guardam cap valor
                m = MODEVC.VARIABLE;
                break;
            case INCOMPLET: // CRIDADA
            case COMPLET:
                m = MODEVC.RESULTAT;
                //No guardam cap valor
                //El tsb va en funció del que retorna
                break;
        }
        if (a.m == MODEVC.CONSTANT && this.m == MODEVC.CONSTANT) {
            if (b.oparit.equals("+")) {
                this.numero = a.numero + c.numero;
            } else if (b.oparit.equals("-")) {
                this.numero = a.numero - c.numero;
            } else {
                //ERROR
                throw new TSBsINVALIDEXPR();
            }
        }
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill(c);
        this.pintarArbre();
        
        this.var = p.gen.novaVariable(this.tsb);
        
        Aritmetica.Operador op = Operador.SUMA;
        
        if (b.oparit.equals("+")) {
            op = Aritmetica.Operador.SUMA;
        } else if (b.oparit.equals("-")) {
            op = Aritmetica.Operador.RESTA;
        }
        p.gen.genera(new Aritmetica(this.var, a.var, c.var, op));
    }

    //Depenent del tsb sabrem si guardarlo dins el numero, valor i el mode m
    //Si ens arriba un Terme, simplement ho hem de passar cap adalt
    //Terme
    public SymbolExpArit(Parser p, SymbolTerme c) throws Exception {
        super("SymbolExpArit", p);
        this.tipus = c.tipus;
        this.tsb = c.tsb;
        switch (c.modevp) {
            case CONSTANT: // String, numero, true o false
                this.m = MODEVC.CONSTANT;
                switch (c.tsb) {
                    case INTEGER:
                        this.numero = c.numero;
                        break;
                    case BOOLEAN:
                    case STRING:
                        this.valor = c.valor;
                        break;
                    default:
                        throw new TSBInvalid();
                }
                break;
            case VARIABLE: // ID
                m = MODEVC.VARIABLE;
                //No guardam cap valor
                //El tsb va en funció del que és la variable
                break;
            case INCOMPLET: // CRIDADA
            case COMPLET:
                m = MODEVC.RESULTAT;
                //No guardam cap valor
                //El tsb va en funció del que retorna
                break;
        }
        this.afegirFill(c);
        this.pintarArbre();
        
        this.ecert = c.ecert;
        this.efals = c.efals;
        this.var = c.var;
    }
}
