/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Assignacio;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Retorn;
import cava.genc3a.Skip;

public class SymbolReturn extends SymbolBase{
    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;
    Retorn r;
    public SymbolReturn(Parser p, SymbolExpRel a){
        super("SymbolReturn",p);
        this.tipus = a.tipus;
        this.tsb = a.tsb;
        this.m = a.m;
        switch (a.tsb) {
            case INTEGER:
                this.numero = a.numero;
                break;
            default:
                this.valor = a.valor; // string, true o false
        }
        //
        this.afegirFill("RETURN");
        this.afegirFill(a);
        this.afegirFill(";");
        this.pintarArbre();
        
        switch (this.tsb) {
            case INTEGER:
                this.numero = a.numero;
                break;
            case BOOLEAN: // Hem de donar 0 o 1 en funcio de si hem vengut de ecert o efalse
                // En el cas en que l'assignació es faci a un valor true
                a.var = p.gen.novaVariable(TSB.BOOLEAN);
                
                Etiqueta ecert = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(ecert));
                Expressio.backpatch(ecert, a.ecert);
                Etiqueta efinal = p.gen.novaEtiqueta();
                p.gen.genera(new Assignacio(a.var,1));
                p.gen.genera(new Goto(efinal));
                
                // En en cas en que l'assignació es faci a un valor false
                Etiqueta efals = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(efals));
                Expressio.backpatch(efals, a.efals);
                p.gen.genera(new Assignacio(a.var,0));
                p.gen.genera(new Skip(efinal));
                
                break;
                
            default:
                this.valor = a.valor; // string
            }
        r = new Retorn(null,a.var);
        p.gen.genera(r);
        
    }
}
