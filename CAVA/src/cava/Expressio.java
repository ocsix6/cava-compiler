/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.GotoCond;
import cava.genc3a.Instruccio;
import cava.genc3a.Variable;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */

public class Expressio extends SymbolBase{
    //Backpatching
    //Necessitam una llista per a CADA expressio
    ArrayList<Instruccio> ecert = new ArrayList<>();
    ArrayList<Instruccio> efals = new ArrayList<>();
    Variable var;
    
    public Expressio(String nom, Parser p){
        super(nom,p);
    }
    
    /*Li passam una etiqueta i una pila. Canviarà tots els objectes d'aquell 
    stack per l'etiqueta que li hem passat per paràmetre. */
    public static void backpatch(Etiqueta e, ArrayList<Instruccio> a){
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i) instanceof Goto){
               Goto g;
               g = (Goto) a.get(i);
               g.e = e;
            }else if (a.get(i) instanceof GotoCond) {
                GotoCond gc;
                gc = (GotoCond) a.get(i);
                gc.ei = e;
            }
        }
    }
    
    public static ArrayList<Instruccio> concatena(ArrayList<Instruccio> a, ArrayList<Instruccio> b){
        ArrayList<Instruccio> resultat = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            resultat.add(a.get(i));
        }
        
        for (int i = 0; i < b.size(); i++) {
            resultat.add(b.get(i));
        }
        return resultat;
    }
}
