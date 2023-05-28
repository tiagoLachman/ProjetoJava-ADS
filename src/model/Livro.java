package model;

import java.io.Serializable;

public class Livro implements Serializable {
    private String titulo;
    private String autor;
    private int ano_publicacao;
    private Categoria categoria;
    private int num_exemplares;

    public Livro(String titulo, String autor, int ano_publicacao, Categoria categoria, int num_exemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.ano_publicacao = ano_publicacao;
        this.categoria = categoria;
        this.num_exemplares = num_exemplares;
    }

    public int getNum_exemplares() {
        return num_exemplares;
    }

    public void setNum_exemplares(int num_exemplares) {
        this.num_exemplares = num_exemplares;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
