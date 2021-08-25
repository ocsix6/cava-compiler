/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DParametre;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.MODEVP;
import cava.genc3a.Variable;
import java.util.ArrayList;

public class SymbolLlistaArg extends SymbolBase{
    String id;
    int next;
    MODEVP modevc;
    String tipusRetorn;
    public ArrayList<SymbolTerme> llistaParam = new ArrayList<>();

    //Primer argument
    public SymbolLlistaArg(Parser p, String a, SymbolTerme b) throws Exception {
        super("SymbolLlistaArg", p);
        //Comprovam que existeix la cridada
        Descripcio dc = p.TS.consulta(a);
        if (dc == null) {
            throw new CridadaNoExistent();
        }
        //MIRAM DE QUIN TIPUS ÉS
        if (dc instanceof DFuncio) {
            //Podem fer el casting perque sabem que sera del tipus DFuncio 
            DFuncio aux = (DFuncio) dc; //Casting
            this.tipusRetorn = aux.tipusRetorn;
        } else if (dc instanceof DProcediment) {
            this.tipusRetorn = "null";
        } else {
            throw new MetodeTipusEx();
        }
        //Hem de comprovar que el primer paràmetre concorda
        this.id = a;
        int idx = p.TS.consultaPrimerArg(a);
        if (idx == -1) {
            //Significa que hem cridat a una crida fora arguments
            throw new InvalidParam();
        }
        DParametre d = (DParametre) p.TS.consultaTExpansio(idx);
        next = idx;
        if (b.tipus.equals("null")) { //Els meus literals: Strings, true i false poden ser null
            if (b.tsb != ((DTipus) p.TS.consulta(d.tipus)).tsb) {
                throw new InvalidParam();
            }
        } else if (!b.tipus.equals(d.tipus)) {
            throw new InvalidParam();
        }
        modevc = MODEVP.INCOMPLET;
        //
        this.afegirFill(a);
        this.afegirFill(b);
        this.pintarArbre();
        //
        this.llistaParam.add(b);
        
    }

    //Conté més arguments
    public SymbolLlistaArg(Parser p, SymbolLlistaArg a, SymbolTerme b) throws Exception {
        super("SymbolLlistaArg", p);
        this.next = p.TS.consultaNexArg(a.next);
        this.tipusRetorn = a.tipusRetorn;
        if (this.next == -1) {
            //ERROR: La crida no té tants de paràmetres
            throw new MassaParam();
        }
        DParametre d = (DParametre) p.TS.consultaTExpansio(this.next);
        if (b.tipus.equals("null")) { //Els meus literals: Strings, true i false poden ser null
            if (b.tsb != ((DTipus) p.TS.consulta(d.tipus)).tsb) {
                throw new InvalidParam();
            }
        } else if (!b.tipus.equals(d.tipus)) {
            throw new InvalidParam();
        }
        modevc = MODEVP.INCOMPLET;
        this.afegirFill(a);
        this.afegirFill(b);
        
        this.llistaParam = a.llistaParam;
        this.llistaParam.add(b);
        this.id = a.id;
    }
}
