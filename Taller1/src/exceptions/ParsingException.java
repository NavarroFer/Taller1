package exceptions;

public class ParsingException
    extends Exception
{
    String error;
    
    public ParsingException(String error)
    {
        this.error = error;
    }

    public String getErrorMessage()
    {
        return error;
    }
}
