/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiodetitulos;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 *
 * @author JAMM
 */
public class Estudio {
    public String [] cedulas;
    public String [] nombres;
    public String direccionInmuebles;
    public String numeroCredito;
    public String fechaCredito;
    public String destinoCredito;
    public double valorHipoteca;
    public double promesaDeCompraventa;
    public double avaluoComercial;
    public double precioVenta;
    public String propiedadHorizontal;
    public String EscrituraDeVenta;
    public String notariaConvenio;
    public String legalizador;
    public String tradicion;
    public String [] matriculasInmbiliarias;
    public String [] fechaDeExpedición;
    public String [] ciudadExpediccion;    
    public double [] areaMetrosCuadrados;
    public String fechaOrdenDeEscrituracion;
    public String fechaDeReciboEstudioDeTitulos;
    public String fechaEntregaEstudioDeTitulos;
    public String estado;
    public boolean pagaresAdjuntos;
    public boolean cartaInstruccionesAdjunta;
    public boolean polizasDeSeguroAdjuntas;
    public boolean pagoDeEstudioDeTitulosAdjunto;    
    public String aprovadoRechazado;
    public String  observaciones;
    public String sarlaft;
    public String LTV;
   
    String query;
    private final String accessFilePath="C:\\Users\\JAMM\\Desktop\\EstudioTitulos1\\EstudioTitulos1.accdb";
    public Estudio(String [] cedulas,String [] nombres, String direcciones, 
            String numeroCredito,String fechaCredito, String destino, double valorHipotetca
           ,double promesa,double avaluo,double precioVenta,String propiedadHorizontal,
           String EscrituraDeVenta,String notariaConvenio,String legalizador
           ,String tradicion,
           String []matriculas, String[] fechasExpedicion, String[]ciudadExpedicion, double[]areaMetros
            ,String fechaOrder,String fechaRecibo,
           String fechaEntrega, String estado, boolean pagares,boolean cartaInstrucciones,
           boolean polizas, boolean pagoEstudio,String aprovadoRechazado,String observaciones, String sarlaft,String LTV) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
       
