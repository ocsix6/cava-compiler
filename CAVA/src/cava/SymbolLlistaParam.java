/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

/**
 *
 * @author Jim
 */
public class SymbolLlistaParam extends SymbolBase{
    
    public SymbolLlistaParam(Parser p,SymbolTipusVar a, SymbolParamList b) {
        super("SymbolLlistaParam",p);
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
        
    }
    
     public SymbolLlistaParam(Parser p,SymbolTipusVar a, SymbolParamList b, SymbolLlistaParam c) {
         super("SymbolLlistaParam",p);
         //
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill(c);
        this.pintarArbre();
        
    }
    
}
