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
public class SymbolOpArit extends SymbolBase{
    String oparit;
    
    public SymbolOpArit(Parser p, String a){
      super("SymbolOpArit",p);
        this.oparit = a;
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
}
