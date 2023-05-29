package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Livro;

public class LivroController implements Serializable {
    public List<Livro> listaLivros = new ArrayList<>();

    private static final long serialVersionUID = 6470090944414208496L;

    public String listarLivros(){
        String res = "";
        for (Livro livro : this.listaLivros) {
            res = res.concat(livro.getTitulo()+"\n");
        }
        
        return res;
    }

    public boolean retirarExemplar(int cod) {
        int num = this.listaLivros.get(cod).getNum_exemplares();
        if (num <= 0)
            return false;
        this.listaLivros.get(cod).setNum_exemplares(num - 1);
        return true;
    }

    public int cadastrar(Livro livro) {
        if (livro == null) {
            return -1;
        }
        this.listaLivros.add(livro);
        return this.listaLivros.size() - 1;
    }

    public Livro buscar(int cod) {
        Livro livro_achado;
        try {
            livro_achado = this.listaLivros.get(cod);
        } catch (Exception e) {
            return null;
        }
        return livro_achado;
    }

    public void atualizarNumEmprestimo(int codLivro) {
        Livro livro = buscar(codLivro);
        int num = livro.getNumero_de_emprestimos();
        livro.setNumero_de_emprestimos(num + 1);
    }

    public Livro pegarMaisPopular(){
        Livro res = null;
        int maior = 0;
        for (Livro livro : listaLivros) {
            if(maior <= livro.getNumero_de_emprestimos()){
                res = livro;
                maior = livro.getNumero_de_emprestimos();
            }
        }
        return res;
    }

    public Livro buscar(Livro livro) {
        String titulo = livro.getTitulo();
        String autor = livro.getAutor();
        int ano_publicacao = livro.getAno_publicacao();
        Categoria categoria = livro.getCategoria();

        for (Livro li : this.listaLivros) {
            if ((li.getTitulo().equals(titulo) || titulo == null) &&
                    (li.getAutor().equals(autor) || autor == null) &&
                    (li.getAno_publicacao() == ano_publicacao || ano_publicacao == -1) &&
                    (li.getCategoria().equals(categoria) || categoria == null)) {
                return li;
            }
        }
        return null;
    }

    public boolean remover(int cod) {
        boolean status;
        try {
            this.listaLivros.remove(cod);
            status = true;
        } catch (Exception e) {
            status = false;
        }
        return status;
    }

}
