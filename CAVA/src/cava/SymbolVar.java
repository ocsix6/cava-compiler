/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DConst;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.DVariable;
import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.MODEVP;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Assignacio;

public class SymbolVar extends SymbolBase{
    //Variable
    public SymbolVar(Parser p, SymbolTipusVar a, String b) throws Exception {
        super("SymbolVar", p);
        DVariable dv = new DVariable(a.valor); //Li donam el seu tipus
        
        DTipus d = (DTipus) p.TS.consulta(a.valor); //El a.valor es per comprovar el tsb
        dv.var = p.gen.novaVariable(d.tsb,b);
        
        p.TS.afegir(b, dv);
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill(";");
        this.pintarArbre();
        
        if("string".equals(a.valor)){
            dv.var.ocupacioString = 255; //Reservam espai si o si per els strings
        }
    }
    
    //Constant
    public SymbolVar(Parser p, SymbolTipusVar a, String b, SymbolExpRel c) throws Exception{
        super("SymbolVar",p);
            //El nostre afegir de la TS, ja comprova si existeix l'identificador
            if(!a.valor.equals(c.tipus)){
               throw new ErrorConstTipus();
            }  
            if(c.m !=MODEVC.CONSTANT ){
                throw new ErrorConstTipus();
            }
            DConst dc;
            if (c.tsb == TSB.INTEGER) {
                dc = new DConst(a.valor, c.numero);
            }else if(c.tsb == TSB.STRING || c.tsb == TSB.BOOLEAN){
                dc = new DConst(a.valor, c.valor);
            }else{
                throw new ErrorTipus();
            }
            dc.var = p.gen.novaVariable(c.tsb,b); //esteim creant la varaible per la constant
            p.gen.genera(new Assignacio(dc.var, c.var));
           
            p.TS.afegir(b,dc);
            
        //
        this.afegirFill("CONST");
        this.afegirFill(a);
        this.afegirFill(b);
        this.afegirFill("=");
        this.afegirFill(c);
        this.afegirFill(";");
        this.pintarArbre();
    }
}
