/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DParametre;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.DVariable;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Preamble;
import cava.genc3a.Skip;
import cava.genc3a.Subprograma;
import cava.genc3a.Variable;

/**
 *
 * @author Jim
 */
public class SymbolFunProc extends SymbolBase{
    
    Goto finalPrograma;
    Subprograma subprograma;
    
    //Procediment amb paràmetres
    public SymbolFunProc(Parser p, SymbolFunProcAp a)throws Exception{
        super("SymbolFunProc",p);
        this.finalPrograma =  a.finalPrograma;
        
        //FICAM ELS PARAMETRES DEL PROCEDIMENT DINS LA TS 
        p.TS.entraBloc();
        int idx = p.TS.consultaPrimerArg(a.id);
        DParametre d;// (DParametre) p.TS.consultaTExpansio(idx);
        DVariable dv;//new DVariable(d.tipus);
        //p.TS.afegir(d.id, dv);
        DProcediment dp= (DProcediment)p.TS.consulta(a.id);
        this.subprograma = dp.subprograma;
        while (idx != -1) {
            d = (DParametre) p.TS.consultaTExpansio(idx);
            dv = new DVariable(d.tipus);
            p.TS.afegir(d.id, dv);
            DTipus descrip = (DTipus)p.TS.consulta(dv.tipus);  
            Variable v = p.gen.novaVariable(descrip.tsb, true);
            dp.subprograma.afegirParam(v);
            dv.var = v;
            d.variable = v;
            idx = p.TS.consultaNexArg(idx);
        }
        
        this.afegirFill(a);
        this.afegirFill(")");
        this.pintarArbre();
    }
    
    //Procediment fora paràmetre
    public SymbolFunProc(Parser p, String a) throws Exception {
        super("SymbolFunProc", p);
        this.finalPrograma =  new Goto(null);
        p.gen.genera(finalPrograma);
        
        Etiqueta e = p.gen.novaEtiqueta(a);
        p.gen.genera(new Skip(e));
        Subprograma subprog = p.gen.nouSubprograma(a, e, true);
        this.subprograma = subprog;
        p.gen.entraSubPrograma(subprog);
        DProcediment dp = new DProcediment(subprog);
        p.TS.afegir(a, dp);
        p.TS.entraBloc();
        this.afegirFill(a);
        this.afegirFill("(");
        this.afegirFill(")");
        this.pintarArbre();
        
        p.gen.genera(new Preamble(subprog));
        
    }
}
