package controlador;

import exceptions.ParsingException;


import negocio.Parser;

import vista.IVista;

/**
 * @author Juampi
 */
public class Controlador
{
    private static Controlador instance = null;
    private static IVista vista = null;


    private Controlador()
    {
        super();
    }


    /**
     * @return instancia del singleton<br>
     * Para poder ser action listener necesita estar instanciado
     *
     * @author Juampi
     */
    public static Controlador getInstance()
    {
        if (instance == null)
        {
            instance = new Controlador();
        }

        return instance;
    }


    /**
     * @param view Vista cualquiera que implemente la interfaz IVista
     */
    public static void setVista(IVista view)
    {
        vista = view;
    }


    /**
     * @param comando el comando ingresado por el usuario
     * @author Tosti (Ft Nawe)
     */
    public void comandoEnviado(String comando)
    {
        try
        {
            Parser.parse(comando);
        }
        catch (ParsingException exception)
        {
            vista.mostrarError(exception.getErrorMessage());
        }
        catch (Exception e)
        {
            vista.mostrarError("UNEXPECTED ERROR: " + e.getMessage());
        }
    }


}
