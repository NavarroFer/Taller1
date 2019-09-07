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
            throw new Exception("Error 000: Se ha introducido un comando vacío");
        if(raw_command == "")
            throw new Exception("Error 000: Se ha introducido un comando vacío");
        
        String split_command[] = raw_command.toUpperCase().split(" ");
        
        // ======================= CREAR =======================
        if(split_command[0].equals("CREAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(fileExists(split_command[1]))
                throw new Exception("Error 004: Operación no realizable. (Ya existe el archivo)");
            
            Sistema.getInstance().crear(split_command[1]);
            ventana.imprimirEnConsola("Almacen "+split_command[1]+" creado correctamente");
        }
        
        // ======================= CARGAR =======================
        else if(split_command[0].equals("CARGAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception("Error 002: Consulta mal construida. (Mas argumentos de los necesarios para la operacion)");
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: Operacion no realizable. (No existe el archivo)");
            try
            {
                Sistema.getInstance().cargar(split_command[1]);
            }
            catch(ClassCastException e)
            {
                throw new Exception("Error 004: Operacion no realizable. (El archivo existe, pero no contiene un almacen)");
            }
            ventana.imprimirEnConsola("Almacen "+split_command[1]+" cargado correctamente");
        }
        
        // ======================= GUARDAR =======================
        else if(split_command[0].equals("GUARDAR"))
        {
            if(split_command.length > 1)
                throw new Exception("Error 000: Comando mal formado (No se esperaba un segundo argumento)");
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception("Error 004: Operación no realizable. (No hay almacÃ©n cargado)");
            Sistema.getInstance().guardar();
            ventana.imprimirEnConsola("Almacen guardado correctamente");
        }     
        
        // ======================= INSERTAR =======================
        else if(split_command[0].equals("INSERTAR"))
        {
            // TODO
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception("Error 002: Consulta mal construida. (Mas argumentos de los necesarios para la operacion)");
            if(!fileExists(split_command[1]))
                throw new Exception("Error 003: OperaciÃ³n no realizable. (No existe el archivo)");
            Sistema.getInstance().insertar(split_command[1]);
            ventana.imprimirEnConsola("Alumno "+split_command[1]+" insertado correctamente");            
        }
        
        // ======================= ELIMINAR =======================  
        else if(split_command[0].equals("ELIMINAR"))
        {
            if(split_command.length < 2)
                throw new Exception("Error 000: Comando mal formado (Falta segundo argumento)");
            if(split_command.length > 2) 
                throw new Exception("Error 002: Consulta mal construida. (Mas argumentos de los necesarios para la operacion)");
            if(!Sistema.getInstance().alumnoExiste(split_command[1]))
                throw new Exception("Error 004: OperaciÃ³n no realizable. (No existe el alumno)");
            Sistema.getInstance().eliminarAlumno(split_command[1]);
            ventana.imprimirEnConsola("Alumno "+split_command[1]+" eliminado correctamente");
        }
        
        // ======================= CONSULTAR =======================
        else if(split_command[0].equals("CONSULTAR")) 
        {
            if(!Sistema.getInstance().tieneAlmacenCargado())
                throw new Exception("Error 004: Operación no realizable. (No hay almacen cargado)");
            
            if((split_command.length!=4)&&(split_command.length!=6)) 
                throw new Exception("Error 000: Comando mal formado. (Cantidad invalida de argumentos)"); 
                
            if(!split_command[2].equals("==") &&
               !split_command[2].equals("!=") &&
               !split_command[2].equals("<") &&
               !split_command[2].equals(">") &&
               !split_command[2].equals(">=") &&
               !split_command[2].equals("<=")
                )
                throw new Exception("Error 002: Operador desconocido (" +split_command[2]+ ")");
            String materia = raw_command.split(" ")[1];
            Double nota;
            try
            {
                nota = Double.parseDouble(split_command[3]);
            }
            catch(Exception e)
            {
                throw new Exception("Error 002: Consulta mal construida. (El tercer argumento no es numerico)");
            }
            
            if(split_command.length==4)
            {
                System.out.println(Sistema.getInstance().listaDeAlumnos(materia, split_command[2], nota));
                ventana.imprimirEnConsola(Sistema.getInstance().listaDeAlumnos(materia, split_command[2], nota).toString());
            }
            else
            {
                if(split_command[4].toUpperCase().equals("TOFILE"))
                    throw new Exception("Error 002: Consulta mal construida (No se encuentra la palabra reservada 'toFile')");
                ventana.imprimirEnConsola(Sistema.getInstance().listaDeAlumnosArch(materia, split_command[2], nota, split_command[5]).toString());
            }               
                                       
        }                                                    
        else
            throw new Exception("Error 001: No se reconoce la instruccion \""+split_command[0]+"\"");
    }
            
    private static boolean fileExists(String filename)
    {
        File f = new File(filename);
        return f.exists();
    }

    public static void setVentana(Ventana v) {
        Parser.ventana=v;
    }
}