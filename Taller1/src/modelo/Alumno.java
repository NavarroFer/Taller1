package modelo;

import java.util.HashMap;

public class Alumno
{
    private String  ID,
            fecha_de_nacimiento,
            apellido_y_nombre,
            domicilio,
            carrera;
     private HashMap<String,Double> materias;
    
    public Alumno()
    {
        super();
        this.materias = new HashMap<String,Double>();
    }

    /**
     * <b>Pre:</b> Nombre_Materia != null y Nombre_Materia != "" El alumno cursa la materia. <br><br>
     * <b>Post:</b> se retorna la nota que tiene el alumno en la materia especificada.
     * 
     * @param Nombre_Materia  nombre de la materia cuya nota se quiere consultar:String.
     * @return Nota que tiene el alumno en la materia especificada.
     */
    public double valorNota(String Nombre_Materia)
    {
        return materias.get(Nombre_Materia);
    }
    public boolean isMateria(String materia)
    {
        return this.materias.containsKey(materia);
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

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setApellido_y_nombre(String apellido_y_nombre) {
        this.apellido_y_nombre = apellido_y_nombre;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public void setMaterias(HashMap<String, Double> materias) {
        this.materias = materias;
    }

    public HashMap<String, Double> getMaterias() {
        return materias;
    }

    @Override
    public String toString()
    {
        return "ID: " + this.getID() + " Nombre y apellido:" + this.getApellido_y_nombre() + " Carrera:" + this.getCarrera();
    }
}
