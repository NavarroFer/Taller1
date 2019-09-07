package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Almacen 
{
    private HashMap<String,Alumno> alumnos;
    private String filename;


    public Almacen()
    {
        super();
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

    public Almacen(String filename) 
    {
        super();
        this.filename=filename;
        this.alumnos = new HashMap<String,Alumno>();
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
    
    /**
     * <b>Pre:</b> El operador es valido. La nota es un numero <br><br>
     * <b>Post:</b> Devuelve la lista de alumnos que cumple con la condicion.
     * 
     * @param materia nombre de la materia:String, operador valor del operador para evaluar:String, nota valor numerico en la materia:double:.
     */
    public ArrayList<Alumno> listaDeAlumno(String materia, String operador, double nota)//devuelve los alumnos que cursan la materia solicitada con su correspondiente nota
    {
        ArrayList<Alumno> listaDeAlumnos = new ArrayList<Alumno>();
        Iterator it = alumnos.entrySet().iterator();
        Alumno aux;
        if(operador.equals("=="))
        {
            while(it.hasNext()) 
            {
                aux=alumnos.get(it.next());
                double auxNota = aux.valorNota(materia);
                if((aux.isMateria(materia))&&(auxNota==nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
                    listaDeAlumnos.add(aux);
            }
        }
        else if(operador.equals("!="))
            {
            while(it.hasNext()) 
            {
                aux=alumnos.get(it.next());
                double auxNota = aux.valorNota(materia);
                if((aux.isMateria(materia))&&(auxNota!=nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es distinta
                    listaDeAlumnos.add(aux);
                }
            }
            else if(operador.equals(">"))
                {
                    while(it.hasNext())
                    {
                        aux=alumnos.get(it.next());
                        double auxNota = aux.valorNota(materia);
                        if((aux.isMateria(materia))&&(auxNota>nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es mayor
                            listaDeAlumnos.add(aux);
                    }
                }
                else if(operador.equals("<"))
                    {
                        while(it.hasNext())
                        {
                            aux=alumnos.get(it.next());
                            double auxNota = aux.valorNota(materia);
                            if((aux.isMateria(materia))&&(auxNota<nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es menor
                                listaDeAlumnos.add(aux);
                        }
                    }
                    else if(operador.equals(">="))
                        {
                            while(it.hasNext()) 
                            {
                                aux=alumnos.get(it.next());
                                double auxNota = aux.valorNota(materia);
                                if((aux.isMateria(materia))&&(auxNota>=nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es mayor o igual
                                    listaDeAlumnos.add(aux);
                            }
                        }
                        else //por descarte <=
                            {
                                while(it.hasNext()) 
                                {
                                    aux=alumnos.get(it.next());
                                    double auxNota = aux.valorNota(materia);
                                    if((aux.isMateria(materia))&&(auxNota<=nota))  //verifico si el alumno cursa la materia y agrega a la lista si la nota es menor o igual
                                        listaDeAlumnos.add(aux);
                                }
                            }
        return listaDeAlumnos;
    }
        
    
    public boolean IDExists(String ID)
    {
        return alumnos.containsKey(ID);
    }

    public void agregarAlumno(Alumno a) {
        this.alumnos.put(a.getID(),a);
    }
}
