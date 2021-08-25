/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.Etiqueta;
import cava.genc3a.Retorn;
import cava.genc3a.Skip;

public class SymbolProc extends SymbolBase{
    public String nom;
        
    public SymbolProc(Parser p, SymbolFunProc a, SymbolCos b)throws Exception{
        super("SymbolProc",p);
        if (!b.llistareturns.isEmpty() ) {
            throw new Return();
        }
        p.TS.sortirBloc();
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
        p.gen.genera(new Retorn(a.subprograma));
        p.gen.surtSubPrograma();
        
        Etiqueta e = p.gen.novaEtiqueta();
        p.gen.genera(new Skip(e));
        a.finalPrograma.e = e; 
    }
    
}
