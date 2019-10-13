package controlador;

import exceptions.ParsingException;

import java.awt.event.ActionListener;


import java.util.Observable;
import java.util.Observer;

import negocio.Parser;

import vista.IVista;

public class Controlador implements Observer{
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


    @Override
    public void update(Observable observable, Object object) {
        String comando = (String) object;
        
        if(observable.equals(clickEnviarComando))
        {
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
    
    public void addClickEnviarComandoObservable(Observable clickEnviarComando){
        this.clickEnviarComando = clickEnviarComando;
        clickEnviarComando.addObserver(this);
    }
}
