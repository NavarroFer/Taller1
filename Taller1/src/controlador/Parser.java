package controlador;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;

import modelo.Alumno;
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
        // Lo bochamos si es una string nula. (Técnicamente imposible?)
        if(raw_command == null)
            throw new ComandoMalFormadoException("Error 000: Se ha introducido un comando vacío");
        
        raw_command = raw_command.toUpperCase();
        String split_command[] = raw_command.split(" ");
        
        // ======================= CREAR =======================
        if(split_command[0].equals("CREAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(fileExists(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (Ya existe el archivo)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            
            //else: Ya pasó todos los filtros de la capa de negocios.
            Sistema.getInstance().crear(split_command[1]);
        }
        
        // ======================= CARGAR =======================
        else if(split_command[0].equals("CARGAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: Operación no realizable. (No existe el archivo)");
            Sistema.getInstance().cargar(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            Sistema.getInstance().cargar(split_command[1]);

        }
        
        // ======================= GUARDAR =======================
        else if(split_command[0].equals("GUARDAR"))
        {
            if(split_command.length > 1)
                throw new Exception("Error 000: Comando mal formado (No se esperaba un segundo argumento)");
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception("Error 004: Operación no realizable. (No hay almacén cargado)");
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            
            //else: Ya pasó todos los filtros de la capa de negocios.
            Sistema.getInstance().guardar();
        }     
        
        // ======================= INSERTAR =======================
        else if(split_command[0].equals("INSERTAR"))
        {
            // TODO
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: Operación no realizable. (No existe el archivo)");
            Sistema.getInstance().insertar(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
            
            
        }
        
        // ======================= ELIMINAR =======================  
        else if(split_command[0].equals("ELIMINAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(!Sistema.getInstance().alumnoExiste(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (No existe el alumno)");
            Sistema.getInstance().eliminarAlumno(split_command[1]);
            //if(split_command.length > 2) 
            //  throw new Exception("Error 002: Consulta mal construida. (Más argumentos de los necesarios para la operación)");
        }
        
        // ======================= CONSULTAR =======================
        else if(split_command[0].equals("CONSULTAR")) 
        {
            if((split_command.length!=3)&&(split_command.length!=5)) 
                throw new Exception("Error 000: Comando mal formado. (Cantidad invalida de argumentos)"); 
            if((split_command[1]!="==")||(split_command[1]!="!=")||(split_command[1]!="<")||(split_command[1]!=">")||(split_command[1]!=">=")||(split_command[1]!="<="))
                throw new Exception("Error 002: Operador desconocido)");
            try
            {
                Double nota = Double.parseDouble(split_command[2]);
                if(split_command.length==3)
                {
                    Sistema.getInstance().listaDeAlumnos(split_command[0], split_command[1], nota);
                }
                else
                {
                    if(split_command[4]!="TOFILE")
                        throw new Exception("Error 002: Consulta mal construida (No se encuentra la palabra reservada 'toFile')");
                    try
                    { 
                        Sistema.getInstance().listaDeAlumnosArch(split_command[0], split_command[1], nota, split_command[5]);
                    }
                    catch(FileNotFoundException e) 
                    {
                        throw new Exception("Error 002: Consulta mal construida (Hay un problema con el nombre del archivo)");    
                    }
                }
            }
            catch(NumberFormatException e)
            {
                throw new Exception("Error 002: Consulta mal construida. (El tercer argumento no es numerico)");
            }
                                   
        }
                                                            
                                                            
        else
            throw new InstruccionDesconocidaException("Error 001: No se reconoce la instrucción \""+split_command[0]+"\"");
    }
            
    private static boolean fileExists(String filename)
    {
        File f = new File(filename);
        boolean resultado = f.exists();
        return resultado;
    }
    
}
