package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DParametre;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.DVariable;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Preamble;
import cava.genc3a.Skip;
import cava.genc3a.Subprograma;
import cava.genc3a.Variable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jim
 */
public class SymbolFuncFun extends SymbolBase{
    TSB retorn;
    Subprograma subprog;
    Goto finalPrograma;
    
    //Amb Paràmetres
    public SymbolFuncFun(Parser p, SymbolTipusVar a, SymbolFunFuncap b)throws Exception{
        super("SymbolFuncFunc",p);
        this.finalPrograma = b.finalPrograma;
        Descripcio d = p.TS.consulta(a.valor);
        DTipus dt =(DTipus) d; //Casting
        this.retorn = dt.tsb;
        
        p.TS.entraBloc();
        int idx = p.TS.consultaPrimerArg(b.id);
        DParametre dp;// (DParametre) p.TS.consultaTExpansio(idx);
        DVariable dv; // new DVariable(dp.tipus);
        //p.TS.afegir(dp.id, dv);
        DFuncio df= (DFuncio)p.TS.consulta(b.id);
        df.tipusRetorn = a.valor;
        while (idx != -1) {
            dp = (DParametre) p.TS.consultaTExpansio(idx);
            dv = new DVariable(dp.tipus);
            p.TS.afegir(dp.id, dv);
            DTipus descrip = (DTipus)p.TS.consulta(dp.tipus);
            Variable v = p.gen.novaVariable(descrip.tsb, true);
            df.subprograma.afegirParam(v);
            dv.var = v;
            dp.variable = v;
            idx = p.TS.consultaNexArg(idx);
        }
        
        //Arbre
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill(")");
        this.pintarArbre();
        //
        
        subprog = b.subprog;
    }
    
    //Fora paràmetres
    public SymbolFuncFun(Parser p, SymbolTipusVar a, String b)throws Exception{
        super("SymbolFuncFun",p);
        this.finalPrograma = new Goto(null);
        p.gen.genera(finalPrograma);
        Etiqueta e = p.gen.novaEtiqueta(b);
        p.gen.genera(new Skip(e));
        subprog = p.gen.nouSubprograma(b, e, false);
        p.gen.entraSubPrograma(subprog);
        DFuncio df = new DFuncio(a.valor, subprog);
        Descripcio d = p.TS.consulta(a.valor);
        DTipus dt =(DTipus) d; //Casting
        this.retorn = dt.tsb;
        p.TS.afegir(b, df);
        p.TS.entraBloc();
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill("(");
        this.afegirFill(")");
        this.pintarArbre();
        
        p.gen.genera(new Preamble(subprog));
    }
    
}
