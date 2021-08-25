/*
 * Es tracta d'un Marcador.  
 * Alfinal aquest marcador ens servirà per crear una etiqueta i colocarla.
 * La etiqueta correspón amb la següent expresió que hem de avaluar. Per ex: 
 * if (expressio1 and M expressio2)
 */

package cava;

import cava.genc3a.Etiqueta;
import cava.genc3a.Skip;

public class M extends SymbolBase{
    
    Etiqueta e;
    
    
    public M(Parser p){
        super("Marcador",p);
        this.e = p.gen.novaEtiqueta();
        p.gen.genera(new Skip(this.e));
    }
}
