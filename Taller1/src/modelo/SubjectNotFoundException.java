package modelo;

public class SubjectNotFoundException
    extends Exception
{
    private String message;
    
    public SubjectNotFoundException(String string)
    {
        this.message = string;
    }

     /**
     * <b>Pre:</b> debe haber sido asignado un nombre de la materia no encontrada a la excepcion con anterioridad. <br><br>
     * <b>Post:</b> se retorna el nombre de la materia no encontrada
     * 
     * @return Nombre de la materia no encontrada
     */
    public String toString()
    {
        return message;
    }
}
