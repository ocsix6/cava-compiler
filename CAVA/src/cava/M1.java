
package cava;

import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Instruccio;
import cava.genc3a.Skip;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class M1 extends SymbolBase {
    
    Etiqueta e;
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public M1(Parser p){
        super("Marcador1",p);
        Goto g = new Goto(null);
        p.gen.genera(g);
        this.seg.add(g);
        Etiqueta e = p.gen.novaEtiqueta();
        p.gen.genera(new Skip(e));
        this.e = e;
    }
}
