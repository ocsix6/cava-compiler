package cava.Taula_de_simbols;

import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TaulaDeSimbols {

    private final int NMAXSIMBOLS = 1000;
    private final int NMAXPROFUNDITAT = 20;

    private final int NUL = -1;

    // Cada pic que entram a bloc, ho augmentam. De manera que sortim, el decrementam.
    int npActual;

    private class Bloc {

        int nivell_profunditat;
        int next; //Index a la posició
        String id;
        Descripcio descripcio; //Si es un argument o no //(tipus (tipus subjacent bàsic, tipus de retorn), variable, funcio, procediment, arguments (nom argument, tipus (int, bool), si es un argument in inout), const (com les variables pero guarden el valor ))
        int posicioOriginal; // Recuperar
        int nivell_original;
        
        public Bloc() {
            this.nivell_profunditat = -1;
            this.next = -1;
            this.id = "";
            this.descripcio = null;
            this.posicioOriginal = -1;
            this.nivell_original = -1;
        }
        
        @Override
        public String toString() {
            return "Bloc{" + "nivell_profunditat=" + nivell_profunditat + ", next=" + next + ", id=" + id + ", descripcio=" + descripcio.toString() + ", posicioOriginal=" + posicioOriginal + '}';
        }
    }

    //Ens donarà Index a la taula de descripció
    HashMap<String, Integer> TaulaDispersio;
    Bloc[] TaulaDescripcio;
    //Guardam les coses que no ens serveixen de la taula de Descripció
    Bloc[] TaulaExpansio;
    //Index a la taula d'expansió.
    Integer[] TaulaAmbit;
    private int primeraPosLliure;

    //CONSTRUCTOR
    public TaulaDeSimbols() {
        this.primeraPosLliure = 0; //Cada pic que feim un put, sumam
        this.npActual = 0;
        this.TaulaDescripcio = new Bloc[NMAXSIMBOLS];
        //es pot inicialitzar aqui o quan realment s'usa el bloc
        for (int i = 0; i < TaulaDescripcio.length; i++) {
            TaulaDescripcio[i] = new Bloc();
        }
        //Guardam les coses que no ens serveixen de la taula de Descripció
        this.TaulaExpansio = new Bloc[NMAXPROFUNDITAT * NMAXSIMBOLS];
        for (int i = 0; i < TaulaExpansio.length; i++) {
            TaulaExpansio[i] = new Bloc();
        }
        //Index a la taula d'expansió.
        this.TaulaAmbit = new Integer[NMAXPROFUNDITAT];
        //Inicialitzam amb (NULL)
        for (int i = 0; i < TaulaAmbit.length; i++) {
            this.TaulaAmbit[i] = NUL;
        }
        this.TaulaDispersio = new HashMap<>();
    }

    public void entraBloc() {
        npActual++;
        TaulaAmbit[npActual] = TaulaAmbit[npActual - 1];
    }

    //Inicialitzar tot a zero
    public void reset() {
        this.npActual = 0;
        this.TaulaDispersio.clear();
        int i;
        for (i = 0; i < TaulaDescripcio.length; i++) {
            TaulaDescripcio[i] = new Bloc();
        }
        for (i = 0; i < TaulaExpansio.length; i++) {
            TaulaExpansio[i] = new Bloc();
        }
        for (i = 0; i < TaulaAmbit.length; i++) {
            this.TaulaAmbit[i] = NUL;
        }
    }

    public void afegirTSBs(String id, Descripcio d) {
        TaulaDispersio.put(id, this.primeraPosLliure);
        TaulaDescripcio[this.primeraPosLliure].id = id;
        TaulaDescripcio[this.primeraPosLliure].descripcio = d;
        TaulaDescripcio[this.primeraPosLliure].nivell_profunditat = NUL;
        TaulaDescripcio[this.primeraPosLliure].next = NUL;
        this.primeraPosLliure++;
    }

    //Donat un string, hem de arribat a una posicó de la taula de dispersió
    public void afegir(String id, Descripcio d) throws Exception{
        Integer i = TaulaDispersio.get(id);
        if (i != null) { // Si existeix, hem de mirar que no estigui al mateix nivell de profunditat
            if (this.TaulaDescripcio[i].nivell_profunditat == this.npActual) {
                throw new ExAfegir();
            } else { //Actualizam
                int idxe = TaulaAmbit[this.npActual];
                idxe++;
                TaulaAmbit[this.npActual] = idxe;

                TaulaExpansio[idxe].descripcio = TaulaDescripcio[i].descripcio;
                TaulaExpansio[idxe].nivell_profunditat = TaulaDescripcio[i].nivell_profunditat;
                TaulaExpansio[idxe].nivell_original = TaulaDescripcio[i].nivell_original;
                TaulaExpansio[idxe].id = TaulaDescripcio[i].id;
                TaulaExpansio[idxe].next = TaulaDescripcio[i].next;
                TaulaExpansio[idxe].posicioOriginal = i;

                TaulaDescripcio[i].nivell_profunditat = this.npActual;
                TaulaDescripcio[i].descripcio = d;
                TaulaDescripcio[i].next = NUL;
            }
        } else { //Si no existeix 
            TaulaDispersio.put(id, this.primeraPosLliure);
            TaulaDescripcio[this.primeraPosLliure].id = id;
            TaulaDescripcio[this.primeraPosLliure].descripcio = d;
            TaulaDescripcio[this.primeraPosLliure].nivell_profunditat = this.npActual;
            TaulaDescripcio[this.primeraPosLliure].nivell_original = this.npActual;
            TaulaDescripcio[this.primeraPosLliure].next = NUL;
            this.primeraPosLliure++;
        }
    }

    public void sortirBloc() throws Exception{
        if (this.npActual == 0) {
            //error, no hem entrat a cap bloc per tant no hi ha ningun bloc del que sortir
            //throws exception
            throw new ExSortirBloc();
        
        }
        for (int i = this.TaulaAmbit[this.npActual]; i > this.TaulaAmbit[this.npActual - 1]; i--) {
            //Tot el que tenim com a nivell de profunditat amb valor NUL. Significa que no el volem moure de la d'expansió 
            if (this.TaulaExpansio[i].nivell_profunditat != NUL) {
                this.TaulaDescripcio[this.TaulaExpansio[i].posicioOriginal] = this.TaulaExpansio[i];
            }
        }
        
        for (int i = 0; i < this.TaulaDescripcio.length; i++) {
            if (this.TaulaDescripcio[i].nivell_original == this.npActual) {
                //Ens quedarà basura dins la taula de Dispersio, tot i que
                //Tenim una mida considerablement grossa per tant ens ho podem 
                //permetre. Això ocorre perque tenim variables a un mateix nivell
                //tot i ser de distints metodes. Si el borram de la taula de 
                //Dispersió. En part sol·lucionam el problema. 
                this.TaulaDispersio.remove(this.TaulaDescripcio[i].id);
            }
        }
        
        this.npActual--;
    }

    public Descripcio consulta(String id) {
        Integer i = TaulaDispersio.get(id);
        //printTDescripcio();
        if (i == null) {
            return null; //Significa que no Existeix
        }
        return this.TaulaDescripcio[i].descripcio;
        
    }
    
    public void printTDescripcio(){
        System.out.println("Taula de descripció:\n");
        for (int i = 0; i < this.TaulaDescripcio.length ; i++) {
            if(!this.TaulaDescripcio[i].id.equals("")){
                System.out.println(this.TaulaDescripcio[i].toString()+"\n");
            }
        }
        System.out.println("---------------------------------------------\n");
        System.out.println("Taula de expansió:\n");
        for (int i = 0; i < this.TaulaExpansio.length; i++) {
            if(!this.TaulaExpansio[i].id.equals("")){
                System.out.println(this.TaulaExpansio[i].toString()+"\n");
            }
        }
        System.out.println("=============================================================");
    }
    
    public int consultaPrimerArg(String id)throws Exception{
        Integer i = TaulaDispersio.get(id); //Es suposa que el mètode existeix 
        int idx= this.TaulaDescripcio[i].next; //Index a la taula d'expansió 
        if (idx == NUL) { //No hi ha paràmetres
            return NUL;//Significa que no te paràmetres
        }
        return idx;
    }
    
    public boolean consultaSiTeArg(String id){
        Integer i = TaulaDispersio.get(id); //Es suposa que el mètode existeix 
        int idx= this.TaulaDescripcio[i].next; //Index a la taula d'expansió 
        if (idx == NUL) { //No hi ha paràmetres
            return false; //Significa que no té paràmetre 
        }
        return true;
    }
    
    public Descripcio consultaTExpansio(int idx){
        return this.TaulaExpansio[idx].descripcio;
    }
    
    public int consultaNexArg(int idx){
        return this.TaulaExpansio[idx].next;
    }

    // idSubp: ID del procedure o de la funció. 
    // idPara: ID del paràmetre.  
    public void posarParam(String idSubp, String idPara, Descripcio d) throws Exception{
        Integer indexSub = TaulaDispersio.get(idSubp);
        //En primer lloc miram si existeix la funció o procedure
        if (indexSub == null) {
            //Error, no existeix la identificació 
        }
        //Inserirem els parametres al final
        int previ = NUL;
        //Actual en un primer moment, és la capçalera cap al primer element de la llista enllaçada
        int actual=this.TaulaDescripcio[indexSub].next; 

        //Recorregut per si hi ha més paràmetres inserits
        while (actual != NUL && !this.TaulaExpansio[actual].id.equals(idPara)) {
            previ = actual;
            actual = this.TaulaExpansio[actual].next;
        }
        if (actual != NUL) {
            //voldra dir que el aprametre ja existeix a la funció
            //Throws: no pot haver dos parametres amb el mateix nom
            throw new ExPosarParam();

        }else {
            //reservam un slot a la TE
            //la taula d'ambit apunta al darrer lloc escrit a la taula d'expansió
            int nouIdx = ++this.TaulaAmbit[this.npActual];

            //El parametre no existeix
            if (previ == NUL) {
                //Inserció al principi de la llista
                this.TaulaDescripcio[indexSub].next = nouIdx;
            } else {
                //Inserció al final de la llista
                this.TaulaExpansio[previ].next = nouIdx;
            }

            this.TaulaExpansio[nouIdx].id = idPara;
            this.TaulaExpansio[nouIdx].descripcio = d;

            //No s'ha de canviar de taula en fer un sortirbloc
            //No volem que vagi cap a la taula de Descripció, per això li donam valor NUL
            this.TaulaExpansio[nouIdx].nivell_profunditat = NUL;
        }
    }
    
    //Volcar taula
    public void volcarTS() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("TS.txt"));
        bw.write("Taula de descripció:\n");
        for (int i = 0; i < this.TaulaDescripcio.length ; i++) {
            if(!this.TaulaDescripcio[i].id.equals("")){
                bw.write(this.TaulaDescripcio[i].toString()+"\n");
            }
        }
        bw.write("---------------------------------------------\n");
        bw.write("Taula de expansió:\n");
        for (int i = 0; i < this.TaulaExpansio.length; i++) {
            if(!this.TaulaExpansio[i].id.equals("")){
                bw.write(this.TaulaExpansio[i].toString()+"\n");
            }
        }
        bw.close();
    }
    
    //EXCEPCIONS
    public class ExAfegir extends Exception{
        public ExAfegir(){
            super("Error: Ja hi ha un identificador amb aquest nom a aquest nivell");
        }
    }
    
    public class ExSortirBloc extends Exception{
        public ExSortirBloc(){
            super("Error: No s'ha entrat a cap bloc, per tant no es pot sortir.");
        }
    }
    
    public class ExPosarParam extends Exception{
        public ExPosarParam(){
            super("Error: Ja existeix un parametre amb aquest nom");
        }
    }
    
    public class ExNoExistCridada extends Exception{
        public ExNoExistCridada(){
            super("Error: No es possible trobar la cridada");
        }
    }
    
    public class ExNoExist extends Exception{
        public ExNoExist(){
            super("Error: No es possible trobar la cridada");
        }
    }
}
