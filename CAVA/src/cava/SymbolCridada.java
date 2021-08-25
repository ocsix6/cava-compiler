package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.MODEVP;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Assignacio;
import cava.genc3a.Call;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Instruccio;
import cava.genc3a.PosaParam;
import cava.genc3a.Skip;
import cava.genc3a.Variable;
import java.util.ArrayList;


/**
 *
 * @author Jim
 */
public class SymbolCridada extends SymbolBase{
    String tipusRetorn;
    MODEVP mvp;
    TSB tsb; //Va en funció del tipus de retorn
    String id;
    Variable var;
    
    ArrayList<Instruccio> seg = new ArrayList<>();
    
    public SymbolCridada(Parser p, SymbolArgs a) throws Exception {
        super("SymbolCridada",p);
        this.id = a.id;
        this.mvp = a.modevc;
        this.tipusRetorn = a.tipusRetorn;
        
        if (tipusRetorn.equals("null")) { 
            this.tsb = TSB.NULL; // idnula
        }else if(tipusRetorn.equals("int")){
            this.tsb = TSB.INTEGER;
        }else if(tipusRetorn.equals("string")){
            this.tsb = TSB.STRING;
        }else if(tipusRetorn.equals("boolean")){
            this.tsb = TSB.BOOLEAN;
        }else{
            throw new TipusTSBIncorrecta();
        }
        //
        this.afegirFill(a);
        this.pintarArbre();
        
        Descripcio d = p.TS.consulta(a.id);
        if (d  instanceof DProcediment) {
            DProcediment dp= (DProcediment) d;
            p.gen.genera(new Call(dp.subprograma));
        }else if(d instanceof DFuncio){
            DFuncio df = (DFuncio) d;
            this.var = p.gen.novaVariable(this.tsb);
            p.gen.genera(new Call(df.subprograma,var));
        }
        
    }
}
