/* La seva funció és crear el codi intermedi */
package cava.genc3a;

import cava.Taula_de_simbols.TSB;
import cava.genc3a.Aritmetica.Operador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jim
 */
public class Generador {

    ArrayList<Instruccio> llistaCodi = new ArrayList<>();
    ArrayList<Variable> taulaVariable = new ArrayList<>();
    ArrayList<Subprograma> taulaSub = new ArrayList<>();
    ArrayList<Etiqueta> taulaEtiquetes = new ArrayList<>();

    private static Subprograma subgeneral = new Subprograma(new Etiqueta(),true);
    public Subprograma subprogramaActual = subgeneral;

    public void genera(Instruccio i) {
        llistaCodi.add(i);
    }

    public Subprograma nouSubprograma(String id, Etiqueta einici, boolean esProc) { 
        Subprograma sub = new Subprograma(einici, esProc);
        taulaSub.add(sub);
        return sub;
    }

    /* Per a la taula de variable */
    public Variable novaVariable(TSB t) {
        Variable var = new Variable(t, this.subprogramaActual, false);
        this.subprogramaActual.llistaVariables.add(var);
        this.taulaVariable.add(var);
        return var;
    }

    public Variable novaVariable(TSB t, String nom) {
        Variable var = new Variable(t, nom, this.subprogramaActual, false);
        this.subprogramaActual.llistaVariables.add(var);
        this.taulaVariable.add(var);
        return var;
    }
    
    public Variable novaVariable(TSB t, boolean parametre){
        Variable var = new Variable(t, this.subprogramaActual, parametre);
        this.subprogramaActual.llistaVariables.add(var);
        this.taulaVariable.add(var);
        
        return var;
    }
    
    public Etiqueta novaEtiqueta(){
        Etiqueta e = new Etiqueta();
        this.taulaEtiquetes.add(e);
        return e;
    }
    
    public Etiqueta novaEtiqueta(String nom){
        Etiqueta e = new Etiqueta(nom);
        this.taulaEtiquetes.add(e);
        return e;
    }
    
    public void printTVarProc(String nom) throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
        bw.write("~~~~ VARIABLES ~~~~ \n");
        for (int i = 0; i < taulaVariable.size(); i++) {
            Variable variable = taulaVariable.get(i);
            if (variable.sub == subgeneral) {
                bw.write("Variable "+ variable.nom + " es una variable global \n");
            }else if(!variable.parametre){
                bw.write("Variable "+ variable.nom + " del subprograma " + variable.sub.toString()+"\n");
            }else {
                bw.write("Parametre "+ variable.nom + " del subprograma " + variable.sub.toString()+"\n");
            }
        }
        bw.write("\n~~~~ PROCEDIMENTS ~~~~ \n");
        for (int i = 0; i < taulaSub.size(); i++) {
            Subprograma sub = taulaSub.get(i);
            if(sub.esProc){
                bw.write(sub.etiqueta + " es un procediment\n");
            }else{
                bw.write(sub.etiqueta + " es una funció\n");
            }
        }
        
