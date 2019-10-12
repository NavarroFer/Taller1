package controlador;

import exceptions.ParsingException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import negocio.Parser;

import vista.BotonEnviarComando;
import vista.IVista;

public class Controlador implements ActionListener{
    private static Controlador instance = null;
    private static IVista vista = null;
    
    public static final String COMMAND_ENVIAR_COMANDO = "enviar el comando que esta escrito";
    
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


    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getActionCommand().equals(Controlador.COMMAND_ENVIAR_COMANDO)){
            BotonEnviarComando button = (BotonEnviarComando) actionEvent.getSource();
            try {
                Parser.parse(button.getComando());
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
    
    public static void setVista(IVista view){
        vista = view;
    }
    
    
}
