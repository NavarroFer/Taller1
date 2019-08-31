package modelo;

import java.util.ArrayList;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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
    
    private final  ArrayList<String> Lista = new ArrayList(); 
    private final  String[][]ListaSalida = new String[20][3];
        
       public void leerNode(Node InNodo)
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
                       Lista.add(InNodo.getNodeValue());
                   }   
               
           }
       
       public String[][] leoArgumento(String LineaXML) throws ParserConfigurationException, SAXException, IOException
       {
           DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           Document doc = db.parse(new InputSource(new StringReader(LineaXML)));
           doc.getDocumentElement().normalize();
           Node Nodo=doc.getFirstChild();
           leerNode(Nodo);
           int k=0;
           for (int i=0; i<Lista.size(); i=i+3) 
           {
                for (int j=i; j<i+3; j++)
                   {
                        ListaSalida[k][j-i]=Lista.get(j);
                   }
               k++;
           }    
           return ListaSalida;
        }
    
}
