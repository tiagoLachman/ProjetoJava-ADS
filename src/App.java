import model.Categoria;
import model.Livro;
import model.Usuario;

public abstract class App {
    public static void main(String[] args) throws Exception {
        Biblioteca biblioteca = new Biblioteca();
        Usuario usuario = new Usuario("Tiago", 22, "vaitekata", "tiago@gmail.com", "asdzcx");
        Livro livro = new Livro("Opa", "Tiago", 2023, Categoria.Culinaria, 1);

        biblioteca.cadastrarUsuario(usuario);
        biblioteca.cadastrarLivro(livro);

        System.out.println("1");
        System.out.println(biblioteca.emprestarLivro(0, 0));
        System.out.println("2");
        System.out.println(biblioteca.listaEmprestados);
        System.out.println(biblioteca.getEmprestimoUsuario(0));

    }
}

// Livro livro_1 = new Livro("Opa", "Tiago", 2023, Categoria.Culinaria);
// Livro livro_2 = new Livro("Ouriço", "Ju", 2012, Categoria.AutoAjuda);
// Livro livro_3 = new Livro("Carros", "Kiwi", 2012, Categoria.Artesanato);
// Livro livro_4 = new Livro("Pato", "Luiziane", 2010, Categoria.Sexualidade);