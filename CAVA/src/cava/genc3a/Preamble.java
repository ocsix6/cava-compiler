/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;

/**
 *
 * @author Joan Canals
 */
public class Preamble extends Instruccio{
    
    Subprograma subprog;
    
    public Preamble(Subprograma subprog){
        this.subprog = subprog;
    }

    @Override
    public String toString() {
        return "preamble_"+subprog.etiqueta +"\n";
    }
    
    @Override
    public String toAssembly(){
        /* Guardar rbp (base pointer), actualitzar rbp al nou, reservar espai 
        de les variables locals i en el cas de tenir arrays, inicialitzar els possibles
        strings. */
        String s = "push %rbp\n"; 
        s += "movq %rsp,%rbp\n";
        s += "addq $8,%rbp\n";//Ens colocam al nou base pointer del nou subprograma
        s += "subq $"+(this.subprog.ocupacioVar) + ",%rsp\n";// Aquest mes 8 es del Base Pointer anterior
        for (int i = 0; i < subprog.llistaVariables.size(); i++) {
            if(subprog.llistaVariables.get(i).tsb == TSB.STRING && !subprog.llistaVariables.get(i).parametre){ 
                int offset = subprog.llistaVariables.get(i).offset + Generador.getOcupacioInt(subprog.llistaVariables.get(i).tsb);
                s += "lea "+Integer.toString(offset)+"(%rbp), %rax\n";
                s += Generador.guarda(subprog.llistaVariables.get(i), Generador.Registre.A);
            }
        }
        return s;
    }
    
}
