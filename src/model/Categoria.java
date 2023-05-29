package model;

public enum Categoria {
    Artesanato,
    AutoAjuda,
    Culinaria,
    Esoterismo,
    Esportes,
    Hobbies,
    Sexualidade;

    @Override
    public String toString() {
        return Categoria.values().toString();
    }
}
