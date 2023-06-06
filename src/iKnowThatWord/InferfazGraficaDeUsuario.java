package iKnowThatWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class is used for to show game on screen and allow to play.
 * @autor Jhoni ipia jhonier.ipia@correounivalle.edu.co
 * @version v.1.0.0
 */

public class InferfazGraficaDeUsuario extends JFrame {

    private Header headerProject;
    private JPanel panelPalabras, panelEspacioEnBlanco1,
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
    private ImageIcon reglasDelNivel, imagenNuevoTamanho, imageGame;
    private Image imagenOtroTamanho;
    private JLabel imagenReglas, palabra;
    private ModelIKnowThatWord game;
    private Escucha listener;

    /**
     * Constructor of GUI class
     */

    public InferfazGraficaDeUsuario(){
        initGUI();
        //Default JFrame configuration
        this.setTitle("I Know That Word");
        this.setSize(600,400);
        this.setMinimumSize(new Dimension(600, 400));
        this.setResizable(false);
        imageGame =new ImageIcon(getClass().getResource("/resources/game.png"));

        // Set the icon image
        if (imageGame != null) {
            Image image = imageGame.getImage();
            int width = 80;
            int height = 70;
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            this.setIconImage(scaledImage);
        }

            // Add a WindowListener to show confirmation dialog on close
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres salir?",
                        "Confirmar salida", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (confirmed == JOptionPane.YES_OPTION) {
                    // Si el usuario confirma la salida, cierra la aplicación
                    dispose();
                    System.exit(0);
                }
            }
        });

        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        getContentPane().setBackground(new Color(255, 230, 153));
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

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
        listener = new Escucha();
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

    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function starts a new level
     */

    public void comenzarNivel() {
        cualGUI = 1;

        numeroNivel = game.getSuNivel();

        game.palabrasPorNivel(numeroNivel);

        empezarNivel.setVisible(false);
        listener.buildGUI1();

        conter = 0;
        revalidate();
        repaint();
        pack();

        timer = new Timer(5000, listener);

        listener.printMemoryWords();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

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
        timer = new Timer(7000, listener);
        listener.printAllWords();
        pack();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

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

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the GUI that show the words to memorize.
     * @param constraints
     */

    public void createPalabrasAMemorizarGUI(GridBagConstraints constraints) {

        createHeader(constraints);
        createHelpButton(constraints);
        createLevelCounter(constraints);
        createPanelPalabrasAMemorizar(constraints);
        createStartLevelButton(constraints);

        cualGUI = 2;

        revalidate();
        repaint();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the header.
     * @param constraints
     */

    public void createHeader(GridBagConstraints constraints) {
        headerProject = new Header("I KNOW THAT WORD", new Color(159, 152, 152));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout


    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the ayuda button.
     * @param constraints
     */

    public void createHelpButton(GridBagConstraints constraints) {
        ayuda = new JButton(" ? ");
        ayuda.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        ayuda.setForeground(Color.white);
        ayuda.removeMouseListener(listener);
        ayuda.addMouseListener(listener);
        ayuda.setBackground(new Color(0, 112, 192));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10,10,10,10);

        add(ayuda, constraints);
    }


    //------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * This function creates the text area to display the actually level.
     * @param constraints
     */

    public void createLevelCounter(GridBagConstraints constraints) {
        nivel = new JTextArea(1, 2);
        //nivel.setMinimumSize(new Dimension(24, 12));
        nivel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        nivel.setText("Nivel: " + numeroNivel);
        nivel.setBackground(new Color(255, 242, 204));
        nivel.setEditable(false);
        nivel.setBorder(BorderFactory.createRaisedBevelBorder());

        constraints.gridx = 4;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(10,10,10,10);
        add(nivel, constraints);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the PanelPalabras panel.
     * @param constraints
     */

    public void createPanelPalabrasAMemorizar(GridBagConstraints constraints)
    {
        panelPalabras = new JPanel();
        panelPalabras.setPreferredSize(new Dimension(390, 240));
        panelPalabras.setBorder(BorderFactory.createTitledBorder("Palabras"));
        panelPalabras.setBackground(new Color(0,0,0,0));
        panelPalabras.setLayout(new BorderLayout());

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(40,10,10,10);

        panelPalabras.add(palabra, BorderLayout.CENTER);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the GUI that show the all words of level and the player will to choose the correct answer.
     * @param constraints
     */

    public void createPalabrasAVerificarGUI(GridBagConstraints constraints) {


        createPanelPalabrasAVerificar(constraints);
        createSpace1(constraints);
        createBotonSI(constraints);
        createBotonNO(constraints);
        createSpace4(constraints);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function repaint the PanelPalabras panel.
     * @param constraints
     */

    public void createPanelPalabrasAVerificar(GridBagConstraints constraints)
    {
        panelPalabras = new JPanel();
        panelPalabras.setBackground(new Color(0,0,0,0));
        panelPalabras.setPreferredSize(new Dimension(390, 140));
        panelPalabras.setFont(new Font("SansSerif", Font.BOLD, 40));
        panelPalabras.setBorder(BorderFactory.createTitledBorder("Palabras"));
        panelPalabras.setLayout(new BorderLayout());

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function create the BotonSI button.
     * @param constraints
     */

    public void createBotonSI(GridBagConstraints constraints)
    {
        botonSI = new JButton("\uF0FC\n");
        botonSI.setPreferredSize(new Dimension(100, 40));
        botonSI.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,24));
        botonSI.setForeground(new Color(32, 50, 20));
        botonSI.removeMouseListener(listener);
        botonSI.addMouseListener(listener);
        botonSI.setBackground(new Color(146, 208, 80));

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function create the BotonNO button.
     * @param constraints
     */

    public void createBotonNO(GridBagConstraints constraints)
    {
        botonNO = new JButton("\uF0FB");
        botonNO.setPreferredSize(new Dimension(100, 40));
        botonNO.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 25));
        botonNO.setForeground(new Color(62, 0, 0));
        botonNO.removeMouseListener(listener);
        botonNO.addMouseListener(listener);
        botonNO.setBackground(new Color(255, 0, 0));

        constraints.gridx = 5;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.NONE;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates white space to separate the panelPalabras pane to botonSI button and botonNO button.
     * @param constraints
     */

    public void createSpace1(GridBagConstraints constraints) {
        panelEspacioEnBlanco1 = new JPanel();
        panelEspacioEnBlanco1.setOpaque(false);
        panelEspacioEnBlanco1.setPreferredSize(new Dimension(115, 40));

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates white space to separate the botonSI button, botonNO button and the GUI border.
     * @param constraints
     */

    public void createSpace4(GridBagConstraints constraints) {
        panelEspacioEnBlanco4 = new JPanel();
        panelEspacioEnBlanco4.setOpaque(false);
        panelEspacioEnBlanco4.setPreferredSize(new Dimension(115, 40));

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the GUI that show the all words of level and the player will to choose the correct answer.
     * @param constraints
     */

    public void createConclusionGUI(GridBagConstraints constraints) {

        createSpace5(constraints);
        createSuccessCounter(constraints);
        createMistakesCounter(constraints);
        createPanelInfo(constraints);

        cualGUI=3;

        revalidate();
        repaint();
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates white space to separate the ayuda button, salir button and nivel text counter to aciertos button and errores boton.
     * @param constraints
     */

    public void createSpace5(GridBagConstraints constraints) {
        panelEspacioEnBlanco5 = new JPanel();
        panelEspacioEnBlanco5.setOpaque(false);
        panelEspacioEnBlanco5.setPreferredSize(new Dimension(115, 40));

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 5;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * This function creates the text area to display the right answers in the level.
     * @param constraints
     */

    public void createSuccessCounter(GridBagConstraints constraints) {
        aciertos = new JTextArea(1, 2);
        aciertos.setMinimumSize(new Dimension(50, 15));
        aciertos.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        aciertos.setText("Aciertos: " + numeroAciertos);
        aciertos.setBackground(new Color(146, 208, 80));
        aciertos.setEditable(false);
        aciertos.setBorder(BorderFactory.createRaisedBevelBorder());

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------


    /**
     * This function creates the text area to display the wrong answers in the level.
     * @param constraints
     */

    public void createMistakesCounter(GridBagConstraints constraints) {
        errores = new JTextArea(1, 2);
        errores.setMinimumSize(new Dimension(50, 15));
        errores.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));;
        errores.setText("Errores: " + numeroErrores);
        errores.setBackground(new Color(255,0,0));
        errores.setEditable(false);
        errores.setBorder(BorderFactory.createRaisedBevelBorder());

        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function create the PanelInfo pane.
     * @param constraints
     */

    public void createPanelInfo(GridBagConstraints constraints)
    {
        panelInfo = new JTextArea();
        panelInfo.setWrapStyleWord(true);
        panelInfo.setLineWrap(true);
        panelInfo.setPreferredSize(new Dimension(390, 240));
        panelInfo.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        panelInfo.setBorder(BorderFactory.createTitledBorder("Información"));
        panelInfo.setBackground(new Color(0,0,0,0));
        panelInfo.setEditable(false);
        panelInfo.setVisible(true);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 5;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the empezarNivel button.
     * @param constraints
     */

    public void createStartLevelButton(GridBagConstraints constraints) {
        empezarNivel = new JButton("Iniciar Nivel");
        empezarNivel.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));;
        empezarNivel.setForeground(Color.black);
        empezarNivel.removeMouseListener(listener);
        empezarNivel.addMouseListener(listener  );
        empezarNivel.setBackground(new Color(255, 242, 204));
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 3;
        constraints.fill = GridBagConstraints.CENTER;
        constraints.anchor = GridBagConstraints.CENTER;
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            InferfazGraficaDeUsuario miProjectGUIIKnowThatWord = new InferfazGraficaDeUsuario();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */

    private class Escucha extends MouseAdapter implements ActionListener {

        GridBagConstraints constraints = new GridBagConstraints();


        @Override
        public void actionPerformed(ActionEvent e) {

            switch (cualGUI)
            {
                case 1:
                    if(e.getSource()==timer) {
                        if (conter < game.getCantidadPalabrasDelNivel() / 2) {
                            printMemoryWords();
                        } else {
                            conter = 0;

                            buildGUI2();

                            verificarPalabras();
                        }
                    }
                    repaint();
                    revalidate();

                    break;
                case 2:
                    if(e.getSource()==timer) {
                        if (conter < game.getCantidadPalabrasDelNivel()) {
                            game.noAnswer();
                            conter++;
                            printAllWords();
                        } else {
                            conter = 0;

                            buildGUI3();

                            terminarNivel();

                            conclusion();
                        }
                    }
                    repaint();
                    revalidate();

                    break;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            GridBagConstraints constraints = new GridBagConstraints();

            if (e.getSource() == salir) {
                game.registrarUsuario();
                System.exit(0);

            } else if (e.getSource() == ayuda) {
                panelInstrucciones.add(instrucciones, BorderLayout.NORTH);
                panelInstrucciones.add(imagenReglas, BorderLayout.CENTER);

                JScrollPane scroll = new JScrollPane(panelInstrucciones);
                scroll.setPreferredSize(new Dimension(440, 455));

                JOptionPane.showMessageDialog(null, scroll, "Instrucciones del juego", JOptionPane.INFORMATION_MESSAGE);

            } else if (e.getSource() == empezarNivel) {

                game.restartPoints();
                nivel.setText("Nivel: " + numeroNivel);
                buildGUI1();

                comenzarNivel();
            }
            else if(e.getSource() == botonSI)
            {
                timer.stop();
                if(conter<game.getCantidadPalabrasDelNivel()) {
                    game.validarPalabra(game.getLevelWords().get(conter), true);
                    conter++;
                    printAllWords();
                } else {
                    printAllWords();
                    timer.stop();
                }
            }
            else if(e.getSource() == botonNO)
            {
                timer.stop();
                if(conter<game.getCantidadPalabrasDelNivel()) {
                    game.validarPalabra(game.getLevelWords().get(conter), false);
                    conter++;
                    printAllWords();
                } else {
                    printAllWords();
                    timer.stop();
                }
            }
        }

        /**
         * This function shows the words to memorize
         */

        public void printMemoryWords()
        {
            panelPalabras.removeAll();
            panelPalabras.add(palabra, BorderLayout.CENTER);
            if(conter<game.getCantidadPalabrasDelNivel()/2) {
                palabra.setText(game.getPalabrasAMemorizar().get(conter));
                conter++;

                palabra.revalidate();
                palabra.repaint();
                panelPalabras.revalidate();
                panelPalabras.repaint();
            }
            timer.start();
            repaint();
            revalidate();
        }

        /**
         * This function shows all the words of the level
         */

        public void printAllWords()
        {
            panelPalabras.removeAll();
            panelPalabras.add(palabra, BorderLayout.CENTER);
            if(conter<game.getCantidadPalabrasDelNivel()) {
                palabra.setText(game.getLevelWords().get(conter));

                palabra.revalidate();
                palabra.repaint();
                panelPalabras.revalidate();
                panelPalabras.repaint();
            }else{
                conter = 0;

                numeroNivel = game.subirNivelUsuario(game.getCantidadPalabrasDelNivel(), game.getAciertos());

                buildGUI3();

                terminarNivel();

                conclusion();
            }
            timer.start();
            repaint();
            revalidate();
        }

        /**
         * This function builds the first interface of the game where the words to be memorized are displayed
         */

        public void buildGUI1()
        {
            remove(panelEspacioEnBlanco5);
            remove(aciertos);
            remove(errores);
            remove(panelInfo);
            remove(empezarNivel);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelPalabras, constraints);

            revalidate();
            repaint();
            pack();
        }

        /**
         * This function shows the second interface of the game where the user chooses the words that appeared and
         * discards the ones that did not
         */

        public void buildGUI2()
        {
            remove(panelPalabras);

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelPalabras, constraints);

            constraints.gridx = 0;
            constraints.gridy = 5;
            constraints.gridwidth = 5;
            constraints.gridheight = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelEspacioEnBlanco1, constraints);

            constraints.gridx = 1;
            constraints.gridy = 4;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(botonSI, constraints);

            constraints.gridx = 3;
            constraints.gridy = 4;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(botonNO, constraints);

            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 5;
            constraints.gridheight = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelEspacioEnBlanco4, constraints);

            revalidate();
            repaint();
            pack();
        }

        /**
         * This function shows the results at the end of the game level
         */

        public void buildGUI3()
        {
            remove(panelPalabras);
            remove(panelEspacioEnBlanco1);
            remove(botonSI);
            remove(botonNO);
            remove(panelEspacioEnBlanco4);

            numeroAciertos = game.getAciertos();
            numeroErrores = game.getErrores();

            constraints.gridx = 0;
            constraints.gridy = 2;
            constraints.gridwidth = 5;
            constraints.gridheight = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelEspacioEnBlanco5, constraints);

            constraints.gridx = 1;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;

            add(aciertos, constraints);
            aciertos.setText("Aciertos: " + numeroAciertos);

            constraints.gridx = 3;
            constraints.gridy = 3;
            constraints.gridwidth = 1;
            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.CENTER;

            add(errores, constraints);
            errores.setText("Errores: " + numeroErrores);

            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 5;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(panelInfo, constraints);

            constraints.gridx = 1;
            constraints.gridy = 5;
            constraints.gridwidth = 3;
            constraints.fill = GridBagConstraints.CENTER;
            constraints.anchor = GridBagConstraints.CENTER;

            add(empezarNivel, constraints);

            revalidate();
            repaint();
            pack();
        }

        /**
         * This function shows the information at the end of the level
         */

        public void conclusion()
        {
            if(numeroNivel==10) {
                if (game.isGanar()) {
                    panelInfo.setText("Has obtenido " + numeroAciertos + " respuestas correctas y " + numeroErrores + " repuestas incorrectas.\n" +
                            "\n¡Lo hiciste muy bien!\nPuedes volver a intentar el máximo nivel dando click en el botón Iniciar Nivel");
                } else {
                    panelInfo.setText("Has obtenido " + numeroAciertos + " respuestas correctas y " + numeroErrores + " repuestas incorrectas.\n" +
                            "\nPuedes hacerlo mejor.\nSi quieres ganar el máximo nivel, inténtalo de nuevo dando click en el botón Iniciar Nivel");
                }
            }
            else if(game.isGanar())
            {
                panelInfo.setText("Has obtenido "+numeroAciertos+" respuestas correctas y "+numeroErrores+" repuestas incorrectas.\n" +
                        "\nEs decir, ¡puedes pasar al próximo nivel! Da click en Iniciar Nivel");
            }
            else
            {
                panelInfo.setText("Has obtenido "+numeroAciertos+" respuestas correctas y "+numeroErrores+" repuestas incorrectas.\n" +
                        "\nEsto no es suficiente para pasar el nivel. Inténtalo de nuevo dando click en el botón Iniciar Nivel");
            }
        }
    }
}