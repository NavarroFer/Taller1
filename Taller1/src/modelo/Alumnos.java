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
}
