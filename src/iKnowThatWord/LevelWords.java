package iKnowThatWord;

import java.util.ArrayList;
import java.util.random;

public class LevelWords {
    private Vocabulary vocabulary;
    private ArrayList<String> todasLasPalabras, palabrasDelNivel, palabrasAMemorizar;

    /**
     * Constructor
     */

    public PalabrasDelNivel(){
        vocabulary = new Vocabulary();
        todasLasPalabras = vocabulary.getVocabulary();
        palabrasDelNivel = new ArrayList<>();
        palabrasAMemorizar = new ArrayList<>();
    }
 /**
     * This function gets a random word from vocabulary.
     * @return a word
     */

     public ArrayList<String> getPalabrasDelNivel(int cantidadPalabras){
        palabrasDelNivel.clear();
        palabrasDelNivel.addAll(todasLasPalabras.subList(0, cantidadPalabras));
        return palabrasDelNivel;
    }

}
