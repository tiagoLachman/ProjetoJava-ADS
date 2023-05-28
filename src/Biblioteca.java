import java.util.ArrayList;
import java.util.List;

import controller.LivroController;
import controller.SerialController;
import controller.UsuarioController;
import model.Categoria;
import model.Livro;
import model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    SerialController serial = new SerialController();
    LivroController livroController;
    UsuarioController usuarioController;

    Map<Integer, Integer> listaEmprestados = new HashMap<>();

    private final String path = "./data";

    public Biblioteca() {
        carregar();
    }

    private void carregar() {
        try {
            livroController = (LivroController) serial.deserializar(path + "/livro");
            livroController = livroController == null ? new LivroController() : livroController;

            usuarioController = (UsuarioController) serial.deserializar(path + "/usuario");
            usuarioController = usuarioController == null ? new UsuarioController() : usuarioController;

        } catch (Exception e) {
            System.out.println("Deu erro:" + e);
        }
    }

    private void salvar() {
        try {
            serial.serializar(path + "/livro", livroController);
            serial.serializar(path + "/usuario", usuarioController);
        } catch (Exception e) {
            System.out.println("Deu erro:" + e);
        }
    }

    public boolean cadastrarLivro(Livro livro) {
        boolean status = livroController.cadastrar(livro) >= 0;
        salvar();
        return status;
    }

    public boolean emprestarLivro(int codLivro, int codUsuario) {
        Usuario user = usuarioController.buscar(codUsuario);
        Livro livro = livroController.buscar(codLivro);

        if (user == null || livro == null) {
            return false;
        }

        int num = livroController.buscar(codLivro).getNum_exemplares();
        if (num <= 0) {
            return false;
        }

        livroController.buscar(codLivro).setNum_exemplares(num - 1);
        this.listaEmprestados.put(codUsuario, codLivro);
        salvar();
        return true;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        boolean status = usuarioController.cadastrar(usuario) >= 0;
        salvar();
        return status;
    }

    public boolean retornarLivro(int codLivro, int codUsuario) {

        // System.out.println(this.listaEmprestados.get(codLivro));
        // if(this.listaEmprestados.get(codUsuario) == null) return false;

        // int num = livroController.buscar(codLivro).getNum_exemplares();
        // livroController.buscar(codLivro).setNum_exemplares(num + 1);
        // this.listaEmprestados.remove(codUsuario);

        salvar();
        return true;
    }

}
