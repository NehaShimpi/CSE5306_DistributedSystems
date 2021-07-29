/*UI for Server*/

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

public class Server_Side_GUI extends JFrame {
	
	public Server_Side_GUI()
	{	
		showUI();
	}

	private void showUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Server Running!");
		setResizable(false);
		setBounds(100, 100, 700,600 );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblClient = new JLabel("Server");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblClient.setBounds(23, 11, 87, 55);
		contentPane.add(lblClient);

		btnExit = new JButton("EXIT");
		btnExit.setBounds(600, 462, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Server_Side_Code.server!=null)
				{
					try {
						Server_Side_Code.server.close();
						System.exit(0);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
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
   
    JLabel lblClient;
    int i;
    JButton btnExit;
    JTextArea textField;
    private JPanel contentPane;
    JLabel lblRegisterUsername;
	
}
