package iKnowThatWord;

import java.io.*;
import java.util.ArrayList;

public class Register {

    public static final String PATH_VOCABULARY = " ";
    public static final String PATH_USER = " ";

    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;


    /**
     * This function reads the words to memorize that are in vocabulary
     * @return the word
     */
    public ArrayList<String> lecturaVobulario(){
        ArrayList<String> palabra = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_VOCABULARY);
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            while (line != null){
                palabra.add(line);
                line = input.readLine();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                input.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return palabra;
    };

    /*------------------------------------------------------------------------------------------------------------------------------------*/

    /**
     * This function reads the saved information of the user
     * @return the user
     */
    public ArrayList<String> lecturaUsuario(){
        ArrayList<String> usuario = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_USER);
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            while (line != null){
                usuario.add(line);
                line = input.readLine();
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                input.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return usuario;
    };

    /*------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * This function writes a new user name in usuario.txt
     * @param linea
     */

    public void escribirUsuario(String linea){
        try {
            fileWriter = new FileWriter(PATH_USER,true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * This function change the level of an old user in usuario.txt
     * @param linea
     */

    public void escribirUsuarioConocido(String linea, boolean conservarTxt)
    {
        try {
            fileWriter = new FileWriter(PATH_USER,conservarTxt);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*------------------------------------------------------------------------------------------------------------------------------------*/


    /**
     * This function writes the user level in usuario.txt
     * @param linea
     */

    public void escribirNivelUsuario(int linea){
        String nivel = String.valueOf(linea);
        try {
            fileWriter = new FileWriter(PATH_USER,true);
            output = new BufferedWriter(fileWriter);
            output.write(nivel);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