        this.areaMetrosCuadrados=areaMetros;
        this.fechaOrdenDeEscrituracion=fechaOrder;
        this.fechaDeReciboEstudioDeTitulos=fechaRecibo;
        this.fechaEntregaEstudioDeTitulos=fechaEntrega;
        this.estado=estado;
        this.pagaresAdjuntos=pagares;
        this.cartaInstruccionesAdjunta=cartaInstrucciones;
        this.polizasDeSeguroAdjuntas=polizas;
        this.pagoDeEstudioDeTitulosAdjunto=pagoEstudio;
        this.tradicion=tradicion;
        this.aprovadoRechazado=aprovadoRechazado;
        this.cedulas=cedulas;
        this.observaciones=observaciones;
        this.nombres=nombres;
        this.direccionInmuebles=direcciones;
        this.numeroCredito=numeroCredito;
        this.sarlaft=sarlaft;
        this.LTV=LTV;
        this.fechaCredito=fechaCredito;
        this.destinoCredito=destino;
        this.valorHipoteca=valorHipotetca;
        this.promesaDeCompraventa=promesa;
        this.avaluoComercial=avaluo;
        this.precioVenta=precioVenta;
        this.propiedadHorizontal=propiedadHorizontal;
        this.EscrituraDeVenta=EscrituraDeVenta;
        this.notariaConvenio=notariaConvenio;
        this.legalizador=legalizador;
        this.matriculasInmbiliarias= matriculas;
        this.fechaDeExpedición=fechasExpedicion;
        this.ciudadExpediccion=ciudadExpedicion;
        
    }
    
    //Estos metodos se realizaron con el fin que no se mostraran los números en notación científica.
    public String getVH(){
   
      Locale.setDefault(Locale.US);
      
      DecimalFormat num = new DecimalFormat("#.###");
      String a= num.format(this.valorHipoteca);
      return a;
    
}
    public String getPC(){
   
      Locale.setDefault(Locale.US);
      
      DecimalFormat num = new DecimalFormat("#.###");
      String a= num.format(this.promesaDeCompraventa);
      return a;
    
}
    public String getAC(){
   
      Locale.setDefault(Locale.US);
      
      DecimalFormat num = new DecimalFormat("#.###");
      String a= num.format(this.avaluoComercial);
      return a;
    
}
    public String getPV(){
   
      Locale.setDefault(Locale.US);
      
      DecimalFormat num = new DecimalFormat("#.###");
      String a= num.format(this.precioVenta);
      return a;
    
}
    public void Update(String [] cedulas,String [] nombres, String direcciones,String numeroCredito,
            String fechaCredito, String destino, double valorHipotetca,double promesa,double avaluo,
            double precioVenta,String propiedadHorizontal,String EscrituraDeVenta,String notariaConvenio,String legalizador
           ,String tradicion,
           String []matriculas, String[] fechasExpedicion, String[]ciudadExpedicion, double[]areaMetros
            ,String fechaOrder,String fechaRecibo,
           String fechaEntrega, String estado, boolean pagares,boolean cartaInstrucciones,
           boolean polizas, boolean pagoEstudio,String aprovadoRechazado,String observaciones){
        
         this.areaMetrosCuadrados=areaMetros;
        this.fechaOrdenDeEscrituracion=fechaOrder;
        this.fechaDeReciboEstudioDeTitulos=fechaRecibo;
        this.fechaEntregaEstudioDeTitulos=fechaEntrega;
        this.estado=estado;
        this.pagaresAdjuntos=pagares;
        this.cartaInstruccionesAdjunta=cartaInstrucciones;
        this.polizasDeSeguroAdjuntas=polizas;
        this.pagoDeEstudioDeTitulosAdjunto=pagoEstudio;
        this.tradicion=tradicion;
        this.aprovadoRechazado=aprovadoRechazado;
        this.cedulas=cedulas;
        this.observaciones=observaciones;
        this.nombres=nombres;
        this.direccionInmuebles=direcciones;
        this.numeroCredito=numeroCredito;
        this.fechaCredito=fechaCredito;
        this.destinoCredito=destino;
        this.valorHipoteca=valorHipotetca;
        this.promesaDeCompraventa=promesa;
        this.avaluoComercial=avaluo;
        this.precioVenta=precioVenta;
        this.propiedadHorizontal=propiedadHorizontal;
        this.EscrituraDeVenta=EscrituraDeVenta;
        this.notariaConvenio=notariaConvenio;
        this.legalizador=legalizador;
        this.matriculasInmbiliarias= matriculas;
        this.fechaDeExpedición=fechasExpedicion;
        this.ciudadExpediccion=ciudadExpedicion;
        
    }
    public void mostrarEstudio(){
        System.out.println("nombres con cedulas");
        for (int i = 0; i < cedulas.length; i++) {
            System.out.println(nombres[i]+" tiene cedula "+cedulas[i]);            
        }
      System.out.println("direcciones " +this.direccionInmuebles);   
      System.out.println("Numero credito "+this.numeroCredito);
      System.out.println("FechaCredito "+this.fechaCredito);
      System.out.println("Destino "+this.destinoCredito);
      System.out.println("Valor hipoteca "+this.valorHipoteca);
      System.out.println("promesa "+this.promesaDeCompraventa);
      System.out.println("avaluo "+this.avaluoComercial);
      System.out.println("precio venta "+this.precioVenta);
      System.out.println("propiedad horizontal "+this.propiedadHorizontal);
      System.out.println("Escritura "+this.EscrituraDeVenta);
      System.out.println("notaria "+this.notariaConvenio);
      System.out.println("legalizador "+this.legalizador);
      System.out.println("tradcion "+this.tradicion);      
        System.out.println("Matriculas");
        for (int i = 0; i < matriculasInmbiliarias.length; i++) {
            System.out.println(matriculasInmbiliarias[i]+" Fue hecha en  "+fechaDeExpedición[i]+" hecha en "+ ciudadExpediccion[i]+" tiene metros cuadrados"+ this.areaMetrosCuadrados[i]);            
        }
      System.out.println("fecha orden "+this.fechaOrdenDeEscrituracion);
      System.out.println("fecha recibo "+this.fechaDeReciboEstudioDeTitulos);
      System.out.println("fecha entrega "+this.fechaEntregaEstudioDeTitulos);
      System.out.println("estado "+this.estado);
      System.out.println("pagare "+this.pagaresAdjuntos);
      System.out.println("carta "+this.cartaInstruccionesAdjunta);
      System.out.println("poliza "+this.polizasDeSeguroAdjuntas);
      System.out.println("Pago "+this.pagoDeEstudioDeTitulosAdjunto);
      System.out.println("Aprovado o Rechaazado "+this.aprovadoRechazado);      
      System.out.println("Observaciones"+this.observaciones);
      
      
      
      
    }
    
}
