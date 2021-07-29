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

public class Client_Side_GUI extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public static javax.swing.JTextArea convoArea;
	    private javax.swing.JButton btnUploadFile,btnOk,btnExit, btnNewButton;
	    public  javax.swing.JTextArea textField;
	    JLabel lblClient;
	    int i;
	    private JPanel contentPane;
	    JLabel lblRegisterUsername;
	    public Client_Side_GUI() {	
		    
	        showUI();
	     }
	/*UI created using eclipse swing readymade drag and drop features*/
	private void showUI() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Client Running!");
		setResizable(false);
		setBounds(100, 100, 700,600 );
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblClient = new JLabel("CLIENT");
		lblClient.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblClient.setBounds(23, 11, 87, 55);
		contentPane.add(lblClient);
		
		lblRegisterUsername = new JLabel("Register User-Name");
		lblRegisterUsername.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblRegisterUsername.setBounds(10, 442, 169, 55);
		contentPane.add(lblRegisterUsername);
		
		textField = new JTextArea();
		textField.setBounds(211, 463, 100, 50);
		textField.setColumns(10);
		contentPane.add(textField);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(323, 462, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(this);
		
		btnExit = new JButton("EXIT");
		btnExit.setBounds(600, 462, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);
		
		convoArea = new JTextArea();
		convoArea.setBounds(10, 143, 699, 289);
		convoArea.setColumns(10);
        contentPane.add(convoArea);
     
        
		btnUploadFile = new JButton("Upload File");
		btnUploadFile.setBounds(422, 459, 89, 29);
		contentPane.add(btnUploadFile);
		btnUploadFile.addActionListener(this);
		
	    btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(521, 462, 72, 21);
		contentPane.add(btnNewButton);
		
		setVisible(true);
	}
 
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String str = textField.getText();
			if(e.getSource()==btnExit)
			{	
				try {
					Client.sendText("1"+str);
					System.exit(0);
				} catch (IOException e1) {
					
					System.exit(0);
				}
			}
			if(e.getSource()==btnOk)
			{
				
				try {
					Client.sendText("2"+str);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
			if(e.getSource()==btnUploadFile)
			{	
				Client.copy();			
			}		
			if(e.getSource()==btnNewButton) 
			{
				try {
					Client.sendFile();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			    	
			}
			
		}
		
}
