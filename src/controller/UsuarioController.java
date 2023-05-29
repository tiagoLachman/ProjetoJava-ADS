package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioController implements Serializable {
    private static final long serialVersionUID = 6470110944414208127L;

    public List<Usuario> listaUsuarios = new ArrayList<>();

    public String listarUsuarios() {
        String res = "";
        for (Usuario livro : listaUsuarios) {
            res = res.concat(livro.getNome() + "\n");
        }

        return res;
    }

    public int cadastrar(Usuario usuario) {

        if (usuario == null) {
            return -1;
        }

        this.listaUsuarios.add(usuario);
        return this.listaUsuarios.size() - 1;
    }

    public Usuario buscar(int cod) {
        Usuario livro_achado;
        try {
            livro_achado = this.listaUsuarios.get(cod);
        } catch (Exception e) {
            return null;
        }
        return livro_achado;
    }

    public Usuario buscar(Usuario usuario) {
        String email = usuario.getEmail();
        String nome = usuario.getNome();

        for (Usuario user : this.listaUsuarios) {

            if ((user.getEmail().equals(email) || email == null) &&
                    (user.getNome().equals(nome) || nome == null)) {
                return user;
            }
        }
        return null;
    }

    public boolean remover(int cod) {
        boolean status;
        try {
            this.listaUsuarios.remove(cod);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }
}
