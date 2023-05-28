package iKnowThatWord;

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


}
