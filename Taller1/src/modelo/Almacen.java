package modelo;

import java.util.HashMap;

public class Almacen 
{
    private HashMap<String,Alumno> alumnos; 
  
    public Almacen() 
    {
        super();
    }
    
    public void eliminarAlumnoID(String ID) throws StudentIDNotFoundException
    {
        if(!alumnos.containsKey(ID))
            throw new StudentIDNotFoundException(ID);
        //else
        alumnos.remove(ID);
    }
    
    
}
