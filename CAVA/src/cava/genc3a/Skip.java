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
public class Skip extends Instruccio{
    Etiqueta e;
    
    public Skip(Etiqueta e){
        this.e = e;
        this.e.skip = this;
    }

    @Override
    public String toString() {
        return this.e.toString() + ":skip\n";
    }
    
    @Override
    public String toAssembly(){
        return this.e.toString() + ":\n";
    }
    
    @Override
    public boolean optimitza(ArrayList<Instruccio> p, int index){
        // Eliminació de codi inaccessible
        if(!this.e.utilitzada){
            p.remove(index);
            return true;
        }
        return false;
    }
}
