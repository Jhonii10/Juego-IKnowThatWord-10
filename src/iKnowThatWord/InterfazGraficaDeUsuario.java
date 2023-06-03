package iKnowThatWord;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for ...
 * @autor Carlos Felipe Montoya carlos.felipe.montoya@correounivalle.edu.co
 * @version v.1.0.0 date:21/03/2023
 */
public class InterfazGraficaDeUsuario extends JFrame {

    private Header headerProject;
    private ImageIcon reglasDelNivel,imagenNuevoTamaño;
    private Image imageOtroTamaño;
    private JPanel panelInstrucciones;
    private JLabel palabra;

    private JTextArea instrucciones;

    private ModelIKnowThatWord game;

    private int numeroNivel;
    private JButton ayuda,play;

    /**
     * Constructor of GUI class
     */
    public InterfazGraficaDeUsuario(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("I Know Than Wordgit ");
        this.setSize(600,400);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,230,153));
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object and Control Object
        game = new ModelIKnowThatWord();
        //Set up JComponents
        reglasDelNivel = new ImageIcon(getClass().getResource("/resources/reglas.jpeg"));
        imageOtroTamaño = reglasDelNivel.getImage().getScaledInstance(410,805,Image.SCALE_SMOOTH);
        imagenNuevoTamaño = new ImageIcon(imageOtroTamaño);

        panelInstrucciones = new JPanel();
        panelInstrucciones.setPreferredSize(new Dimension(410,1005));
        panelInstrucciones.setBackground(Color.WHITE);
        panelInstrucciones.setBorder(BorderFactory.createTitledBorder("Instrucciones del juego."));
        panelInstrucciones.setFont(new Font(Font.DIALOG,Font.BOLD,40));
        panelInstrucciones.setLayout(new BorderLayout());

        instrucciones = new JTextArea();
        instrucciones.setBackground(null);
        instrucciones.setText("intruciones aqui");
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

        //constraints
        createHeader(constraints);
        ayuda(constraints);
        createPlayButton(constraints);

        //empezar nivel
    }

    /**
     * This function creates the header.
     * @param constraints
     */

    public void createHeader(GridBagConstraints constraints) {
        headerProject = new Header("I Know that Word", new Color(128, 96, 0));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout

        revalidate();
        repaint();
    }

    /**
     * This function creates the ayuda button.
     * @param constraints
     */

    public void ayuda(GridBagConstraints constraints) {
        ayuda = new JButton(" ? ");
        ayuda.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
        ayuda.setForeground(Color.white);
        ayuda.setBackground(new Color(0, 112, 192));
        // escuchas
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.FIRST_LINE_START;
        constraints.anchor = GridBagConstraints.LINE_START;

        add(ayuda, constraints);
    }

    /**
     * This function creates the ayuda button.
     * @param constraints
     */

    public void createPlayButton(GridBagConstraints constraints) {
        play = new JButton("Play");
        play.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
        play.setForeground(Color.WHITE);
        play.setBackground(new Color(192, 0, 0));
        // escuchas
        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.LINE_END;

        add(play, constraints);
    }





    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            InterfazGraficaDeUsuario miProjectGUI = new InterfazGraficaDeUsuario();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}