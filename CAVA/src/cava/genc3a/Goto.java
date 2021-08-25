/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import java.util.ArrayList;

/**
 *
 * @author Jim
 */

public class Goto extends Instruccio{
    public Etiqueta e;
    
    public Goto(Etiqueta ei){
        this.e = ei;
    }

    @Override
    public String toString() {
        return "goto "+ this.e+"\n";
    }
    
    @Override
    public String toAssembly(){
        return "jmp " + this.e + "\n";
    }

    @Override
    public boolean optimitza(ArrayList<Instruccio> p, int index) {
        //Brancament sobre brancament
        boolean canvis = true;
        boolean retorn = false;
        while(canvis) {
            canvis = false;
            int i = p.indexOf(this.e.skip);
            if (i != -1 && (i+1)< p.size() && (p.get(i + 1) instanceof Goto)) { //Comprovam que realment sigui un goto
                Goto g = (Goto) p.get(i + 1); //Agafam el Goto a on bota
                if(this.e != g.e){ // Comprovam que la etiqueta a on anam no es 
                                   // la mateixa, i per tant, no hi ha canvis
                    this.e = g.e; //Canviam la etiqueta
                    canvis = true;
                    retorn = true;
                }
            }
        }
        
        //Miram si la següent instrucció és la propia etiqueta a on bota
        if(p.get(index+1) instanceof Skip){
           //comprovam que realment bota a aquesta instrucció
            if (((Skip)p.get(index+1)).e == this.e) { //Podem eliminar el Goto
                p.remove(index);
                return true;
            }
        }
        
        // Optimització de codi inaccessible
        // 
        index++;
        while(index < p.size() &&((p.get(index) instanceof Skip && !((Skip)p.get(index)).e.utilitzada) || !(p.get(index) instanceof Skip))){
            p.remove(index);
            retorn = true;
        }
        return retorn;
    }
        
}


