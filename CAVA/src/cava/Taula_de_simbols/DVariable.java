package cava.Taula_de_simbols;

import cava.genc3a.Variable;


public class DVariable extends Descripcio{
    private static int idincrement = 0;
    public String tipus; //int, boolean, string
    int numVariable;
    
    public Variable var;

    /* No guardam el valor ja que aixó es sabra durant el temps de execució */
    public DVariable(String tipus) {
        this.tipus = tipus;
        this.numVariable = idincrement++;
    }

    @Override
    public String toString() {
        return "DVariable{" + "tipus=" + tipus + ", numVariable=" + numVariable + '}';
    }
}
