package Aplicacion;
import Persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class aplicacionCliente {

   static conexionOracle co = null; 
    
    public static void main(String[] args) {    
    
        try {
            co = new conexionOracle();
            System.out.println("Conexion realizada con éxito\n");
            System.out.println("Menú de Opciones");
            System.out.println("----------------");
            System.out.println("1. Expertos por nacionalidad");
            System.out.println("2. Insertar un experto");
            System.out.println("3. Insertar una colaboración");
            System.out.println();
            System.out.print("¿Qué quieres hacer?  ");
            Scanner sc = new Scanner(System.in);
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":   ejercicio1();
                            break;
                case "2":   ejercicio2();
                            break;
                case "3":   ejercicio3();
                            break;
            }
            
            co.desconexion();
        }      
        catch (SQLException e) {
            System.out.println("Error al conectar con la BD: " + e.getMessage());
        }    
        catch (Exception e) {
            System.out.println("Error en el programa principal: " + e.getMessage());
        }
    }
    
    /*
    Mostrar por pantalla todos los expertos cuya nacionalidad sea igual a una solicitada por teclado
    */
    public static void ejercicio1() 
    {
       manejaExperto me = new manejaExperto(co);
       
        System.out.print("Introduce la nacionalidad: ");
        
        // El 'ISO-8859-1' se añade porque sino el Scanner no me lee bien la letra 'ñ'.
        
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        String nacionalidad = sc.nextLine();
        
       try 
       {
           ArrayList<experto> expertos = me.listaExpertosPorPais(nacionalidad);
           
           expertos.forEach((e) -> { System.out.println(e); });
       } 
       
       catch (SQLException e) 
       {
           System.out.println("Error: " + e.getMessage());
       }
    }
    /*
    Insertar datos en la tabla EXPERTO    
    */
    public static void ejercicio2() throws SQLException 
    {
        if(null != aplicacionCliente.expertoInsertado())
            System.out.println("Experto insertado correctamente.");
        
        else
            System.out.println("No se ha insertado el experto, algún dato no es correcto.");
    }
    /*
    Insertar datos en la tabla COLABORA
    */
    public static void ejercicio3() throws SQLException
    { 
        try 
        {
            co.inicioTransaccion();
            
            String codExperto = aplicacionCliente.obtener_codExperto();
            String codCaso = aplicacionCliente.obtener_codCaso();
            
            aplicacionCliente.colaboraciónInsertada(codExperto, codCaso);

            co.finTransaccionCommit();
        } 
        
        catch (SQLException e) 
        {
            System.out.println("Error: " + e.getMessage());
            
            co.finTransaccionRollback();
        } 
        
        catch (Exception e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /***
     * Devuelve el código de un experto, el cual se insertará en la BD sino existe.
     * @return El código del experto introducido.
     * @throws SQLException
     * @throws Exception 
     */
    private static String obtener_codExperto() throws SQLException, Exception
    {
        Scanner teclado = new Scanner(System.in, "ISO-8859-1");
        
        System.out.print("Introduce el código del experto: ");
        String codExperto = teclado.nextLine();

        if(!new manejaExperto(co).existeExperto(codExperto))   // El experto no existe.
            codExperto = aplicacionCliente.expertoInsertado();
            
        if(null == codExperto)
            throw (new Exception("No se ha insertado el experto, algún dato no es correcto."));
        
        return codExperto;
    }
    
    /***
     * Devuelve el código de un caso, el cual se insertará en la BD sino existe.
     * @return El código del caso introducido.
     * @throws SQLException
     * @throws Exception 
     */
    private static String obtener_codCaso() throws SQLException, Exception
    {
        Scanner teclado = new Scanner(System.in, "ISO-8859-1");
        
        System.out.print("Introduce el código del caso: ");
        String codCaso = teclado.nextLine();

        if(!new manejaCaso(co).existeCaso(codCaso))   // El caso no existe.
            codCaso = aplicacionCliente.casoInsertado();

        if(null == codCaso)
            throw (new Exception("No se ha insertado el caso, algún dato no es correcto."));
        
        return codCaso;
    }
    
    /***
     * Pide los datos de un experto e intenta insertarlo en la BD.
     * @return El código del experto insertado si todo ha ido bien, null en caso contrario.
     * @throws SQLException Si ha ido algo mal, se puede lanzar esta excepción.
     */
    private static String expertoInsertado()
    {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        experto exp = new experto();
        
        System.out.print("Código de experto: ");
        
        String codExperto = sc.nextLine();
        
        exp.setCodExperto(codExperto);
        
        System.out.print("Nombre: ");
        exp.setNombre(sc.nextLine());
        
        System.out.print("País: ");
        exp.setPais(sc.nextLine());
        
        System.out.print("Sexo('F','M'): ");
        exp.setSexo(sc.nextLine());
        
        System.out.print("Especialidad: ");
        exp.setEspecialidad(sc.nextLine());
        
        try 
        {
            if(new manejaExperto(co).insertaExperto(exp))
                return codExperto;
        } 
        
        catch (SQLException e) 
        {
            System.out.println("Error: " + e.getMessage());
            
            return null;
        }
        
        return null;
    }
    
    private static String casoInsertado()
    {
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        caso cs = new caso();
        
        System.out.print("Código del caso: ");
        
        String codCaso = sc.nextLine();
        
        cs.setCodCaso(codCaso);
        
        System.out.print("Nombre: ");
        cs.setNombre(sc.nextLine());
        
        System.out.print("Fecha de inicio (YYYY-MM-DD): ");
        cs.setFechaInicio(sc.nextLine());
        
        System.out.print("Fecha de fin(YYYY-MM-DD): ");
        cs.setFechaFin(sc.nextLine());
        
        try 
        {
            if(new manejaCaso(co).insertaCaso(cs))
                return codCaso;
        } 
        
        catch (SQLException e) 
        {
            System.out.println("Error: " + e.getMessage());
            
            return null;
        }
        
        return null;
    }
    
    private static void colaboraciónInsertada(String codExperto, String codCaso) throws SQLException
    {
        Scanner teclado = new Scanner(System.in, "ISO-8859-1");
        manejaColabora colaboración = new manejaColabora(co);
            
        if(!colaboración.existeColaboracion(codExperto, codCaso))
        {
            System.out.print("Inserta la fecha de la colaboración (YYYY-MM-DD): ");
            String fecha = teclado.nextLine();

            System.out.print("Inserta la descripción de la colaboración: ");
            String des = teclado.nextLine();

            colabora col = new colabora(codExperto, codCaso, fecha, des);
            
            if(colaboración.insertaColaboracion(col))
                System.out.println("Colaboración insertada correctamente.");
            
            else
                System.out.println("No se ha insertado el caso, algún dato no es correcto.");
        }

        else
            System.out.println("La colaboración ya existe.");
    }
}
