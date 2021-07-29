
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashSet;

/*Class to manage clients inputs*/
public class Client_Handler extends Server_Side_Code implements Runnable{

	Socket newClient;
    DataInputStream in;
    DataOutputStream dos;
    static HashSet<Socket> list = new HashSet<>();
    static int count=0;
	public Client_Handler(Socket socket) {
	this.socket = socket;
	}
	
	@Override
	public  void run() {
	  
        try {
        	/*Continuously listen to client inputs and decides the s action*/
			dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        	in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String str;
			while(true)
			{
			str=in.readUTF() ;
			System.out.println(str);
			/*Client is disconnected*/
			if(str.charAt(0)=='1')
			{
			      
			    	  Server_Side_GUI.Conversations.append("\n"+str.substring(1)+" Disconnected");	  
			    	  list.remove(socket);
			    	  count--;    	  
			    	  
			      
			}
			/*Client is connected*/
			else if(str.charAt(0)=='2')
			{    
			    	  
			    	  if(count<3)
						{
			    		  
							if(!list.contains(socket))
							{
								 list.add(socket);	
								 count++;
								 Server_Side_GUI.Conversations.append("\n"+str.substring(1)+" Connected");
								 dos.writeUTF("hey client!you are connected");
								 dos.flush();
							}
							else
							{
								Server_Side_GUI.Conversations.append("\n"+"Client Name already present.Please enter another name!");
							}
				    	  
					    }
						else
						{	
							Server_Side_GUI.Conversations.append("\n"+"You are exceeding the server limit!!");	
							dos.writeUTF("Sorry...Cant connect you through!!Close the window");
							dos.flush();	
						}
		    	  
			      } 
			else /*File sent by client is received*/    
			      {
				     Server_Side_GUI.Conversations.append("\n"+"File received");
			    	 String wer = SpellChecker.checkSpelling(str);	 
			    	 dos.writeUTF(wer);
			    	 dos.flush();
			      }
			}
	
		} catch (IOException e) {

			System.out.println("Client has disconnected");
		}
		finally/*Close all the sockets and streams*/
		{
			try {
				in.close();
			    dos.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}

}
