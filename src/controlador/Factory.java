package controlador;

import vista.Colaborar;
import vista.Consultar;
import vista.InsertarExperto;
import vista.Screen;

/**
 *
 * @author jprecreativo
 */
public abstract class Factory 
{
    public static conexionOracle co;
    private static Colaborar colaborar = null;
    private static Consultar consultar = null;
    private static InsertarExperto insertar = null;
    
    public static void factoryMethod(String screen, String codExperto)
    {
        switch(screen)
        {
            case "Colaborar":
                                if(colaborar != null)
                                    colaborar.dispose();
                                
                                colaborar = new Colaborar(Factory.co);
                                
                                break;
                                
            case "Consultar":
                                if(consultar != null)
                                    consultar.dispose();
                                
                                consultar = new Consultar(Factory.co);
                                
                                break;
                                
            case "Insertar":
                                if(insertar != null)
                                    insertar.dispose();
                                
                                insertar = new InsertarExperto(Factory.co, codExperto);
                                
                                break;
        }    
    }
}
