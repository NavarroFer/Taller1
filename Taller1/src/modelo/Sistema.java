package modelo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

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
