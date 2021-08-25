/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cava;

import cava.Taula_de_simbols.DConst;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.DVariable;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.MODEVC;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Assignacio;
import cava.genc3a.Etiqueta;
import cava.genc3a.Goto;
import cava.genc3a.Skip;

/**
 *
 * @author Jim
 */
public class SymbolAssig extends SymbolBase {

    Integer numero;
    String valor; // string, true, false
    MODEVC m; //CONSTANT (num,string_literal,true o false), VARIABLE (id), RESULTAT (cridada)
    String tipus; // id, int,string, boolean, void (null) 
    TSB tsb;

    public SymbolAssig(Parser p, String a, SymbolExp b) throws Exception {
        //En el cas de voler donarli un nou valor a una constat. No hem de deixar.
        //En el cas de ser una variable. Primer hauriem de mirar el seu tipus, i llavors
        //intentar posarli el nou valor que ens ha passat l'usuari. Recordar que aquests valors
        //poden variar durant la execució i en cada moment s'ha de comprovar que es correcta la
        //inserció. 
        // El que podem fer es mirar si el valor que li vol assignar l'usuari es correcta
        // i correspon amb el tipus que toca. Per a que així durant la execució funcioni correctament. 
        super("SymbolAssig", p);
        Descripcio d = p.TS.consulta(a);
        if (d instanceof DConst) {
            throw new ImposibleDonarValor();
        } else if (d instanceof DVariable) {
            DTipus descaux = new DTipus(null);
            DVariable daux = (DVariable) d; //Casting
            descaux = (DTipus) p.TS.consulta(daux.tipus);
            if (descaux == null) {
                //Significa que intenam donar valor a una variable que no existeix
                throw new VarNoEx();
            }
            if (b.tsb != descaux.tsb) {
                //Error de tipus, esteim assignat un tipus incorrecta
                throw new assigIncorrecta();
            }
            this.tipus = daux.tipus;
            this.tsb = descaux.tsb;
            this.m = b.m;
            switch (b.tsb) {
            case INTEGER:
                this.numero = b.numero;
                p.gen.genera(new Assignacio(daux.var, b.var));
                break;
            case BOOLEAN: // Hem de donar 0 o 1 en funcio de si hem vengut de ecert o efalse
                // En el cas en que l'assignació es faci a un valor true
                Etiqueta ecert = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(ecert));
                p.gen.genera(new Assignacio(daux.var,1));
                Expressio.backpatch(ecert, b.ecert);
                Etiqueta efinal = p.gen.novaEtiqueta();
                p.gen.genera(new Goto(efinal));
                
                // En en cas en que l'assignació es faci a un valor false
                Etiqueta efals = p.gen.novaEtiqueta();
                p.gen.genera(new Skip(efals));
                p.gen.genera(new Assignacio(daux.var,0));
                Expressio.backpatch(efals, b.efals);
                p.gen.genera(new Skip(efinal));
                break;
            default:
                this.valor = b.valor; // string
                p.gen.genera(new Assignacio(daux.var, b.var));
            }
            
        } else {
            //ERROR: No es ni una variable ni una constant 
            throw new VarNoEx();
        }
        this.afegirFill(a);
        this.afegirFill("=");
        this.afegirFill(b);
        this.afegirFill(";");
        this.pintarArbre();
        
    }
}
