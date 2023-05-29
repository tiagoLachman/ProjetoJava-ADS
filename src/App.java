import java.util.Scanner;

import model.Categoria;
import model.Livro;
import model.Usuario;

public abstract class App {
    public static void main(String[] args) throws Exception {
        Categoria categoria;
        Biblioteca biblioteca = new Biblioteca();

        int op, temp;
        String temp_string;
        Scanner in = new Scanner(System.in);
        String mensagemIncial = "\n\nEsse negocio \n\n" +
                "1- Cadastrar Livro \n" +
                "2- Cadastrar Usuário \n" +
                "3- Emprestar Livro \n" +
                "4- Devolver Livro \n" +
                "5- Listar livro \n" +
                "6- Listar usuários \n" +
                "7- Listar empréstimos \n" +
                "8- Buscar Usuario \n" +
                "9- Relatórios \n" +
                "10- Log \n" +
                "0- Sair\n"+
                "Opção: ";
        do {
            System.out.print(mensagemIncial);
            op = in.nextInt();
            in.nextLine();

            switch (op) {
                case 1:
                    Livro livro = new Livro();

                    System.out.print("Titulo do livro: ");
                    livro.setTitulo(in.nextLine());

                    System.out.print("Nome do autor: ");
                    livro.setAutor(in.nextLine());
                    try {
                        System.out.print("Generos:\n" +
                                "Artesanato\n" +
                                "AutoAjuda\n" +
                                "Culinaria\n" +
                                "Esoterismo\n" +
                                "Esportes\n" +
                                "Hobbies\n" +
                                "Sexualidade\n Genero do livro: ");
                        livro.setCategoria(Categoria.valueOf(in.nextLine()));
                    } catch (Exception e) {
                        System.out.println("Essa categoria não existe");
                        break;
                    }
                    System.out.print("Ano: ");
                    try {
                        livro.setAno_publicacao(in.nextInt());
                    } catch (Exception e) {
                        System.out.println("Tipo do dado informado invalido");
                        in.nextLine();
                        break;
                    }
                    in.nextLine();

                    System.out.print("Numero exemplares: ");
                    livro.setNum_exemplares(in.nextInt());
                    in.nextLine();
                    try {
                        biblioteca.cadastrarLivro(livro);
                    } catch (Exception e) {
                        System.out.println("Não foi possivel cadastrar o livro");
                        break;
                    }
                    System.out.println("Livro cadastrado com sucesso");
                    break;

                case 2:
                    Usuario usuario = new Usuario();

                    System.out.print("Nome: ");
                    usuario.setNome(in.nextLine());

                    System.out.print("Email: ");
                    usuario.setEmail(in.nextLine());

                    System.out.println("Senha: ");
                    usuario.setSenha(in.nextLine());

                    System.out.println("Idade: ");
                    usuario.setIdade(in.nextInt());
                    in.nextLine();

                    System.out.println("Endereço: ");
                    usuario.setEndereco(in.nextLine());

                    biblioteca.cadastrarUsuario(usuario);
                    break;
                case 3:
                    System.out.println("Id do livro: ");
                    temp = in.nextInt();
                    in.nextLine();

                    System.out.println("Id do usuario: ");

                    biblioteca.emprestarLivro(temp, in.nextInt());
                    in.nextLine();
                    break;
                case 4:
                    System.out.println("Id do livro: ");
                    temp = in.nextInt();
                    in.nextLine();

                    System.out.println("Id do usuario: ");

                    biblioteca.retornarLivro(temp, in.nextInt());
                    in.nextLine();

                    break;
                case 5:
                    System.out.println("Livros:\n");

                    System.out.println(biblioteca.listarLivros());
                    break;
                case 6:
                    System.out.println("Usuarios:\n");
                    System.out.println(biblioteca.listarUsuarios());
                    break;
                case 7:
                    System.out.println("Emprestimos:\n");
                    System.out.println(biblioteca.listarEmprestimos());
                    break;
                case 8:
                    System.out.println("Nome do usuario:\n");
                    temp_string = in.nextLine();

                    System.out.println(biblioteca.buscarUsuario(temp_string));
                    break;
                case 9:
                    System.out.println("Relatorios:\n");
                    System.out.println(biblioteca.gerarRelatorio());
                    break;
                case 10:
                    System.out.println("log:\n");

                    System.out.println(biblioteca.logs());
                    break;

                default:
                    break;
            }
        } while (op != 0);
    }
}