package controlador;

import java.io.File;


import java.util.ArrayList;

import modelo.Sistema;

import vista.Ventana;

public abstract class Parser
{
    /*     private static ArrayList<String> COMMANDS = new ArrayList<String>(Arrays.asList("CREAR",
                                                                                    "CARGAR",
                                                                                    "GUARDAR",
                                                                                    "INSERTAR",
                                                                                    "ELIMINAR",
                                                                                    "CONSULTAR")); */
    private final static String INSTRUCCION_CREAR = "CREAR";
    private final static String INSTRUCCION_CARGAR = "CARGAR";
    private final static String INSTRUCCION_GUARDAR = "GUARDAR";
    private final static String INSTRUCCION_INSERTAR = "INSERTAR";
    private final static String INSTRUCCION_ELIMINAR = "ELIMINAR";
    private final static String INSTRUCCION_CONSULTAR = "CONSULTAR";
    
    private final static String ERROR_000 = "Error 000: Comando mal formado";
    private final static String ERROR_001 = "Error 001: Operacion no conocida"; 
    private final static String ERROR_002 = "Error 002: Consulta mal construida";
    private final static String ERROR_003 = "Error 003: Base de datos inexistente ";
    private final static String ERROR_004 = "Error 004: Alumno duplicado";
    private final static String ERROR_005 = "Error 005: Archivo inexistente";
    private final static String ERROR_006 = "Error 006: Operacion no realizable";
    private final static String ERROR_007 = "Error 007: Dato inexistente";
    
    private static Ventana ventana = null;
    
    
    /**
     * Pre: 
     * @param raw_command: Raw input from user
     * 
     * Post:
     * @throws ComandoMalFormadoException: Wrong syntax
     * @throws InstruccionDesconocidaException: non-existant instruction
     * 
     */
    public static void parse(String raw_command) throws Exception
    {
        // Lo bochamos si es una string nula. (Tecnicamente imposible?)
        if(raw_command == null)
            throw new Exception(ERROR_000 + " (Se ha introducido un comando vacio)");
        if(raw_command == "")
            throw new Exception(ERROR_000 + " (Se ha introducido un comando vacio)");
        
        String split_command[] = raw_command.toUpperCase().split(" ");
        
        // ======================= CREAR =======================
        if(split_command[0].equals(INSTRUCCION_CREAR))
        {
            if(split_command.length < 2)
                throw new Exception(ERROR_000 + " (Falta segundo argumento)");
            if(fileExists(split_command[1]))
                throw new Exception(ERROR_006 + " (Ya existe el archivo)");
            
            Sistema.getInstance().crear(split_command[1]);
            ventana.imprimirEnConsola("Almacen "+split_command[1]+" creado correctamente");
        }
        
        // ======================= CARGAR =======================
        else if(split_command[0].equals(INSTRUCCION_CARGAR))
        {
            if(split_command.length < 2)
                throw new Exception(ERROR_000 + " (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
            if(!fileExists(split_command[1]))
                throw new Exception(ERROR_006 + " (No existe el archivo)");
            try
            {
                Sistema.getInstance().cargar(split_command[1]);
            }
            catch(ClassCastException e)
            {
                throw new Exception(ERROR_006 + " (El archivo existe, pero no contiene un almacen)");
            }
            ventana.imprimirEnConsola("Almacen "+split_command[1]+" cargado correctamente");
        }
        
        // ======================= GUARDAR =======================
        else if(split_command[0].equals(INSTRUCCION_GUARDAR))
        {
            if(split_command.length > 1)
                throw new Exception(ERROR_000 + " (No se esperaba un segundo argumento)");
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception(ERROR_006 + " (No hay almacen cargado)");
            Sistema.getInstance().guardar();
            ventana.imprimirEnConsola("Almacen guardado correctamente");
        }     
        
        // ======================= INSERTAR =======================
        else if(split_command[0].equals(INSTRUCCION_INSERTAR))
        {
            // TODO
            if(split_command.length < 2)
                throw new Exception(ERROR_000 + " (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
            if(!fileExists(split_command[1]))
                throw new Exception(ERROR_006 + " (No existe el archivo)");
            if(Sistema.getInstance().alumnoExiste(split_command[1]))
                throw new Exception(ERROR_004);
            Sistema.getInstance().insertar(split_command[1]);
            ventana.imprimirEnConsola("Alumno "+split_command[1]+" insertado correctamente");            
        }
        
        // ======================= ELIMINAR =======================  
        else if(split_command[0].equals(INSTRUCCION_ELIMINAR))
        {
            if(split_command.length < 2)
                throw new Exception(ERROR_000 + " (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception(ERROR_000 + " (Mas argumentos de los necesarios para la operacion)");
            if(!Sistema.getInstance().alumnoExiste(split_command[1]))
                throw new Exception(ERROR_005 + " (No existe el alumno)");
            Sistema.getInstance().eliminarAlumno(split_command[1]);
            ventana.imprimirEnConsola("Alumno "+split_command[1]+" eliminado correctamente");
        }
        
        // ======================= CONSULTAR =======================
        else if(split_command[0].equals(INSTRUCCION_CONSULTAR)) 
        {
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception(ERROR_006 + " (No hay almacen cargado)");
            
            if((split_command.length!=4)&&(split_command.length!=6)) 
                throw new Exception(ERROR_000 + " (Cantidad invalida de argumentos)"); 
                
            if(!split_command[2].equals("==") &&
               !split_command[2].equals("!=") &&
               !split_command[2].equals("<") &&
               !split_command[2].equals(">") &&
               !split_command[2].equals(">=") &&
               !split_command[2].equals("<=")
                )
                throw new Exception(ERROR_002 + " (Operador desconocido: " +split_command[2]+ ")");
            String materia = raw_command.split(" ")[1];
            Double nota;
            try
            {
                nota = Double.parseDouble(split_command[3]);
            }
            catch(Exception e)
            {
                throw new Exception(ERROR_002 + " (El tercer argumento no es numerico)");
            }
            
            if(split_command.length==4)
            {
                System.out.println(Sistema.getInstance().listaDeAlumnos(materia, split_command[2], nota));
                ventana.imprimirEnConsola(Sistema.getInstance().listaDeAlumnos(materia, split_command[2], nota).toString());
            }
            else
            {
                if(split_command[4].toUpperCase().equals("TOFILE"))
                    throw new Exception(ERROR_002 + " (No se encuentra la palabra reservada 'toFile')");
                ventana.imprimirEnConsola(Sistema.getInstance().listaDeAlumnosArch(materia, split_command[2], nota, split_command[5]).toString());
            }               
                                       
        }                                                    
        else
            throw new Exception(ERROR_001 + " (No se reconoce la instruccion \""+split_command[0]+"\")");
    }
            
    private static boolean fileExists(String filename)
    {
        File f = new File(filename);
        return f.exists();
    }

    public static void setVentana(Ventana v)
    {
        Parser.ventana=v;
    }
}