/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiodetitulos;

import java.io.IOException;

/**
 *
 * @author JAMM
 */
public class EstudioDeTitulos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Log log;
        log = new Log("se inicio la aplicación");
//        GenerarExcel a= new GenerarExcel(null);
//        
//        a.excribirExcel( );
        VentanaPrincipal main;
        main= new VentanaPrincipal();
        main.show();
        
    }
}
