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

public class Etiqueta {

    private static int n = 0;
    int num;
    String nom;
    public Skip skip; //A quina etiqueta esteim
    boolean utilitzada;
    
    public Etiqueta(String nom){
        n++;
        this.num = n;
        this.nom = nom;
    }
    
    public Etiqueta(){
        n++;
        this.num = n;
        this.nom = "e";
    }

    @Override
    public String toString() {
        if (this.nom.equals("e")) {
            return this.nom + "_" +this.num;
        }else{
            return this.nom;
        }
    }
    
    
    
}
