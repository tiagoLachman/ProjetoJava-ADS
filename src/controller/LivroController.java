package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Livro;

public class LivroController implements Serializable {
    public List<Livro> listaLivros = new ArrayList<>();

    private static final long serialVersionUID = 6470090944414208496L;

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

    public Livro buscar(Livro livro) {
        String titulo = livro.getTitulo();
        String autor = livro.getAutor();
        int ano_publicacao = livro.getAno_publicacao();
        Categoria categoria = livro.getCategoria();

        for (Livro li : this.listaLivros) {
            if ((li.getTitulo() == titulo || titulo == null) &&
                    (li.getAutor() == autor || autor == null) &&
                    (li.getAno_publicacao() == ano_publicacao || ano_publicacao == -1) &&
                    (li.getCategoria() == categoria || categoria == null)) {
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
