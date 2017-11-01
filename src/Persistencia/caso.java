package Persistencia;
/**
 * Caso 
 */


public class caso {
    private String codCaso;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
	
   /**
   * Constructor por defecto   
    */
    public caso() {
	// TODO implementar operaciones
    }
    
    /**
   * Constructor con parametros
   * @param cc, n, fi, ff
     * @param n
     * @param fi
     * @param ff
    */
    public caso(String cc, String n, String fi, String ff) 
    {
	codCaso = cc;
        nombre = n;
        fechaInicio = fi;
        fechaFin = ff;
    }

    /**
     * @return the codCaso
     */
    public String getCodCaso() {
        return codCaso;
    }

    /**
     * @param codCaso the codCaso to set
     */
    public void setCodCaso(String codCaso) {
        this.codCaso = codCaso;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }    
}
