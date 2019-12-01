package server;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import javax.swing.*;

public class Server extends JFrame 
{
   private JTextArea displayArea; //Area onde a info é mostrada pro usuario
   private ServerSocket server; // socket 
   private ExecutorService runChat; // executa o chat
   private List<chatClient> clients; // Lista dos clientes

   public Server()
   {
      super( "Servidor Morse" );

      runChat = Executors.newCachedThreadPool();
      clients = new ArrayList<>();
      
      try 
      {
         server = new ServerSocket( 1234, 100 ); // porta 1234, maximo 100 clientes
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
      
      displayArea = new JTextArea(); 
      displayArea.setEditable(false); 
      displayArea.setBackground(Color.BLACK);
      displayArea.setForeground(Color.GREEN);
      add( new JScrollPane( displayArea ), BorderLayout.CENTER );

      setSize( 600, 300 ); 
      setVisible( true ); 
      displayMessage("Servidor morse inicia");
   }

    public void execute() {
       
       while ( clients.size() < 100 ) // maximo 100 clientes
       {
          try 
          {
             clients.add( new chatClient( server.accept(), clients.size()) );
             displayMessage( "Conexao " + clients.size() + " recebida do: "
                             + clients.get(clients.size() -1 ).connection.getInetAddress().getHostName() );             
             runChat.execute(clients.get(clients.size() -1 ));
          } 
          catch ( IOException ioException ) 
          {
             ioException.printStackTrace();
          } 
       } 
   } 

   private void displayMessage( final String messageToDisplay)
   {
       SwingUtilities.invokeLater(
               new Runnable()
               {
                   public void run() 
                   {
                       displayArea.append( messageToDisplay + "\n");
                   } 
               } 
       ); 
   } 
   
   private void sendData( String message )
   {
      try 
      {
          for (chatClient client : clients) 
          {
              client.output.writeObject( message );
              client.output.flush(); 
          }    
      } 
      catch ( IOException ioException ) 
      {
         displayMessage( "Erro escrevendo objeto" );
      } 
   } 
   
  
    private void removeClient( int removeClientNum)
    {
          displayMessage("Removendo cliente " + removeClientNum);
          clients.remove(removeClientNum); 
          if ( (clients.size() > 0) && (removeClientNum < clients.size() ) )
          {   
              // Reordenar os numeros dos clientes
              for (chatClient client : clients)
              {
                  if( client.clientNumber != 0) // Ignora cliente 0
                    client.clientNumber -= 1;
              } 
          }
          displayMessage("Clientes reordenados");
    } 
   
   private class chatClient implements Runnable 
   {
       private ObjectOutputStream output; 
       private ObjectInputStream input; 
       private Socket connection; 
       private int clientNumber;
       private String clientName;  
       private Boolean openConnection; 

      public chatClient( Socket socket, int number )
      {
         connection = socket;  
         clientNumber = number;  
         try
         {
             getStreams();
         }
         catch( IOException ioException )
         {
             ioException.printStackTrace();
         }
         openConnection = true;
      } 

   private void getStreams() throws IOException
   {
      output = new ObjectOutputStream( connection.getOutputStream() );
      output.flush(); 

      input = new ObjectInputStream( connection.getInputStream() );
      try
      {
          clientName = (String) input.readObject();
      }
      catch ( ClassNotFoundException classNotFoundException )
      {}
      displayMessage("recebe I/O streams para o cliente " + clientName + "(" + clientNumber + ")");
   } 
   
   private void processConnection() throws IOException
   {
      String message = "Conexao com o cliente " + clientNumber + " bem sucedida";

      while( openConnection )
      { 
         try 
         {
            message = ( String ) input.readObject();  
            if( message.equalsIgnoreCase("\\quit") )  
                openConnection = false;
            else  
                sendData( clientName + "\n" + message );
         }  
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            sendData( "Objeto de tipo desconhecido recebido" );
         }  

      }  
   }  

    
   private void closeConnection() 
   {
       displayMessage("\nEncerrando conexao do cliente " + clientNumber + "\n");      
      try 
      {
          output.close();  
          input.close();  
          connection.close();  
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      }  
   }  
   
      public void run()
      {
            try 
            {
                processConnection(); 
            } 
            catch ( EOFException eofException ) 
            {
                sendData( "\nServer encerrou conexao do cliente " + clientNumber + "\n" );
            }  
            catch ( IOException ioException ) 
            {
                ioException.printStackTrace();
            }  
            finally 
            {
                closeConnection(); 
                removeClient(clientNumber); 
                Thread.currentThread().interrupt(); 
            } 
      }  
   }  
} 