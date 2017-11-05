package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import vista.Consultar;

/**
 *
 * @author jprecreativo
 */
public class Nacionalidad implements ItemListener
{
    private final conexionOracle co;

    public Nacionalidad(conexionOracle co) 
    {
        this.co = co;
    }
    
    @Override
    public void itemStateChanged(ItemEvent event) 
    {
        if(event.getStateChange() == ItemEvent.SELECTED)
        {
            String nacionalidad = event.getItem().toString();
            manejaExperto me = new manejaExperto(co);
            
            try 
            {
                Consultar.rellenarTabla(me.listaExpertosPorPais(nacionalidad));
            } 
            
            catch (SQLException e) 
            {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static ResultSet obtenerNacionalidades() throws SQLException
    {
        return conexionOracle.co.createStatement().executeQuery("SELECT DISTINCT PAIS FROM EXPERTO");
    }
}
