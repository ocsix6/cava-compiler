
package cava;

import cava.genc3a.Instruccio;
import java.util.ArrayList;

/**
 *
 * @author Joan Canals
 */
public class SymbolCos extends SymbolBase{
    ArrayList<SymbolReturn> llistareturns = new ArrayList<>();
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public SymbolCos(Parser p, SymbolDecl a)throws Exception {
        super("SymbolCos",p);
        this.llistareturns = a.llistareturns;
        p.TS.sortirBloc();
        //
        this.afegirFill(a);
        this.pintarArbre();
        
        this.seg = a.seg;
    }
    
}
