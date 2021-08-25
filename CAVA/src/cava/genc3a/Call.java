/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

/**
 *
 * @author Jim
 */
public class Call extends Instruccio{
    Subprograma s; 
    public Variable v;
    
    public Call(Subprograma s){
        this.s = s;
        this.v = null;
    }
    
    public Call(Subprograma s, Variable v){ //Constructor per una funció
        this.s = s;
        this.v = v;
    }

    @Override
    public String toString() {
        if (this.s.esProc == false) {
            return this.v.nom + " = " + "Call " +s.etiqueta+"\n";
        }
        return "Call " +s.etiqueta+"\n";
    }
    
    @Override
    public String toAssembly(){
        String s = "";
        if (this.s.esProc == false) {
            //Reservam espai el retorn de la funció
            s += "subq $8,%rsp\n";
        }
        s += "call " + this.s.etiqueta+ "\n";
        //Borram els paràmetres de la pila
        if (this.s.esProc == false){ //Cas d'una funció
            s += "pop %r9\n";
            s += "movq %r9,"+ Generador.etiqEnsamblador(v)+ "\n";
        }
        s += "addq $"+ this.s.ocupacioParam+",%rsp\n"; //Cas de procediment
        return s;
    }
    
}
