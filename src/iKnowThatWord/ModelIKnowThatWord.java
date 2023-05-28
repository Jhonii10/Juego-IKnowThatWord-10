package iKnowThatWord;

import java.util.ArrayList;

public class ModelIKnowThatWord {

    private int conteoErrores, conteoAciertos, suNivel;
    private boolean ganar;
    private ArrayList<String> palabrasDelNivel, palabrasAMemorizar;


    /**
     * Constructor
     * */
    public ModelIKnowThatWord(){
        conteoErrores = 0;
        conteoAciertos = 0;
        suNivel = 0;
        palabrasDelNivel = new ArrayList<String>();
        palabrasAMemorizar = new ArrayList<String>();
        ganar = false;
    }

}


