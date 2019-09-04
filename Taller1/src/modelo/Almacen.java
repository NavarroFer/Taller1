package modelo;

import java.util.HashMap;

public class Almacen 
{
    private HashMap<String,Alumno> alumnos;
    private String filename;

    public void setAlumnos(HashMap<String, Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public HashMap<String, Alumno> getAlumnos() {
        return alumnos;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public Almacen(String filename) 
    {
        super();
        this.filename=filename;
        this.alumnos = new HashMap<String,Alumno>();
    }
    
    public Almacen(){
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

    public void agregarAlumno(Alumno a) {
        this.alumnos.put(a.getID(),a);
    }
}
