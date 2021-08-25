/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.Descripcio;
import cava.genc3a.Call;

public class SymbolPrograma extends SymbolBase{
    
    public SymbolPrograma(Parser p,SymbolLlistaDecl a)throws Exception{
        super("SymbolPrograma",p);
        Descripcio d; 
        if((d = p.TS.consulta("main")) == null){
            throw new noExMain();
        }
        if (d instanceof DFuncio){
            throw new  MainInc();
        }
        if(p.TS.consultaSiTeArg("main")){
            throw new  MainAmbParam();
        }
        
        //Comprovació de que els noms dels diferents procedures arriben correctament 
        /*for (int i = 0; i < a.procediments.size(); i++) {
            System.out.println(a.procediments.get(i));
        }*/
        
        //Per dibuixar el graf
        //tambe se han de posar els terminals
        this.afegirFill(a);
        this.pintarArbre();
        
        DProcediment dp = (DProcediment) p.TS.consulta("main");
        p.gen.genera(new Call(dp.subprograma));
    }       
}
