/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import java.util.ArrayList;


public class SymbolDec extends SymbolBase {
    //String nomProcediment;
    
    public SymbolDec(Parser p, SymbolProc a) {
        super("SymbolDec",p);
        //this.nomProcediment = a.nom;
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolDec(Parser p, SymbolFunc a) {
        super("SymbolDec",p);
        //this.nomProcediment = a.nom;
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolDec(Parser p, SymbolVar a) {
        super("SymbolDec",p);
        //
        this.afegirFill(a);
        this.pintarArbre();
        
    }
    
    public SymbolDec(Parser p, SymbolAssig a) {
        super("SymbolDec",p);
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    
}
