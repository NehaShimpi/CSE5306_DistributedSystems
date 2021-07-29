/*https://www.ejbtutorial.com/distributed-systems/using-sockets-to-create-a-group-chat-system-with-a-graphical-interface*/
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server_Side_Code{
	
	public static void main(String[] args) {	
		Server_Side_Code s = new Server_Side_Code();
		new Server_Side_GUI();
		s.conversation(port);	
	}
	/*Handling multiple clients by assigning threads to each of them*/
	public void conversation(int port) {
		try {
			   server = new ServerSocket(port);
			   Server_Side_GUI.Conversations.setText("Listening");			   
	        while(true){
	             socket = server.accept();
	             Thread clientThread = new Thread(new Client_Handler(socket));
	             clientThread.start();
	             }	 
		} catch (IOException e) {
			System.out.println("Server Already opened or Server Closed");	  
			}	
	}
	public static ServerSocket server   = null; 
	public  Socket socket   = null;
	BufferedReader br;
	static int  port = 5556;
	
}
