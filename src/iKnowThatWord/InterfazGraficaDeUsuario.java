package iKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is used for to show game on screen and allow to play.
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

     /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     *
     */

     private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object and Control Object
        escucha = new Escucha();
        game = new ModelIKnowThatWord();
        //Set up JComponents

        reglasDelNivel = new ImageIcon(getClass().getResource("/resources/reglas.jpeg"));
        imagenOtroTamanho = reglasDelNivel.getImage().getScaledInstance(410,805,Image.SCALE_SMOOTH);
        imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);

        imagenReglas = new JLabel(imagenNuevoTamanho);

        panelInstrucciones = new JPanel();
        panelInstrucciones.setPreferredSize(new Dimension(410,1005));
        panelInstrucciones.setBackground(Color.WHITE);
        panelInstrucciones.setBorder(BorderFactory.createTitledBorder("Instrucciones del juego."));
        panelInstrucciones.setFont(new Font(Font.DIALOG,Font.BOLD,40));
        panelInstrucciones.setLayout(new BorderLayout());

        instrucciones = new JTextArea();
        instrucciones.setBackground(null);
        instrucciones.setText(INSTRUCCIONES);
        instrucciones.setLineWrap(true);
        instrucciones.setPreferredSize(new Dimension(408, 200));
        instrucciones.setWrapStyleWord(true);
        instrucciones.setLineWrap(true);
        instrucciones.setEditable(false);

        palabra = new JLabel("");
        palabra.setBackground(null);
        palabra.setAlignmentY(SwingConstants.CENTER);
        palabra.setHorizontalAlignment(JLabel.CENTER);
        palabra.setVerticalAlignment(JLabel.CENTER);
        palabra.setFont(new Font(Font.DIALOG,Font.BOLD,40));

        game.pedirDatos();


        numeroNivel = game.getSuNivel();
        createPalabrasAMemorizarGUI(constraints);
        createPalabrasAVerificarGUI(constraints);
        createConclusionGUI(constraints);

        comenzarNivel();
    }

     /**
     * This function starts a new level
     */

     public void comenzarNivel() {
        cualGUI = 1;

        numeroNivel = game.getSuNivel();

        game.palabrasPorNivel(numeroNivel);

        empezarNivel.setVisible(false);
        escucha.buildGUI1();

        conter = 0;
        revalidate();
        repaint();
        pack();

        timer = new Timer(5000, escucha);
        escucha.printMemoryWords();
    }

    /**
     * This function is responsible for displaying the interface in the window where the player indicates wether the
     * word appeared or not
     */

     public void verificarPalabras() {
        cualGUI = 2;
        timer.stop();

        revalidate();
        repaint();

        conter = 0;
        timer = new Timer(7000, escucha);
        escucha.printAllWords();
        pack();
    }
    /**
     * This function shows how many successes and errors there were, if the level was passed and the button to pass the level
     */

     public void terminarNivel() {
        cualGUI = 3;
        timer.stop();

        numeroAciertos = game.getAciertos();
        numeroErrores = game.getErrores();

        empezarNivel.setVisible(true);
        pack();
    }


    
}
