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
public class GotoCond extends Instruccio{
    public Etiqueta ei;
    Tipus t;
    Variable a;
    Variable b;
    
    public GotoCond(Etiqueta ei, Tipus t, Variable a, Variable b){
        this.ei = ei;
        this.t = t;
        this.a = a;
        this.b =b;
    }
     
    public enum Tipus{
        EQ, NEQ, GT, LT, GTE, LTE
    }
    
    private String jmpType[] = {"je", "jne", "jg", "jl", "jge", "jle"}; 
    
    @Override
    public String toString() {
        if (this.a.constantPermanent) {
            return "if "+ this.a.valorEnter +" "+ this.t.toString()+" "+ this.b + " goto "+this.ei+"\n";
        }
        
        if (this.b.constantPermanent) {
            return "if "+ this.a +" "+ this.t.toString()+" "+ this.b.valorEnter + " goto "+this.ei+"\n";
        }
        return "if "+ this.a +" "+ this.t.toString()+" "+ this.b+ " goto "+this.ei+"\n";
    }
    
    @Override
    public String toAssembly(){
        String s = "";
        if (this.a.constantPermanent) {
            s += "mov"+Generador.getOcupacio(a.tsb)+ " $" +a.valorEnter+" ,"
                + Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.A)+"\n";
        }else{
            s += "mov"+Generador.getOcupacio(a.tsb)+ " " +Generador.etiqEnsamblador(a)+" ,"
                + Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.A)+"\n";
        }
        
        if (this.b.constantPermanent) {
            s += "mov"+Generador.getOcupacio(b.tsb)+ " $"+ b.valorEnter +" ," +
                 Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.D)+"\n";
        }else{
            s += "mov"+Generador.getOcupacio(b.tsb)+ " "+ Generador.etiqEnsamblador(b)+" ," +
                 Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.D)+"\n";
        }
        
        s += "cmp"+Generador.getOcupacio(a.tsb)+ " " +Generador.retornaRegistre(Generador.getOcupacio(b.tsb), Generador.Registre.D)+" ,"
                + Generador.retornaRegistre(Generador.getOcupacio(a.tsb), Generador.Registre.A) + "\n";

        s += jmpType[t.ordinal()] + " " + this.ei + "\n";
        return s;
    }

    @Override
    public boolean optimitza(ArrayList<Instruccio> p, int index) {
        
        //Sabem que la següent instrucció pot ser un Goto
        if (p.get(index + 1) instanceof Goto) {
            //Brancament adjacents
            //Asseguram que ho sigui
            Goto g = (Goto) p.get(index + 1);
            this.ei = g.e;
            Tipus tip[] = {Tipus.NEQ, Tipus.EQ, Tipus.LTE, Tipus.GTE, Tipus.LT, Tipus.GT};
            this.t = tip[t.ordinal()];
            //Borram el Goto
            p.remove(index + 1);
            return true;
        }
        
        //Brancament sobre brancament
        boolean canvis = true;
        boolean retorn = false;
        while(canvis) {
            canvis = false;
            int i = p.indexOf(this.ei.skip);
            if (p.get(i + 1) instanceof Goto) { //Comprovam que realment sigui un goto
                Goto g = (Goto) p.get(i + 1); //Agafam el Goto a on bota
                this.ei = g.e; //Canviam la etiqueta
                canvis = true;
                retorn = true;
            }
        }
        
        // Operacions constants
        if(a.nAssignacions == 1 && b.nAssignacions == 1){
            switch (t) {
                case EQ:
                    if(a.valorEnter == b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                case GT:
                    if(a.valorEnter > b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                case GTE:
                    if(a.valorEnter >= b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                case LT:
                    if(a.valorEnter < b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                case LTE:
                    if(a.valorEnter <= b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                case NEQ:
                    if(a.valorEnter != b.valorEnter){
                        p.set(index, new Goto(ei));
                    }else{
                        p.remove(index);
                    }
                    retorn = true;
                    break;
                default:
                    break;
            }
        }
        
        return retorn;
    }
}



