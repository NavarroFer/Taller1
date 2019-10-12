package controlador;

import exceptions.ParsingException;

import java.io.File;


import java.util.ArrayList;

import modelo.Alumno;
import modelo.Sistema;

import vista.IVista;
import vista.Ventana;

public abstract class Parser
{
    private final static String INSTRUCCION_CREAR = "CREAR";
    private final static String INSTRUCCION_CARGAR = "CARGAR";
    private final static String INSTRUCCION_GUARDAR = "GUARDAR";
    private final static String INSTRUCCION_INSERTAR = "INSERTAR";
    private final static String INSTRUCCION_ELIMINAR = "ELIMINAR";
    private final static String INSTRUCCION_CONSULTAR = "CONSULTAR";
    
    private final static String ERROR_000 = "Error 000: Comando mal formado";
    private final static String ERROR_001 = "Error 001: Operacion no conocida"; 
    private final static String ERROR_002 = "Error 002: Consulta mal construida";
    private final static String ERROR_003 = "Error 003: Base de datos inexistente";
    private final static String ERROR_004 = "Error 004: Alumno duplicado";
    private final static String ERROR_005 = "Error 005: Archivo inexistente";
    private final static String ERROR_006 = "Error 006: Operacion no realizable";
    private final static String ERROR_007 = "Error 007: Dato inexistente";
    
    private final static int CREAR_EXPECTED_LENGTH = 2;    
    private final static int CARGAR_EXPECTED_LENGTH = 2;    
    private final static int GUARDAR_EXPECTED_LENGTH = 1;    
    private final static int INSERTAR_EXPECTED_LENGTH = 2;    
    private final static int ELIMINAR_EXPECTED_LENGTH = 2;    
    private final static int CONSULTAR_EXPECTED_LENGTH = 4;   
    private final static int CONSULTAR_EXPECTED_LENGTH_WITH_TOFILE = 6;
    
