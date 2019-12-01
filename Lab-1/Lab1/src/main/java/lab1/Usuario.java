package lab1;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
/**
 *
 * @author vitor
 */
public class Usuario {
 int flag =  0; // flag para saber se o usuario tem um livro em posse ou nao
 String Nome;
 Date DataNasc = new Date();
 int nUsp;
 int debito = 0;
 Date emprestimo; // dia de emprestimo
 String End[]; // Rua , número , Cidade - em cada posição da array
 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
   void Usuario(){ // construtor
       this.End = new String[3]; //inicializando o atributo endereçø com 3 espaços
    }
   
   void cobra(){
       if(flag == 1){
       int multa;
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Date hj = new Date();
       try {
			

			//in milliseconds
			long diff = hj.getTime() - emprestimo.getTime();
			int diffDays =(int) diff / (24 * 60 * 60 * 1000);

			if(diffDays > 7){
                            multa = (diffDays-7)*10;
                            this.debito = multa;
                        }
                        
			

		} catch (Exception e) {
			e.printStackTrace();
		}
       }
       
   }
  
   
    public void inicializa(String name, Date Nasc, int nUsp, String End[]){ // cria um novo usuario
           this.Nome = name;
           this.DataNasc = Nasc;
           this.nUsp = nUsp;    
           this.End = End;
        }
    
 }
    