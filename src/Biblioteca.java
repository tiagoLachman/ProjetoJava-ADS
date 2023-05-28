import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import controller.LivroController;
import controller.LogController;
import controller.SerialController;
import controller.UsuarioController;
import model.Livro;
import model.Usuario;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca {
    SerialController serial = new SerialController();
    LivroController livroController;
    UsuarioController usuarioController;

    Map<Integer, Map<String, String>> listaEmprestados;

    LogController log;

    private final String pathData = "./data";
    private final String pathLog = "./log";

    public Biblioteca() {
        carregar();
    }

    private void carregar() {
        try {
            livroController = (LivroController) serial.deserializar(pathData + "/livro");
            livroController = livroController == null ? new LivroController() : livroController;

            usuarioController = (UsuarioController) serial.deserializar(pathData + "/usuario");
            usuarioController = usuarioController == null ? new UsuarioController() : usuarioController;

            listaEmprestados = (HashMap<Integer, Map<String, String>>) serial.deserializar(pathData + "/listaEmprestimos");
            listaEmprestados = listaEmprestados == null ? new HashMap<Integer, Map<String, String>>() : listaEmprestados;

            log = (LogController) serial.deserializar(pathLog + "/log");
            log = log == null ? new LogController() : log;

        } catch (Exception e) {
            System.out.println("Deu erro:" + e);
        }
    }

    private void salvar() {
        try {
            serial.serializar(pathData + "/livro", livroController);
            serial.serializar(pathData + "/usuario", usuarioController);
            serial.serializar(pathData + "/listaEmprestimos", listaEmprestados);

            serial.serializar(pathLog + "/log", log);

        } catch (Exception e) {
            System.out.println("Deu erro:" + e);
        }
    }

    public boolean cadastrarLivro(Livro livro) {
        String horaReq = getHorario();
        boolean status = livroController.cadastrar(livro) >= 0;
        String res = status ? "true" : "false";

        log.gerarLog(horaReq + " RETORNOU: " + res + " / cadastrarLivro / " + livro);
        salvar();
        return status;
    }

    public boolean cadastrarUsuario(Usuario usuario) {
        String horaReq = getHorario();
        boolean status = usuarioController.cadastrar(usuario) >= 0;
        String res = status ? "true" : "false";

        log.gerarLog(horaReq + " RETORNOU: " + res + " / cadastrarUsuario / " + usuario);
        salvar();
        return status;
    }

    public String emprestarLivro(int codLivro, int codUsuario) {
        String horaReq = getHorario();

        String res = "Emprestado com sucesso";
        Usuario user = usuarioController.buscar(codUsuario);
        Livro livro = livroController.buscar(codLivro);

        if (user == null || livro == null) {
            res = "Usuario ou livro não encontrados";
            log.gerarLog(horaReq + " RETORNOU: " + res + " / emprestarLivro / " + user + " / " + livro);
            return res;
        }

        if (getEmprestimoUsuario(codUsuario) >= 0) {
            res = "O usuario já tem um livro emprestado";
            log.gerarLog(horaReq + " RETORNOU: " + res + " / emprestarLivro / " + user + " / " + livro);
            return res;
        }

        int num = livroController.buscar(codLivro).getNum_exemplares();
        if (num <= 0) {
            res = "Não há exemplares para emprestar";
            log.gerarLog(horaReq + " RETORNOU: " + res + " / emprestarLivro / " + user + " / " + livro);
            return res;
        }

        livroController.buscar(codLivro).setNum_exemplares(num - 1);
        Map<String, String> temp = new HashMap<>();
        temp.put("IdLivro", Integer.toString(codLivro));
        temp.put("DataEmprestado", horaReq);
        temp.put("DataPrevisao", getHorario(7));

        this.listaEmprestados.put(codUsuario, temp);

        log.gerarLog(horaReq + " RETORNOU: " + res + " / emprestarLivro / " + user + " / " + livro);
        salvar();
        return res;
    }

    public String retornarLivro(int codLivro, int codUsuario) {
        String horaReq = getHorario();

        String res = "Livro retornado com sucesso.";
        Usuario user = usuarioController.buscar(codUsuario);
        Livro livro = livroController.buscar(codLivro);

        if (user == null || livro == null) {
            res = "Usuario ou livro não encontrados";
            log.gerarLog(horaReq + " RETORNOU: " + res + " / retornarLivro / " + user + " / " + livro);
            return res;
        }

        if (this.listaEmprestados.get(codUsuario) == null) {
            res = "Este usuario não tem nenhum livro emprestado.";
            log.gerarLog(horaReq + " RETORNOU: " + res + " / retornarLivro / " + user + " / " + livro);
            return res;
        }

        int num = livroController.buscar(codLivro).getNum_exemplares();
        livroController.buscar(codLivro).setNum_exemplares(num + 1);
        this.listaEmprestados.remove(codUsuario);

        log.gerarLog(horaReq + " RETORNOU: " + res + " / retornarLivro / " + user + " / " + livro);
        salvar();
        return res;
    }

    public int verExemplares(int codLivro) {
        return livroController.buscar(codLivro).getNum_exemplares();
    }

    public String logs() {
        return log.toString();
    }

    private String getHorario() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    private String getHorario(long dias) {
        return LocalDateTime.now().plusDays(dias).format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public int getEmprestimoUsuario(int codUsuario) {
        Integer res = Integer.parseInt(this.listaEmprestados.get(codUsuario).get("IdLivro"));
        return res == null ? -1 : res;
    }

}
