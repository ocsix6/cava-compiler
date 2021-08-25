/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Retorn;
import cava.genc3a.Skip;

/**
 *
 * @author Jim
 */
public class SymbolFunc extends SymbolBase{
    
    public SymbolFunc(Parser p, SymbolFuncFun a, SymbolCos b)throws Exception{
        super("SymbolFunc",p);
        p.TS.sortirBloc();
        if (b.llistareturns.isEmpty()) {
            throw new noReturn();
        }
        for (int i = 0; i < b.llistareturns.size(); i++) {
            if (b.llistareturns.get(i).tsb != a.retorn) {
                //ERROR: Un dels retorns té el tipus incorrecta
                throw new returnIncorrecta();
            }
            b.llistareturns.get(i).r.subprog = a.subprog;
        }   
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
        
        p.gen.genera(new Retorn(a.subprog));
        
        p.gen.surtSubPrograma();
        Etiqueta e = p.gen.novaEtiqueta();
        p.gen.genera(new Skip(e));
        a.finalPrograma.e = e; 
    }
}
