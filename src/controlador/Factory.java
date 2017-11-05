package controlador;

import vista.Colaborar;
import vista.Consultar;
import vista.Insertar;

/**
 *
 * @author jprecreativo
 */
public abstract class Factory 
{
    private static conexionOracle co;
    private static Colaborar colaborar;
    private static Consultar consultar;
    private static Insertar insertar;

    public Factory(conexionOracle co) 
    {
        Factory.co = co;
        
        colaborar = null;
        consultar = null;
        insertar = null;
    }
    
    public static void factoryMethod(String screen)
    {
        switch(screen)
        {
            case "Colaborar":
                                if(colaborar != null)
                                    colaborar.dispose();
                                
                                colaborar = new Colaborar(co);
                                
                                break;
                                
            case "Consultar":
                                if(consultar != null)
                                    consultar.dispose();
                                
                                consultar = new Consultar(co);
                                
                                break;
                                
            case "Insertar":
                                if(insertar != null)
                                    insertar.dispose();
                                
                                insertar = new Insertar(co);
                                
                                break;
        }    
    }
}
