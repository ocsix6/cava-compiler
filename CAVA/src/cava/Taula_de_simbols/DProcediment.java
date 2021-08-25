package cava.Taula_de_simbols;

import cava.genc3a.Subprograma;

/**
 *
 * @author Jim
 */
public class DProcediment extends Descripcio{
    int numProcediment; 
    private static int idincrement = 0;
    String tipusRetorn; //Sempre sera null (void)
    public Subprograma subprograma;
    
    public DProcediment(Subprograma subprograma) {
        this.numProcediment = idincrement;
        idincrement++;
        tipusRetorn = "null";
        this.subprograma = subprograma;
    }

    @Override
    public String toString() {
        return "DProcediment{" + "numProcediment=" + numProcediment + ", tipusRetorn=" + tipusRetorn + '}';
    }
}

