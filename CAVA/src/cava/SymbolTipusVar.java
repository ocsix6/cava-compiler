/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

public class SymbolTipusVar extends SymbolBase{
    
    String valor; //int, boolean, string 
    public SymbolTipusVar(Parser p, String a) {
        super("TipusVar",p);
        this.valor = a;
        //
        //this.afegirFill("{");
        this.afegirFill(a);
        //this.afegirFill("}");
        this.pintarArbre();
    }
    
    
}
