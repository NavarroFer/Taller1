package modelo;

public class ComandoMalFormadoException
    extends Exception
{
    private String message;
    
    public ComandoMalFormadoException(String string)
    {
        message = string;
    }
    
    public String toString()
    {
        return message;
    }
}