    private static IVista vista = null;
    
    
    /**
     * @author Nahuel<br>
     * @param raw_command: Input del usuario tal y como fue ingresado.<br><br>
     * 
     * <b>Nota al lector:</b> El parser es la <i>capa de negocios</i> de este sistema. Recibe el input del usuario y 
     * en función del comando que se desea ejecutar verifica que se cumplan todas las precondiciones para hacer
     * la correspondiente llamada al modelo.<br><br>
     * 
     * <b>Pre:</b><br>
     * No hay precondiciones.<br>
     * 
     * <b>Post:</b><br>
     * Ejecutará, de ser posible, el comando solicitado por el usuario, y enviará, en caso de ejecución satisfactoria, un mensaje de confirmación.<br>
     * De no haber podido realizar lo anterior, lanzará una excepción con mensaje de error.<br>
     * Nota: Todas las instrucciones y la mayoría de los parámetros no son case sensitive. Para saber exactamente cuales son case 
     * sensitive y cuales no, remitirse a la documentación de cada método en el modelo. En caso de no aclararse se asume 
     * que no es case sensitive.<br><br>
     * 
     * <b>Inv:</b><br>
     * raw_command<br>
     * 
     * @throws ParsingException
     * Dado que la única información relevante de las excepciones arrojadas por este método es simplemente su mensaje 
     * (código de error y descripción), las excepciones arrojadas por este método son todas de la misma clase "ParsingException". 
     * Utilizar <u><a href="../exceptions/ParsingException.html#getErrorMessage--">getErrorMessage()</a></u> si desea obtener el mensaje de error.<br><br>
     * 
     * <b>Códigos de error: (No coinciden con los códigos de error del SRS, debieron ser cambiados en tiempo de diseño
     * pues el SRS era inconsistente)</b><br>
     * <b>Error 000:</b> Comando mal formado<br>
     * <b>Error 001:</b> Operacion no conocida<br>
     * <b>Error 002:</b> Consulta mal construida<br>
     * <b>Error 003:</b> Base de datos inexistente<br>
     * <b>Error 004:</b> Alumno duplicado<br>
     * <b>Error 005:</b> Archivo inexistente<br>
     * <b>Error 006:</b> Operacion no realizable<br>
     * <b>Error 007:</b> Dato inexistente<br>
     * <br>
     * <p style="font-size:8px">Si usted considera que esta documentación está incompleta comuníquese al 0223 481-6600 y pregunte por El Dema</p>
     */
    public static void parse(String raw_command) throws ParsingException
    {
        // Lo bochamos si es una string nula. (En nuestra vista es imposible que pase pero nos independizamos de la vista)
        if(raw_command == null)
            throw new ParsingException(ERROR_000 + " (Se ha introducido un comando vacio)");
        if(raw_command == "")
            throw new ParsingException(ERROR_000 + " (Se ha introducido un comando vacio)");
        
        String split_command[] = raw_command.toUpperCase().split(" "); // Pasamos el comando a uppercase (para que no sea case sensitive) y separamos donde encuentre espacios
        String instruccion = split_command[0];
        
        // ======================= CREAR =======================
        if(instruccion.equals(INSTRUCCION_CREAR))
        {
            parseCrear(split_command); //Las excepciones arrojadas por este método son propagadas
        }
        // ======================= CARGAR =======================
        else if(instruccion.equals(INSTRUCCION_CARGAR))
        {
            parseCargar(split_command); //Las excepciones arrojadas por este método son propagadas
        }
        // ======================= GUARDAR =======================
        else if(instruccion.equals(INSTRUCCION_GUARDAR))
        {
            parseGuardar(split_command); //Las excepciones arrojadas por este método son propagadas
        }     
        // ======================= INSERTAR =======================
        else if(instruccion.equals(INSTRUCCION_INSERTAR))
        {
            parseInsertar(split_command); //Las excepciones arrojadas por este método son propagadas    
        }
        // ======================= ELIMINAR =======================  
        else if(instruccion.equals(INSTRUCCION_ELIMINAR))
        {
            parseEliminar(split_command); //Las excepciones arrojadas por este método son propagadas
        }
        // ======================= CONSULTAR ======================
        else if(instruccion.equals(INSTRUCCION_CONSULTAR)) 
        {
            parseConsultar(split_command, raw_command); //Las excepciones arrojadas por este método son propagadas
        }                                                    
        else
            throw new ParsingException(ERROR_001 + " (No se reconoce la instruccion \""+instruccion+"\")");
    }

    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseCrear(String split_command[]) throws ParsingException
    {
        if(split_command.length < CREAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > CREAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        String filename = split_command[1];
        if(fileExists(filename))
            throw new ParsingException(ERROR_006 + " (Ya existe el archivo)");
        
        Sistema.getInstance().crear(filename);
        vista.imprimirEnConsola("Almacen "+filename+" creado correctamente");
    }
    
    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseCargar(String split_command[]) throws ParsingException
    {
        if(split_command.length < CARGAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > CARGAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        String filename = split_command[1];
        if(!fileExists(filename))
            throw new ParsingException(ERROR_006 + " (No existe el archivo)");
        try
        {
            Sistema.getInstance().cargar(filename);
        }
        catch(ClassCastException e)
        {
            throw new ParsingException(ERROR_006 + " (El archivo existe, pero no contiene un almacen)");
        }
        vista.imprimirEnConsola("Almacen "+filename+" cargado correctamente");
    }
    
    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseGuardar(String split_command[]) throws ParsingException
    {
        if(split_command.length > GUARDAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        if(!Sistema.getInstance().tieneAlmacenCargado())
            throw new ParsingException(ERROR_006 + " (No hay almacen cargado)");
        Sistema.getInstance().guardar();
        vista.imprimirEnConsola("Almacen guardado correctamente");
    }
    
    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseInsertar(String split_command[]) throws ParsingException
    {
        if(split_command.length < INSERTAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > INSERTAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        if(!fileExists(split_command[1]))
            throw new ParsingException(ERROR_006 + " (No existe el archivo)");
        if(Sistema.getInstance().alumnoExiste(split_command[1]))
            throw new ParsingException(ERROR_004);
        Sistema.getInstance().insertar(split_command[1]);
        vista.imprimirEnConsola("Alumno "+split_command[1]+" insertado correctamente");    
    }
         
    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseEliminar(String split_command[]) throws ParsingException
    {
        if(split_command.length < ELIMINAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > ELIMINAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        if(!Sistema.getInstance().alumnoExiste(split_command[1]))
            throw new ParsingException(ERROR_005 + " (No existe el alumno)");
        Sistema.getInstance().eliminarAlumno(split_command[1]);
        vista.imprimirEnConsola("Alumno "+split_command[1]+" eliminado correctamente");
    }
    
    /**
     * @author Nahuel
     * Método creado únicamente para modularizar el método parse. No se recomienda llamarlo por si solo.
     * @param split_command creado en "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseConsultar(String split_command[], String raw_command) throws ParsingException
    {
        if(!Sistema.getInstance().tieneAlmacenCargado())
            throw new ParsingException(ERROR_006 + " (No hay almacen cargado)");
        
        if((split_command.length!=CONSULTAR_EXPECTED_LENGTH)&&(split_command.length!=CONSULTAR_EXPECTED_LENGTH_WITH_TOFILE)) 
            throw new ParsingException(ERROR_000 + " (Cantidad invalida de argumentos)"); 
        
        String operador = split_command[2];
        if(!operador.equals("==") &&
           !operador.equals("!=") &&
           !operador.equals("<") &&
           !operador.equals(">") &&
           !operador.equals(">=") &&
           !operador.equals("<=")) //Ya sé que esto se ve un poco feo pero te juro que todos juntos era peor
            throw new ParsingException(ERROR_002 + " (Operador desconocido: " +split_command[2]+ ")");
        String materia = raw_command.split(" ")[1];
        Double nota;
        try
        {
            nota = Double.parseDouble(split_command[3]);
        }
        catch(Exception e)
        {
            throw new ParsingException(ERROR_002 + " (El tercer argumento no es numerico)");
        }
        
        if(split_command.length==CONSULTAR_EXPECTED_LENGTH)
        {
            ArrayList<Alumno> resultado = Sistema.getInstance().consultar(materia, split_command[2], nota);
            
            if(resultado.size()==0)
                vista.imprimirEnConsola("No se encontro ningun alumno que cumpla la condición");
            else
                vista.imprimirEnConsola(resultado.toString());
        }
        else
        {
            if(!split_command[4].toUpperCase().equals("TOFILE"))
                throw new ParsingException(ERROR_002 + " (Expected 'TOFILE' but found "+split_command[4].toUpperCase()+")");
            
            ArrayList<Alumno> resultado = Sistema.getInstance().consultarArch(materia, split_command[2], nota, split_command[5]);
            if(resultado.size()==0)
                vista.imprimirEnConsola("No se encontro ningun alumno que cumpla la condición");
            else
                vista.imprimirEnConsola(resultado.toString());
        }
    }


    /**
     * @author Nahuel
     *
     * @param nombre del archivo
     * @return true if exists
     */
    private static boolean fileExists(String filename)
    {
        File f = new File(filename);
        return f.exists();
    }

    /**
     * @author Mauri
     * @param v referencia a la ventana
     */
    public static void setVista(IVista v)
    {
        Parser.vista=v;
    }
}