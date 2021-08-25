package cava.Taula_de_simbols;

import cava.genc3a.Variable;


public class DParametre extends Descripcio{
    //No guardam l'id perque el guardam dins el bloc 
    public String tipus; //Int, char, String, bool, etc.
    public String id;
    public enum argtipus {IN, OUT, INOUT}
    argtipus atribut;
    public Variable variable;

    public DParametre(String id, String tipus, argtipus atribut) {
        this.id = id;
        this.tipus = tipus;
        this.atribut = atribut;
    }  

    @Override
    public String toString() {
        return "DParametre{" + "tipus=" + tipus + ", id=" + id + ", atribut=" + atribut + '}';
    }
}
