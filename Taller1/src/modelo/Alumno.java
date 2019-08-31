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

    /**
     * <b>Pre:</b> Nombre_Materia != null y Nombre_Materia != "". <br><br>
     * <b>Post:</b> se retorna la nota que tiene el alumno en la materia especificada.
     * 
     * @param Nombre_Materia  nombre de la materia cuya nota se quiere consultar:String.
     * @return Nota que tiene el alumno en la materia especificada.
     * @throws SubjectNotFoundException Materia con nombre Nombre_Materia no encontrada.
     */
    public double valorNota(String Nombre_Materia) throws SubjectNotFoundException 
    {
        if(!materias.containsKey(Nombre_Materia))
            throw new SubjectNotFoundException(Nombre_Materia);
        return materias.get(Nombre_Materia);
    }

    public String getID()
    {
        return this.ID;
    }

    public String getDomicilio()
    {
      return domicilio;
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
        return "ID: " + this.getID() + " Nombre y apellido:" + this.getApellido_y_nombre() + " Carrera:" + this.getCarrera();
    }
}

