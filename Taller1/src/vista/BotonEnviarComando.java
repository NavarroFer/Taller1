package vista;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

public class BotonEnviarComando extends JButton {
    private String comando;
    
    public BotonEnviarComando() {
        super("Enviar");
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getComando() {
        return comando;
    }
}
