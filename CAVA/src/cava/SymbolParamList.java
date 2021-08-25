/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

/**
 *
 * @author xiscotalavera
 */
public class SymbolParamList extends SymbolBase{
    
    public SymbolParamList(Parser p, String a) {
        super("SymbolParamList",p);
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolParamList(Parser p,String a, SymbolParamList b) {
        super("SymbolParamList",p);
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
    }
    
}
