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
public class Retorn extends Instruccio {
    
    Variable v;
    public Subprograma subprog;
    
    public Retorn(Subprograma subprog){
        this.subprog = subprog;
        this.v = null;
    }
    
    public Retorn(Subprograma subprog, Variable v){
        this.subprog = subprog;
        this.v = v;
    }

    @Override
    public String toString() {
        if(subprog.esProc){
            return "return \n";
        }else{
            //La v null es el return predeterminat que posam nosaltres al final d'una funcio
            if (this.v != null && this.v.constantPermanent) {
                if (this.v.tsb == TSB.STRING) {
                    return "return "+this.v.valorString+"\n";
                }
                return "return "+this.v.valorEnter+"\n";
            }
            return "return "+this.v+"\n";
        }
    }
    
    @Override
    public String toAssembly(){
        String s= "";
        if(subprog.esProc == false){
            //Hem de posar el valor de retorn dins la pila
            //Estira al bsp + 8
            if (this.v != null) {
                if (this.v.constantPermanent) {
                    if (this.v.tsb == TSB.STRING) {
                        s += "movq $" + this.v.valorString + " ,%r9\n";
                        s += "movq %r9, 8(%rbp)\n";
                    }else{
                        s += "movq $" + this.v.valorEnter + " ,%r9\n";
                        s += "movq %r9, 8(%rbp)\n";
                    }
                } else {
                    s += "movq " + Generador.etiqEnsamblador(v) + " ,%r9\n";
                    s += "movq %r9, 8(%rbp)\n";
                }
            }
        }
        //1- Eliminar variables locals
        s += "addq $" + (subprog.ocupacioVar) + ", %rsp\n";
        //2- Recuperam base pointer
        s += "pop %rbp\n";
        s += "ret\n";
        return s;
    }
    
    
}
