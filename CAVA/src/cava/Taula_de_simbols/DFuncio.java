package cava.Taula_de_simbols;

import cava.genc3a.Subprograma;


public class DFuncio extends Descripcio{
    private static int idincrement = 0;
    int numFuncio;
    public Subprograma subprograma;
    
    public String tipusRetorn; //int, boolean, string

    public DFuncio(String tipusRetorn, Subprograma subprograma){
        this.tipusRetorn = tipusRetorn;
        this.numFuncio = idincrement;
        this.numFuncio = idincrement++;
        this.subprograma = subprograma;
    }

    @Override
    public String toString() {
        return "DFuncio{" + "numFuncio=" + numFuncio + ", tipusRetorn=" + tipusRetorn + '}';
    }
}
