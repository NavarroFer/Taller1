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

    private void cargarPersistentes()
    {
        try
        {
            XMLDecoder xmldecoder = new XMLDecoder(new FileInputStream("BotFrases.xml"));
            this.almacen = (Almacen) xmldecoder.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("=======================");
            System.out.println("=Archivo no encontrado=");
            System.out.println("=======================");
        }
    }
    
    protected void guardarPersistentes()
    {
        try 
        {
            XMLEncoder xmlencoder = new XMLEncoder(new FileOutputStream("BotFrases.xml"));
            xmlencoder.writeObject(this.almacen);
            xmlencoder.close();
        }
        catch (FileNotFoundException e)
        {               
        }
    }
    
    public void crear(String string)
    {
        // todo
    }
    
    public void cargar(String string)
    {
        // todo
    }
    
    public void guardar()
    {
        // todo
    }
    
    public void insertar(String string)
    {
        // todo
    }
    
    public void eliminar(String string)
    {
        // todo
    }
    
    public boolean tieneAlmacenCargado()
    {
        return almacen != null;
    }
    
    public boolean alumnoExiste(String string)
    {
        return true; // todo
    }

    public void eliminarAlumno(String string)
    {
        // todo
    }
}
