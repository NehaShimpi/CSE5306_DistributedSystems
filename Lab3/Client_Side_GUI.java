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

public class Client_Side_GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	/*Variable declaration*/
	private static final long serialVersionUID = 1L;
	public static javax.swing.JTextArea convoArea;
	private javax.swing.JButton btnUploadFile, btnOk, btnSendMessage, btnExit, btnSendFile;
	public javax.swing.JTextArea textField;
	JLabel lblClient;
	int i;
	private JPanel contentPane;
	JLabel lblRegisterUsername;
	
	public static String str;

	public Client_Side_GUI() {

		showUI();
	}

	/* UI created using eclipse swing readymade drag and drop features */
	private void showUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Client Running!");
		setResizable(false);
		setBounds(100, 100, 700, 600);

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

		btnOk = new JButton("Connect");
		btnOk.setBounds(323, 462, 89, 23);
		contentPane.add(btnOk);
		btnOk.addActionListener(this);

		btnSendMessage = new JButton("Send Message");
		btnSendMessage.setBounds(323, 500, 120, 23);
		contentPane.add(btnSendMessage);
		btnSendMessage.addActionListener(this);

		btnExit = new JButton("Exit");
		btnExit.setBounds(422, 462, 89, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(this);

		convoArea = new JTextArea();
		convoArea.setBounds(10, 143, 699, 289);
		convoArea.setColumns(10);
		contentPane.add(convoArea);

		btnUploadFile = new JButton("Upload File");
		btnUploadFile.setBounds(450, 500, 110, 23);
		contentPane.add(btnUploadFile);
		btnUploadFile.addActionListener(this);

		btnSendFile = new JButton("Send File");
		btnSendFile.addActionListener(this);
		btnSendFile.setBounds(570, 500, 110, 23);
		contentPane.add(btnSendFile);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*When exit button is clicked, client window is exited*/
		str = textField.getText();
		if (e.getSource() == btnExit) {
			try {/*Server is sent a message before exiting which enables server to know which client is exited*/
				Client.sendText("1" + str);
				System.exit(0);
			} catch (Exception e1) {
				System.exit(0);
			}
		}
		if (e.getSource() == btnOk) {/*When Connect button is clicked*/
			sendUserConnectedMessage();
		}
		if (e.getSource() == btnUploadFile) {/*When Upload file button is clicked*/
			Client.copy();
		}
		if (e.getSource() == btnSendFile) {/*When send file button is clicked,file is sent to server*/
			try {
				Client.sendFile();
			} catch (IOException e1) {

				e1.printStackTrace();
			}

		}
		
		if (e.getSource() == btnSendMessage) {/*When send message button is clicked,words are added to queue*/
			try {
				String message = textField.getText().toString().trim();
				Client.addMessageToQueue(message);/*words are added to queue*/
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}
	
	public static void sendUserConnectedMessage() {
		try {
			Client.sendText("2" + str);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

}
