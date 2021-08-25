/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.Taula_de_simbols;


public class DTipus extends Descripcio{
    public TSB tsb;
    // El nom no el guardam, perque estara dins bloc. 

    public DTipus(TSB tsb) {
        this.tsb = tsb;
    }

    @Override
    public String toString() {
        return "DTipus{" + "tsb=" + tsb + '}';
    }
    
    
}
