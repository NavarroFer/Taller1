package controlador;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.ComandoMalFormadoException;
import modelo.InstruccionDesconocidaException;

public abstract class Parser
{
    private static ArrayList<String> COMMANDS = new ArrayList<String>(Arrays.asList("CREAR",
                                                                                    "CARGAR",
                                                                                    "GUARDAR",
                                                                                    "INSERTAR",
                                                                                    "ELIMINAR",
                                                                                    "CONSULTAR"));


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
        // Lo bochamos si es una string nula. (Técnicamente imposible?)
        if(raw_command == null)
            throw new ComandoMalFormadoException("Error 000: Se ha introducido un comando vacío");
        
        raw_command = raw_command.toUpperCase();
        String split_command[] = raw_command.split(" ");
        
        if(!COMMANDS.contains(split_command[0]))
            throw new InstruccionDesconocidaException("Error 001: No se reconoce la instrucción \""+split_command[0]+"\"");
        
        // ======================= CREAR =======================
        if(split_command[0] == "CREAR")
        {
            if(fileExists(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (Ya existe el archivo)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            
            //else: Ya pasó todos los filtros de la capa de negocios.
            Sistema.getInstance().crear(split_command[1]);
        }
        
        // ======================= CARGAR =======================
        else if(split_command[0] == "CARGAR")
        {
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: Operación no realizable. (No existe el archivo)");
            Sistema.getInstance().cargar(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            Sistema.getInstance().cargar(split_command[1]);

        }
        
        // ======================= GUARDAR =======================
        else if(split_command[0] == "GUARDAR")
        {
            if(Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception("Error 004: Operación no realizable. (Ya hay un almacén cargado)");
            if(fileExists(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (Ya existe el archivo)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            
            //else: Ya pasó todos los filtros de la capa de negocios.
            Sistema.getInstance().crear(split_command[1]);
        }     
        
        // ======================= INSERTAR =======================
        else if(split_command[0] == "INSERTAR")
        {
            // TODO
        }
        
        // ======================= ELIMINAR =======================  
        else if(split_command[0] == "ELIMINAR")
        {
            if(!alumnoExiste(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (No existe el alumno)");
            Sistema.eliminarAlumno(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
        }
        
        // ======================= CONSULTAR =======================
                
    }
            
    private static boolean fileExists(String filename)
    {
        return true; //TODO!!!!!!!!!!!!
    }
    
}
