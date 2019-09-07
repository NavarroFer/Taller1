package modelo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Sistema
{

    private Almacen almacen = null;
    private static Sistema instance =null;
    

    private Sistema()
    {
        super();
    }
    
    public static Sistema getInstance()
    {
        if(instance==null)
            instance=new Sistema();
        return instance;
    }

    public void cargar(String filename)
    {
        try
        {
            XMLDecoder xmldecoder = new XMLDecoder(new FileInputStream(filename));
            this.almacen = (Almacen) xmldecoder.readObject();
        }
        catch (FileNotFoundException e)
        {
            //Imposible por precondición
        }
    }
    
    public void guardar()
    {

        try 
        {
            XMLEncoder xmlencoder = new XMLEncoder(new FileOutputStream(almacen.getFilename()));
            xmlencoder.writeObject(this.almacen);
            xmlencoder.close();
        }
        catch (FileNotFoundException e)
        {               
            //Imposible por precondición
        }
    }
    
    public void crear(String filename)
    {
        almacen = new Almacen(filename);
    }
    

    
    public void eliminarAlumno(String ID)
    {
        almacen.eliminarAlumnoID(ID);
    }
    
    public boolean tieneAlmacenCargado()
    {
        return almacen != null;
    }
    
    public boolean alumnoExiste(String ID)
    {
        return almacen.IDExists(ID);
    }
    
    /**
     * <b>Pre:</b> El operador es valido. La nota es un numero <br><br>
     * <b>Post:</b> Devuelve la lista de alumnos que cumple con la condicion.
     * 
     * @param materia nombre de la materia:String, operador valor del operador para evaluar:String, nota valor numerico en la materia:double:.
     */
    public ArrayList<Alumno> listaDeAlumnos(String materia,String operador,double nota)
    {
       return this.almacen.listaDeAlumno(materia,operador,nota);
    }
    
    public ArrayList<Alumno> listaDeAlumnosArch(String materia,String operador,double nota,String nombreArch)
    {
        ArrayList<Alumno> aux = this.almacen.listaDeAlumno(materia,operador,nota);
        XMLEncoder encoder = null;
        try
        {
            encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArch)));
        }
        catch (FileNotFoundException e)
        {
            //Imposible por precondición
        }
        encoder.writeObject(aux);
        encoder.close();
        return aux;
    }
    
    public void insertar(String filename) 
    {
    
        try
        {
            XMLDecoder xmldecoder = new XMLDecoder(new FileInputStream(filename));
            Alumno a = (Alumno) xmldecoder.readObject();
            almacen.agregarAlumno(a);
        }
        catch (FileNotFoundException e)
        {
            //Imposible por precondición
        }
    }
}
