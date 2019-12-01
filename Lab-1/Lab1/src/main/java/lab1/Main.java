package lab1;

/**
 *
 * @author vitor
 */
import java.util.*;

public class Main {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String stru;
        String strm;
        UserBase ub = new UserBase();
        Acervo ac = new Acervo();
        while(true){
        System.out.println("\n\n Sistema de gerenciamento de biblioteca \n "
                + "Escolha uma opcao Seu modo:\n"
                + "1 - adicao de usuarios\n"
                + "2 - adicao de midias\n"
                + "3 - emprestimo de livros\n"
                + "4 - devolucao de livros\n"
                + "5 - Consulta de Midias\n"
                + "6 - sair");
        
        Scanner mode = new Scanner(System.in);
        int a = Integer.parseInt(mode.nextLine()); 
        switch(a){
            case(1):
                ub.createUsr(ub);
                ub.printUsrB();
                break;
            case(2):
                ac.insertMidia(ac);
                ac.printAcervo();
                break;
                
            case(3):
                System.out.println("insira o nome do usuario");
                 stru = mode.nextLine();
                System.out.println("insira o nome da midia");
                 strm = mode.nextLine();
                ac.empresta(ub,ac,stru,strm);
                break;
            case(4):
                System.out.println("insira o nome do usuario");
                stru = mode.nextLine();
                System.out.println("insira o nome da midia");
                strm = mode.nextLine();
                ac.devolver(ub,ac,stru,strm);
                break;
            case(5):
                 System.out.println("insira o nome ou parte do nome da midia que deseja procurar:");
                 strm = mode.nextLine();
                 ac.print(ac.recupera(ac.consulta(strm)));
                break;
            case(6):
                System.exit(0);
            default:
                break;
             
            }
        }    
    }
 }
