package modelo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Sistema
{
    private Almacen almacen;

    public Sistema()
    {
        super();
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
}
