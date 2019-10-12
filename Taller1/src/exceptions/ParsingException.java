package exceptions;

public class ParsingException
    extends Exception
{
    String error;

    /**
     * @author Nahuel
     * @param error String con la descripción del error. <br>
     * Idealmente debe respetar el formato: <b>Error XXX: DESCRIPCION_ERROR</b>
     */
    public ParsingException(String error)
    {
        this.error = error;
    }

    /**
     * @author Nahuel
     * @return Mensaje de error arrojado por el parser
     */
    public String getErrorMessage()
    {
        return error;
    }
}
