package iKnowThatWord;

import java.io.File;
import java.util.ArrayList;

public class Vocabulary {
    private ArrayList<String> vocabulary = new ArrayList<>();
    private ArrayList<String> levelWords;

    /**
    * Constructor
    */
    public Vocabulary(){
        Register register = new Register();
        vocabulary = register.lecturaVobulario();
        levelWords = new ArrayList<String>();
    }


    /*----------------------------------------------------------------------------------------------------------------*/

    public ArrayList<String>getVocabulary(){
        return vocabulary;
    }
}
