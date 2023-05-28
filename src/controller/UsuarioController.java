package controller;

import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioController {
    public List<Usuario> listaUsuarios = new ArrayList<>();

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
            if ((user.getEmail() == email || email == null) &&
                    (user.getNome() == nome || nome == null)) {
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
