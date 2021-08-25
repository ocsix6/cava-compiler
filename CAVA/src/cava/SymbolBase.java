package cava;

import java.io.IOException;
import java.util.ArrayList;

//Ho emplearem per a pintar l'arbre sintàctic
public class SymbolBase {

    private static int idAutoIncrement = 0;
    ArrayList<String> fills = new ArrayList<String>();
    private int idNode;
    private static int idTerminals=0;
    String nomNode;
    Parser p;

    public SymbolBase(String NomSimbol, Parser p) {
        //assignar i augmentar el node id;
        idNode = idAutoIncrement++;
        this.nomNode = NomSimbol;
        this.p = p;
    }

    public int getIdNode() {
        return idNode;
    }
    
    public void pintarArbre() {
        p.archiuArbre.escriure(idNode + " [label=\"" + this.nomNode + "\"]\n");
        for (int i = 0; i < fills.size(); i++) {
            p.archiuArbre.escriure(idNode + "->" + fills.get(i) + "\n");
        }
        p.archiuArbre.escriure("{rank=same;\n");
        for (int i = 0; i < fills.size(); i++) {
            p.archiuArbre.escriure(fills.get(i)+";\n");
        }
        p.archiuArbre.escriure("}\n");
        for (int i = 1; i < fills.size(); i++) {
            p.archiuArbre.escriure(fills.get(i - 1) + "->" + fills.get(i) + "[style=invis] \n");
        }
    }

    public void afegirFill(SymbolBase fill) {
        fills.add(Integer.toString(fill.idNode));
    }
    
    public void afegirFill(String terminal) {
        String id;
        id="\""+this.idNode+"_"+idTerminals+"\"";
        idTerminals++;
        fills.add(id);
        p.archiuArbre.escriure(id+ "[label=\""+terminal+"\",shape=plaintext]\n");
    }

    
    //--------------------------------------------------------------------------
    //EXCEPCIONS 
    //Amb el nostre compilador, no permetem posar el mateix nom a dos metodes
    //tot i que tenguin paràmetres distints
    public class CridadaNoExistent extends Exception {

        public CridadaNoExistent() {
            super("ERROR: La cridada no existeix");
        }
    }

    public class CridaTeParam extends Exception {

        public CridaTeParam() {
            super("ERROR: La cridada necessita paràmetres");
        }
    }

    public class MetodeTipusEx extends Exception {

        public MetodeTipusEx() {
            super("ERROR: La cridada no es ni un procedure ni una funcio");
        }
    }

    public class InvalidParam extends Exception {

        public InvalidParam() {
            super("ERROR: Paràmetre tipus invàlid a la cridada");
        }
    }
    
    public class MassaParam extends Exception{
        public MassaParam(){
            super("ERROR: La crida no té tants de paràmetres");
        }
    }
    
    public class TipusTSBIncorrecta extends Exception {

        public TipusTSBIncorrecta() {
            super("ERROR: El tipus TSB es incorrecta");
        }
    }
    
    public class ErrorTipus extends Exception {

        public ErrorTipus() {
            super("ERROR: El tipus es incorrecta");
        }
    }
    
    public class ErrorConstTipus extends Exception {

        public ErrorConstTipus() {
            super("ERROR: Assignació a la constant de tipus incorrecta");
        }
    }
    
    public class VarNoEx extends Exception {

        public VarNoEx() {
            super("ERROR: La variable no existeix");
        }
    }
    
    public class TSBInvalid extends Exception {

        public TSBInvalid() {
            super("ERROR: TSB no vàlid");
        }
    }
    
    public class TSBsINVALIDEXPR extends Exception{
        public TSBsINVALIDEXPR() {
            super("ERROR: Expressió invàlida, no podem mesclar tipus");
        }
    }
    
    public class ImposibleDonarValor extends Exception{
        public ImposibleDonarValor() {
            super("ERROR: No es pot donar valor a una constant");
        }
    }
    
    public class assigIncorrecta extends Exception{
        public assigIncorrecta(){
            super("ERROR: Assignació incorrecta");
        }
    }
    
    public class returnIncorrecta extends Exception{
        public returnIncorrecta(){
            super("ERROR: Tipus return incorrecta");
        }
    }
    
    public class noExMain extends Exception{
        public noExMain(){
            super("ERROR: No existeix Main");
        }
    }
    
    public class noReturn extends Exception{
        public noReturn(){
            super("ERROR: Falta return a la funció");
        }         
    }
    
    public class Return extends Exception{
        public Return(){
            super("ERROR: S'ha detectat un return a un procediment");
        }         
    }
    
    public class MainInc extends Exception{
        public MainInc(){
            super("ERROR: El main es de tipus funció");
        }         
    }
    
    public class MainAmbParam extends Exception{
        public MainAmbParam(){
            super("ERROR: El main té paràmetres");
        }         
    }
}
