package iKnowThatWord;

import java.io.*;
import java.util.ArrayList;

public class Register {

    ppublic static final String PATH_VOCABULARY = "src/files/vocabulary.txt";
    public static final String PATH_USER = "src/files/user.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter;
    private BufferedWriter output;

    /**
     * This function reads the words to memorize that are in the dictionary.
     * @return the list of words
     */
    public ArrayList<String> lecturaDiccionario() {
        ArrayList<String> palabra = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_DICCIONARIO);
            input = new BufferedReader(fileReader);
            String line;
            while ((line = input.readLine()) != null) {
                palabra.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return palabra;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function reads the saved information of the user.
     * @return the list of users
     */
    public ArrayList<String> lecturaUsuario() {
        ArrayList<String> usuario = new ArrayList<>();
        try {
            fileReader = new FileReader(PATH_USUARIO);
            input = new BufferedReader(fileReader);
            String line;
            while ((line = input.readLine()) != null) {
                usuario.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return usuario;
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function writes a new user name in usuario.txt
     * @param linea the line to write
     */
    public void escribirUsuario(String linea) {
        try {
            fileWriter = new FileWriter(PATH_USUARIO, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function changes the level of an existing user in usuario.txt
     * @param linea the line to write
     * @param conservarTxt whether to preserve the existing text or overwrite the file
     */
    public void escribirUsuarioConocido(String linea, boolean conservarTxt) {
        try {
            fileWriter = new FileWriter(PATH_USUARIO, conservarTxt);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function writes the user level in usuario.txt
     * @param linea the user level to write
     */
    public void escribirNivelUsuario(int linea) {
        String nivel = String.valueOf(linea);
        try {
            fileWriter = new FileWriter(PATH_USER, true);
            output = new BufferedWriter(fileWriter);
            output.write(nivel);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (output != null) {
                    output.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}