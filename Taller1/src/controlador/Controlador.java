package controlador;

import exceptions.ParsingException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.Observable;
import java.util.Observer;

import negocio.Parser;

import vista.IVista;

public class Controlador{
    private static Controlador instance = null;
    private static IVista vista = null;
    
    private Observable clickEnviarComando;
    
    
    private Controlador() {
        super();
    }


    /**
     * Para poder ser action listener necesita estar instanciado
     * @return
     */
    public static Controlador getInstance(){
        if(instance == null){
            instance = new Controlador();
        }
        
        return instance;
    }


    
    public static void setVista(IVista view){
        vista = view;
    }



    public void comandoEnviado(String comando) {
        try {
            Parser.parse(comando);
            
        } 
        catch (ParsingException exception)
        {
            vista.mostrarError("\n" + exception.getErrorMessage());
        }
        catch (Exception e)
        {
            vista.mostrarError("\n UNEXPECTED ERROR: " + e.getMessage());
        }
    }

    
}
