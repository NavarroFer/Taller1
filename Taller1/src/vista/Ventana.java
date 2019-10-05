
package vista;

import controlador.Parser;

import exceptions.ParsingException;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.beans.XMLEncoder;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.HashMap;

import javax.swing.JButton;

import modelo.Alumno;

/**
 *
 * @author Mau
 */
public class Ventana
    extends javax.swing.JFrame
{

    /** Creates new form Ventana */
    public Ventana()
    {

        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width*2/3, screenSize.height*2/3);
        this.setMinimumSize(new Dimension(screenSize.width*6/10,screenSize.height*6/10));
        this.zonaErorres.setMinimumSize(new Dimension(zonaErorres.getPreferredSize().width/2,zonaErorres.getPreferredSize().width/2));
        this.jPanelSalida.setMinimumSize(new Dimension(jPanelSalida.getPreferredSize().width/2,jPanelSalida.getPreferredSize().width/2));
        this.BotonEnviar.setEnabled(false);
        this.BotonEnviar.setBackground(Color.gray);
        setLocationRelativeTo(null);
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTFEntrada.requestFocus();
        this.getRootPane().setDefaultButton(BotonEnviar);
        Parser.setVentana(this);

    }

    public void imprimirEnConsola(String texto)
    {
        this.jTextArea1.append(texto + "\n");
        this.repaint();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents()//GEN-BEGIN:initComponents
    {

        jPanelBotonyTextField = new javax.swing.JPanel();
        jPanelBotonyTFAdentro = new javax.swing.JPanel();
        JPanelTF = new javax.swing.JPanel();
        jTFEntrada = new javax.swing.JTextField();
        jPanelButton = new javax.swing.JPanel();
        BotonEnviar = new javax.swing.JButton();
        jPanelSalida = new javax.swing.JPanel();
        jPanelSalidaAdentro = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        zonaErorres = new javax.swing.JPanel();
        jPanelErroresAdentro = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Almacen Alumnos");

        jPanelBotonyTextField.setBorder(javax.swing.BorderFactory.createTitledBorder("Comandos"));
        jPanelBotonyTextField.setLayout(new java.awt.BorderLayout());

        jPanelBotonyTFAdentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelBotonyTFAdentro.setLayout(new java.awt.BorderLayout());

        JPanelTF.setLayout(new java.awt.BorderLayout());

        jTFEntrada.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                jTFEntradaKeyReleased(evt);
            }
        });
        JPanelTF.add(jTFEntrada, java.awt.BorderLayout.CENTER);

        jPanelBotonyTFAdentro.add(JPanelTF, java.awt.BorderLayout.CENTER);

        BotonEnviar.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        BotonEnviar.setForeground(new java.awt.Color(15, 147, 74));
        BotonEnviar.setText("Enviar");
        BotonEnviar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                BotonEnviarActionPerformed(evt);
            }
        });
        BotonEnviar.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyReleased(java.awt.event.KeyEvent evt)
            {
                BotonEnviarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonLayout = new javax.swing.GroupLayout(jPanelButton);
        jPanelButton.setLayout(jPanelButtonLayout);
        jPanelButtonLayout.setHorizontalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelButtonLayout.setVerticalGroup(
            jPanelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBotonyTFAdentro.add(jPanelButton, java.awt.BorderLayout.EAST);

        jPanelBotonyTextField.add(jPanelBotonyTFAdentro, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanelBotonyTextField, java.awt.BorderLayout.SOUTH);

        jPanelSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("Salida"));
        jPanelSalida.setToolTipText("rucula");
        jPanelSalida.setPreferredSize(new java.awt.Dimension(150, 119));
        jPanelSalida.setLayout(new java.awt.BorderLayout());

        jPanelSalidaAdentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelSalidaAdentro.setLayout(new java.awt.BorderLayout());

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanelSalidaAdentro.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanelSalida.add(jPanelSalidaAdentro, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanelSalida, java.awt.BorderLayout.CENTER);

        zonaErorres.setBorder(javax.swing.BorderFactory.createTitledBorder("Errores"));
        zonaErorres.setToolTipText("rucula");
        zonaErorres.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        zonaErorres.setPreferredSize(new java.awt.Dimension(400, 119));
        zonaErorres.setLayout(new java.awt.BorderLayout());

        jPanelErroresAdentro.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelErroresAdentro.setMinimumSize(new java.awt.Dimension(60, 26));
        jPanelErroresAdentro.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setHorizontalScrollBar(null);

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanelErroresAdentro.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        zonaErorres.add(jPanelErroresAdentro, java.awt.BorderLayout.CENTER);

        getContentPane().add(zonaErorres, java.awt.BorderLayout.EAST);

        pack();
    }//GEN-END:initComponents

    private void BotonEnviarActionPerformed(java.awt.event.ActionEvent evt) 
    {//GEN-FIRST:event_jButton1ActionPerformed
        String usrInput = jTFEntrada.getText();
        jTextArea1.append(usrInput + "\n");
        try
        {
            Parser.parse(usrInput);
        }
        catch (ParsingException exception)
        {
            jTextArea2.append("\n" + exception.getErrorMessage());
        }
        catch (Exception e)
        {
            jTextArea2.append("\n UNEXPECTED ERROR: " + e.getMessage());
        }

        jTFEntrada.setText("");  
        this.BotonEnviar.setEnabled(false);
        this.BotonEnviar.setBackground(Color.gray);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void BotonEnviarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BotonEnviarKeyReleased
      
    }//GEN-LAST:event_BotonEnviarKeyReleased

    private void jTFEntradaKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jTFEntradaKeyReleased
    {//GEN-HEADEREND:event_jTFEntradaKeyReleased
        if (this.jTFEntrada
                .getText()
                .equals(""))
        {
            this.BotonEnviar.setEnabled(false);
            this.BotonEnviar.setBackground(Color.gray);
        }
        else
        {
            this.BotonEnviar.setEnabled(true);
            this.BotonEnviar.setBackground(new JButton().getBackground());
        }
    }//GEN-LAST:event_jTFEntradaKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing
                                                                  .UIManager
                                                                  .getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing
                         .UIManager
                         .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(Ventana.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(Ventana.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(Ventana.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util
                .logging
                .Logger
                .getLogger(Ventana.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
            .EventQueue
            .invokeLater(new Runnable()
            {
                public void run()
                {
                    new Ventana().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonEnviar;
    private javax.swing.JPanel JPanelTF;
    private javax.swing.JPanel jPanelBotonyTFAdentro;
    private javax.swing.JPanel jPanelBotonyTextField;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelErroresAdentro;
    private javax.swing.JPanel jPanelSalida;
    private javax.swing.JPanel jPanelSalidaAdentro;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTFEntrada;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JPanel zonaErorres;
    // End of variables declaration//GEN-END:variables

}
