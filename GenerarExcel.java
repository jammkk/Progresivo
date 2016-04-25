package estudiodetitulos;

/**
 *
 * @author JAMM
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import jxl.*;
import jxl.read.biff.BiffException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
        
public class GenerarExcel {  
    Estudio estudio;
    String ruta;
    public GenerarExcel(Estudio persona) throws FileNotFoundException, IOException{
        this.estudio=persona;
        ruta=sacaRutaDeArchivo();
    }
    public void excribirExcel( ) throws FileNotFoundException, IOException
    {
      String file = "";
       try{
                FileInputStream test;
                test = new FileInputStream(new File("InformeEstudioDeTitulos.xlsx"));
                
               
		XSSFWorkbook MYworkbook = new XSSFWorkbook (test);
                System.out.println("todaviaa sirve");
                
		XSSFSheet sheet = MYworkbook.getSheetAt(0);       
                System.out.println("todaviaa sirve 2");                
                XSSFCell fecha,direccion,sarlaft,LTV;
                fecha=sheet.getRow(3).getCell(0);
                fecha.setCellValue(estudio.numeroCredito);
               
                fecha=sheet.getRow(5).getCell(0);
                fecha.setCellValue(estudio.fechaCredito);
                
                sarlaft=sheet.getRow(7).getCell(3);
                sarlaft.setCellValue(" SARLAFT: "+ estudio.sarlaft);     
                
                LTV=sheet.getRow(7).getCell(5);
                LTV.setCellValue(" LTV: "+ estudio.LTV +"%"); 
                
                direccion=sheet.getRow(7).getCell(1);
                direccion.setCellValue( estudio.direccionInmuebles); 
                
                for (int i = 0; i < estudio.nombres.length; i++) {
                     direccion=sheet.getRow(3+i).getCell(1);
                     direccion.setCellValue( estudio.nombres[i]); 
               
                }
                   for (int i = 0; i < estudio.cedulas.length; i++) {
                     direccion=sheet.getRow(3+i).getCell(3);
                     direccion.setCellValue( estudio.cedulas[i]); 
               
                }
                   for (int i = 0; i < estudio.areaMetrosCuadrados.length; i++) {
               direccion=sheet.getRow(3+i).getCell(7);
                     direccion.setCellValue( estudio.areaMetrosCuadrados[i]);
           }
                      for (int i = 0; i < estudio.fechaDeExpedición.length; i++) {
               direccion=sheet.getRow(3+i).getCell(5);
                     direccion.setCellValue( estudio.fechaDeExpedición[i]);
           }
                         for (int i = 0; i < estudio.ciudadExpediccion.length; i++) {
               direccion=sheet.getRow(3+i).getCell(6);
                     direccion.setCellValue( estudio.ciudadExpediccion[i]);
           }
                            for (int i = 0; i < estudio.matriculasInmbiliarias.length; i++) {
               direccion=sheet.getRow(3+i).getCell(4);
                     direccion.setCellValue( estudio.matriculasInmbiliarias[i]);
           }
                            
                     direccion=sheet.getRow(13).getCell(4);
                     direccion.setCellValue( estudio.avaluoComercial);
                     
                     direccion=sheet.getRow(13).getCell(5);
                     direccion.setCellValue( estudio.promesaDeCompraventa);
                     
                     direccion=sheet.getRow(13).getCell(6);
                     direccion.setCellValue( estudio.precioVenta);
                            
                     direccion=sheet.getRow(0).getCell(5);
                     direccion.setCellValue( estudio.legalizador);
                     
                     direccion=sheet.getRow(1).getCell(5);
                     direccion.setCellValue( estudio.notariaConvenio);
                  
                     direccion=sheet.getRow(15).getCell(4);
                     direccion.setCellValue( estudio.fechaDeReciboEstudioDeTitulos);
                  
                     direccion=sheet.getRow(15).getCell(5);
                     direccion.setCellValue( estudio.fechaOrdenDeEscrituracion);
                  
                     direccion=sheet.getRow(11).getCell(1);
                     direccion.setCellValue( estudio.propiedadHorizontal);
                     
                     direccion=sheet.getRow(15).getCell(0);
                     direccion.setCellValue( estudio.pagaresAdjuntos?"SI":"NO");
                     
                     direccion=sheet.getRow(15).getCell(1);
                     direccion.setCellValue( estudio.polizasDeSeguroAdjuntas?"SI":"NO");
                     
                     direccion=sheet.getRow(15).getCell(2);
                     direccion.setCellValue( estudio.pagoDeEstudioDeTitulosAdjunto?"SI":"NO");
                     
                     direccion=sheet.getRow(15).getCell(3);
                     direccion.setCellValue( estudio.cartaInstruccionesAdjunta?"SI":"NO");
                     
                     direccion=sheet.getRow(13).getCell(0);
                     direccion.setCellValue( estudio.destinoCredito);
                     
                      direccion=sheet.getRow(8).getCell(   1);
                     direccion.setCellValue( estudio.tradicion);
                     
                     direccion=sheet.getRow(13).getCell(1);
                     direccion.setCellValue( estudio.EscrituraDeVenta);
          String obse=estudio.observaciones;
          int i=0;
          String c1="",c2="",c3="";
          System.out.println("obse 0 es:"+obse.charAt(1)+":");
                     while((obse.length()>i)) {
                         
                         c1+=obse.charAt(i);
                          i++;
                         if ((obse.charAt(i)=='\n')) {
                             
                             while((obse.length()>i)) {
                                 c2+=obse.charAt(i);
                                  i++;
                                  if ((obse.charAt(i)=='\n')) {
                                      
                                       while((obse.length()>i)) {
                                           if (obse.charAt(i)=='\n') {
                                               i++;
                                           c3+=" "   ;
                                           }
                                           c3+=obse.charAt(i);
                                          i++;
                                         }
                                  }
                                 
                             }
                             
                         }
                        
                     }
                     
                     
                     direccion.setCellValue( estudio.tradicion);
                     
                    
                     direccion=sheet.getRow(18).getCell(1);
                     direccion.setCellValue( c1);
                      direccion=sheet.getRow(19).getCell(1);
                     direccion.setCellValue( c2);
                      direccion=sheet.getRow(20).getCell(1);
                     direccion.setCellValue( c3);
                     
                     direccion=sheet.getRow(13).getCell(3);
                     direccion.setCellValue( estudio.valorHipoteca);
                System.out.println("todaviaa sirve 3");
                test.close();
                 file=ruta+""+estudio.numeroCredito+".xlsx";
                FileOutputStream output_file= new FileOutputStream (new File (file));
                
                    MYworkbook.write(output_file);
                
                output_file.close();
       }catch(Exception e){
          
       }
              if (ruta==null) {
                  JOptionPane.showMessageDialog(null, "Es posible que la ruta este en blanco, es probable que el programase ejecute sin contratiempos, sin embargo "
                          + "quedara almacenada en la carpeta del proyecto ");
               
           }
         try{ 
 
   Runtime.getRuntime().exec("cmd /c start "+file);
     
   }catch(IOException e){
      e.printStackTrace();
   } 
       

    }
    
    private String nombresEstudio(){
       String nombres="";
        for (int i = 0; i < estudio.nombres.length; i++) {
            nombres+=estudio.nombres[i];
            
        }
        return nombres;
    }

    private String sacaRutaDeArchivo() throws FileNotFoundException, IOException {
        File lectura;
        lectura= new File("salida.txt");
        BufferedReader entrada = new BufferedReader(new FileReader(lectura)); 
        String renglon; 
        renglon = entrada.readLine() ;      
        return renglon;
    }





   

}
