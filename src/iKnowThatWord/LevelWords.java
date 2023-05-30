package iKnowThatWord;

import java.util.ArrayList;
import java.util.Random;

public class LevelWords {



    private Vocabulary vocabulary;
    private ArrayList<String> todasLasPalabras, palabrasDelNivel, palabrasAMemorizar;

    /**
     * Constructor
     */

    public LevelWords(){
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

/**
     * This function returns half of the words in the level for the player to memorize
     * @return the words to memorize
     */

     public ArrayList<String> palabrasAMemorizar(int cantidadPalabras){
        Random aleatorio = new Random();
        palabrasAMemorizar.clear();
        while (palabrasAMemorizar.size() < cantidadPalabras){
            String unaPalabra = palabrasDelNivel.get(aleatorio.nextInt(getCantidadPalabrasDelNivel()));
            if (!palabrasAMemorizar.contains(unaPalabra)){
                palabrasAMemorizar.add(unaPalabra);
            }
        }
        return palabrasAMemorizar;
    }

/**
     * This method gets the number of words that the level
     * @return the number of words that the level
     */

     public int getCantidadPalabrasDelNivel() {
        return palabrasDelNivel.size();
    }
/**
     * This method gets a word
     * @param cualPalabra
     * @return the word
     */

     public String getUnaPalabra(int cualPalabra){
        return palabrasDelNivel.get(cualPalabra);
    }

}
