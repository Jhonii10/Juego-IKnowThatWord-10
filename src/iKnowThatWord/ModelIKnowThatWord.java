package iKnowThatWord;

import java.util.ArrayList;

public class ModelIKnowThatWord {

    private LevelWords words;
    private Users user;
    private String theUser;

    private int conteoErrores, conteoAciertos, suNivel;
    private boolean ganar;
    private ArrayList<String> levelWords, palabrasAMemorizar;


    /**
     * Constructor
     * */
    public ModelIKnowThatWord(){
        words = new LevelWords();
        user = new Users();
        conteoErrores = 0;
        conteoAciertos = 0;
        suNivel = user.getNivelUsuario();
        levelWords = new ArrayList<String>();
        palabrasAMemorizar = new ArrayList<String>();
        ganar = false;
    }

    /**
     * This function show the words of the level that is indicated
     * @param nivel
     */

    public void palabrasPorNivel(int nivel) {
        if (nivel >= 1 && nivel <= 10) {
            int palabrasTotales = nivel * 20;
            int palabrasMemorizar = palabrasTotales / 2;
            levelWords = words.getPalabrasDelNivel(palabrasTotales);
            palabrasAMemorizar = words.palabrasAMemorizar(palabrasMemorizar);
        }
    }

    /**
     * This function checks whether or not it came out in the list of words to be memorized.
     * @param word
     */

    public void validarPalabra(String word, boolean respuestaAfirmativa) {
        boolean correcta = false;
        int flag = 0;
        
        if (respuestaAfirmativa) {
            for (flag = 0; flag < palabrasAMemorizar.size(); flag++) {
                if (palabrasAMemorizar.get(flag).equals(word)) {
                    correcta = true;
                    break; // Se encontró la palabra, se puede salir del bucle
                }
            }
        } else {
            correcta = true;
            for (flag = 0; flag < palabrasAMemorizar.size(); flag++) {
                if (palabrasAMemorizar.get(flag).equals(word)) {
                    correcta = false;
                    break; // Se encontró la palabra, se puede salir del bucle
                }
            }
        }
        
        if (correcta) {
            conteoAciertos++;
        } else {
            conteoErrores++;
        }
    }
     /**
      * * This function level ups user and determines if the user wins or loses
       */
      
    public int subirNivelUsuario(int totalPalabras, int palabrasAcertadas) {
        double porcentajeMinimo = 0.7 + (suNivel - 1) * 0.05;
        double porcentajeNivel10 = 1.0;

    if (suNivel == 10) {
        if (palabrasAcertadas == totalPalabras) {
            ganar = true;
        } else {
            ganar = false;
        }
    } else {
        if (palabrasAcertadas >= (totalPalabras * porcentajeMinimo)) {
            suNivel++;
            ganar = true;
        } else {
            suNivel = suNivel;
            ganar = false;
        }
    }

    return suNivel;
}
    /**
    * this function counts errors
     */
    public void noAnswer(){
        conteoErrores++;
    } 
    /**
    * This function calls the pedirDatos functions of the Model class to request the name of the player in the GUI
    */
     public void pedirDatos(){
        user.pedirDatos();
        detectNewOrOldUser();
    }
    /**
    * This function saves the information of a new user.
    */

    public void registrarUsuario(){
        user.registrarUsuario(suNivel);
    }

    /**
    * This function detects if the player has already played or not
    */

    public void detectNewOrOldUser(){
        theUser = user.getUsuarioIngresado();
        suNivel = user.getNivelUsuario();
    }

    /**
    * This function restablish the point conters
    */

    public void restartPoints() {
        conteoAciertos=0;
        conteoErrores=0;
    }

    /**
    * This method gets the user level.
    * @return new level
    */

    public int getSuNivel() {
        return suNivel;
    }
    /**
    * This method returns the number of words that the level
    * @return the number of words that the level
    */

    public int getCantidadPalabrasDelNivel() {
        return words.getCantidadPalabrasDelNivel();
    }

    /**
    * This method returns the number of the errors that were made
    * @return the number of the errors
    */

    public int getErrores() {
        return conteoErrores;
    }
    /**
    * This method returns the number of the hits that were made
    * @return the number of the hits
    */

    public int getAciertos() {
        return conteoAciertos;
    }

    /**
     * This method gets the ganar value
     * @return ganar
     */

    public boolean isGanar() {
        return ganar;
    }
/**
     * This function returns all words in the level
     * @return an array with the words
     */

     public ArrayList<String> getLevelWords() {
        return levelWords;
    }

    //--------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function returns only the words to be menorized from the level
     * @return an array with the words
     */

    public ArrayList<String> getPalabrasAMemorizar() {
        return palabrasAMemorizar;
    }
    

}


