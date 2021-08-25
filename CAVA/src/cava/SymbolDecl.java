/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.Instruccio;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class SymbolDecl extends SymbolBase{
    ArrayList<SymbolReturn> llistareturns = new ArrayList<>();
    
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public SymbolDecl(Parser p, SymbolInstr a, SymbolDecl b, M m){
        super("SymbolDecl",p);
        this.llistareturns = b.llistareturns;
        if (!a.llistareturns.isEmpty()) {
            //Hem de guardar el return per a la comprovació futura dels returns
            for (int i = 0; i < a.llistareturns.size(); i++) {
            this.llistareturns.add(a.llistareturns.get(i));
        }
        }
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();    

        Expressio.backpatch(m.e, a.seg);
        this.seg = b.seg;
    }
    
    public SymbolDecl(Parser p, SymbolVar a, SymbolDecl b){
        super("SymbolDecl",p);
        this.llistareturns = b.llistareturns;
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
    }
    
    public SymbolDecl(Parser p){
        super("SymbolDecl",p);
        //
        this.pintarArbre();
                
    }
}
