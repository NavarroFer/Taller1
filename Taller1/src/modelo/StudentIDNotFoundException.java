package modelo;

public class StudentIDNotFoundException
    extends Exception
{
    private String message;
    
    public StudentIDNotFoundException(String string)
    {
        this.message = string;
    }

    public String toString()
    {
        return message;
    }
}
