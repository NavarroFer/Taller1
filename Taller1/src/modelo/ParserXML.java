package modelo;

import java.util.ArrayList;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;

import java.util.NoSuchElementException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ParserXML {
   
    public ParserXML()
    {
        super();
    }
    
    private final  ArrayList<String> lista = new ArrayList<String>(); 
    private final  ArrayList<DatosAtributoXML> listaSalida = new ArrayList<DatosAtributoXML>();



    /**
     * <b>Pre:</b> inNodo != null.<br><br>
     * <b>Post:</b> si es nodo hoja el nodo agrega su valor a lista, si no es hoja hace que sus hijos sean parametrizados para invocaciones de leerNode.
     * 
     * @param InNodo Nodo cuyo valor va a ser agreagado a la lista o va a servir para invocar recursivamente con sus hijos.
     */
    public void leerNode(Node inNodo)
    {
      
        if (inNodo.hasChildNodes())
          {
              NodeList nList = inNodo.getChildNodes();
              for (int i=0; i<nList.getLength(); i++)
              {
                   leerNode(nList.item(i));
              } 
          }     
        else
           {
               lista.add(inNodo.getNodeValue());
           }   
    }

    /**
     *
     * <b>Pre:</b> LineaXML != null, donde cada elemento(campo) debe tener 3 atributos definidos nombre_atributo, tipo y valor.<br><br>
     * <b>Post:</b> si es nodo hoja el nodo agrega su valor a lista, si no es hoja hace que sus hijos sean parametrizados para invocaciones de leerNode.
     *
     * @param LineaXML String con formato XML en una sola linea
     *
     * @return listaSalida que es un ArrayList de que contiene variables cuyo formato incluye [{nombre_atributo}, {tipo}, {valor}]
     *
     * //@throws ParserConfigurationException si el DocumentBuilder no puede ser instanciado satisfaciendo la configuracion especificada, no debería ser arrojada ya que no se especifica la configuracion
     * @throws SAXException errores en el formato del XML que se desea parsear
     * @throws IOException interrupcion o fallo de la operacion de I/O en el parseo del ImputSource cuya base es la String LineaXML
     * @throws NoSuchElementException si el iterator no pudo leer en sets de 3 elementos por atributo [{nombre_atributo}, {tipo}, {valor}] tira esta excepcion
     */
    @SuppressWarnings("oracle.jdeveloper.java.insufficient-catch-block")
    public ArrayList<DatosAtributoXML> leoArgumento(String lineaXML) throws SAXException, IOException, NoSuchElementException
    {
        DocumentBuilder db = null;
        
        try {
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } 
        catch (ParserConfigurationException e) {
            //no debería ser arrojada ya que no se especifica la configuracion del DocumentBuilder
        }
        Document doc = db.parse(new InputSource(new StringReader(lineaXML)));
        doc.getDocumentElement().normalize();
        Node Nodo=doc.getFirstChild();
        leerNode(Nodo);
        
        
        for (Iterator<String> it = lista.iterator(); it.hasNext(); )
        {
            DatosAtributoXML datosAtribAux;
            datosAtribAux = new DatosAtributoXML(it.next(), it.next(), it.next()); //si no puede hacer 3 it.next() consecutivos esta mal armado el XML --> NoSuchElementException
            listaSalida.add(datosAtribAux);
        }
        
        
        return listaSalida;
    }

}
