
package server;

import javax.swing.JFrame;

public class ServerTest
{
   public static void main( String[] args )
   {
      Server application = new Server(); // Cria o server
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.execute(); // Roda o server
   } 
} 