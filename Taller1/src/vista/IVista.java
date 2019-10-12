package vista;

public interface IVista {
    /**
     * @param texto: el texto a escribir
     * Se encarga de imprimir en la vista informacion sobre el funcionamiento del programa
     * 
     */
    void imprimirEnConsola(String texto);
    
    void mostrarError(String texto);
}
