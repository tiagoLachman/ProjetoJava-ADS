package model;

public class Usuario extends Pessoa {
    private String email;
    private String senha;

    public Usuario() {
        super("", -1, "");
    }

    public Usuario(String nome, int idade, String endereco, String email, String senha) {
        super(nome, idade, endereco);
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario [" +
                "nome=" + super.getNome() +
                ", email=" + email +
                ", idade=" + super.getIdade() +
                "]";
    }
}
