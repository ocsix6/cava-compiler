/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;
import cava.genc3a.Generador.Registre;
import static cava.genc3a.Generador.etiqEnsamblador;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class Aritmetica extends Instruccio{
    public Variable a;
    public Variable b;
    public Operador op;
    public Variable desti;
    
    public Aritmetica(Variable desti, Variable a, Variable b, Operador op) {
        this.desti = desti;
        this.a = a;
        this.b = b;
        this.op = op;
    }
    
    @Override
    public String toString() {
        String op[] = {"+", "-"};
        String string = this.desti + " = ";
        if(a.constantPermanent){
            string += this.a.valorEnter + " ";
        }else{
            string += this.a + " ";
        }
        string += op[this.op.ordinal()] + " ";
        if(b.constantPermanent){
            string += this.b.valorEnter;
        }else{
            string += this.b;
        }
        string += "\n";
        return string;
    }
    
    @Override
    public String toAssembly() {
        String s="";
        if (!(a.constantPermanent && a.valorEnter ==1)) {
            s += Generador.carrega(a, Generador.Registre.A);
        }
        if (!(b.constantPermanent && b.valorEnter ==1)) {
            s += Generador.carrega(b, Generador.Registre.D);
        }
        //add offsetB(%rbp), offsetA(%rbp)
        Registre r = Generador.Registre.A;
        switch(this.op){
            case SUMA:
                if(a.constantPermanent && a.valorEnter == 1){
                    s+="inc"+Generador.getOcupacio(a.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.D)+"\n";
                    r = Generador.Registre.D;
                }else if(b.constantPermanent && b.valorEnter == 1){
                    s+="inc"+Generador.getOcupacio(b.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.A)+"\n";
                }else if(a.constantPermanent && a.valorEnter == 0){
                    // No feim res, perque sumar 0 no serveix, pero canviam el registre de destí, ens interessa moure
                    // l'operand de la dreta
                    r = Generador.Registre.D;
                }else if(b.constantPermanent && b.valorEnter == 0){
                    // No feim res perque sumar 0 no fa res.
                }else{
                    s+="add"+Generador.getOcupacio(a.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.D)+", "+
                    Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.A)+"\n";
                }
                break;
            case RESTA:
                if(a.constantPermanent && a.valorEnter == 1){
                    s+="dec"+Generador.getOcupacio(a.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.D)+"\n";
                    r = Generador.Registre.D;
                }else if(b.constantPermanent && b.valorEnter == 1){
                    s+="dec"+Generador.getOcupacio(b.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.A)+"\n";
                }else if(a.constantPermanent && a.valorEnter == 0){
                    s+="neg"+Generador.getOcupacio(b.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.D)+"\n";
                    r = Generador.Registre.D;
                }else if(b.constantPermanent && b.valorEnter == 0){
                    // No feim res, perque restar 0 és fer res.
                }else{
                    s+="sub"+Generador.getOcupacio(a.tsb)+" "+Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.D)+", "+
                    Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.A)+"\n";
                }
                break;             
        }
        s+= Generador.guarda(this.desti, r);
        return s;
    }
    
    @Override
    public boolean optimitza(ArrayList<Instruccio> p, int index){
        Aritmetica arit = (Aritmetica)p.get(index);
        if(arit.a.constantPermanent && arit.b.constantPermanent){
            long temp = 0;
            if(arit.op.equals(Operador.SUMA)){
                temp = arit.a.valorEnter + arit.b.valorEnter;
            }else if(arit.op.equals(Operador.RESTA)){
                temp = arit.a.valorEnter - arit.b.valorEnter;
            }
            Assignacio assig = new Assignacio(arit.desti,(int)temp); 
            p.set(index, assig);
            return true;
        }
        return false;
    }
    
    public enum Operador{
        SUMA, RESTA
    }
}


