/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package estudiodetitulos;


import java.sql.*;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Conectar {   
    private Connection CanalBD;
    private Statement Instruccion;
    public String[] cedulas;
    public String[] nombres;
    public String[]Matriculas;
    public String[] fechaExpedicion;
    String credito;
    public String[]Observaciones;
    public String[]ciudadExpedicion;
    public double [] areaMetros;
    
    String query;
    
    
    
    public Conectar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
try{
    CanalBD = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\JAMM\\Desktop\\EstudioTitulos1\\EstudioDeTitulos\\EstudioTitulos1.accdb","Canela2015","Canela2015");
        Instruccion = CanalBD.createStatement();
    }
    catch(SQLException ex){
        System.err.println("Error: "+ ex);
    }
  
    }
    
    public Estudio Extraer(String dato,String campo) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException{
      
        Estudio estudioExtraido = null;
         ArrayList a = null;
         
      
         
        if (campo.equals("cedula")) {
            
        System.out.println("Sabe que es cedula");
        
          String cantidadR = ("SELECT  IdUsuario  FROM Usuarios WHERE CedulaUsuario="
                  + "'"+dato+"' ");     
        
          
         ResultSet CR = Instruccion.executeQuery(cantidadR); 
         CR.next();
         
          String estes = ("SELECT  IdCredito  FROM UsuariosEnCredito WHERE IdUsuario="
                  + "'"+CR.getInt("IdUsuario")+"' ");   
        
                           
         ResultSet ES = Instruccion.executeQuery(estes); 
        
         
         System.out.println("Ejecuto la query con " +dato);
         
         //  System.out.println("Esta pasando el registro con umcredit "+CR.getString("NumeroCredito"));
        a= new ArrayList();
        
         while(ES.next()){
             
          
                 
                 
                 System.out.println(2+"--" +ES.getInt("IdCredito"));
                 
                 String registro = ("SELECT  NumeroCredito  FROM Creditos WHERE IdCredito="
                  + "'"+ES.getInt("IdCredito")+"' "); 
                 
                 System.out.println(3);
                 ResultSet ref = Instruccion.executeQuery(registro);
                   
                 ref.next();
                 System.out.println(4+"--" +ref.getString("NumeroCredito"));
                    a.add(ref.getString("NumeroCredito"));//Aqui deben estar los nombres de los usuarios y registros.
                    System.out.println("Esta pasando el registro con numcredit "+ref.getString("NumeroCredito"));
                    System.out.println(5);
                } 
          System.out.println("entro al while" );
        }
      
        ResultSet rs3 = null; credito="";
         int cantidadRegistros = 0;
        try     {
          
           
           cantidadRegistros=a.toArray().length;
           
           System.out.println("la cantidad de estudios que tiene ese usuario son "+cantidadRegistros);
           
        }catch(Exception e){
            
        }
        //cantidadRegistros=2;
        // System.out.println("dato: "+ dato+" campo: " + campo);
        if (cantidadRegistros>1) {
                    
                    
                   
       try{
                int seleccion = JOptionPane.showOptionDialog( null,"Más de un usuario o credito cumplé con los parametros de busqueda, seleccione el usuario requerido por su número de credito",
                 "Selector de opciones",JOptionPane.YES_NO_CANCEL_OPTION,
                 JOptionPane.QUESTION_MESSAGE,null,
                 a.toArray(),"opcion 1");

                if (seleccion == 0){

                     System.out.println("seleccionada opcion " + (seleccion + 1));
                }  
                campo="NumeroCredito";
                dato=(String) a.get(seleccion);
       }catch(Exception e){
           JOptionPane.showMessageDialog(null, "Se debe elegir una opción");
       }
        }
        
        if ("cedula".equals(campo)) {
            
            
        String sql = ("SELECT * FROM Usuarios WHERE CedulaUsuario='"+dato+"'");   
        ResultSet rs = Instruccion.executeQuery(sql); 
        rs.next();
       
        
        String sql2= ("SELECT * FROM UsuariosEnCredito WHERE IdUsuario='"+rs.getString("IdUsuario")+"'");  
        ResultSet rs2=Instruccion.executeQuery(sql2);
        
          
      //  String name = rs.getString(2);
        
        rs2.next();
        String sql3= ("SELECT * FROM Creditos WHERE IdCredito='"+rs2.getString("IdCredito")+"'");          
        rs3=Instruccion.executeQuery(sql3);                         
        rs3.next();               
       // System.out.println(rs3.getString("NumeroCredito"));
         credito=rs2.getString("IdCredito");
       // estudioExtraido.mostrarEstudio();
         
        }else if ("NumeroCredito".equals(campo)){
         System.out.println("hola estamos e numerocredito");  
         
        String sql3= ("SELECT * FROM Creditos WHERE NumeroCredito='"+(dato)+"'");  
        
        rs3=Instruccion.executeQuery(sql3);   
        
        rs3.next();
        
        System.out.println(rs3.getString("IdCredito"));
        
        credito=rs3.getString("IdCredito");
        }
        
        ExtraerCedulas(credito);
        ExtraerMatriculas(credito);
        ExtraerObsevaciones(credito);
        estudioExtraido= new Estudio(
                cedulas,
                nombres,
                rs3.getString("Direccion"),
                rs3.getString("NumeroCredito"),
                rs3.getString("FechaCredito"),
                rs3.getString("DestinoCredito"),
                rs3.getDouble("ValorHipoteca"),
                rs3.getDouble("PromesaCompraventa"),
                rs3.getDouble("AvaluoComercial"),
                rs3.getDouble("PrecioVenta"),                
                rs3.getString("PropiedadHorizontal"),
                rs3.getString("EscrituraVenta"),
                rs3.getString("NotariaConvenio"),                
                rs3.getString("Legalizador"),
                rs3.getString("Tradicion"),
                Matriculas,
                fechaExpedicion,
                ciudadExpedicion,
                areaMetros,
                rs3.getString("FechaOrdenEscrituracion"),
                rs3.getString("FechaReciboEstudio"),
                rs3.getString("FechaEntregaEstudio"),
                rs3.getString("Estado"),
                rs3.getBoolean("Pagares"),
                rs3.getBoolean("CartaInstrucciones"),
                rs3.getBoolean("PolizasSeguro"),
                rs3.getBoolean("PagoEstudioTitulos"),
                rs3.getString("AprovadoRechazado"),
                rs3.getString("Observaciones"),
                rs3.getString("sarlaft"),
                rs3.getString("LTV")
                );
        
        
         return estudioExtraido;
        
    }   
    //Extre tanto cedulas como nombres de las personas de cada credito.
    public void  ExtraerCedulas(String idCredito) throws SQLException{
        
        String sqlIds = ("SELECT IdUsuario FROM UsuariosEnCredito WHERE IdCredito='"+idCredito+"'");         
        String cantidad = ("SELECT Count (*) AS total FROM UsuariosEnCredito WHERE IdCredito='"+idCredito+"'");      
        
        ResultSet rsIds = Instruccion.executeQuery(sqlIds);   
        ResultSet rs3 =Instruccion.executeQuery(cantidad);
        rs3.next();
        int filasTotal=rs3.getInt("total");
         //  System.out.println(filasTotal);
                
        cedulas= new String[filasTotal];
        nombres= new String[filasTotal];
        int i=0;
   
        while(rsIds.next()){
            
        String sqlCedulas = ("SELECT * FROM Usuarios WHERE IdUsuario='"+rsIds.getString("IdUsuario")+"'");         
        
        ResultSet rsCedulas = Instruccion.executeQuery(sqlCedulas);        
        
        rsCedulas.next();
        cedulas[i]=rsCedulas.getString("CedulaUsuario");
        nombres[i]=rsCedulas.getString("NombreUsuario");
       // System.out.println("Usuario "+cedulas[i]+" con nombre "+nombres[i]);
        i++;
       
            
        }
        
       
    }    
    public boolean insertar (Estudio estudio) throws SQLException{        
        boolean ingresar;
        System.out.println("vamos");
      try{
          ingresar=false;
          String tester = ("SELECT  *  FROM Creditos WHERE NumeroCredito="
                  + "'"+estudio.numeroCredito+"' ");     
         
         ResultSet TSTST = Instruccion.executeQuery(tester); 
         TSTST.next();
         
        String pba= TSTST.getString("NumeroCredito");
         
        JOptionPane.showMessageDialog(null, "Este registro de número de crédito ya existe en la base de datos. Por favor verificar  "+pba);
         
        
      }catch (Exception e){
          ingresar=true;
      }
       System.out.println("vamos 2");
        try{    
            if(ingresar){
        
        PreparedStatement preparedStatement = null,ps=null,pstat=null,prepa=null,ulti=null;      
              String InsercionCreditos = "INSERT INTO Creditos"
                                         
                      + "(NumeroCredito,"
                      + " FechaCredito,"
                      + "DestinoCredito,"
                      + "ValorHipoteca,"
                      + "PromesaCompraventa,"
                      + "AvaluoComercial,"
                      + "PropiedadHorizontal,"
                      + "EscrituraVenta,"
                      + "NotariaConvenio,"
                      + "Legalizador,"
                      + "Tradicion,"
                      + "FechaOrdenEscrituracion,"
                      + "FechaReciboEstudio,"
                      + "FechaEntregaEstudio,"
                      + "Estado,"
                      + "Pagares,"
                      + "CartaInstrucciones,"
                      + "PolizasSeguro,"
                      + "PagoEstudioTitulos,"
                      + "PrecioVenta,"
                      + "AprovadoRechazado,"
                      + "Direccion,"
                      + "Observaciones,"
                      + "Mes,"
                      + "yiar,"
                      + "sarlaft,"
                      + "LTV) VALUES"
                                         + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                          preparedStatement = CanalBD.prepareStatement(InsercionCreditos);
                          
                          
                          preparedStatement.setString(1,estudio.numeroCredito );
                          preparedStatement.setString(2,estudio.fechaCredito);
                          preparedStatement.setString(3,estudio.destinoCredito );
                          preparedStatement.setDouble(4,estudio.valorHipoteca);
                          preparedStatement.setDouble(5,estudio.promesaDeCompraventa );
                          preparedStatement.setDouble(6,estudio.avaluoComercial);
                          preparedStatement.setString(7,estudio.propiedadHorizontal );
                          preparedStatement.setString(8,estudio.EscrituraDeVenta);
                          preparedStatement.setString(9,estudio.notariaConvenio );
                          preparedStatement.setString(10,estudio.legalizador);
                          preparedStatement.setString(11,estudio.tradicion );
                          preparedStatement.setString(12,estudio.fechaOrdenDeEscrituracion);
                          preparedStatement.setString(13,estudio.fechaDeReciboEstudioDeTitulos );
                          preparedStatement.setString(14,estudio.fechaEntregaEstudioDeTitulos);
                          preparedStatement.setString(15,estudio.estado );
                          preparedStatement.setBoolean(16,estudio.pagaresAdjuntos);
                          preparedStatement.setBoolean(17,estudio.cartaInstruccionesAdjunta );
                          preparedStatement.setBoolean(18,estudio.polizasDeSeguroAdjuntas);
                          preparedStatement.setBoolean(19,estudio.pagoDeEstudioDeTitulosAdjunto );
                          preparedStatement.setDouble(20,estudio.precioVenta);
                          preparedStatement.setString(21,estudio.aprovadoRechazado );
                          preparedStatement.setString(22,estudio.direccionInmuebles);
                          preparedStatement.setString(23,estudio.observaciones);     
                          preparedStatement.setInt(24,mesActual());
                          preparedStatement.setInt(25,anoActual());  
                          preparedStatement.setString(26,estudio.sarlaft);
                          preparedStatement.setString(27,estudio.LTV);
                          preparedStatement.executeUpdate();
                          
                                  System.out.println("se realizo la insercion a la base de datos");
                          
                                   System.out.println("vamos 3");
          String idnuevo = ("SELECT * FROM Creditos WHERE NumeroCredito='"+estudio.numeroCredito+"'");       
         
         System.out.println(estudio.numeroCredito);
         
         ResultSet rsIn = Instruccion.executeQuery(idnuevo); 
         
         rsIn.next();
         
         String id=rsIn.getString("IdCredito");
         System.out.println("etapa 1 con el id de credito"+rsIn.getString("IdCredito"));
         
         for (int i = 0; i < estudio.cedulas.length; i++) {
             
               String insertarCedula = "INSERT INTO Usuarios"
                            + "(CedulaUsuario, NombreUsuario) VALUES"
                            + "(?,?)";
               
             ps= CanalBD.prepareStatement(insertarCedula);
             
                          ps.setString(1,estudio.cedulas[i]);
                          ps.setString(2,estudio.nombres[i]);   
                          ps.executeUpdate();                                                    
                 String idnuevoU = ("SELECT  *  FROM Usuarios WHERE CedulaUsuario="
                 + "'"+estudio.cedulas[i]+""
                 + "' AND NombreUsuario ='"+estudio.nombres[i]+"' ");     
         
         ResultSet RSUSN = Instruccion.executeQuery(idnuevoU); 
         RSUSN.next();                          
                          String insertarRelacionCC = "INSERT INTO UsuariosEnCredito"
                            + "(IdUsuario, IdCredito) VALUES"
                            + "(?,?)";
                          prepa=CanalBD.prepareStatement(insertarRelacionCC);
                          System.out.println("El usuario "+RSUSN.getString("IdUsuario")+" con el credito con id"+ id);
                          
                          prepa.setString(1,RSUSN.getString("IdUsuario"));
                          
                          prepa.setString(2,id);   
                          prepa.executeUpdate();
                          System.out.println("hola vamos bien");
                          
        } System.out.println("incluso salimos bien");
         
         for (int i = 0; i < estudio.matriculasInmbiliarias.length; i++) {
             
              String insertaMatricula= "INSERT INTO MatriculaInmobiliaria"
                            + "(MatriculaInmobiliaria, FechaExpedicion,CiudadExpedicion,AreaMetrosCuadrados) VALUES"
                            + "(?,?,?,?)";
               
            pstat=  CanalBD.prepareStatement(insertaMatricula);
             
                          pstat.setString(1,estudio.matriculasInmbiliarias[i]);
                          pstat.setString(2,estudio.fechaDeExpedición[i]); 
                          pstat.setString(3,estudio.ciudadExpediccion[i]);
                          pstat.setDouble(4,estudio.areaMetrosCuadrados[i]);  
                          pstat.executeUpdate();
                          
                 String idnuevoM = ("SELECT  *  FROM MatriculaInmobiliaria WHERE MatriculaInmobiliaria="
                 + "'"+estudio.matriculasInmbiliarias[i]+""
                 + "' AND FechaExpedicion ='"+estudio.fechaDeExpedición[i]+"' ");     
         
         ResultSet RSUSN = Instruccion.executeQuery(idnuevoM); 
         RSUSN.next();
                          
                          String insertarRelacionCC = "INSERT INTO MatriculaEnCredito"
                            + "(IdMatricula, IdCredito) VALUES"
                            + "(?,?)";
                          ulti=  CanalBD.prepareStatement(insertarRelacionCC);
                          System.out.println("El usuario "+RSUSN.getString("IdMatricula")+" con el credito con id"+ id);
                          
                          ulti.setString(1,RSUSN.getString("IdMatricula"));
                          ulti.setString(2,id);   
                          ulti.executeUpdate();
                          
        }
                          JOptionPane.showMessageDialog(null, "se realizo la inserción del Credito");
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e.toString());

    }return ingresar;
    }
    private void ExtraerMatriculas(String idCredito) throws SQLException {
         String sqlIds = ("SELECT * FROM MatriculaEnCredito WHERE IdCredito='"+idCredito+"'");      
         String cantidad = ("SELECT Count (*) AS total FROM MatriculaEnCredito WHERE IdCredito='"+idCredito+"'");
         ResultSet rsIds = Instruccion.executeQuery(sqlIds);   
         ResultSet rs3 =Instruccion.executeQuery(cantidad);
         rs3.next();
         int filasTotal=rs3.getInt("total");
         //System.out.println("filas total "+filasTotal);
         Matriculas=new String[filasTotal];
         fechaExpedicion= new String[filasTotal];
         ciudadExpedicion=new String[filasTotal];
         areaMetros=new double[filasTotal];
         int i =0;
         
         while(rsIds.next()){
         
         
         String sqlMatriculas = ("SELECT * FROM MatriculaInmobiliaria WHERE IdMatricula='"+rsIds.getString("IdMatricula")+"'");
         
         
          ResultSet rsMatriculas = Instruccion.executeQuery(sqlMatriculas);  
          rsMatriculas.next();
        //System.out.println(rsMatriculas.getString("IdMatricula"));
          
          Matriculas[i]=rsMatriculas.getString("MatriculaInmobiliaria");
          fechaExpedicion[i]=rsMatriculas.getString("FechaExpedicion");
          ciudadExpedicion[i]=rsMatriculas.getString("CiudadExpedicion");
          areaMetros[i]=(double)rsMatriculas.getDouble("AreaMetrosCuadrados");
      // System.out.println("Matricula "+Matriculas[i]+" expedida el "+fechaExpedicion[i]+" En "+ciudadExpedicion[i]+" con " +areaMetros[i]+" Metros cuadrados ");
                  i++;
         }
         
    }
    private void ExtraerObsevaciones(String idCredito) throws SQLException {
        
         String sqlIds = ("SELECT * FROM ObservacionEnCredito WHERE IdCredito='"+idCredito+"'"); 
         
         String cantidad = ("SELECT Count (*) AS total FROM ObservacionEnCredito WHERE IdCredito='"+idCredito+"'");
         
         ResultSet rsIds = Instruccion.executeQuery(sqlIds);   
         ResultSet rs3 =Instruccion.executeQuery(cantidad);
         
         rs3.next();
         
         int filasTotal=rs3.getInt("total");
         
         System.out.println("filas total "+filasTotal);
         
         Observaciones=new String[filasTotal];
         
         int i =0;
         while(rsIds.next()){
            
        String sqlObs = ("SELECT * FROM Observaciones WHERE IdObservacion='"+rsIds.getString("IdObservacion")+"'");         
        
        ResultSet rsObs = Instruccion.executeQuery(sqlObs);        
        
        rsObs.next();
        Observaciones[i]=rsObs.getString("Observacion");
        
       // System.out.println("observación "+Observaciones[i]);
        i++;
       
            
        }
         
         
    }
    void Actualizar(Estudio estudioActualizar) throws SQLException {
      try{
        int     cantidadceduas,
                cantidadmatriculas;
                
        
         System.out.println("estamos esperando la actualización");
         PreparedStatement preparedStatement = null;      
         PreparedStatement otrops=null;
        
         String cantidad = ("SELECT Count (*) AS total FROM UsuariosEnCredito WHERE IdCredito='"+credito+"'");           
         ResultSet ced = Instruccion.executeQuery(cantidad); 
         ced.next();
         String usuarioId ;
         int EAA=estudioActualizar.cedulas.length;
         cantidadceduas=ced.getInt("total");         
         
         String cedulasEnBase = ("SELECT * FROM UsuariosEnCredito WHERE IdCredito='"+credito+"'"); 
         ResultSet cedu = Instruccion.executeQuery(cedulasEnBase); 
         cedu.next() ;
                 for (int i = 0; i < (EAA>cantidadceduas?cantidadceduas:EAA); i++) {
          
                   String sSQL = "UPDATE Usuarios SET CedulaUsuario = ?,"+"NombreUsuario = ?"+""
                         + "WHERE IdUsuario = ?";
                    
                    preparedStatement = CanalBD.prepareStatement(sSQL);

                    usuarioId=cedu.getString("IdUsuario");
                    
                  
                    preparedStatement.setString(1, estudioActualizar.cedulas[i] );
                    preparedStatement.setString(2, estudioActualizar.nombres[i]);
		    preparedStatement.setString(3, usuarioId);
                    
                    
                    System.out.println(cantidadceduas+" es la cantidad de cedulas "+EAA);
                   
                    
                   preparedStatement.executeUpdate();
                    cedu.next() ;
                    
                    }                   
                       if(EAA<cantidadceduas){
                 
                            int l=cantidadceduas-EAA;
                            System.out.println(l);
                            
                           for (int i = 0; i < l; i++) {
                               
                               Statement stmt = CanalBD.createStatement();  
                               
                               System.out.println(cedu.getString("IdUsuario"));
                               stmt.execute("DELETE FROM Usuarios WHERE IdUsuario = '"+cedu.getString("IdUsuario")+"'");
                               
                               stmt.execute("DELETE FROM UsuariosEnCredito WHERE IdUsuario = '"+cedu.getString("IdUsuario")+"'");
                               //esta información debe quedar para el log
                               System.out.println("Se eliminó el registro de la base de datos");
                               cedu.next();
                               
                           }
                           
                       
                       
                       }if(cantidadceduas<EAA){                           
                          System.out.println("hola desde agregar");
                          int agregados=EAA-cantidadceduas;
                          for (int i = 0; i < agregados; i++) {
             String insertTableSQL = "INSERT INTO Usuarios"
                            + "(CedulaUsuario, NombreUsuario) VALUES"
                            + "(?,?)";
                          
                          preparedStatement = CanalBD.prepareStatement(insertTableSQL);
                          
                          preparedStatement.setString(1, estudioActualizar.cedulas[cantidadceduas+i]);
                          preparedStatement.setString(2,estudioActualizar.nombres[cantidadceduas+i]);                          
                          preparedStatement.executeUpdate();
                          
                          
                            String insertTableRelacionSQL = "INSERT INTO UsuariosEnCredito"
                            + "(IdUsuario, IdCredito) VALUES"
                            + "(?,?)";
                            
         String idnuevo = ("SELECT  *  FROM Usuarios WHERE CedulaUsuario='"+estudioActualizar.cedulas[cantidadceduas+i]+"' AND NombreUsuario ='"+estudioActualizar.nombres[cantidadceduas+i]+"' ");     
         
         ResultSet rsIn = Instruccion.executeQuery(idnuevo); 
         rsIn.next();
                            
                          preparedStatement = CanalBD.prepareStatement(insertTableRelacionSQL);
                          //System.out.println("usuario "+usuarioId+"  credito "+ rsIn.getString("IdUsuario"));
                          
                          preparedStatement.setString(1,rsIn.getString("IdUsuario") );
                          preparedStatement.setString(2,credito);
                          preparedStatement.executeUpdate();
                         
        }                                    
                       }
                       //PAra el log, se agregaron usuarios   
          String VariasCosasAActulizar =                                     
                  "UPDATE Creditos SET "          +"NumeroCredito = ?,"
                  +"FechaCredito = ?,"             +"DestinoCredito = ?,"
                  +"ValorHipoteca = ?,"           +"PromesaCompraventa = ?,"
                  +"AvaluoComercial = ?,"         +"PropiedadHorizontal = ?,"
                  +"EscrituraVenta = ?,"          +"NotariaConvenio = ?,"                  
                  +"Legalizador = ?,"             +"Tradicion = ?,"
                  +"FechaOrdenEscrituracion = ?," +"FechaReciboEstudio = ?,"                  
                  +"FechaEntregaEstudio = ?,"     +"Estado = ?,"
                  +"Pagares = ?,"                 +"CartaInstrucciones = ?,"
                  +"PolizasSeguro = ?,"           +"PagoEstudioTitulos = ?,"
                  +"PrecioVenta = ?,"             +"AprovadoRechazado = ?,"
                  +"Direccion = ?,"               +"Observaciones =?, " 
                  +"sarlaft=?,"                   +"LTV=?"
                  + "WHERE IdCredito = ?";                  
          
                    otrops = CanalBD.prepareStatement(VariasCosasAActulizar);                    
                    otrops.setString(1, estudioActualizar.numeroCredito );
                    otrops.setString(2, estudioActualizar.fechaCredito);
                    otrops.setString(3, estudioActualizar.destinoCredito );
                    otrops.setDouble(4, estudioActualizar.valorHipoteca);
                    otrops.setDouble(5, estudioActualizar.promesaDeCompraventa );
                    otrops.setDouble(6, estudioActualizar.avaluoComercial);
                    otrops.setString(7, estudioActualizar.propiedadHorizontal );
                    otrops.setString(8, estudioActualizar.EscrituraDeVenta);
                    otrops.setString(9, estudioActualizar.notariaConvenio );
                    otrops.setString(10, estudioActualizar.legalizador);
                    otrops.setString(11, estudioActualizar.tradicion );
                    otrops.setString(12, estudioActualizar.fechaOrdenDeEscrituracion);
                    otrops.setString(13, estudioActualizar.fechaDeReciboEstudioDeTitulos );
                    otrops.setString(14, estudioActualizar.fechaEntregaEstudioDeTitulos);
                    otrops.setString(15, estudioActualizar.estado );
                    otrops.setBoolean(16, estudioActualizar.pagaresAdjuntos);
                    otrops.setBoolean(17, estudioActualizar.cartaInstruccionesAdjunta );
                    otrops.setBoolean(18, estudioActualizar.polizasDeSeguroAdjuntas);
                    otrops.setBoolean(19, estudioActualizar.pagoDeEstudioDeTitulosAdjunto );
                    otrops.setDouble(20, estudioActualizar.precioVenta);
                    otrops.setString(21, estudioActualizar.aprovadoRechazado );
                    otrops.setString(22, estudioActualizar.direccionInmuebles);                                        
                    otrops.setString(23, estudioActualizar.observaciones);
                    otrops.setString(24, estudioActualizar.sarlaft);
                    otrops.setString(25, estudioActualizar.LTV);
                    
		    otrops.setString(26, credito);
                    
                    otrops.executeUpdate();
                    
                    
                    
           String cantidadMatriculas = ("SELECT Count (*) AS total1 FROM MatriculaEnCredito WHERE IdCredito='"+credito+"'");  
           ResultSet matri = Instruccion.executeQuery(cantidadMatriculas); 
           matri.next();
           cantidadmatriculas=matri.getInt("total1");  
                    
                       System.out.println(" la cantidad de matriculas es"+ cantidadmatriculas);
                       
         String cMatriculasEnBase = ("SELECT * FROM MatriculaEnCredito WHERE IdCredito='"+credito+"'"); 
         ResultSet matriculasbase = Instruccion.executeQuery(cMatriculasEnBase); 
         
         matriculasbase.next() ;
         int tamaMatri=         estudioActualizar.matriculasInmbiliarias.length;

                  for (int i = 0; i < (tamaMatri>cantidadmatriculas?cantidadmatriculas:tamaMatri); i++) {
          
                   String sSQL = "UPDATE MatriculaInmobiliaria SET "
                           +"MatriculaInmobiliaria = ?,"
                           +"FechaExpedicion = ?,"
                           +"CiudadExpedicion=?,"
                           +"AreaMetrosCuadrados=?"
                           +"WHERE IdMatricula = ?";                    
                    preparedStatement = CanalBD.prepareStatement(sSQL);

                    usuarioId=matriculasbase.getString("IdMatricula");
                    
                  
                    preparedStatement.setString(1, estudioActualizar.matriculasInmbiliarias[i] );
                    preparedStatement.setString(2, estudioActualizar.fechaDeExpedición[i]);
                    preparedStatement.setString(3, estudioActualizar.ciudadExpediccion[i]);
                    preparedStatement.setDouble(4, estudioActualizar.areaMetrosCuadrados[i]);
                    
		    preparedStatement.setString(5, usuarioId);
                    
                    
                    System.out.println(tamaMatri+" es la cantidad de cedulas "+cantidadmatriculas);
                   
                    
                   preparedStatement.executeUpdate();
                    matriculasbase.next() ;                    
                    }   
                    if(tamaMatri<cantidadmatriculas){
                 
                            int l=cantidadmatriculas-tamaMatri;
                            System.out.println(l);
                            
                           for (int i = 0; i < l; i++) {
                               
                               Statement stmt = CanalBD.createStatement();  
                               
                               System.out.println(matriculasbase.getString("IdMatricula"));
                               
                               stmt.execute("DELETE FROM MatriculaInmobiliaria WHERE IdMatricula = '"+matriculasbase.getString("IdMatricula")+"'");
                               
                               stmt.execute("DELETE FROM MatriculaEnCredito WHERE IdMatricula = '"+matriculasbase.getString("IdMatricula")+"'");
                               //esta información debe quedar para el log
                               System.out.println("Se eliminó el registro de la base de datos");
                               matriculasbase.next();
                               
                           }
                           
                       
                       
                       }if(cantidadmatriculas<tamaMatri){                           
                          System.out.println("hola desde agregar");
                          
                          int agregados=tamaMatri-cantidadmatriculas;
                          for (int i = 0; i < agregados; i++) {
                            String insertTableSQL = "INSERT INTO MatriculaInmobiliaria"
                                                + "(MatriculaInmobiliaria, FechaExpedicion,CiudadExpedicion,AreaMetrosCuadrados) VALUES"
                                                + "(?,?,?,?)";
                          
                          preparedStatement = CanalBD.prepareStatement(insertTableSQL);
                          
                          preparedStatement.setString(1, estudioActualizar.matriculasInmbiliarias[cantidadmatriculas+i]);
                          preparedStatement.setString(2,estudioActualizar.fechaDeExpedición[cantidadmatriculas+i]);                          
                          preparedStatement.setString(3, estudioActualizar.ciudadExpediccion[cantidadmatriculas+i]);
                          preparedStatement.setDouble(4,estudioActualizar.areaMetrosCuadrados[cantidadmatriculas+i]);
                          preparedStatement.executeUpdate();
                          
                          
                            String insertTableRelacionSQL = "INSERT INTO MatriculaEnCredito"
                            + "(IdMatricula, IdCredito) VALUES"
                            + "(?,?)";
                             
         String idnuevo = ("SELECT  *  FROM MatriculaInmobiliaria WHERE MatriculaInmobiliaria='"+estudioActualizar.matriculasInmbiliarias[cantidadmatriculas+i]+"' AND FechaExpedicion ='"+estudioActualizar.fechaDeExpedición[cantidadmatriculas+i]+"' ");     
         
         ResultSet rsIn = Instruccion.executeQuery(idnuevo); 
         rsIn.next();
                            
                          preparedStatement = CanalBD.prepareStatement(insertTableRelacionSQL);
                          //System.out.println("usuario "+usuarioId+"  credito "+ rsIn.getString("IdUsuario"));
                          
                          preparedStatement.setString(1,rsIn.getString("IdMatricula") );
                          preparedStatement.setString(2,credito);
                          preparedStatement.executeUpdate();
                          
        }                                    
                       }
                       
                       
                       JOptionPane.showMessageDialog(null, "Se ha realizado la actualización de datos del credito con número: "+credito);
                             
        }catch(Exception e){
            
               if (e.toString().equals("java.lang.NullPointerException")) {
                      JOptionPane.showMessageDialog(null, "Recuerda Ingresar un valor  en todas los campos numéricos");
                 
              }
               
           
        }   
                       
    }   
    public void CDCMES(int ano,int mes) throws SQLException{
        
        String cantidadR = ("SELECT  *  FROM Creditos WHERE Mes="
                  + "'"+mes+"'AND yiar='"+ano+"' ");                                     
         ResultSet CR = Instruccion.executeQuery(cantidadR); 
       int i=0;
        while(CR.next()){
            i++;
                
            
            System.out.println(CR.getString("NumeroCredito"));
            
        }
         
        if (i==0) {
            JOptionPane.showMessageDialog(null, "El mes "+(mes+1)+" del año "+ano+" no tiene registros  de estudios de titulos desarrollados en la base de datos");
            
        }
}
    private int mesActual() {
       
    
     Calendar fecha= new GregorianCalendar();
     
       System.out.println(fecha.get(Calendar.MONTH));
       return fecha.get(Calendar.MONTH);
    }
    private int anoActual() {
       Calendar fecha= new GregorianCalendar();
     
       System.out.println(fecha.get(Calendar.YEAR));
       
       return fecha.get(Calendar.YEAR);
    }
}
