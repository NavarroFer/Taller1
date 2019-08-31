package modelo;

public class InstruccionDesconocidaException
    extends Exception
{
    private String message;
    
    public InstruccionDesconocidaException(String string)
    {
        message = string;
    }

    public String toString()
    {
        return message;
    }
}
