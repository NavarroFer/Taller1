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

    /**
     * Constructor vacio para ser usado con fines de persistencia. No se recomienda su uso para otros fines.
     */
    public Alumno()
    {
        super();
        this.materias = new HashMap<String,Double>();
    }

    /**
     * @author Nahuel
     * @param materia nombre de la materia cuya nota se quiere consultar:String.
     * <b>Pre:</b><br>
     * Se ha verificado previamente que el alumno hace la materia utilizando <u><a href="#haceMateria-java.lang.String-">haceMateria(materia)</a></u><br><br>
     * 
     * <b>Post:</b> Retorna la nota que tiene el alumno en la materia especificada.
     * 
     * @return Nota que tiene el alumno en la materia especificada.
     */
    public double valorNota(String materia)
    {
        assert(materia!=null);
        assert(materia!="");
        return materias.get(materia);
    }
    
    /**
     * @author Nahuel
     * @param materia nombre de la materia cuya que se quiere consultar:String. Recuerde que el nombre de la materia no debe llevar espacios.
     * <b>Pre:</b><br>
     * materia != null<br>
     * materia != ""<br>
     * materia.contains(" ") = false<br><br>
     * 
     * <b>Post:</b> retorna true si el alumno tiene la materia en su coleccion de materias.
     * 
     * @return retorna true si el alumno tiene la materia en su coleccion de materias.
     */
    public boolean haceMateria(String materia)
    {
        assert(materia!=null);
        assert(materia!="");
        return this.materias.containsKey(materia);
    }

    public String getID()
    {
        return ID;
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

    public void setID(String ID) 
    {
        this.ID = ID;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) 
    {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public String getFecha_de_nacimiento() 
    {
        return fecha_de_nacimiento;
    }

    public void setApellido_y_nombre(String apellido_y_nombre) 
    {
        this.apellido_y_nombre = apellido_y_nombre;
    }

    public void setDomicilio(String domicilio) 
    {
        this.domicilio = domicilio;
    }

    public void setCarrera(String carrera) 
    {
        this.carrera = carrera;
    }

    /**
     * A ser usado con fines de serialización.
     * @param materias HashMap de materias. Precondición: Ninguna materia contiene espacio en su nombre. (Utilizar_guiones_bajos).
     */
    public void setMaterias(HashMap<String, Double> materias) 
    {
        this.materias = materias;
    }

    public HashMap<String, Double> getMaterias() 
    {
        return materias;
    }

    @Override
    public String toString()
    {
        return "Alumno nro " + this.getID() + ". Nombre completo: " + this.getApellido_y_nombre() + ". Carrera:" + this.getCarrera();
    }
}
