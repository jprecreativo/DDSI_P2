package controlador;

import modelo.experto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class manejaExperto {
    // Se crea un objeto de tipo "conexionOracle"
    conexionOracle co = null;
    
    // Se crea el PreparedStatement como atributo de la clase manejaExperto para
    // utilizarlo en los diferentes métodos
    PreparedStatement ps = null;
    

    /**
    * Implementa operaciones sobre la tabla EXPERTO
    * @param c conexión con Oracle
    */    
    public manejaExperto(conexionOracle c) 
    {
      co = c;
    }
     /**
    * Devuelve una lista con todos los expertos cuyo país se pase por parámetro
    * @param pais
    * @throws SQLException si ocurre alguna anomalía
    * @return ArrayList<experto>
    */
    public ArrayList<experto> listaExpertosPorPais(String pais) throws SQLException 
    {
        ps = conexionOracle.co.prepareStatement("SELECT * FROM EXPERTO WHERE PAIS = ?");
        
        ps.setString(1, pais);
        
        ResultSet rs = ps.executeQuery();
        ArrayList<experto> expertos = new ArrayList();
        
        while(rs.next())
        {
            experto e =  new experto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            
            expertos.add(e);
        }
        
        ps.close();
        
        return expertos;
    }    
    /**
    * Comprueba si existe un experto
    * @param codExperto
     * @return 
    * @throws SQLException si ocurre alguna anomalía
     */
    public boolean existeExperto(String codExperto) throws SQLException 
    {
        ps = conexionOracle.co.prepareStatement("SELECT * FROM EXPERTO WHERE CODEXPERTO = ?");
        
        ps.setString(1, codExperto);

        ResultSet rs = ps.executeQuery();

        return rs.next();
    }
    
     /**
    * inserta un experto
    * @param exp
     * @return
     */
    public boolean insertaExperto(experto exp)
    {
        try 
        {
            if("...".equals(exp.getSexo()))   // No se especifica sexo.
            {
                ps = conexionOracle.co.prepareStatement("INSERT INTO EXPERTO (CODEXPERTO, NOMBRE, PAIS, ESPECIALIDAD)"
                                                      + " VALUES (?, ?, ?, ?)");

                ps.setString(1, exp.getCodExperto());
                ps.setString(2, exp.getNombre());
                ps.setString(3, exp.getPais());
                ps.setString(4, exp.getEspecialidad());
            }
            
            else   // Se especifica sexo.
            {
                ps = conexionOracle.co.prepareStatement("INSERT INTO EXPERTO VALUES (?, ?, ?, ?, ?)");

                ps.setString(1, exp.getCodExperto());
                ps.setString(2, exp.getNombre());
                ps.setString(3, exp.getPais());
                ps.setString(4, exp.getSexo());
                ps.setString(5, exp.getEspecialidad());
            }
            
            ps.executeUpdate();
            ps.close();

            return true;
        } 

        catch (SQLException e) 
        {
            System.out.println("Error: " + e.getMessage());

            return false;
        }
    }
}