        bw.close();
    }
    
    public void toTxt(String nom) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
        for (int i = 0; i < llistaCodi.size(); i++) {
            bw.write(llistaCodi.get(i).toString());
        }
        bw.close();
    }
  
    public void toAssembly(String nom) throws IOException {
        //actualizar les taules
        calculaOcupacio();
        BufferedWriter bw = new BufferedWriter(new FileWriter(nom));
        bw.write(".data\n\r"); //Tenim la adreça des array
        for (int i = 0; i < taulaVariable.size(); i++) { // Posam les variables globals
            Variable variable = taulaVariable.get(i);
            if (variable.sub == subgeneral && variable.tsb == TSB.STRING) {
                bw.write(variable.nom+ ": .quad "+ variable.nom + "_ \n");  
            }
        }
        bw.write(".bss\n\r"); //Tenim les dades de la adreça 
        for (int i = 0; i < taulaVariable.size(); i++) { // Posam les variables globals
            Variable variable = taulaVariable.get(i);
            if (variable.sub == subgeneral) {
                if (variable.tsb == TSB.STRING) {
                     bw.write(variable.nom+ "_: .fill "+ variable.ocupacioString +", 1, 0\n");  //ocupatció TSB, 1, 0  //Ho volem repetir un pic amb 0s
                }else{ //INTEGER
                    bw.write(variable.nom+ ": .fill "+ getOcupacioInt(variable.tsb) +", 1, 0\n");  //ocupatció TSB, 1, 0 
                }
                
            }
        }
        bw.write(".global _start\n\r");
        bw.write(".global _main\n\r");
        bw.write(".text\n\r");
        bw.write("_start:\n\r");
        bw.write("_main:\n\r");

        for (int i = 0; i < llistaCodi.size(); i++) {
            bw.write("/* "+llistaCodi.get(i).toString()+"*/\n");
            bw.write(llistaCodi.get(i).toAssembly());
        }
        //HALT
        bw.write("movq	$1, %rax\n");
        bw.write("movq	$0, %rbx\n");
        bw.write("int	$0x80 \n");
        bw.close();
    }
    
    public void calculaOcupacio(){
        //reiniciam l'ocupacio perque tendrem optimizacions i elminiarem variables.
        for (int i = 0; i < taulaSub.size(); i++) {
            taulaSub.get(i).ocupacioVar=0;
            taulaSub.get(i).offsetVar=-8;
        }
        int aux=0;
        for (int i = 0; i < taulaVariable.size(); i++) { 
            Variable variable = taulaVariable.get(i);
            if (variable.constantPermanent) { //Les constants no ocupen memoria
                //System.out.println("Variable " +variable.nom +" ,Tsb"+variable.tsb);
                continue; 
            }
            if(!variable.parametre && variable.sub != subgeneral){
                variable.sub.ocupacioVar+=getOcupacioInt(variable.tsb);
                variable.sub.ocupacioVar+=variable.ocupacioString; //Guardam el bloc
                variable.sub.offsetVar-=getOcupacioInt(variable.tsb);
                variable.sub.offsetVar-=variable.ocupacioString;
                variable.offset=variable.sub.offsetVar;
                
                System.out.println("Variable "+ variable.nom + " del subprograma " + variable.sub.toString()+ " té mida "+ getOcupacioInt(variable.tsb) 
                        +" de X offset: "+ variable.offset);
            }else if (variable.parametre){
                System.out.println("Parametre "+ variable.nom + " del subprograma " + variable.sub.toString()+ " té mida "+ getOcupacioInt(variable.tsb) 
                        +" de X offset: "+ variable.offset);
            } 
        }
    }
    
    
    
    public void optimitzacio() throws IOException {
        boolean b = true;
        
        //int idx = 0;
        while(b){ // Mentre hi hagi canvis, seguim optimitzant
            actualitzaConst();
            b = false;
            for (int i = 0; i < llistaCodi.size(); i++) {
                b = llistaCodi.get(i).optimitza(llistaCodi, i) || b;  
            }
            /*
            BufferedWriter bw = new BufferedWriter(new FileWriter("iteracio"+idx+".txt"));
            for (int i = 0; i < llistaCodi.size(); i++) {
                bw.write(llistaCodi.get(i).toString());
            }
            bw.close(); idx++;*/
        }
        //Les constants les borram a la darrera iteració i així asseguram de 
        //optimitzar el codi amb les constants disponibles
         actualitzaConst();
        for (int i = 0; i < llistaCodi.size(); i++) {
            if (llistaCodi.get(i) instanceof Assignacio) {
                if(((Assignacio)llistaCodi.get(i)).target.constantPermanent){
                    llistaCodi.remove(i);
                    i--;
                }
            }
        }
    }

    public static int getOcupacioInt(TSB t) {
        switch (t) {
            case INTEGER:
                return 8;
            case STRING:
                return 8;
            case CARACTER:
            case BOOLEAN:
                return 1;
            default:
                return 0;
        }
    }
    public static String getOcupacio(TSB t) {
        switch (t) {
            case INTEGER:
                return "q";
            case STRING:
                return "q";
            case CARACTER:
            case BOOLEAN:
                return "b";
            default:
                return "";
        }
    }
    
    public static String retornaRegistre(String ocupacio, Registre r){
        String oneByteRegister[] = {
            "al", "bl", "cl", "dl", "sil", "dil", "bpl", "spl", "r8b",
            "r9b", "r10b", "r11b", "r12b", "r13b", "r14b", "r15b"
        };

        String fourByteRegister[] = {
            "eax", "ebx", "ecx", "edx", "esi", "edi", "ebp", "esp", "r8d",
            "r9d", "r10d", "r11d", "r12d", "r13d", "r14d", "r15d"
        };

        String eightByteRegister[] = {
            "rax", "rbx", "rcx", "rdx", "rsi", "rdi", "rbp", "rsp", "r8",
            "r9", "r10", "r11", "r12", "r13", "r14", "r15"
        };
        
        String retorn = "%";
       
        switch (ocupacio) {
            /* Recordar que les variables globals estan al .bss o al .data amb un nom de variable! 
            Les locals estan dins la pila i hi accedim amb el registre BP */
            //subgeneral = subprogramaGeneral
            case "b":
                retorn+=oneByteRegister[r.ordinal()];
                break;
            case "l":
                retorn+=fourByteRegister[r.ordinal()];
                break;
            case "q":
                retorn+=eightByteRegister[r.ordinal()];
                break;
            default:
        }
        return retorn;
    }

    public static String carrega(Variable t, Registre r) {
        String retorn;
        if(t.constantPermanent){
            retorn = "mov"+getOcupacio(t.tsb) +" $"+ t.valorEnter+ ", " + retornaRegistre(getOcupacio(t.tsb), r);
        }else{
            retorn = "mov"+getOcupacio(t.tsb) +" "+ etiqEnsamblador(t)+ ", " + retornaRegistre(getOcupacio(t.tsb), r);
        }
        return retorn+"\n";
    }
    
    public static String guarda(Variable t, Registre r) {
        String retorn = "mov"+getOcupacio(t.tsb) + " " +retornaRegistre(getOcupacio(t.tsb), r) + ", " +etiqEnsamblador(t);
        return retorn+"\n";
        
    }
    
    public static String guarda(Variable t, int i) {
        String retorn = "mov"+getOcupacio(t.tsb) + " $"+i + ", " +etiqEnsamblador(t);
        return retorn+"\n";
    }
    
    public static String guarda(Variable t, String s){
        String retorn = "mov"+getOcupacio(TSB.CARACTER) + " $"+(int) s.charAt(0)+", " + etiqEnsamblador(t);
        return retorn+"\n";
    }
    
    public static String guarda(Variable target, Variable offset, String s){
        String retorn = "";
        retorn += "movq "+ etiqEnsamblador(target) + ",%r8\n";
        retorn += "movq $"+ offset.valorEnter + ",%r9\n";
        retorn += "mov"+ getOcupacio(TSB.CARACTER) + " $" +(int) s.charAt(0)+ ",(%r8, %r9)";
        return retorn+"\n";
    }
    
    //TIPUS1: a[x] = b
    //TIPUS2: a = b[x]
    public static String guarda(Variable a, Variable b, Variable x, Assignacio.TIPUSASSIG tip){
        String retorn = ""; 
        switch(tip){
            case TIPUS1:
                retorn += "movq "+ etiqEnsamblador(b) + ",%r10\n";
                retorn += "movq "+ etiqEnsamblador(a) + ",%r8\n";
                retorn += "movq "+ etiqEnsamblador(x) + ",%r9\n";
                retorn += "mov" + getOcupacio(b.tsb) +" "+ retornaRegistre(getOcupacio(b.tsb), Registre.R10)+ ",(%r8, %r9)";
                break;
            case TIPUS2:
                break;
            default:
                    
        }
        return retorn+"\n";
    }
    

    public enum Registre {
        A, B, C, D, SI, DI, BP, SP, R8, R9, R10, R11, R12, R13, R14, R15
    }
    
    public static String etiqEnsamblador(Variable t){
        if (t.sub != subgeneral) {
            //Es local
            return t.offset+"(%rbp)";
        } else {
            //Es global
            return t.toString();
        }
    }

    public void entraSubPrograma(Subprograma p){
        this.subprogramaActual = p;
    }

    public void surtSubPrograma() {
        this.subprogramaActual = subgeneral;
    }
    
    public void actualitzaConst(){
        Instruccio instr;
        for (int j = 0; j < taulaVariable.size(); j++) {
            if(taulaVariable.get(j).constantPermanent == false){
                taulaVariable.get(j).nAssignacions = 0;
            }
        }
        
        for (int i = 0; i < taulaEtiquetes.size(); i++) {
            taulaEtiquetes.get(i).utilitzada = false;
        }
        for (int i = 0; i < taulaSub.size(); i++) {
            taulaSub.get(i).etiqueta.utilitzada=true;
        }
        for (int i = 0; i < llistaCodi.size(); i++) {
            instr = llistaCodi.get(i);
            if(instr instanceof Assignacio){
                Assignacio assig = (Assignacio)instr;
                if (assig.tipus == Assignacio.TIPUSASSIG.TIPUS1) {
                    assig.target.nAssignacions = 2;
                }
                if (assig.tipus == Assignacio.TIPUSASSIG.SIMPLE && assig.target.tsb == TSB.STRING) {
                    assig.target.nAssignacions = 2;
                }
                if(!assig.target.constantPermanent){
                    assig.target.nAssignacions++;
                }
                if(assig.target.nAssignacions == 1 && !assig.target.constantPermanent){
                    if (assig.target.tsb == TSB.INTEGER || assig.target.tsb == TSB.BOOLEAN){ // Serveix tant per enters com booleans
                        if(!assig.valornull){ // a = constant
                            assig.target.valorEnter = assig.valorint;
                        }else{ // a = b
                            assig.target.nAssignacions++;
                        }
                    }else if(assig.target.tsb == TSB.CARACTER){
                        
                    }
                }
            }else if(instr instanceof Aritmetica){
                Aritmetica arit = (Aritmetica)instr;
                arit.desti.nAssignacions = 2; //Asseguram que no es una constant permanent
            }else if(instr instanceof Goto){ // Optimització de codi inaccessible
                ((Goto)instr).e.utilitzada = true;
            }else if(instr instanceof GotoCond){ // Optimització de codi inaccessible
                ((GotoCond)instr).ei.utilitzada = true;
            }
        }
        for (int j = 0; j < taulaVariable.size(); j++) {
            if(taulaVariable.get(j).nAssignacions == 1){
                taulaVariable.get(j).constantPermanent = true;
            }
        }
    }
}
