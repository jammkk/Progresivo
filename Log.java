/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiodetitulos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author JAMM
 */
public class Log {
    static String ruta = "ArchivoLog.txt";
    static File archivo;
    Date fecha ;
    
     public Log (String action) throws IOException{
         archivo= new File(ruta);        
         BufferedWriter bw = null;        
        bw = new BufferedWriter(new FileWriter(archivo));                   
       fecha = new Date(0);
    
       // DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
      
     
       bw.write(action +"");
        bw.newLine();
          
        
          bw.close();
         
     }
}
