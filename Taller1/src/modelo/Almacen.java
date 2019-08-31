package modelo;

import java.util.HashMap;

public class Almacen 
{
    private HashMap<String,Alumno> alumnos; 
  
    public Almacen() 
    {
        super();
    }

    /**
     * <b>Pre:</b> Valid ID. ID Exists <br><br>
     * <b>Post:</b> se elimina el alumno cuya ID fue ingresada en el metodo.
     * 
     * @param ID Identificador del alumno:String.
     */
    public void eliminarAlumnoID(String ID)
    {
        alumnos.remove(ID);
    }    
    
    public boolean IDExists(String ID)
    {
        return alumnos.containsKey(ID);
    }
}
