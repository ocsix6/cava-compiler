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
public class SymbolParam extends SymbolBase{
    
    public SymbolParam(Parser p,SymbolLlistaParam a) {
        super("SymbolParam",p);
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolParam(Parser p){
        super("SymbolParam",p);
        //
        this.pintarArbre();
    }
    
}
