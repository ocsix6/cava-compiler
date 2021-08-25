package cava;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Jim
 */
public class Graphwiz {
    private FileWriter writer ;
    private BufferedWriter bw;
    
    public static String INTRO = "\r\n";
    public static String COMILLA = "\"";
    
    public Graphwiz() throws IOException{
        this.writer = new FileWriter("graf.txt"); //Gràcies al true, evitam sobreescriure
        bw = new BufferedWriter(writer);
        bw.write("digraph G {" + INTRO);
    }
    
    public void escriure(String s) {
        try {
            bw.write(s);
        } catch (IOException ex) {   
        }
    }
    
    public void close() throws IOException{
        bw.write("}");
        bw.close();
    }
}
