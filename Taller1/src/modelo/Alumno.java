package modelo;

import java.util.HashMap;

public class Alumno
{
    String  ID,
            fecha_de_nacimiento,
            apellido_y_nombre,
            domicilio,
            carrera;
    HashMap<String,Double> materias;
    
    public Alumno()
    {
        super();
    }
    
    public double valorNota(String Nombre_Materia) throws SubjectNotFoundException 
    {
        if(!materias.containsKey(Nombre_Materia))
            throw new SubjectNotFoundException(Nombre_Materia);
        return materias.get(Nombre_Materia);
    }
        

    public String getID()
    {
        return ID;
    }

    public String getDomicilio()
    {
        return domicilio;
    }
    
    public String getApellido_y_nombre()
    {
        return apellido_y_nombre;
    }
}