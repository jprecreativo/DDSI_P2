package controlador;
import java.sql.*;

public class conexionOracle 
{
    public static Connection co = null;
    
    /** 
      Establece la conexión con el servidor
     * @param username
     * @param pass
    @throws Exception si ocurre cualquier anormalidad
    */
    public conexionOracle(String username, String pass) throws ClassNotFoundException, SQLException 
    {
        Class.forName("oracle.jdbc.OracleDriver");
        
        co = DriverManager.getConnection("jdbc:oracle:thin:@172.17.20.75:1521:rabida", username, pass); 
    }  
    
    /** 
      Implementa la desconexión con el servidor
    @throws SQLException si ocurre cualquier anormalidad
    */
    public void desconexion() throws SQLException 
    {    
        co.close();
    }
    
    /**  
    *  Inicia una transacción
    *@throws SQLException si ocurre cualquier anormalidad
    */
    public void inicioTransaccion() throws SQLException 
    {
        try 
        {
            co.setAutoCommit(false);
        } 
        
        catch (SQLException e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
        
        finally
        {
            co.setAutoCommit(true);
        }
    }
    
    /** 
    *  Finaliza una transacción con commint
    *@throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionCommit() throws SQLException 
    {
       conexionOracle.co.commit();
    }
    
    /**  
    *  Finaliza una transacción con rollback
    @throws SQLException si ocurre cualquier anormalidad
    */			
    public void finTransaccionRollback() throws SQLException 
    {
        conexionOracle.co.rollback();
    }

}
