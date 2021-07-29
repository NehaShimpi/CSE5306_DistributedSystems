/*Name:-Neha Anil Shimpi
Student id:-1001827779
*/

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/*Java file for Server UI in Java Swing*/
public class Server_Side_GUI extends JFrame {
	
	public Server_Side_GUI()
	{	
		showUI();
	}
/*Function that is loaded as soon as the program loads*/
	private void showUI() {
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Server");
		setResizable(false);
		setBounds(100, 100, 700,600 );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblClient = new JLabel("Main Server");/*Label in Swing*/
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblClient.setBounds(23, 11, 287, 55);
		contentPane.add(lblClient);

		btnExit = new JButton("EXIT");/*Java button for exit*/
		btnExit.setBounds(600, 462, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			/*Action performed after exit button is pressed*/
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Server_Side_Code.server!=null)
				{
					try {
						Server_Side_Code.server.close();
						System.exit(0);/*Program exits after exit button is pressed*/
					} catch (IOException e1) {
				
						System.out.println("Server Closed I guess!!");
					}
				}
				
			}
		});
		
		Conversations = new JTextArea();
		Conversations.setBounds(10, 143, 699, 289);
		Conversations.setColumns(10);
        contentPane.add(Conversations);
   
		
		setVisible(true);
	}
	public static javax.swing.JTextArea Conversations;
	/*Variable declaration*/
    JLabel lblClient;
    int i;
    JButton btnExit;
    JTextArea textField;
    private JPanel contentPane;
    JLabel lblRegisterUsername;
	
}
