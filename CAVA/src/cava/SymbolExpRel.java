/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Goto;
import cava.genc3a.GotoCond;
import cava.genc3a.Variable;

/**
 *
 * @author Jim
 */
public class SymbolExpRel extends Expressio{
    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;
    Variable var; //Representa la variable

    //Passa el mateix que amb les operacions aritmètiques, només permetem fer feina amb enters
    public SymbolExpRel(Parser p, SymbolExpRel a, SymbolOpRel b, SymbolExpArit c)throws Exception{
        super("SymbolExpRel",p);
        this.tipus = c.tipus; 
        if(!a.tipus.equals(c.tipus) && (!c.tsb.equals(a.tsb)) ){
            throw new TSBsINVALIDEXPR();
        }
       
        this.tsb = TSB.BOOLEAN;
        
        this.m = c.m;
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill(c);
        this.pintarArbre();
        
        /*Feim aquesta comprovació perque es en aquest moment que sabem el valor de a i de b.
        Si es vertader, hem de botar o a la dreta de l'and, o a les instruccions del while, etc. 
        En el cas de ser fals, no botarem*/
        GotoCond gc = new GotoCond(null, b.tipusop, a.var, c.var);
        this.ecert.add(gc);
        p.gen.genera(gc);
        Goto g = new Goto(null);
        this.efals.add(g);
        p.gen.genera(g);
    }
    
    public SymbolExpRel(Parser p, SymbolExpArit a){
        super("SymbolExpRel",p);
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
