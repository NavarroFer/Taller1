package controlador;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.ComandoMalFormadoException;
import modelo.InstruccionDesconocidaException;
import modelo.Sistema;

public abstract class Parser
{
    /*     private static ArrayList<String> COMMANDS = new ArrayList<String>(Arrays.asList("CREAR",
                                                                                    "CARGAR",
                                                                                    "GUARDAR",
                                                                                    "INSERTAR",
                                                                                    "ELIMINAR",
                                                                                    "CONSULTAR")); */


    /**
     * Pre: 
     * @param raw_command: Raw input from user
     * 
     * Post:
     * @throws ComandoMalFormadoException: Wrong syntax
     * @throws InstruccionDesconocidaException: non-existant instruction
     * 
     */
    public static void parse(String raw_command) throws ComandoMalFormadoException, InstruccionDesconocidaException,
                                                        Exception
    {
        // Lo bochamos si es una string nula. (T�cnicamente imposible?)
        if(raw_command == null)
            throw new ComandoMalFormadoException("Error 000: Se ha introducido un comando vac�o");
        
        raw_command = raw_command.toUpperCase();
        String split_command[] = raw_command.split(" ");
        
        // ======================= CREAR =======================
        if(split_command[0].equals("CREAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(fileExists(split_command[1]))
                throw new Exception("Error 004: Operaci�n no realizable. (Ya existe el archivo)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (M�s argumentos de los necesarios para la operaci�n)");
            
            //else: Ya pas� todos los filtros de la capa de negocios.
            Sistema.getInstance().crear(split_command[1]);
        }
        
        // ======================= CARGAR =======================
        else if(split_command[0].equals("CARGAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: Operaci�n no realizable. (No existe el archivo)");
            Sistema.getInstance().cargar(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (M�s argumentos de los necesarios para la operaci�n)");
            Sistema.getInstance().cargar(split_command[1]);

        }
        
        // ======================= GUARDAR =======================
        else if(split_command[0].equals("GUARDAR"))
        {
            if(split_command.length > 1)
                throw new Exception("Error 000: Comando mal formado (No se esperaba un segundo argumento)");
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception("Error 004: Operaci�n no realizable. (No hay almac�n cargado)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (M�s argumentos de los necesarios para la operaci�n)");
            
            //else: Ya pas� todos los filtros de la capa de negocios.
            Sistema.getInstance().guardar();
        }     
        
        // ======================= INSERTAR =======================
        else if(split_command[0].equals("INSERTAR"))
        {
            // TODO
        }
        
        // ======================= ELIMINAR =======================  
        else if(split_command[0].equals("ELIMINAR"))
        {
            if(!Sistema.getInstance().alumnoExiste(split_command[1]))
                throw new Exception("Error 004: Operaci�n no realizable. (No existe el alumno)");
            Sistema.getInstance().eliminarAlumno(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (M�s argumentos de los necesarios para la operaci�n)");
        }
        
        // ======================= CONSULTAR =======================
                
                
                
                
        else
            throw new InstruccionDesconocidaException("Error 001: No se reconoce la instrucci�n \""+split_command[0]+"\"");
    }
            
    private static boolean fileExists(String filename)
    {
        return true; //TODO!!!!!!!!!!!!
    }
    
}
