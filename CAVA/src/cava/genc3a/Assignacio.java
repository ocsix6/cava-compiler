/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */

public class Assignacio extends Instruccio{

    Variable target; // A on el volem deixar
    Variable source; // El que volem guardar
    Variable offset;
    int valorint;
    String valorString;
    TIPUSASSIG tipus;
    
    boolean valornull; // Si integer null
    
    //a=b
    public Assignacio(Variable t, Variable s) {
        this.target = t;
        this.source = s;
        this.tipus = TIPUSASSIG.SIMPLE;
        this.offset = null;
        this.valornull = true;
        this.valorString = null;
    }

    //a = 0;
    public Assignacio(Variable t, int i) {
      this.target = t;
      this.valorint = i;
      this.source = null;
      this.offset = null;
      this.tipus = TIPUSASSIG.SIMPLE;
      this.valorString = null;
      this.valornull = false;
    }
    
    // a = 'l'
    public Assignacio(Variable t, String s){
        this.target =t;
        this.valorString = s;
        this.source = null;
        this.offset = null;
        this.tipus = TIPUSASSIG.SIMPLE;
        this.valornull = true;
    }
    
    //TIPUS1: a[x] = b
    public Assignacio(Variable a, Variable b, Variable x, TIPUSASSIG tip){ 
        this.target = a;
        this.source = b;
        this.offset = x;
        this.tipus = tip;
        this.valorString = null;
        this.valornull = true;
    }

    @Override
    public String toString() {
        switch (this.tipus) {
            case SIMPLE:
                if (this.source != null && this.target != null) {
                    return this.target.toString() + " = " + this.source.toString() + "\n";
                } else if (this.source == null && this.target != null && !valornull) {
                    return this.target.toString() + " = " + this.valorint + "\n";
                } else if (this.target != null && this.valorString != null && this.offset == null) {
                    return this.target + " = '" + this.valorString + "'\n";
                }
                break;
            case TIPUS1:
                if (this.source!=null) {
                    return this.target + "[" + this.offset + "] = " + this.source+"\n";
                }else{
                    return this.target + "[" + this.offset.valorEnter + "] = '" + this.valorString+"'\n";
                }
            case TIPUS2:
                break;
            default:
        }
        
        return "";
    }

    @Override
    public String toAssembly() {
        String s = "";
        switch (this.tipus) {
            case SIMPLE:
                if (this.source != null && this.target != null) {
                    s += Generador.carrega(source, Generador.Registre.A);
                    s += Generador.guarda(target, Generador.Registre.A);
                } else if (this.source == null && this.target != null && !valornull) {
                    s += Generador.guarda(target, this.valorint);
                } else if (this.target != null && this.valorString != null && this.offset == null) {
                    s += Generador.guarda(this.target, this.valorString);
                }
                break;
            case TIPUS1:
                if (this.source!=null) { //No Optimitzat
                    return Generador.guarda(this.target, this.source, this.offset, this.tipus);
                }else{ //Optimitzat
                    return Generador.guarda(this.target, this.offset, this.valorString);
                }
            case TIPUS2:
                break;
            default:
        }
        
        return s;
    }
    
    @Override
    public boolean optimitza(ArrayList<Instruccio> p, int index){
        Assignacio assig = (Assignacio)p.get(index);
        //System.out.println(assig.toString());
        if(assig.source!= null && assig.source.constantPermanent){
            if(assig.source.tsb == TSB.INTEGER){
                p.set(index,new Assignacio(this.target,(int)this.source.valorEnter));
            }else if(assig.source.tsb == TSB.CARACTER){
                if (assig.tipus == Assignacio.TIPUSASSIG.TIPUS1){ 
                    if (p.get(index - 1) instanceof Assignacio) { //b[0] = a
                        if (((Assignacio)p.get(index - 1)).target == this.source) { //t1 = 'a'; t[0]=t1; ----> t[0] = 'a'
                            //Suposició de que a damunt tenim el caracter
                            this.valorString = ((Assignacio) p.get(index - 1)).valorString;
                            this.source = null;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    
    //SIMPLE -> a = b
    //TIPUS1: a[x] = b
    //TIPUS2: a = b[x]
    public enum TIPUSASSIG {
        SIMPLE, TIPUS1, TIPUS2;
    }

}
