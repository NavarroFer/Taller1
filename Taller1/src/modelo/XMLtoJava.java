package modelo;
import java.util.List;

public class XMLtoJava 
{
    public XMLtoJava() {
        super();
    }    
    
    public static void main(String[] args) throws Exception
          {
              String LineaXML ="<Alumno>" +
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
              ParserXML p=new ParserXML();
              String[][] Lista=p.leoArgumento(LineaXML);
              for (int i=0; i<Lista.length; i++)
              {    
                    System.out.print(Lista[i][0]);
                    System.out.print("/ ");
                    System.out.print(Lista[i][1]);
                    System.out.print("/ ");
                    System.out.println(Lista[i][2]); 
                  }
          }
}