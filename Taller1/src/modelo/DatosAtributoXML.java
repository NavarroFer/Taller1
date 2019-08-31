package modelo;


public class DatosAtributoXML {
    private String nombre;
    private String tipo;
    private String valor;


    public DatosAtributoXML(String nombre, String tipo, String valor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }


    public String getNombre() {
        return nombre;
    }



    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }


    @Override
    public String toString() {
        return String.format("Nombre: %-23s Tipo: %-13s Valor: %-20s", getNombre(), getTipo(), getValor());
    }


}
