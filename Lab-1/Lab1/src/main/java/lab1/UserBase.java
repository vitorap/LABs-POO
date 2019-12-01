package lab1;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 *
 * @author vitor
 */
public class UserBase {
   
    ArrayList <Usuario> UsrBase = new <Usuario> ArrayList(); 
    Iterator itr = UsrBase.iterator();
    UserBase(){ // constructor
    
    }
    public ArrayList <Usuario> init(){
        return this.UsrBase;
    }
    
    public void printUsrB(){
        Usuario usr;
        for(int i = 0 ;i < this.UsrBase.size(); i++ ){
         usr = this.UsrBase.get(i);
         System.out.println(usr.Nome + " " + usr.DataNasc + " " + usr.nUsp);
        }
    }
    
    
    public Usuario createUsr(UserBase usrb){
       SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
       Usuario usuario = new Usuario();
       Scanner myObj = new Scanner(System.in);
       System.out.println("\n Insira o nome do usuario\n");
       String name;
       String nac;
       int nu = 0;
       int flag = -1;
       String adr[] = new String[3];        
       name = myObj.nextLine();
       System.out.println("\n Insira a data de nascimento - formato dd/mm/yy\n");
       nac = myObj.nextLine();
       Date Nasc = null;
       try { // formatação dos dados de nasc
            Nasc = format.parse(nac);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
       System.out.println("\n Insira o número Usp:\n");
       while(flag != 1){ // validação de dados do nusp
          nu = Integer.parseInt(myObj.nextLine());
          if(nu <= 9999999 && nu >= 1000000)
            {flag = 1;}
          else
            System.out.println("Digito invalido");
         }
       System.out.println("\n Insira o endereço:"
               + "\n Escreva a rua: \n");
        adr[0] = myObj.nextLine();
        System.out.println("\nEscreva numero \n");
        adr[1] = myObj.nextLine();
        System.out.println("\nEscreva a cidade \n");
        adr[2] = myObj.nextLine();

        usuario.inicializa(name, Nasc, nu, adr);
        
        this.UsrBase.add(usuario);
        
        return usuario;
        
       }
    
    public void cadastraNovo(Usuario usuario){
        this.UsrBase.add(usuario);
    }
    public int consulta(String string){ 
       
        Usuario currUsr;
        int indexOf = -1;

      for(int i=0;i< this.UsrBase.size();i++)  
           {  
            currUsr = UsrBase.get(i);
            if(currUsr.Nome.contains(string) || currUsr.nUsp == (Integer.parseInt(string)) ){
                indexOf = UsrBase.indexOf(currUsr);
            }
           }
      if(indexOf == -1){
          System.out.println("Usuario não encontrado");
      }
        return indexOf;
    }
    public void remove(int index){ // remove midia de acervo
        try{
        if(index == -1){throw new IllegalArgumentException("Usuario não encontrado");
        }else{
       Usuario usuario;
        usuario = UsrBase.get(index);
        UsrBase.remove(usuario);
    }
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            
        }
    }
    public Usuario recupera(int index){
        try{
        if(index == -1){throw new IllegalArgumentException("Usuario não encontrado");}
       Usuario usuario;
       usuario = UsrBase.get(index);
       return usuario;
        }
        catch(IllegalArgumentException ex){
            System.out.println(ex.getMessage());
            System.exit(0);
            
        }
        return null;
        }
    
    
    
    
}
