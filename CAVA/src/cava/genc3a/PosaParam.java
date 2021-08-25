/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;

/**
 *
 * @author xiscotalavera
 */
public class PosaParam extends Instruccio{
    Variable var;
    Subprograma subprograma;
       
    public PosaParam(Variable v, Subprograma s){
        this.var = v;
        this.subprograma=s;
    }

    @Override
    public String toString() {
        if (var.constantPermanent) {
            if (var.tsb == TSB.STRING) {
                return "PosarParam "+this.var.nom+"\n";
            }
            return "PosarParam "+this.var.valorEnter+"\n";
        }else{
            return "PosarParam "+this.var+"\n";
        }
        
    }
    
    @Override
    public String toAssembly() {  
        
        if (var.constantPermanent) {
            if (var.tsb == TSB.STRING) {
                String s = "movq "+ Generador.etiqEnsamblador(var) +",%r9\n";
                s += "push %r9\n";
            return s;
            }
            String s = "movq $"+ this.var.valorEnter +",%r9\n";
            s += "push %r9\n";
            return s;
        }else{
            //Reservar espai per tots els parametres i llavors posar els parametres
            String s = "movq "+ Generador.etiqEnsamblador(var) +",%r9\n";
            s += "push %r9\n";
            return s;
        }
    }
    
    
}
