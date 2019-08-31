package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XMLtoJava //TODO una vez que este bien implementado lo de parsear XML, volar esta clase
{
    public XMLtoJava() {
        super();
    }    
    
    public static void main(String[] args) throws Exception
          {
              String LineaXML ="<Alumno>" +  //ejemplo. Asi hay que levantarlo de archivo
                                    "<Campo>" +
                                        "<Nombre>Id</Nombre>" +
                                        "<Tipo>String</Tipo>" +
                                        "<Valor>0001</Valor>" +
                                    "</Campo>" +
                                    "<Campo>" +
                                        "<Nombre>Fecha_de_Nacimiento</Nombre>" +
                                        "<Tipo>String</Tipo>" +
                                        "<Valor>01-01-1990</Valor>" +
                                    "</Campo>" +
                                    "<Campo>" +
                                        "<Nombre>Apellido_y_Nombre</Nombre>" +
                                        "<Tipo>String</Tipo>" +
                                        "<Valor>Juan Perez</Valor>" +
                                    "</Campo>" +
                                    "<Campo>" +
                                        "<Nombre>Domicilio</Nombre>" +
                                        "<Tipo>String</Tipo>" +
                                        "<Valor>Reconquista 5004</Valor>" +
                                    "</Campo>" +
                                    "<Campo>" +
                                        "<Nombre>Carrera</Nombre>" +
                                        "<Tipo>String</Tipo>" +
                                        "<Valor>Licenciatura en Física</Valor>" +
                                    "</Campo>" + 
                                    "<Campo>" +
                                        "<Nombre>Materias</Nombre>" +
                                        "<Tipo>ArrayList</Tipo>" +
                                        "<Value>2</Value>" +
                                            "<Valores_ArrayList>" +
                                                "<Materia>" +
                                                    "<Campo>" +
                                                        "<Nombre>Nombre_Materia</Nombre>" +
                                                        "<Tipo>String</Tipo>" +
                                                        "<Valores>Algebra A</Valores>" +
                                                    "</Campo>" +
                                                    "<Campo>" +
                                                        "<Nombre>Nota</Nombre>" +
                                                        "<Tipo>Double</Tipo>" +
                                                        "<Valor>6.5</Valor>" +
                                                    "</Campo>" +
                                                "</Materia>" +
                                                "<Materia>" +
                                                    "<Campo>" +
                                                        "<Nombre>Nombre_Materia</Nombre>" +
                                                        "<Tipo>String</Tipo>" +
                                                        "<Valores>Algebra B</Valores>" +
                                                    "</Campo>" +
                                                    "<Campo>" +
                                                        "<Nombre>Nota</Nombre>" +
                                                        "<Tipo>Double</Tipo>" +
                                                        "<Valor>7.5</Valor>" +
                                                    "</Campo>" +
                                                "</Materia>" +
                                            "</Valores_ArrayList>" +
                                        "</Campo>" +
                                    "</Alumno>";
              
              ArrayList<DatosAtributoXML> lista= ParserXML.leoArgumento(LineaXML);
              for (Iterator<DatosAtributoXML> it = lista.iterator(); it.hasNext(); )
              {    
                    System.out.println(it.next()); 
              }
          }
}