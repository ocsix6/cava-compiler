/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Instruccio;
import java.util.ArrayList;


public class SymbolWhile extends SymbolBase{
    String valor; // true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;
    
    ArrayList<SymbolReturn> llistareturns = new ArrayList<>();
    
    ArrayList<Instruccio> seg = new ArrayList<>(); //Representa totes les instruccions que van despres del while. Un cop ha acabat el while. 
    //A on hem de anar un cop ha acabat el while. 
    
    public SymbolWhile(Parser p, SymbolExp a, SymbolCos b, M m1, M m2)throws Exception{
        super("SymbolWhile",p);
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
        this.afegirFill("while");
        this.afegirFill("(");
        this.afegirFill(a);
        this.afegirFill(")");
        this.afegirFill(b);
        this.pintarArbre();
        
        p.gen.genera(new Goto(m1.e));
        Expressio.backpatch(m1.e, b.seg);
        Expressio.backpatch(m2.e, a.ecert); // Si la expressio avalua cert, anam al seu bloc. 
        this.seg = a.efals; //Tenim una altra produccio que fa el backpatch dels efalsos 
    }
    
    
}
