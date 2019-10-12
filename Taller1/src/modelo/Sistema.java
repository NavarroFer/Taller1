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

    /**
     * @return devuelve la instancia del sistema (Singleton)
     */
    public static Sistema getInstance()
    {
        if(instance==null)
            instance=new Sistema();
        return instance;
    }


    /**
     * Este metodo carga un almacen al sistema, que llega por parametro <br><br> 
     * 
     * <b>pre:</b> filename distinto de vacio y distinto de null <br><br> 
     * <b>post:</b> se ha cargado un almacen en el sistema <br><br> 
     * 
     * @param filename es el nombre de el archivo el cual cargara el almacen. filename != "", filename != null
     */
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
        /*         
        catch (ClassCastException e)
        {
            Esta excepción la propagamos.
        } 
        */
    }


    /**
     * Este metodo guarda el almacen que esta cargado actualmente en el sistema creando un archivo
     * con el nombre que tiene el atributo filename <br><br> 
     * 
     * <b>pre:</b> el almacen tiene asignado un almacen, almacen != null
     */
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

    /**
     * Este metodo crea un almacen, asignandolo al atributo almacen de el sistema <br><br> 
     * 
     * <b>pre:</b> filename != null, filename != "" <br><br> 
     * 
     * @param filename Nombre de el archivo que representara el almacen. filename != null, filename != ""
     */
    public void crear(String filename)
    {
        almacen = new Almacen(filename);
    }


    /**
     * Este metodo elimina un alumno de el almacen, segun su identificacion <br><br> 
     * 
     * <b>pre:</b> ID != null, ID != "". Almacen != null <br><br> 
     * <b>post:</b> hay un alumno menos en el almacen <br><br> 
     * 
     * @param ID Identificacion de el alumno que se desea eliminar. ID != null, ID != ""
     */
    public void eliminarAlumno(String ID)
    {
        almacen.eliminarAlumnoID(ID);
    }


    /**
     * Verifica si hay un almacen cargado <br><br> 
     *   
     * @return si almacen es distinto de null, retorna verdadero, sino falso
     */
    public boolean tieneAlmacenCargado()
    {
        return almacen != null;
    }
    
    
    /**
     * Este metodo verifica que dada la identificacion de un alumno, verifica si existe o no en el almacen <br><br>
     *
     * <b>pre:</b> ID Identificacion de alumno ID != null, ID != "". Almacen != null <br><br>
     *
     * @param ID Identificacion de el alumno
     * @return Si el alumno existe retorna verdadero, sino falso  
     */
    public boolean alumnoExiste(String ID)
    {
        return almacen.IDExists(ID);
    }
    
    /**
     * @author Nacho     
     *  
     * @param materia nombre de la materia:String, operador valor del operador para evaluar:String, nota valor numerico en la materia:double:.
     * @param operador Operador valido para comparar la materia con la nota
     * @param nota Nota mayor que 0, nota de la materia para comparar<br><br>
     * Este metodo delega al almacen el listado de alumnos<br><br> 
     * 
     * <b>Pre:</b> materia != null, materia != vacio. operador != null, operador != vacio, operador valido. La nota es un numero positivo<br><br> 
     * <b>Post:</b> Devuelve la lista de alumnos que cumple con la condicion. <br><br> 
     * <b>inv: </b> <br> Almacen != null

     * 
     * @return lista de alumnos que cumplen con la consulta
     */
    public ArrayList<Alumno> consultar(String materia,String operador,double nota)
    {
        assert(this.tieneAlmacenCargado());
        assert(materia!= null);
        assert(materia != "");
        assert(operador != null);
        assert(operador != "");
        assert(operador.equals("==") ||
           operador.equals("!=") ||
           operador.equals("<") ||
           operador.equals(">") ||
           operador.equals(">=") ||
           operador.equals("<="));
        return almacen.listaDeAlumno(materia,operador,nota);
    }

    /**
     * @author Nacho
     * 
     * @param materia Nombre de la materia. materia != "", materia != null
     * @param operador Operador valido para comparar la materia con la nota
     * @param nota Nota mayor que 0, nota de la materia para comparar
     * @param nombreArch nombre de el archivo donde se guardaran los datos<br><br>
     * 
     * Este metodo delega al almacen el listado de alumnos y guarda el listado en un archivo<br><br> 
     * 
     * <b>El parámetro "materia" es case sensitive.</b><br><br>
     * 
     * <b>Pre:</b><br> 
     * Almacen != null<br>
     * materia != null<br>
     * materia != ""<br>
     * operador != null<br>
     * operador != ""<br>
     * operador valido<br>
     * La nota es un numero positivo<br><br> 
     * 
     * <b>Post:</b> Devuelve la lista de alumnos que cumple con la condicion y la almacena en un archivo. <br><br> 
     * <b>inv: </b><br> 
     * Almacen != null<br>
     * materia != null<br>
     * materia != ""<br>
     * operador != null<br>
     * operador != ""<br>
     * operador valido<br>
     * 
     * 
     * @return arrayList de alumnos que cumplen con la condicion establecida
     */
    public ArrayList<Alumno> consultarArch(String materia,String operador,double nota,String nombreArch)
    {
        assert(this.tieneAlmacenCargado());
        assert(materia!= null);
        assert(materia != "");
        assert(operador != null);
        assert(operador != "");
        assert(operador.equals("==") ||
           operador.equals("!=") ||
           operador.equals("<") ||
           operador.equals(">") ||
           operador.equals(">=") ||
           operador.equals("<="));
        
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


    /**
     * Este metodo inserta un alumno en el almacen actual <br><br> 
     * 
     * <b>pre:</b> almacen != null, filename, filename != "" <br><br> 
     * <b>post:</b> se ha insertado un alumno a el almacen actual <br><br> 
     * 
     * @param filename nombre de el archivo de el alumno a insertar
     */
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
