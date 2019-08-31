package modelo;

public class StudentIDNotFoundException
    extends Exception
{
    String ID;
    
    public void SubjectNotFoundException(String string)
    {
        this.ID = string;
    }

    public String getName()
    {
        return ID;
    }
}
