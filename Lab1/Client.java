//https://www.geeksforgeeks.org/socket-programming-in-java/
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;

public class Client implements Runnable {
    static Socket client = null;
   static DataInputStream in;
   static DataOutputStream dos;
	static OutputStream os;
	static JFileChooser fc;
	static File selectedFile;
	static BufferedOutputStream bw;
/*start point of client- Establishing connection and continuous listening of server output.*/
public static void main(String[] args) {
 
 try {
	client = new Socket("localhost",5556);
	dos = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
	 in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
	new Client_Side_GUI();
	Thread t = new Thread(new Client()); /* thread to listen outputof server continuously.*/
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
	    Client_Side_GUI.convoArea.append("\nServer said:-"+line);/*Displaying message from server on textarea*/
	    
	  }
     
  }catch (IOException ex) {
          System.out.println("Server closed I guess!");     
       }
}
/*Function to send file to server*/
public static void sendFile() throws IOException {

		
		 FileInputStream fis = new FileInputStream(selectedFile);
		 BufferedReader in = new BufferedReader(new InputStreamReader(fis));
		 String line;
		 StringBuilder sb = new StringBuilder();
		 while((line=in.readLine())!=null)
		 {
			 sb.append(line);	 
		 }
		 
		 dos.writeUTF(sb.toString());	
		 dos.flush();  
         Client_Side_GUI.convoArea.append("\n"+"File sent");
}

/*Function to send text to server*/
static void sendText(String str) throws IOException {
	dos.writeUTF(str);
	dos.flush();
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
