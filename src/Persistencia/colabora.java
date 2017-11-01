package Persistencia;

/**
 * Colabora 
 */

public class colabora {
    private String codExperto;
    private String codCaso;
    private String fecha;
    private String descripcionColaboracion;
	
     /**
   * Constructor con parametros 
     * @param ce
     * @param cc
     * @param f
     * @param dc
   * @ param ce, cc, f, dc
    */
    public colabora(String ce, String cc, String f, String dc) 
    {
	codExperto = ce;
        codCaso = cc;
        fecha = f;
        descripcionColaboracion = dc;
    }

    /**
     * @return the codExperto
     */
    public String getCodExperto() {
        return codExperto;
    }

    /**
     * @param codExperto the codExperto to set
     */
    public void setCodExperto(String codExperto) {
        this.codExperto = codExperto;
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
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the descripcionColaboracion
     */
    public String getDescripcionColaboracion() {
        return descripcionColaboracion;
    }

    /**
     * @param descripcionColaboracion the descripcionColaboracion to set
     */
    public void setDescripcionColaboracion(String descripcionColaboracion) {
        this.descripcionColaboracion = descripcionColaboracion;
    }
}
