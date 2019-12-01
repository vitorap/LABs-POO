package lab1;
import java.text.ParseException;
import java.util.*;  
import java.util.Date;
/**
 *
 * @author vitor
 */
public class Acervo {
    
    ArrayList <Midia> Acervo = new <Midia> ArrayList(); 
    Iterator itr = Acervo.iterator();
    
    Acervo(){ 
        
    }
    public  ArrayList <Midia> init(){ 
        
        return this.Acervo;
    }
    public void cadastraNovo(Midia midia){
        this.Acervo.add(midia);
    }
    public int consulta(String string){ 
       
        Midia curMe;
        int indexOf = -1;

      for(int i=0;i< this.Acervo.size();i++)  
           {  
            curMe = Acervo.get(i);
            if(curMe.autor.contains(string) ||curMe.tit.contains(string) || curMe.editor.contains(string)){
                indexOf = Acervo.indexOf(curMe);
            }
           }
      
        return indexOf;
    }
    public void remove(int index){ // remove midia de acervo
        try{
        if(index == -1){throw new IllegalArgumentException("Não encontrado.");}
        Midia midia;
        midia = Acervo.get(index);
        Acervo.remove(midia);
    }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            
        }
    }
    public Midia recupera(int index){
        try{
         if(index == -1){throw new IllegalArgumentException("Não encontrado..");}
        
      
       Midia midia;
       midia = Acervo.get(index);
       return midia;
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
            return null;
        }
    }
    public void empresta(UserBase ub, Acervo ac, String stru, String strm){
                     
        Usuario Usr = ub.recupera(ub.consulta(stru));
        Midia Mi = ac.recupera(ac.consulta(strm));
        if(Usr.debito == 0 && Mi.borrowed != -1){
        Mi.Usuario = Usr;     
        Mi.borrowed = -1;
        Usr.emprestimo = new Date();
        Usr.flag = 1;
        System.out.println("Livro" + Mi.tit +"emprestado\n Usuario:"+ Usr.Nome+" Data:"+ Usr.emprestimo);
        }else{
            if(Mi.borrowed == -1){System.out.println("Conteudo ja emprestado");}else{
                System.out.println("Usuario devendo");}
            
        }
    }
    public void devolver(UserBase ub, Acervo ac, String stru, String strm){
        Usuario Usr = ub.recupera(ub.consulta(stru));
        Midia Mi = ac.recupera(ac.consulta(strm));
        Mi.Usuario = null;
        Mi.borrowed = 0;
        Usr.flag = 0;
        Usr.emprestimo = new Date();
        System.out.println("Livro" + Mi.tit +"devolvido \n Usuario:"+ Usr.Nome+" Data:"+ Usr.emprestimo);
    }
  
     public Midia insertMidia(Acervo ac){
       Midia mi = new Midia();
       Scanner myObj = new Scanner(System.in);
       System.out.println("\nEscreva o tit");
       String tit = myObj.nextLine();
       System.out.println("\nEscreva o autor");
       String autor = myObj.nextLine();
       System.out.println("\nEscreva o ano de publicacao");    
       String pub = (myObj.nextLine());
       int pubYr = 0;
       try { // formatação dos dados de nasc
            pubYr = Integer.parseInt(pub);
           
        }catch(NumberFormatException e) {
            e.printStackTrace();
        }
       System.out.println("\nEscreva o tipo de midia");
       String mediaType = myObj.nextLine();
       System.out.println("\nEscreva a editora");
       String editor = myObj.nextLine();
       String local[] = new String[2];
       System.out.println("\nEscreva o andar e a seçao da midia");
       for(int i =0; i< 2; i++){
         local[i] = (myObj.nextLine());
     } 
       mi.inicializa(tit, autor, pubYr, mediaType, editor, local);
       this.Acervo.add(mi);
       return mi;
        
       }
     void printAcervo(){
         Midia mi;
         for(int i = 0 ;i < this.Acervo.size(); i++ ){
         mi = this.Acervo.get(i);
         System.out.println(i + "- tit: "+ mi.tit + " Autor: " + mi.autor + "Ano de publicação: " + mi.pubYr+ "Editor: "+ mi.editor + "Andar:" + mi.local[0] + "secão:"+ mi.local[1]);    
        }
         
     }
     void print(Midia mi){
         System.out.println("tit: " + mi.tit + "Autor: " + mi.autor + "Editor: "+ mi.editor + "Andar:" + mi.local[0] + "secão:"+ mi.local[1]);
     }
     
}
