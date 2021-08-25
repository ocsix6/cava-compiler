/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jim
 */
public class SymbolLlistaDecl extends SymbolBase {
    //ArrayList<String> procediments = new ArrayList<>();
    
    public SymbolLlistaDecl(Parser p, SymbolDec a){
        super("SymbolLlistaDecl",p);
        //procediments.add(a.nomProcediment);
        
        //per dibuixar el graf
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolLlistaDecl(Parser p,SymbolDec a, SymbolLlistaDecl b){
        super("SymbolLlistaDecl",p);
        //procediments = b.procediments; //Recuperam tots els noms que hem trobat fins ara
        //procediments.add(a.nomProcediment);
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
    }

    
}
