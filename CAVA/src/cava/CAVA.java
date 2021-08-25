
package cava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.CharArrayReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *
 * @author Jim
 */
public class CAVA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Reader input;
        
        try {
            if (args.length > 0) {
                input = new FileReader(args[0]);
            } else {
                System.out.println("Escriu el teu programa:");
                System.out.print(">>> ");
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                input = new CharArrayReader(in.readLine().toCharArray());
            }
            Scanner scanner = new Scanner(input);
            Parser parser = new Parser(scanner);
            parser.parse();
            parser.bw.close();
            parser.archiuArbre.close();
            parser.TS.volcarTS();
            System.out.println("COMPILACIÓ CORRECTA!");
            parser.gen.printTVarProc("TaulaVarProc.txt");
            parser.gen.toTxt("CODI3a.txt");
            parser.gen.toAssembly("CODI.s");
            System.out.println("\n------------------OPTIMITZAM EL CODI-------------------\n");
            parser.gen.optimitzacio();
            parser.gen.toTxt("CODI3aOPT.txt");
            parser.gen.toAssembly("CODIOPT.s");
        } catch(Exception e) {
            System.out.println("COMPILACIÓ INCORRECTA!");
            BufferedWriter bw;
            System.out.println(e.getMessage());
            //e.printStackTrace();
            try {
                bw = new BufferedWriter(new FileWriter("Err.txt"));
                bw.write("Error: \n");
                bw.write(e.getMessage());
                bw.close();
            } catch (IOException ex) {
                
            }
        } 
         
    }
}
