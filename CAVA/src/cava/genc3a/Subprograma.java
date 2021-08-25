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
public class Subprograma {
    public Etiqueta etiqueta;
    public int ocupacioVar;
    public int offsetVar;
    public int ocupacioParam;
    public boolean esProc; //Si es una funcio o no
    private int offsetParams;
    public ArrayList<Variable> llistaVariables = new ArrayList();
 
    public Subprograma(Etiqueta e, boolean esProc) {
        this.etiqueta = e;
        this.ocupacioVar = 0;
        this.ocupacioParam = 0;
        this.esProc = esProc;
        if (esProc) {
            this.offsetParams = 8;
        }
        else{
            this.offsetParams = 16;
        }
        this.offsetVar = -8;
    }
    
    public void afegirParam(Variable a){
        this.ocupacioParam += 8;
        a.offset=this.offsetParams;
        this.offsetParams += 8;
        //System.out.println("Som subprograma: " + this.etiqueta.toString() + "Me fiquen: "+ a.toString()+"\n");
        
    }

    @Override
    public String toString() {
        return etiqueta.toString();
    }
    
    
    
    
}
