package iKnowThatWord;

import javax.swing.*;
import java.util.ArrayList;

public class Users {

    private ArrayList<String> usuarios, editarUsuarios = new ArrayList<String>();
    private int unUsuario, nivelUsuario;
    private String nombreUsuario;
    private Register register;

    /**
     * Constructor
     */

    public Users() {
        register = new Register();
        usuarios = register.lecturaUsuario();

        unUsuario = 0;
        nivelUsuario = 1;
        nombreUsuario = " ";
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * This function ask an username at the user.
     */

    public void pedirDatos(){
        nombreUsuario = JOptionPane.showInputDialog(null, "Ingresa tu nombre",
                "Solicitud de datos", JOptionPane.QUESTION_MESSAGE);
        if(nombreUsuario == null || nombreUsuario.equals("")){
            pedirDatos();
        }
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * This function saves the information of a new user or upload the level to an old user.
     */

    public void registrarUsuario(int nivelUsuario) {
        usuarios = register.lecturaUsuario();
        boolean añadir = true;
        for (int flag = 0; flag < usuarios.size(); flag++) {
            if(usuarios.get(flag).equals(nombreUsuario)) {
                boolean conservarUsuarios = false;
                añadir = false;
                usuarios.remove(flag+1);
                usuarios.add(flag+1,String.valueOf(nivelUsuario));
                for(int flag1=0;flag1<usuarios.size();flag1++)
                {
                    register.escribirUsuarioConocido(usuarios.get(flag1), conservarUsuarios);
                    conservarUsuarios = true;
                    añadir = false;
                }
                break;
            }
            else
            {
                añadir =true;
            }
        }
        if(añadir)
        {
            register.escribirUsuario(nombreUsuario);
            register.escribirNivelUsuario(nivelUsuario);
        }
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * This function gets the user level.
     * @return user level
     */

    public int getNivelUsuario() {
        for (unUsuario = 0; unUsuario < usuarios.size(); unUsuario++) {
            if (usuarios.get(unUsuario).equals(nombreUsuario)) {
                nivelUsuario = Integer.parseInt(usuarios.get(unUsuario+1));
                break;
            } else {
                nivelUsuario = 1;
            }
        }
        return nivelUsuario;
    }

    /*----------------------------------------------------------------------------------------------------------------*/

    /**
     * This function gets the user name.
     * @return user level
     */

    public String getUsuarioIngresado() {
        return nombreUsuario;
    }

}
