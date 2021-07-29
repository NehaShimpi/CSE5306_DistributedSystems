/*Name:-Neha Anil Shimpi
Student id:-1001827779
*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*Class to manage clients inputs*/
public class Client_Handler extends Server_Side_Code implements Runnable {

	DataInputStream in;
	DataOutputStream dos;
	static HashSet<String> list = new HashSet<>();
	static int count = 0;

	public Client_Handler(Socket socket) {
		this.socket = socket;
	}
/*Function to poll clients*/
	public void pollClients() {
		try {
			if (dos != null) {/*Ask client every 60 sec if its queue is full or not*/
				dos.writeUTF("CLIENTS_MESSAGE_QUEUE_CHECK");
				dos.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {

		try {
			/* Continuously listen to client inputs and decides the action */
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String str;
			while (true) {
				str = in.readUTF();
				//System.out.println(str);
				/* If incoming text has 1 appended at the beginning, Client is disconnected */
				if (str.charAt(0) == '1') {

					Server_Side_GUI.Conversations.append("\n" + str.substring(1) + " Disconnected");
					list.remove(str);
					System.out.println(socket);
					
					count--;

				}
				/*If incoming text has 2 appended at the beginning, Client is connected */
				else if (str.charAt(0) == '2') {

					if (count < 3) {

						if (!list.contains(str)) {
							list.add(str);
							count++;
							/*Displays message on server text area*/
							Server_Side_GUI.Conversations.append("\n" + str.substring(1) + " Connected");
							dos.writeUTF("hey client!you are connected");
							dos.flush();
						} else {/*If client with same name already present*/
							Server_Side_GUI.Conversations
									.append("\n" + "Client Name already present.Please enter another name!");
						}

					} else {/*If more than 3 clients are already connected*/
						Server_Side_GUI.Conversations.append("\n" + "You are exceeding the server limit!!");
						dos.writeUTF("Sorry...Cant connect you through!!Close the window");
						dos.flush();
					}

				}
/*If incoming text has 3 appended at the beginning,File sent by client is received*/
				else if (str.charAt(0) == '3') {	
					Server_Side_GUI.Conversations.append("\n" + "File received");
					String wer = SpellChecker.checkSpelling(str.substring(1));
					dos.writeUTF("1"+wer);
					dos.flush();
					/*If the incoming text has nothing appended in the begining this means queue strings have arrived*/
				} else 
				{	
					SpellChecker.addWordstoSet(str);/*Goes to spellChecker class to add words to file*/
				}
			}

		} catch (IOException e) {

			System.out.println("Client has disconnected");
		} finally/* Close all the sockets and streams */
		{
			try {
				in.close();
				dos.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
