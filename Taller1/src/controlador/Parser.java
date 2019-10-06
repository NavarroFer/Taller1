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
     * @author Nawe
     * Pre: 
     * @param raw_command: Input del usuario tal y como fue ingresada
     * 
     * Post:
     * Ejecutar�, de ser posible, el comando solicitado por el usuario.
     * Enviar�, en caso de ejecuci�n satisfactoria, un mensaje de confirmaci�n; y caso contrario una excepci�n con mensaje de error.
     * Nota: Todas las instrucciones y la mayor�a de los par�metros no son case sensitive. Para saber exactamente cuales son case sensitive y cuales no, remitirse a la documentaci�n del modelo. En caso de no aclararse se asume que no es case sensitive.
     * 
     * @throws ParsingException
     * Dado que la �nica informaci�n pertinente de las excepciones arrojadas por este m�todo es el contenido de su mensaje (c�digo de error y descripci�n), 
     * las excepciones arrojadas por este m�todo son todas de la misma clase "ParsingException". Utilizar getErrorMessage() para obtener el mensaje de error.
     * 
     * C�digos de error:
     * Error 000: Comando mal formado
     * Error 001: Operacion no conocida
     * Error 002: Consulta mal construida
     * Error 003: Base de datos inexistente
     * Error 004: Alumno duplicado
     * Error 005: Archivo inexistente
     * Error 006: Operacion no realizable
     * Error 007: Dato inexistente
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
            parseCrear(split_command); //Las excepciones arrojadas por este m�todo son propagadas
        }
        // ======================= CARGAR =======================
        else if(instruccion.equals(INSTRUCCION_CARGAR))
        {
            parseCargar(split_command); //Las excepciones arrojadas por este m�todo son propagadas
        }
        // ======================= GUARDAR =======================
        else if(instruccion.equals(INSTRUCCION_GUARDAR))
        {
            parseGuardar(split_command); //Las excepciones arrojadas por este m�todo son propagadas
        }     
        // ======================= INSERTAR =======================
        else if(instruccion.equals(INSTRUCCION_INSERTAR))
        {
            parseInsertar(split_command); //Las excepciones arrojadas por este m�todo son propagadas    
        }
        // ======================= ELIMINAR =======================  
        else if(instruccion.equals(INSTRUCCION_ELIMINAR))
        {
            parseEliminar(split_command); //Las excepciones arrojadas por este m�todo son propagadas
        }
        // ======================= CONSULTAR =======================
        else if(instruccion.equals(INSTRUCCION_CONSULTAR)) 
        {
            parseConsultar(split_command, raw_command); //Las excepciones arrojadas por este m�todo son propagadas
        }                                                    
        else
            throw new ParsingException(ERROR_001 + " (No se reconoce la instruccion \""+instruccion+"\")");
    }




    /**
     * M�todo creado �nicamente para modularizar el m�todo parse.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseCrear(String split_command[]) throws ParsingException
    {
        if(split_command.length < CREAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > CREAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        if(fileExists(split_command[1]))
            throw new ParsingException(ERROR_006 + " (Ya existe el archivo)");
        
        Sistema.getInstance().crear(split_command[1]);
        vista.imprimirEnConsola("Almacen "+split_command[1]+" creado correctamente");
    }
    
    /**
     * M�todo creado �nicamente para modularizar el m�todo parse.
     * @param split_command idem "parse"
     * @throws ParsingException idem "parse"
     */
    private static void parseCargar(String split_command[]) throws ParsingException
    {
        if(split_command.length < CARGAR_EXPECTED_LENGTH)
            throw new ParsingException(ERROR_000 + " (Falta segundo argumento)");
        if(split_command.length > CARGAR_EXPECTED_LENGTH) 
            throw new ParsingException(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
        if(!fileExists(split_command[1]))
            throw new ParsingException(ERROR_006 + " (No existe el archivo)");
        try
        {
            Sistema.getInstance().cargar(split_command[1]);
        }
        catch(ClassCastException e)
        {
            throw new ParsingException(ERROR_006 + " (El archivo existe, pero no contiene un almacen)");
        }
        vista.imprimirEnConsola("Almacen "+split_command[1]+" cargado correctamente");
    }
    
    /**
     * M�todo creado �nicamente para modularizar el m�todo parse.
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
     * M�todo creado �nicamente para modularizar el m�todo parse.
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
     * M�todo creado �nicamente para modularizar el m�todo parse.
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
     * @author Nacho
     * M�todo creado �nicamente para modularizar el m�todo parse.
     * @param split_command idem "parse"
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
           !operador.equals("<=")) //Ya s� que esto se ve un poco feo pero te juro que todos juntos era peor
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
            ArrayList<Alumno> resultado = Sistema.getInstance().listaDeAlumnos(materia, split_command[2], nota);
            
            if(resultado.size()==0)
                vista.imprimirEnConsola("No se encontro ningun alumno que cumpla la condici�n");
            else
                vista.imprimirEnConsola(resultado.toString());
        }
        else
        {
            if(!split_command[4].toUpperCase().equals("TOFILE"))
                throw new ParsingException(ERROR_002 + " (Expected 'TOFILE' but found "+split_command[4].toUpperCase()+")");
            vista.imprimirEnConsola(Sistema.getInstance().listaDeAlumnosArch(materia, split_command[2], nota, split_command[5]).toString());
        }
    }




    private static boolean fileExists(String filename)
    {
        File f = new File(filename);
        return f.exists();
    }

    public static void setVentana(Ventana v)
    {
        Parser.vista=v;
    }
}