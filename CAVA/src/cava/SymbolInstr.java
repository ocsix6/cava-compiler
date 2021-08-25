/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.Goto;
import cava.genc3a.Instruccio;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class SymbolInstr extends SymbolBase{
 
    ArrayList<SymbolReturn> llistareturns = new ArrayList<>();
    
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public SymbolInstr(Parser p, SymbolAssig a){
        super("SymbolInstr",p);
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    public SymbolInstr(Parser p, SymbolIf a ){
        super("SymbolInstr",p);
        this.llistareturns = a.llistareturns;
        //
        this.afegirFill(a);
        this.pintarArbre();
        
        this.seg = a.seg;
    }
    
    public SymbolInstr(Parser p, SymbolWhile a ){
        super("SymbolInstr",p);
        this.llistareturns = a.llistareturns;
        //
        this.afegirFill(a);
        this.pintarArbre();
        
        this.seg = a.seg;
    }
    
    public SymbolInstr(Parser p, SymbolReturn a ){
        super("SymbolInstr",p);
        this.llistareturns.add(a);
        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    
    public SymbolInstr(Parser p, SymbolCridada a){
        super("SymbolInstr",p);
        //
        this.afegirFill(a);
        this.afegirFill(";");
        this.pintarArbre();
    }
    
}
