package modelo;

public class StudentIDNotFoundException
    extends Exception
{
    String ID;

    
    public StudentIDNotFoundException(String string)
    {
        this.ID = string;
    }

    /**
     * <b>Pre:</b> debe haber sido asignado un ID de alumno no encontrado a la excepcion con anterioridad. <br><br>
     * <b>Post:</b> se retorna el ID del alumno no encontrado
     * 
     * @return ID del alumno no encontrado
     */
    public String getID()
    {
        return ID;
    }
}
