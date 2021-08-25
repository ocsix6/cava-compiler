/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.GotoCond;

/**
 *
 * @author Jim
 */
public class SymbolOpRel extends SymbolBase{
    String oprel;
    GotoCond.Tipus tipusop;
 
    public SymbolOpRel(Parser p, String a){
        super("SymbolOpRel",p);
        this.oprel = a;
        //
        this.afegirFill(a);
        this.pintarArbre();
        
        GotoCond.Tipus gt;
        if (a.equals(">")) {
            tipusop = GotoCond.Tipus.GT;
        }else if(a.equals("<")){
            tipusop = GotoCond.Tipus.LT;
        }else if(a.equals("==")){
            tipusop = GotoCond.Tipus.EQ;
        }else if(a.equals("!=")){
            tipusop = GotoCond.Tipus.NEQ;
        }else if(a.equals("<=")){
            tipusop = GotoCond.Tipus.LTE;
        }else{ // >=
            tipusop = GotoCond.Tipus.GTE;
        }
    }
}
