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
        String res = "";
        for (Categoria categoria : Categoria.values()) {
            res = res.concat(categoria.toString() + "\n");
        }
        return res;
    }
}
