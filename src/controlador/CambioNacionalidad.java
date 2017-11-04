package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.experto;
import vista.Consultar;

/**
 *
 * @author jprecreativo
 */
public class CambioNacionalidad implements ItemListener
{
    private final conexionOracle co;

    public CambioNacionalidad(conexionOracle co) 
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
}
