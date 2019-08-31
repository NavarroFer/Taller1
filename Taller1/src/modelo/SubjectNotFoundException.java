package modelo;

public class SubjectNotFoundException
    extends Exception
{
    private String message;
    
    public SubjectNotFoundException(String string)
    {
        this.message = string;
    }

    public String toString()
    {
        return message;
    }
}
