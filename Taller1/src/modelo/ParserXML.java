package modelo;

import java.util.ArrayList;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public abstract class ParserXML {
   
    public ParserXML()
    {
        super();
    }
    
    private static final  ArrayList<String> lista = new ArrayList<String>(); 
    private static final  ArrayList<DatosAtributoXML>listaSalida = new ArrayList<DatosAtributoXML>(); //TODO cambiar este tipo de dato a un arraylist de String[3] para no tener problemas con la cantidad de materias
    private static int cantElemPars;


    public static int getCantElemPars() {
        return cantElemPars;
    }


    /**
     * <b>Pre:</b> inNodo != null.<br><br>
     * <b>Post:</b> si es nodo hoja el nodo agrega su valor a lista, si no es hoja hace que sus hijos sean parametrizados para invocaciones de leerNode.
     * 
     * @param InNodo Nodo cuyo valor va a ser agreagado a la lista o va a servir para invocar recursivamente con sus hijos.
     */
    public static void leerNode(Node InNodo)
    {
      
        if (InNodo.hasChildNodes())
          {
              NodeList nList = InNodo.getChildNodes();
              for (int i=0; i<nList.getLength(); i++)
              {
                   leerNode(nList.item(i));
              } 
          }     
        else
           {
               lista.add(InNodo.getNodeValue());
           }   
       
    }

    /**
     * 
     * <b>Pre:</b> LineaXML != null, donde cada elemento(campo) debe tener 3 atributos definidos nombre_atributo, tipo y valor.<br><br>
     * <b>Post:</b> si es nodo hoja el nodo agrega su valor a lista, si no es hoja hace que sus hijos sean parametrizados para invocaciones de leerNode.
     * 
     * @param LineaXML String con formato XML en una sola linea
     * @return listaSalida que es un ArrayList de que contiene variables cuyo formato incluye [{nombre_atributo}, {tipo}, {valor}]
     * @throws ParserConfigurationException TODO investigar para que sirven esas 3 exception
     * @throws SAXException
     * @throws IOException
     */
    public static ArrayList<DatosAtributoXML> leoArgumento(String LineaXML) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = db.parse(new InputSource(new StringReader(LineaXML)));
        doc.getDocumentElement().normalize();
        Node Nodo=doc.getFirstChild();
        leerNode(Nodo);
        
        for (Iterator<String> it = lista.iterator(); it.hasNext(); )
        {
            DatosAtributoXML datosAtribAux;
            datosAtribAux = new DatosAtributoXML(it.next(), it.next(), it.next());
            listaSalida.add(datosAtribAux);
        }
        
        
        return listaSalida;
    }

}
