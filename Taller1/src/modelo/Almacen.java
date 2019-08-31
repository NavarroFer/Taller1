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
     * <b>Pre:</b> ID != null e ID != "". <br><br>
     * <b>Post:</b> se elimina el alumno cuya ID fue ingresada en el metodo.
     * 
     * @param ID Identificador del alumno:String.
     * @throws StudentIDNotFoundException Estudiante no encontrado
     */
    public void eliminarAlumnoID(String ID) throws StudentIDNotFoundException
    {
        if(!alumnos.containsKey(ID))
            throw new StudentIDNotFoundException(ID);
        //else
        alumnos.remove(ID);
    }    
    
    
}
