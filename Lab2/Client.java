/*Name:-Neha Anil Shimpi
Student id:-1001827779
*/
/*https://www.geeksforgeeks.org/socket-programming-in-java*/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

public class Client implements Runnable {
/*Variable declaration*/
    static Socket client = null;
	static DataInputStream in;
	static DataOutputStream dos;
	static FileOutputStream fos;
	static OutputStream os;
	static JFileChooser fc;
	static File selectedFile;
	static BufferedOutputStream bw;
	public static ArrayList<String> message_queue = new ArrayList<String>();
	
/*start point of client- Establishing connection and continuous listening of server output.*/
public static void main(String[] args) {
 
 try {
	client = new Socket("localhost",5556);/*accepts the connection at port 5556*/
	dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
	 in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
	new Client_Side_GUI();
	
	Thread t = new Thread(new Client()); /* thread to listen output of server continuously.*/
	  t.start();
     }  catch (IOException e) {
	e.printStackTrace();
   }
 
}
//
public void run () {
  try {         
	  String line;
	  	
	  while(true) {
	    line = in.readUTF();
	    /*Server response is received and messages from queue are sent to server*/
	    if(line.equals("CLIENTS_MESSAGE_QUEUE_CHECK")) {
	    	
	    	sendAllMessagesInQueue();
	    }
	    else if(line.charAt(0)=='1')/*if the response from server has 1 appended write it to file*/
	    {
	    	FileWriter myWriter = new FileWriter("D:/Neha_Documents/Study/Spring2021/sample.txt");
			myWriter.write(line.substring(1));	
		    myWriter.close();
	    	
	    }
	    else {
	    	Client_Side_GUI.convoArea.append("\nServer said:-"+line);/*Displaying message from server on textarea*/
	    } 
	    
	  }
     
  }catch (IOException ex) {
          System.out.println("Server closed I guess!");     
       }
}
/*Function to send file to server*/
public static void sendFile() throws IOException {

	/*Code to create a file and send the file to server*/
		 FileInputStream fis = new FileInputStream(selectedFile);
		 BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		 String line;
		 StringBuilder sb = new StringBuilder();
		 sb.append("3");
		 while((line=in.readLine())!=null)
		 {
			 sb.append(line);	 
		 }
		 System.out.println(sb.toString());
		 dos.writeUTF(sb.toString());	/*File content written to server side*/
		 dos.flush();  
         Client_Side_GUI.convoArea.append("\n"+"File sent");
}

/*Function to send text to server*/
static void sendText(String str) {
	try {
		dos.writeUTF(str);
		dos.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
/*message is added to queue*/
public static void addMessageToQueue(String message) {
	message_queue.add(message);
	
}

public static void printQueue() {
	for(int i = 0; i<message_queue.size(); i++) {
		System.out.println("message_queue["+i+"]="+message_queue.get(i));
	}
}
/*Message in queue are printed on screen when sent to server*/
public static void sendAllMessagesInQueue() {
	for(int i = 0; i<message_queue.size(); i++) {
		Client_Side_GUI.convoArea.append("\n"+message_queue.get(i));
		sendText(message_queue.get(i));/*Messages sent to server*/
		
	}

	message_queue.clear();
}


/* Function to choose file from dialog box*/
/*https://stackoverflow.com/questions/19152256/how-to-make-gui-client-to-upload-file-in-java*/
public static void copy() {
	fc = new JFileChooser();
	int x = fc.showOpenDialog(null);
	if (x == JFileChooser.APPROVE_OPTION) {
		 selectedFile = fc.getSelectedFile();
	}
   Client_Side_GUI.convoArea.append("\n"+"Uploading File.. ..");	
  }

}
