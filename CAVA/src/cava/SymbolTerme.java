package cava;

import cava.Taula_de_simbols.DConst;
import cava.Taula_de_simbols.DFuncio;
import cava.Taula_de_simbols.DProcediment;
import cava.Taula_de_simbols.DTipus;
import cava.Taula_de_simbols.DVariable;
import cava.Taula_de_simbols.Descripcio;
import cava.Taula_de_simbols.MODEVP;
import cava.Taula_de_simbols.TSB;
import cava.genc3a.Aritmetica;
import cava.genc3a.Assignacio;
import cava.genc3a.Etiqueta;
import cava.genc3a.Generador;
import cava.genc3a.Goto;
import cava.genc3a.GotoCond;
import cava.genc3a.GotoCond.Tipus;
import cava.genc3a.PosaParam;
import cava.genc3a.Skip;
import cava.genc3a.Variable;

public class SymbolTerme extends Expressio {

    Integer numero;
    String valor;

    String tipus; // id, int, string, boolean, void (null) 
    TSB tsb;
    MODEVP modevp;

    //En el cas de ser una cridada 
    public SymbolTerme(Parser p, SymbolCridada a) {
        super("SymbolTerme", p);
        //Ja haurem mirat si existeix la cridada 
        //Hem de guardar el tipus de retorn.
        this.tipus = a.tipusRetorn;
        //tsb: basat amb el retorn de la funcio
        this.tsb = a.tsb;
        //Sempre que arribem a aquest punt, sabrem
        //la cridada està completa.
        this.modevp = a.mvp;
        this.var = a.var;
        switch (this.tsb) {
            case BOOLEAN: 
                Variable tcert = p.gen.novaVariable(TSB.BOOLEAN, false);
                p.gen.genera(new Assignacio(tcert,1));
                GotoCond c = new GotoCond(null, Tipus.EQ, this.var, tcert);
                p.gen.genera(c);
                this.ecert.add(c);
                
                Goto c2 = new Goto(null);
                p.gen.genera(c2);
                this.efals.add(c2);
                break;
            default:
               
            }
        //
        this.afegirFill(a);
        this.pintarArbre();
    }

    //En el cas que Terme sigui un número
    public SymbolTerme(Parser p, Integer a) {
        super("SymbolTerme", p);
        this.numero = a;
        this.tipus = "int";
        this.tsb = TSB.INTEGER;
        this.modevp = MODEVP.CONSTANT;
        //
        this.afegirFill(Integer.toString(a));
        this.pintarArbre();

        this.var = p.gen.novaVariable(this.tsb);
        p.gen.genera(new Assignacio(this.var, a));
    }

    //En el cas de que sigui un ID
    public SymbolTerme(Parser p, String a) throws Exception {
        super("SymbolTerme", p);
        //Significarà que és una variable creada la qual esteim cridant
        Descripcio d = p.TS.consulta(a);
        if (d == null) {
            //ERROR: no existeix la variable
            throw new VarNoEx();
        }
        //Cap la possibilitat que sigui una cridada
        if (d instanceof DFuncio || d instanceof DProcediment) {
            throw new ErrorTipus(); //No es una variable!!! CANVIAR ERROR
        } else {
            //Existeix la variable que ens han escrit
            //Miram si és del tipus Constant
            DTipus descaux = new DTipus(null);
            if (d instanceof DVariable) {
                DVariable daux = (DVariable) d; //Casting
                descaux = (DTipus) p.TS.consulta(daux.tipus);
                this.tipus = daux.tipus;
                this.tsb = descaux.tsb;
                this.var = daux.var;
            } else if (d instanceof DConst) {
                DConst daux = (DConst) d; //Casting
                descaux = (DTipus) p.TS.consulta(daux.tipus);
                this.tipus = daux.tipus;
                this.tsb = descaux.tsb;
                this.valor = daux.valor; //Ja que es una constant, podem donar valor
                this.var = daux.var;
            } else {
                //ERROR: No es ni una variable ni una constant 
                throw new ErrorTipus();
            }
            this.modevp = MODEVP.VARIABLE;
        }
        if (this.tsb == TSB.BOOLEAN) {
            Variable truevar = p.gen.novaVariable(TSB.BOOLEAN);
            p.gen.genera(new Assignacio(truevar, 1));
            GotoCond gotoCondtrue = new GotoCond(null, Tipus.EQ, this.var, truevar);
            this.ecert.add(gotoCondtrue);
            p.gen.genera(gotoCondtrue);
            Goto g = new Goto(null);
            this.efals.add(g);
            p.gen.genera(g);
        }
        //
        this.afegirFill(a);
        this.pintarArbre();
    }

