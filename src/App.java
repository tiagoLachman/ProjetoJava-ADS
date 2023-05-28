import java.util.Scanner;

import model.Categoria;
import model.Livro;
import model.Usuario;

public abstract class App {
    public static void main(String[] args) throws Exception {
        Categoria categoria;
        Biblioteca biblioteca = new Biblioteca();

        int op;
        Scanner in = new Scanner(System.in);
        String mensagemIncial = "\n\nBIBLIOTECA!!! \n\n" +
                "1- Cadastrar Livro \n" +
                "2- Cadastrar Usuário \n" +
                "3- Emprestar Livro \n" +
                "4- Devolver Livro \n" +
                "5- Listar livro \n" +
                "6- Listar usuários \n" +
                "7- Listar empréstimos \n" +
                "8- Buscar Usuario \n" +
                "9- Relatórios \n" +
                "10- Log livros \n" +
                "11- Log usuários \n" +
                "12- Log Empréstimos \n" +
                "0- Sair";
        do {
            System.out.println(mensagemIncial);
            op = in.nextInt();
            in.nextLine();

            switch (op) {
                case 1:
                    Livro livro = new Livro();

                    System.out.print("Titulo do livro: ");
                    livro.setTitulo(in.nextLine());

                    System.out.print("Nome do autor: ");
                    livro.setAutor(in.nextLine());

                    System.out.print("Generos:\n" +
                            "Artesanato\n" +
                            "AutoAjuda\n" +
                            "Culinaria\n" +
                            "Esoterismo\n" +
                            "Esportes\n" +
                            "Hobbies\n" +
                            "Sexualidade\n Genero do livro: ");
                    livro.setCategoria(Categoria.valueOf(in.nextLine()));

                    System.out.print("Ano: ");
                    livro.setAno_publicacao(in.nextInt());
                    in.nextLine();

                    System.out.print("Numero exemplares: ");
                    livro.setNum_exemplares(in.nextInt());
                    in.nextLine();

                    biblioteca.cadastrarLivro(livro);
                    break;

                case 2:
                    Usuario usuario = new Usuario(mensagemIncial, op, mensagemIncial, mensagemIncial, mensagemIncial);
                    
                    System.out.print("Nome usuario: ");
                    usuario.setNome(in.nextLine());

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 0:

                    break;

            }

        } while (op != 0);
    }
}

// Livro livro_1 = new Livro("Opa", "Tiago", 2023, Categoria.Culinaria);
// Livro livro_2 = new Livro("Ouriço", "Ju", 2012, Categoria.AutoAjuda);
// Livro livro_3 = new Livro("Carros", "Kiwi", 2012, Categoria.Artesanato);
// Livro livro_4 = new Livro("Pato", "Luiziane", 2010, Categoria.Sexualidade);