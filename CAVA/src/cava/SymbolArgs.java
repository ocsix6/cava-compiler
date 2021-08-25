/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.MODEVP;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Assignacio;
import cava.genc3a.Call;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Instruccio;
import cava.genc3a.PosaParam;
import cava.genc3a.Skip;
import cava.genc3a.Subprograma;
import cava.genc3a.Variable;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class SymbolArgs extends SymbolBase{
    MODEVP modevc;
    String tipusRetorn;
    String id; // Nom del procedure o funció \
    public ArrayList<SymbolTerme> llistaParam = new ArrayList<>();
    
    // Cridada amb paràmetres
    public SymbolArgs(Parser p, SymbolLlistaArg a){
        super("SymbolArgs",p);
        //En aquest punt sabem que ja tenim la cridada completa i correcta
        this.id = a.id;
        this.tipusRetorn = a.tipusRetorn;
        this.modevc = MODEVP.COMPLET;
        //
        this.afegirFill(a);
        this.afegirFill(")");
        this.pintarArbre();
        
        Subprograma subprograma;
        Descripcio d = p.TS.consulta(this.id);
        this.llistaParam = a.llistaParam;
        if (d instanceof DFuncio){
            subprograma = ((DFuncio) d).subprograma;
        }else{
            subprograma = ((DProcediment) d).subprograma;
        }
        
        for (int i = llistaParam.size()-1; i >= 0; i--) {
            switch (llistaParam.get(i).tsb) {
            case BOOLEAN: 
                Etiqueta ecert = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(ecert)); //Etiqueta per cada variable
                Variable aux = p.gen.novaVariable(TSB.BOOLEAN, false);
                p.gen.genera(new Assignacio(aux,1));
                Expressio.backpatch(ecert, llistaParam.get(i).ecert);
                Etiqueta efinal = p.gen.novaEtiqueta();
                p.gen.genera(new Goto(efinal));
                
                //Valor False
                Etiqueta efals = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(efals));
                p.gen.genera(new Assignacio(aux,0));
                Expressio.backpatch(efals, llistaParam.get(i).efals);
                p.gen.genera(new Skip(efinal));
                p.gen.genera(new PosaParam(aux, subprograma));
                break;
            default:
                p.gen.genera(new PosaParam(llistaParam.get(i).var,subprograma));
            }
            
            
        }
    }
    
    // Cridada fora paràmetres
    public SymbolArgs(Parser p, String a) throws Exception{
        super("SymbolArgs",p);
        Descripcio dc = p.TS.consulta(a); //Comprovació de que existeix
        if (dc == null) {
            throw new CridadaNoExistent();
        }
        //Miram si te parametres, ja que si en te, es un error 
        if(p.TS.consultaSiTeArg(a)){
            //ERROR: La cridada que ha fet l'usuari, tenia paràmetres. 
            throw new CridaTeParam();
        } 
        Subprograma subprograma;
        //MIRAM DE QUIN TIPUS ÉS        
        if (dc instanceof DFuncio) {
            DFuncio aux = (DFuncio) dc; //Casting
            subprograma = aux.subprograma;
            this.tipusRetorn = aux.tipusRetorn;
        } else if (dc instanceof DProcediment) {
            this.tipusRetorn = "null";
            DProcediment aux = (DProcediment) dc;
            subprograma = aux.subprograma;
        } else {
            throw new MetodeTipusEx();
        }
        this.id = a;
        this.modevc = MODEVP.COMPLET; //Sabem que sera complet 
        //
        this.afegirFill(a);
        this.afegirFill("(");
        this.afegirFill(")");
        this.pintarArbre();
    }   
}
