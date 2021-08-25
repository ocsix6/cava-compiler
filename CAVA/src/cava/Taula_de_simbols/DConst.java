package cava.Taula_de_simbols;

import cava.genc3a.Variable;

public class DConst extends Descripcio{
    private static int idincrement = 0;
    //Aquest numero de variable, té tal posició de memoria
    int numVariable;
    
    public String tipus; //int, boolean, string
    /*De la constant necessitam el seu valor ja que és una constant */
    public String valor; 
    
    public Variable var;

    public DConst(){}
    
    /* Ens arriba un String: cridada, id, stringliteral, true i false */ 
    public DConst(String tipus, String valor) {
        this.tipus = tipus;
        this.valor = valor;
        this.numVariable = idincrement++;
    }
    
    /* Ens arriba un numero: num */
    public DConst(String tipus, Integer valor) {
        this.tipus = tipus;
        this.valor = valor+""; // Passam de integer a String
        this.numVariable = idincrement++;
    }
    
    @Override
    public String toString() {
        return "DConst{" + "numVariable=" + numVariable + ", tipus=" + tipus + ", valor=" + valor + '}';
    }
}
