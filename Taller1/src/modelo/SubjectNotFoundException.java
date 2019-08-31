package modelo;

public class SubjectNotFoundException
    extends Exception
{
    String name;
    
    public SubjectNotFoundException(String string)
    {
        this.name = string;
    }

    public String getName()
    {
        return name;
    }
}