    public SymbolTerme(Parser p, String a, int c) {
        super("SymbolTerme", p);
        switch (c) {
            case 0: //String
                this.tsb = TSB.STRING;
                this.tipus = "string";
                this.valor = a;
                /* Reservar espai de memoria dins la pila, i ficar cada un dels
                caracters dins l'espai reservat. També hem de guardar un espai 
                dins la memoria que contendra el punter a aquesta llista. */
                this.var = p.gen.novaVariable(this.tsb);
                //Es el tamany del bloc de memòria
                this.var.ocupacioString = Generador.getOcupacioInt(TSB.CARACTER) * a.length() + 1; //+1 per el final del string '0' 
                for (int i = 0; i < a.length(); i++) {
                    Variable offset = p.gen.novaVariable(TSB.INTEGER, false);
                    p.gen.genera(new Assignacio(offset, i)); //Donam valor al la variable offset
                    
                    Variable caracter = p.gen.novaVariable(TSB.CARACTER, false);
                    p.gen.genera(new Assignacio(caracter, Character.toString(a.charAt(i))));
                    
                    p.gen.genera(new Assignacio(this.var, caracter , offset , Assignacio.TIPUSASSIG.TIPUS1));
                }
                Variable caracterZero = p.gen.novaVariable(TSB.CARACTER, false);
                Variable offset = p.gen.novaVariable(TSB.INTEGER, false);
                p.gen.genera(new Assignacio(offset, a.length())); //Donam valor al la variable offset
                p.gen.genera(new Assignacio(caracterZero, "\0"));
                p.gen.genera(new Assignacio(this.var, caracterZero , offset , Assignacio.TIPUSASSIG.TIPUS1));
                break;
            case 1: //True
                Goto g = new Goto(null);
                this.ecert.add(g);
                p.gen.genera(g);
                this.afegirFill("TRUE"); //Per l'arbre sintàctic
                this.tsb = TSB.BOOLEAN;
                this.tipus = "boolean";
                this.valor = "True";
                break;
            case 2: //False
                Goto go = new Goto(null);
                this.efals.add(go);
                p.gen.genera(go);
                this.afegirFill("FALSE"); // Per l'arbre sintàctic
                this.tsb = TSB.BOOLEAN;
                this.tipus = "boolean";
                this.valor = "False";
                break;
        }
        this.modevp = MODEVP.CONSTANT;
        //
        this.pintarArbre();
    }

   
    //PER ACCEDIR A UN PARAMETRE DE UN ARRAY 
    public SymbolTerme(Parser p, String a, SymbolExp b) throws Exception {
        super("SymbolTerme", p);
        //Comprovar q sigui un num
        this.tsb = b.tsb;
        if (tsb != TSB.INTEGER) {
            //ERROR
            throw new TSBInvalid();
        }
        Descripcio d = p.TS.consulta(a);
        if (d == null) {
            //ERROR: no existeix la variable
            throw new VarNoEx();
        }

        //Existeix la variable que ens han escrit
        //Miram si és del tipus Constant
        DTipus descaux;
        if (d instanceof DVariable) {
            DVariable daux = (DVariable) d; //Casting
            if (!daux.tipus.equals("string")) {
                throw new ErrorTipus();
            }
            descaux = (DTipus) p.TS.consulta(daux.tipus);
            this.tipus = daux.tipus;
            this.tsb = descaux.tsb;
            this.var = daux.var;
            this.modevp = MODEVP.VARIABLE;
        } else if (d instanceof DConst) {
            DConst daux = (DConst) d; //Casting
            if (!daux.tipus.equals("string")) {
                throw new ErrorTipus();
            }
            descaux = (DTipus) p.TS.consulta(daux.tipus);
            this.tipus = daux.tipus;
            this.tsb = descaux.tsb;
            this.valor = daux.valor; //Ja que es una constant, podem donar valor
            this.var = daux.var;
            this.modevp = MODEVP.CONSTANT;
        } else {
            //ERROR: No es ni una variable ni una constant 
            throw new ErrorTipus();
        }

        //
        this.afegirFill(a);
        this.pintarArbre();
    }
    
    //(EXPRESSIO)
    public SymbolTerme(Parser p, SymbolExp b){
        super("SymbolTerme", p);
        this.tipus = b.tipus;
        switch(b.m){
            case VARIABLE: this.modevp = MODEVP.VARIABLE; break;  
            case CONSTANT: this.modevp= MODEVP.CONSTANT; break;
            case RESULTAT: this.modevp= MODEVP.VARIABLE; break;
        }
        this.tsb = b.tsb;
        this.ecert = b.ecert;
        this.efals = b.efals;
        this.var = b.var;
        this.numero = b.numero;
        this.valor = b.valor;
    }
    
    //-TERME
    public SymbolTerme(Parser p, String a, SymbolTerme b) throws Exception{
        super("SymbolTerme", p);
        if (b.tsb != TSB.INTEGER) {
            throw new TSBInvalid();
        }
        if (b.modevp != MODEVP.CONSTANT) {
            throw new TipusTSBIncorrecta();
        }
        this.modevp = MODEVP.VARIABLE;
        this.numero = -b.numero;
        this.tsb = b.tsb;
        this.tipus = b.tipus;
        this.var = p.gen.novaVariable(this.tsb, false);
        Variable aux = p.gen.novaVariable(this.tsb, false);
        p.gen.genera(new Assignacio(aux, 0));
        p.gen.genera(new Aritmetica(this.var, aux , b.var, Aritmetica.Operador.RESTA));
    }
}
