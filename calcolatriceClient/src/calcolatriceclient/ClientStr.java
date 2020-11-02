
package calcolatriceclient;

import java.io.*;
import java.net.*;

public class ClientStr {
  String nomeServer="localhost";
  int portaServer=8888;
  Socket miosocket;
  BufferedReader tastiera;
  String stringaClient;
  String risultatoRicevuto;
  DataOutputStream outVersoServer;
  BufferedReader inDalServer; 
    
   public Socket connetti(){
      System.out.println("2 CLIENT partito in esecuzione");
      try{
          tastiera=new BufferedReader(new InputStreamReader(System.in));
          miosocket= new Socket(InetAddress.getLocalHost(), 8888);
          outVersoServer= new DataOutputStream(miosocket.getOutputStream());
          inDalServer= new BufferedReader(new InputStreamReader(miosocket.getInputStream()));     
      }
      catch (UnknownHostException e){
          System.err.println("Host sconosciuto");
      }
      catch (Exception e){
          System.out.println(e.getMessage());
          System.out.println("Errore durante la connessione!");
          System.exit(1); 
      }
      return miosocket;
  }
  
  public void comunica(){
      try{
          System.out.println("4 CLIENT inserisci il primo valore da trasmettere al server:" + '\n');
          stringaClient= tastiera.readLine();
          outVersoServer.writeBytes(stringaClient+'\n');
          System.out.println("5 CLIENT invio il primo valore al server ");
          System.out.println("Inserisci il secondo valore da trasmettere al server:" + '\n');
          stringaClient=tastiera.readLine();
          System.out.println("5 CLIENT invio il secondo valore al server ");
          outVersoServer.writeBytes(stringaClient+'\n');
          System.out.println("Inserisci l'operatore da trasmettere al server:" + '\n');
          stringaClient=tastiera.readLine();
          System.out.println("5 CLIENT invio l'operatore al server e attendo ");
          outVersoServer.writeBytes(stringaClient+'\n');
          risultatoRicevuto=inDalServer.readLine();
          System.out.println("8 CLIENT risposta dal server " +'\n'+ risultatoRicevuto);
          System.out.println("9 CLIENT: termina elaborazione e chiude connessione");
          miosocket.close();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
          System.out.println("Errore durante la comunicazione col server!");
          System.exit(1);
      }
  }
  
  public static void main (String args[]){
      ClientStr cliente= new ClientStr();
      cliente.connetti();
      cliente.comunica();
  } 
}
