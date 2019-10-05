package modelo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Almacen implements Serializable
{
    private HashMap<String,Alumno> alumnos;
    private String filename;

    /**
     * Exclusivamente para que funcione la serializacion
     * @author: Mau
     */
    public Almacen(){
        
    }

    /**
     * <b>Pre:</b> filename != null y != "".<br><br>
     * <b>Post:</b> se instancia el almacen con el nombre de archivo dado.
     * 
     * @param filename nombre del archivo en que se guardara el almacen en caso de solicitarlo.
     */
    public Almacen(String filename) 
    {
        assert filename != null:"El nombre de archivo es null";
        assert !filename.equals(""):"El nombre del archivo es String vacia";
        
        this.filename=filename;
        this.alumnos = new HashMap<String,Alumno>();
        
        verificarInvariante();
    }

    
    public void setAlumnos(HashMap<String, Alumno> alumnos) 
    {
        this.alumnos = alumnos;
    }

    public HashMap<String, Alumno> getAlumnos() 
    {
        return alumnos;
    }

    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }

    

    /**
     * <b>Pre:</b> Valid ID. <br>
     * ID Exists.<br>
     * ID != null.<br>
     * ID != "". <br><br>
     * 
     * <b>Post:</b> se elimina el alumno cuya ID fue ingresada en el metodo.
     * 
     * @param ID Identificador del alumno:String.
     */
    public void eliminarAlumnoID(String ID)
    {
        assert ID != null:"El ID ingresado es null";
        assert !ID.equals(""):"El ID ingresado es String vacia";
        assert IDExists(ID):"No existe ningun alumno con la ID solicitada";
        verificarInvariante();
        //------------------------------------------------------------------------------
        
        alumnos.remove(ID);
    }    
    
    /**
     * <b>Pre:</b> materia != null y != "". operador != null y != "". La nota es un numero.  <br><br>
     * <b>Post:</b> Devuelve la lista de alumnos que cumple con la condicion ({operador} sobre una {nota} en una {materia}).
     * 
     * @param materia nombre de la materia:String
     * @param operador valor del operador para evaluar:String
     * @param nota valor numerico en la materia:double:.
     */
    public ArrayList<Alumno> listaDeAlumno(String materia, String operador, double nota)//devuelve los alumnos que cursan la materia solicitada con su correspondiente nota
    {
        assert materia != null: "La materia ingresada es null";
        assert !materia.equals(""): "La materia ingresada es String vacia";
        assert operador != null: "El operador ingresado es null";
        assert !operador.equals(""): "El operador ingresado es String vacia";
        verificarInvariante();
        //------------------------------------------------------------------------------
        
        ArrayList<Alumno> listaDeAlumnos = new ArrayList<Alumno>();
        Iterator<Alumno> it = alumnos.values().iterator();
        Alumno aux;
        if(operador.equals("=="))
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota==nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        else if(operador.equals("!="))
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota!=nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        else if(operador.equals(">"))
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota>nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        else if(operador.equals("<"))
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota<nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        else if(operador.equals(">="))
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota>=nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        else //por descarte <= y por precondici�n: S� o si es uno de ellos.
        {
            while(it.hasNext()) 
            {
                aux= it.next();
                if(aux.haceMateria(materia))
                {
                    double auxNota = aux.valorNota(materia);
                    if(auxNota<=nota)  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                        listaDeAlumnos.add(aux);
                }
            }
        }
        return listaDeAlumnos;
    }


    /**
     * <b>Pre:</b> ID != null y != "". <br><br>
     * <b>Post:</b> Devuelve si el ID de alumno ingresado ya estaba en la coleccion de alumnos o no.
     * 
     * @param ID identificador de un alumno
     * @return devuelve si el ID de alumno ingresado es valido o no
     */
    public boolean IDExists(String ID)
    {
        assert ID != null:"El ID ingresado es null";
        assert !ID.equals(""):"El ID ingresado es String vacia";
        verificarInvariante();
        //--------------------------------------------------------------
        
        return alumnos.containsKey(ID);
    }

    /**
     * <b>Pre:</b> alumno != null y el alumno no estaba agregado previamente en la coleccion. <br><br>
     * <b>Post:</b> agrega el alumno al HashMap de alumnos.
     * 
     * 
     * @param alumno instancia de alumno a agregar al HashMap de alumnos
     */
    public void agregarAlumno(Alumno alumno) 
    {
        assert alumno != null: "El alumno que se quiere insertar es null";
        verificarInvariante();
        
        this.alumnos.put(alumno.getID(),alumno);
    }

    /**
     * Verifica que se cumplan los invariantes de clase
     */
    private void verificarInvariante(){
        assert this.alumnos != null: "El HashMap de alumnos es null";
        assert this.filename != null: "El filename es null";
        assert !this.filename.equals(""): "El filename es String vacia";
    }
    
}
