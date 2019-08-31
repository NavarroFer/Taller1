package modelo;

import java.util.HashMap;

public class Alumnos
{
    String  ID,
            fecha_de_nacimiento,
            apellido_y_nombre,
            domicilio,
            carrera;
    HashMap<String,Double> materias;
    
    public Alumnos()
    {
        super();
    }
    
    double valorNota(String Nombre_Materia)
    {
        return materias.get(Nombre_Materia);
    }


  public String getApellido_y_nombre()
  {
    return apellido_y_nombre;
  }
  
    public String getID()
    {
        String ID = this.ID; // lol
        return ID;
    }
}
