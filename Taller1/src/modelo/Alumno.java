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
        String ID = this.ID; // lol
        return ID;
    }

    public String getCarrera()
    {
        return carrera;
    }
    
    public String getApellido_y_nombre()
    {
        return apellido_y_nombre;
    }

    @Override
    public String toString()
    {
        return this.getID() + " " + this.getApellido_y_nombre() + " " + this.getCarrera();
    }
}

