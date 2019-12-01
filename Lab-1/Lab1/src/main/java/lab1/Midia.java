package lab1;

/**
 *
 * @author vitor
 */
public class Midia {
    
    String tit;
    String autor;
    int pubYr;
    String mediaType;
    String editor;
    String local[];
    Usuario Usuario;
    int borrowed = 0;
    
    Midia(){
        this.local = new String[2];
       }
    
    public void inicializa(String tit, String autor,int pubYr,String mediaType,String editor,String local[]){
        this.tit = tit;
        this.autor = autor;
        this.editor = editor;
        this.pubYr = pubYr;
        this.mediaType = mediaType;
        for(int i = 0; i < 2; i++){
            this.local[i]= local[i];
        }   
        
    }
}
