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
            System.out.println("=======================");
            System.out.println("=Archivo no encontrado=");
            System.out.println("=======================");
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
            System.out.println("esto no deberia pasar nunca");
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
    public ArrayList<Alumno> listaDeAlumnosArch(String materia,String operador,double nota,String nombreArch)throws FileNotFoundException //no se si esta bien serializar aca xd
    {
        ArrayList<Alumno> aux = this.almacen.listaDeAlumno(materia,operador,nota);
        XMLEncoder encoder = null;
        encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(nombreArch)));    
        encoder.writeObject(aux);
        encoder.close();
        return aux;
    }
    
    public void insertar(String filename) {
    
        try
        {
            XMLDecoder xmldecoder = new XMLDecoder(new FileInputStream(filename));
            Alumno a = (Alumno) xmldecoder.readObject();
            if(almacen.IDExists(a.getID())){ //si ya hay un almumno con esa ID
                System.out.println("La verdad q no tendriamos q haber tirado una excepcion ams arriba??"); //TODO mirar esto y lo de los prints de no encontrado q me parece medio raro?? -Mau
            }
            else{
                almacen.agregarAlumno(a);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("==============================");
            System.out.println("=Archivo alumno no encontrado=");
            System.out.println("==============================");
        }
    }
}
