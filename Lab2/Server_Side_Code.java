/*Name:-Neha Anil Shimpi
Student id:-1001827779
*/
/*https://www.ejbtutorial.com/distributed-systems/using-sockets-to-create-a-group-chat-system-with-a-graphical-interface*/
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server_Side_Code {

	ArrayList<Client_Handler> client_list = new ArrayList<Client_Handler>();
	Timer pollTimer;

	public Server_Side_Code() {
		try {
			SpellChecker.loadWordsFromFile();/*words are loaded from lexicon file*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*Server Side Code begins from here*/
	public static void main(String[] args) {
		Server_Side_Code s = new Server_Side_Code();
		new Server_Side_GUI();/*Server UI is loaded*/
		s.conversation(port);
	}

	/* Handling multiple clients by assigning threads to each of them */
	public void conversation(int port) {
		try {
			server = new ServerSocket(port);
			Server_Side_GUI.Conversations.setText("Listening");
			stopPollTimer(); /*After socket connection if already any timer is running clear it*/
			startPollTimer(); /*Start new timer*/

			while (true) {/*This part of code enables handling of multiple clients*/
				socket = server.accept();
				Client_Handler myClient = new Client_Handler(socket);
				Thread clientThread = new Thread(myClient);
				client_list.add(myClient);/*New thread is spawned for every connected client*/
				clientThread.start();
			}
		} catch (IOException e) {
			System.out.println("Server Already opened or Server Closed");
		}
	}
	
	
/*https://stackoverflow.com/questions/9413656/how-to-use-timer-class-to-call-a-method-do-something-reset-timer-repeat*/
	private void startPollTimer() {
		pollTimer = new Timer();
     
		pollTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				/*loop through every connected client*/
				for (int i = 0; i < client_list.size(); i++) {
					if(client_list.get(i).socket.isClosed())
					{
						client_list.remove(i);
					}
					else if (client_list.get(i) != null) {
						client_list.get(i).pollClients(); /*Poll every connected client*/
						
					}
				}

			}
		}, 60 * 1000, 60 * 1000);/*Give delay of 60 seconds and repeat the task every 60second*/
		
		
	}

	private void stopPollTimer() {/*Cancel the timer*/
		if (pollTimer != null) {
			pollTimer.cancel();
		}
	}
	/*Variable declaration*/
	public static ServerSocket server = null;
	public Socket socket = null;
	BufferedReader br;
	static int port = 5556;/* Port on which communication will start*/

}
