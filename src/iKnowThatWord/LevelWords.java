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
