package cliente;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Client extends JFrame 
{
   private JTextField enterField; 
   private JTextArea displayArea; 
   private JTextArea notificationArea; 
   private JScrollPane notices; 
   private ObjectOutputStream output; 
   private ObjectInputStream input; 
   private String message = ""; 
   private String chatServer; 
   private String name = ""; 
   private Socket client; 
   private Boolean openConnection; 
   final private Character[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
       'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
       'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9','0', '.',
   ',', ':', '?', '\'', '-', '/', '"', '@', '='};
   // tabela do codigo morse
   final private String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
       "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
   ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
   "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", 
   "-----", ".-.-.-", "--..--", "---...", "..--..", ".----.", "-....-", "-..-.",
   ".-..-.", ".--.-.", "-...-"};
   private  Map<Character, String> encodeMorse; // Map para codificar
   private Map<String, Character> decodeMorse; // Map para decodificar

   public Client( String host )
   {
      super( "Pratica 4" ); 

      chatServer = host;
      while ( (name.length() == 0) || (name.replace(" ", "").length() == 0))
        name = JOptionPane.showInputDialog("Digite seu nome:");
      enterField = new JTextField(); 
      enterField.setEditable( false ); 
      enterField.addActionListener( 
         new ActionListener() 
         {
            public void actionPerformed( ActionEvent event )
            {
                if( event.getActionCommand().equalsIgnoreCase("terminate"))
                {
                    sendData("\\quit");
                    openConnection = false;
                }
                else
                    sendData(convertToMorse( event.getActionCommand()) );
                enterField.setText( "" ); 
            } 
         } 
      ); 
      
      this.addWindowListener( 
        new WindowAdapter()
        {
            @Override
            public void windowClosing( WindowEvent e)
            {
                SwingUtilities.invokeLater(
                   new Runnable()
                   {
                      public void run() 
                      {
                        if (openConnection)
                        {
                            sendData("\\quit");
                            openConnection = false;
                        }
                        dispose();
                        System.exit(1);                          
                      } 
                   }  
                ); 
            } 
        } 
      ); 

      add( enterField, BorderLayout.NORTH ); 

      displayArea = new JTextArea(); 
      displayArea.setEditable(false); 
      add( new JScrollPane( displayArea ), BorderLayout.CENTER ); 
      
      notificationArea = new JTextArea(); 
      notificationArea.setEditable(false); 
      notificationArea.setBackground(Color.LIGHT_GRAY);
      notificationArea.setForeground(Color.DARK_GRAY);
      notices = new JScrollPane(notificationArea);
      notices.setPreferredSize( new Dimension(800, 150)); 
      add( notices, BorderLayout.SOUTH); 

      setSize( 800, 600 ); 
      setVisible( true ); 
      
      openConnection = true;
      encodeMorse = new TreeMap<>();
      decodeMorse = new TreeMap<>();
      for(int i = 0; i < characters.length; i++)
      {
          encodeMorse.put( characters[i], codes[i]);
          decodeMorse.put( codes[i], characters[i]);
      }
   } 

   // conecta ao server e processa as mensagens
   public void runClient() 
   {
       Boolean socketConnected = false;
       try 
       {
           
           if ( socketConnected = connectToServer() )
           {
               getStreams(); 
               processConnection(); 
           } 
       } 
       catch ( EOFException eofException ) 
       {
          displayNotice( "Cliente encerrou conexao" );
       } 
       catch ( IOException ioException ) 
       {
          ioException.printStackTrace();
       } 
       finally 
       {
          
           if (socketConnected)
             closeConnection(); 
       } 
    } 

   // Connectar ao server
   private boolean connectToServer() throws IOException
   {      
      displayNotice( "seja bem vindo " + name + "!" ); 

      try
      {
        
        client = new Socket( InetAddress.getByName( chatServer ), 1234 );
      }
      catch(IOException e)
      {
          displayNotice("conexao com servidor nao foi estabelecida");
          return false;
      }
      
      displayNotice( "conectado a : " + client.getInetAddress().getHostName() );
      return true;
   } 

   private void getStreams() throws IOException
   {
      output = new ObjectOutputStream( client.getOutputStream() );
      output.writeObject(name); 
      output.flush(); 

      input = new ObjectInputStream( client.getInputStream() );

      displayNotice( "recebidas streams I/O" );
   } 

   private void processConnection() throws IOException
   {
      setTextFieldEditable( true );
      int newLine; 

      while ( openConnection ) 
      { 
         try  
         {
            message = ( String ) input.readObject(); 
            
            newLine = message.indexOf('\n');
            displayMessage( message.substring( 0, newLine) );
            displayMessage( "     " + convertToText(message.substring(newLine + 1)) );
         } 
         catch ( ClassNotFoundException classNotFoundException ) 
         {
            displayNotice( "Objeto de tipo desconhecido recebido" );
         } 

      } 
   } 

   private void closeConnection() 
   {
      displayNotice( "Encerrando conexao" );
      setTextFieldEditable( false ); 

      try 
      {
         output.close(); 
         input.close(); 
         client.close(); 
      } 
      catch ( IOException ioException ) 
      {
         ioException.printStackTrace();
      } 
   }  

   private void sendData( String message )
   {
      try  
      {
          output.writeObject(message); 
          output.flush();  
      } 
      catch ( IOException ioException )
      {
         displayNotice( "Erro ao escrever objeto" );
      }  
   }  

   private void displayMessage( final String messageToDisplay )
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
   
   private void displayNotice( final String messageToDisplay )
   {
      SwingUtilities.invokeLater(
         new Runnable()
         {
            public void run() 
            {
                notificationArea.append( messageToDisplay + "\n");
            }  
         }   
      );  
   }  

   private void setTextFieldEditable( final boolean editable )
   {
      SwingUtilities.invokeLater(
         new Runnable() 
         {
            public void run()  
            {
               enterField.setEditable( editable );
            }  
         }  
      );  
   }  
   
    
   private String convertToMorse( String encodeMessage )
   {
       String[] tempWords = encodeMessage.split(" ");
       Character tempChar; 
       StringBuilder codedString = new StringBuilder(); 
       for (String token : tempWords)  
       {
           for (int i = 0; i < token.length(); i++) 
           {
               tempChar = Character.toLowerCase(token.charAt(i) );
               if ( encodeMorse.containsKey(tempChar) )
                   codedString.append( encodeMorse.get( tempChar ) );
               else
                   codedString.append( token.charAt(i));
               if (i != (token.length() - 1) )
                   codedString.append('/'); // Separa o codigo morse com um /
           }
           codedString.append("//"); // separa palavras com  //
       }
       
       displayNotice( "Sua mensagem em morse:\n          " + 
                      codedString.toString() + "\n" );
       
       return codedString.toString();
   } 
   
   private String convertToText( String decodeMessage )
   {
       String[] tempWords = decodeMessage.split("//");
       String[] tempString; 
       StringBuilder decodedString = new StringBuilder(); 
       Boolean capNext = true; 
 
       for ( String token : tempWords) 
       {
           tempString = token.split("/"); 
           for (String code : tempString) 
           {
               if ( decodeMorse.containsKey( code ) )
               {
                   if (capNext) 
                   {
                       decodedString.append( Character.toUpperCase(decodeMorse.get( code )) );
                       capNext = false;
                   } 
                   else
                       decodedString.append( decodeMorse.get( code ) );
                    if ( code.equals(".-.-.-") || code.equals("..--..") || code.equals('!') )
                        capNext = true;                   
               } 
               else
                   decodedString.append( code );
           } 
           decodedString.append(" "); 
       } 
       
       return decodedString.toString();
   } 
   
} 