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
import cava.genc3a.Instruccio;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class SymbolIf extends SymbolBase{
    String valor; // true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;
    
    ArrayList<SymbolReturn> llistareturns = new ArrayList<>();
    
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public SymbolIf(Parser p, SymbolExp a, SymbolCos b, M m)throws Exception{
        super("SymbolIf",p);
        this.tipus = a.tipus; //boolean
        //L'expressió ha de ser del tipus boolean
        if (!a.tsb.equals(TSB.BOOLEAN)) {
            throw new TSBsINVALIDEXPR();
        }
        this.tsb = TSB.BOOLEAN;
        this.valor = a.valor;
        this.m = a.m;
        this.llistareturns = b.llistareturns;
        //
        this.afegirFill("IF");
        this.afegirFill("(");
        this.afegirFill(a);
        this.afegirFill(")");
        this.afegirFill(b);
        this.pintarArbre();
        
        
        Expressio.backpatch (m.e, a.ecert);
        this.seg = a.efals;
    }
    
    public SymbolIf(Parser p, SymbolExp a, SymbolCos b, SymbolCos c, M m, M1 m1)throws Exception{
        super("SymbolIf",p);
        this.tipus = a.tipus; //boolean
        //L'expressió ha de ser del tipus boolean
        if (!a.tsb.equals(TSB.BOOLEAN)) {
            throw new TSBsINVALIDEXPR();
        }
        this.tsb = TSB.BOOLEAN;
        this.valor = a.valor;
        this.m = a.m;
        this.llistareturns = b.llistareturns;
        for (int i = 0; i < c.llistareturns.size(); i++) {
            this.llistareturns.add(c.llistareturns.get(i));
        }
        //
        this.afegirFill("IF");
        this.afegirFill("(");
        this.afegirFill(a);
        this.afegirFill(")");
        this.afegirFill(b);
        this.afegirFill("ELSE");
        this.afegirFill(c);
        this.pintarArbre();
        
        Expressio.backpatch(m.e, a.ecert);
        Expressio.backpatch (m1.e, a.efals);
        this.seg = Expressio.concatena(m1.seg, Expressio.concatena(b.seg, c.seg));
        
    }
}
