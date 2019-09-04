package modelo;


public class Prueba
{
    public static void main(String args[])
    {
        Sistema.getInstance().crear("hola.xml");
        Sistema.getInstance().insertar("alumnotext.xml");
        Sistema.getInstance().guardar();
    }
}
