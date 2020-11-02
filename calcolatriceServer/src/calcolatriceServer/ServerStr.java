
package calcolatriceServer;

import java.io.*;
import java.net.*;
import java.util.*;


public class ServerStr {
    
    ServerSocket server = null;
    Socket client = null;
    String n1Ricevuto=null;
    String n2Ricevuto=null;
    String risposta;
    char operatoreRicevuto;
    double n1, n2, risultato = 0; 
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;
    
    public Socket attendi(){
        try{
            System.out.println("1 SERVER partito in esecuzione ");
            server = new ServerSocket(8888);
            client = server.accept();
            server.close();
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
            System.out.println(1);
        }
        return client;
    }
    
    public void comunica(){
        try{
            System.out.println("3 benvenuto client, scrvi il primo valore dell'operazione. Attendo ");
            n1Ricevuto = inDalClient.readLine();
            System.out.println("Inserisci il secondo valore. Attendo");
            n2Ricevuto = inDalClient.readLine();
            System.out.println("Inserisci l'operatore. Attendo");
            operatoreRicevuto= (char) inDalClient.read();
            System.out.println("6 volori ricevuti");
            n1= Double.parseDouble(n1Ricevuto);
            n2=Double.parseDouble(n2Ricevuto);
            switch(operatoreRicevuto){
                case '+':
                    risultato=n1+n2;
                    risposta=Double.toString(risultato);
                    break;
                case '-':
                    risultato=n1-n2;
                    risposta=Double.toString(risultato);
                    break;
                case '/':
                    risultato=n1/n2;
                    risposta=Double.toString(risultato);
                    break;
                case '*':
                    risultato=n1*n2;
                    risposta=Double.toString(risultato);
                    break;
                default:
                    risposta="Operatore inserito non valido";
            }
            System.out.println("7 invio il risultato al client ");
            outVersoClient.writeBytes(risposta+'\n');
            System.out.println("9 SERVER: fine elaborazione buona notte!");
            client.close();
        }
        catch(Exception e){
           System.out.println(e.getMessage());  
        }
    }
    
    public static void main(String args[]){
        ServerStr server = new ServerStr();
        server.attendi();
        server.comunica();
    }
    
}
