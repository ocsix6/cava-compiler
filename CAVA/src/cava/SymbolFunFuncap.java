package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DParametre;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Preamble;
import cava.genc3a.Skip;
import cava.genc3a.Subprograma;

/**
 *
 * @author Jim
 */
public class SymbolFunFuncap extends SymbolBase{
    String id;
    Subprograma subprog;
    Goto finalPrograma;
    
    //Ens arriba un primer paràmetre
    public SymbolFunFuncap(Parser p, String a, SymbolTipusVar b, String c) throws Exception {
        super("SymbolFunFuncap", p);
        this.finalPrograma = new Goto(null);
        p.gen.genera(finalPrograma);
        Etiqueta e = p.gen.novaEtiqueta(a);
        p.gen.genera(new Skip(e));
        subprog = p.gen.nouSubprograma(a, e, false);
        p.gen.entraSubPrograma(subprog);
        DFuncio df = new DFuncio(b.valor, subprog);
        this.id = a;
        p.TS.afegir(a, df);
        DParametre dp = new DParametre(c,b.valor, DParametre.argtipus.INOUT);
        p.TS.posarParam(a, c, dp);
        //
        this.afegirFill(a);
        this.afegirFill("(");
        this.afegirFill(b);
        this.afegirFill(c);
        this.pintarArbre();
        
        p.gen.genera(new Preamble(subprog));
        
    }

    //Hi ha més paràmetres
    public SymbolFunFuncap(Parser p, SymbolFunFuncap a, SymbolTipusVar b, String c) throws Exception {
        super("SymbolFunFuncap", p);
        this.finalPrograma = a.finalPrograma;
        DParametre dp = new DParametre(c,b.valor, DParametre.argtipus.INOUT);
        this.id = a.id;
        p.TS.posarParam(a.id, c, dp);
        //
        this.afegirFill(a);
        this.afegirFill(",");
        this.afegirFill(b);
        this.afegirFill(c);
        this.pintarArbre();
        this.subprog = a.subprog;
    }
}
