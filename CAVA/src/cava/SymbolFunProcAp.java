package cava;

import cava.Taula_de_simbols.DParametre;
import cava.Taula_de_simbols.DProcediment;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Preamble;
import cava.genc3a.Skip;
import cava.genc3a.Subprograma;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jim
 */
public class SymbolFunProcAp extends SymbolBase {

    String id;
    Goto finalPrograma;

    //Ens arriba un primer paràmetre 
    public SymbolFunProcAp(Parser p, String a, SymbolTipusVar b, String c)throws Exception{
        super("SymbolFunProcAp", p);
        this.finalPrograma = new Goto(null);
        p.gen.genera(finalPrograma);
        Etiqueta e = p.gen.novaEtiqueta(a);
        p.gen.genera(new Skip(e));
        Subprograma subprog = p.gen.nouSubprograma(a, e, true);
        p.gen.entraSubPrograma(subprog);
        DProcediment df = new DProcediment(subprog);
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

    //Tenim més d'un paràmetre
    public SymbolFunProcAp(Parser p, SymbolFunProcAp a, SymbolTipusVar b, String c)throws Exception{
        super("SymbolFunProcAp", p);
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
    }

}
