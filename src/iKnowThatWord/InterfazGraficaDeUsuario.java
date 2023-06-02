package iKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class InterfazGraficaDeUsuario extends JFrame {
    private Header headerProject;
    private JPanel panelPalabras, panelEspacioEnBlanco1, panelEspacioEnBlanco2, panelEspacioEnBlanco3,
                   panelEspacioEnBlanco4, panelEspacioEnBlanco5, panelInstrucciones;
    private JButton ayuda, salir, botonSI, botonNO, empezarNivel;
    private JTextArea nivel, aciertos, errores, instrucciones, panelInfo;
    private Timer timer;
    int numeroNivel, numeroAciertos, numeroErrores, cualGUI, conter = 0;
    private  static final String INSTRUCCIONES = "En la pantalla aparecerán palabras para memorizar, cuentas con 5 "
            + "segundos para memorizar cada una de las palabras\n"
            + "\nTras la serie de palabras a memorizar, el juego te presentará un listado con el doble de palabras que se "
            + "mostraron. Por cada una las palabras debes indicar si la palabra estaba o no contenida en el "
            + "listado a memorizar y tendrás un tiempo de 7 segundos para responder, en caso de no hacerlo se tomará "
            + "como un error.\n"
            + "\nInicias en el nivel 1 y solo puedes pasar al siguiente nivel si lo logras superar. Aquí están las"
            + " condiciones para poder superar cada nivel: ";
    private ImageIcon reglasDelNivel, imagenNuevoTamanho;
    private Image imagenOtroTamanho;
    private JLabel imagenReglas, palabra;
    private ModelIKnowThatWord game;
    private Escucha escucha;

     /**
     * Constructor of GUI class
     */

     public InterfazGraficaDeUsuario(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 230, 153));
    }

    
}
