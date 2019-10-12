package modelo;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Almacen
  implements Serializable
{
  private HashMap<String, Alumno> alumnos;
  private String filename;

  /**
   * Exclusivamente para que funcione la serializacion. No utilizar.
   * @author: Mau
   */
  public Almacen()
  {

  }

  /**
   * @param filename nombre del archivo en que se guardara el almacen en caso de solicitarlo.
   *
   * <b>Pre:</b><br>
   * filename != null<br>
   * filename != "".<br><br>
   * <b>Post:</b><br>
   * Se instancia el almacen con el nombre de archivo dado.
   */
  public Almacen(String filename)
  {
    assert filename != null: "El nombre de archivo es null";
    assert !filename.equals(""): "El nombre del archivo es String vacia";

    this.filename = filename;
    this.alumnos = new HashMap<String, Alumno>();

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
   * Elimina un alumno de el HashMap de alumnos, segun su ID.
   *
   * @param ID Identificador del alumno:String.
   *
   * <b>Pre:</b><br>
   * Valid ID. <br>
   * ID Exists.<br>
   * ID != null.<br>
   * ID != "" <br><br>
   *
   * <b>Post:</b><br>
   * Se elimina el alumno cuya ID fue ingresada en el metodo.
   */
  public void eliminarAlumnoID(String ID)
  {
    assert ID != null: "El ID ingresado es null";
    assert !ID.equals(""): "El ID ingresado es String vacia";
    assert IDExists(ID): "No existe ningun alumno con la ID solicitada";
    verificarInvariante();
    //------------------------------------------------------------------------------

    alumnos.remove(ID);
  }

  /**
   * @author Nacho
   *
   * Este metodo crea una lista de alumnos segun la consulta del usuario. Donde se consulta por la nota de una materia
   *
   * @param materia nombre de la materia:String
   * @param operador valor del operador para evaluar:String
   * @param nota valor numerico en la materia:double.
   *
   * <b>Pre:</b><br>
   * materia != null<br>
   * materia != ""<br>
   * operador != null<br>
   * operador != ""<br>
   * La nota es un numero.<br><br>
   *
   * <b>Post:</b><br>
   * Devuelve la lista de alumnos que cumple con la condicion ({operador} sobre una {nota} en una {materia}).<br>
   * @return Devuelve la lista de alumnos que cumple con la condicion. Si ninguno satisfajo la consulta, devuelve un arraylist vacío.
   */
  public ArrayList<Alumno> listaDeAlumno(String materia, String operador, double nota) //devuelve los alumnos que cursan la materia solicitada con su correspondiente nota

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
    if (operador.equals("=="))
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota == nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    else if (operador.equals("!="))
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota != nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    else if (operador.equals(">"))
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota > nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    else if (operador.equals("<"))
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota < nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    else if (operador.equals(">="))
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota >= nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    else //por descarte <= y por precondición: Sí o si es uno de ellos.
    {
      while (it.hasNext())
      {
        aux = it.next();
        if (aux.haceMateria(materia))
        {
          double auxNota = aux.valorNota(materia);
          if (auxNota <= nota) //verifico si el alumno cursa la materia y agrega a la lista si la nota es igual
            listaDeAlumnos.add(aux);
        }
      }
    }
    return listaDeAlumnos;
  }


  /**
   * Este metodo verifica que el ID de el alumno exista en el almacen
   *
   * <b>Pre:</b><br>
   * ID != null<br>
   * ID != "". <br><br>
   *
   * <b>Post:</b><br>
   * Devuelve si el ID de alumno ingresado ya estaba en la coleccion de alumnos o no.
   *
   * @param ID identificador de un alumno
   * @return devuelve si el ID de alumno ingresado es valido o no
   */
  public boolean IDExists(String ID)
  {
    assert ID != null: "El ID ingresado es null";
    assert !ID.equals(""): "El ID ingresado es String vacia";
    verificarInvariante();
    //--------------------------------------------------------------

    return alumnos.containsKey(ID);
  }

  /**
   * Agrega un alumno a el HashMap de alumnos.
   *
   * <b>Pre:</b><br>
   * alumno != null<br>
   * El alumno no estaba agregado previamente en la coleccion. <br><br>
   *
   * <b>Post:</b><br> Agrega el alumno al HashMap de alumnos.
   *
   * @param alumno instancia de alumno a agregar al HashMap de alumnos
   */
  public void agregarAlumno(Alumno alumno)
  {
    assert alumno != null: "El alumno que se quiere insertar es null";
    verificarInvariante();

    this.alumnos.put(alumno.getID(), alumno);
  }

  /**
   * Verifica que se cumplan los invariantes de clase
   */
  private void verificarInvariante()
  {
    assert this.alumnos != null: "El HashMap de alumnos es null";
    assert this.filename != null: "El filename es null";
    assert !this.filename.equals(""): "El filename es String vacia";
  }

}
