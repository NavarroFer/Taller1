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
    


    /**
     * <b>Pre:</b> debe haber sido asignado un ID al alumno con anterioridad. <br><br>
     * <b>Post:</b> se retorna el ID del alumno en cuestion.
     * 
     * @return ID del alumno en cuestion.
     */
    public String getID()
    {
        return ID;
    }

    /**
     * <b>Pre:</b> debe haber sido asignado un domicilio al alumno con anterioridad. <br><br>
     * <b>Post:</b> se retorna el domicilio del alumno en cuestion.
     * 
     * @return Domicilio del alumno en cuestion.
     */
    public String getDomicilio()
    {
      return domicilio;
    }
     /**
     * <b>Pre:</b> debe haber sido asignado una carrera al alumno con anterioridad. <br><br>
     * <b>Post:</b> se retorna la carrera del alumno en cuestion.
     * 
     * @return carrera del alumno en cuestion.
     */
    public String getCarrera()
    {
        return carrera;
    }

    /**
     * <b>Pre:</b> debe haber sido asignado un apellido y un nombre al alumno con anterioridad. <br><br>
     * <b>Post:</b> se retorna el apellido y un nombre del alumno en cuestion.
     * 
     * @return Apellido y un nombre del alumno en cuestion.
     */
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
