package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.Admin;

public class SignIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JPasswordField passwordField;
	private JLabel DispMsg;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignIn() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 800, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(366, 190, 252, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				SignUp frame = new SignUp();
				frame.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(513, 371, 105, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin t = new Admin();
				String username = textField.getText();
				String password = passwordField.getText();
				Integer status = t.login(username, password);
				if( status.equals(1))
				{
					DispMsg.setText("Successfully Logged in!");
					dispose();
				}
				else if( status.equals(0))
				{
					DispMsg.setText("Wrong password");
				}
				else
				{
					DispMsg.setText("User not Found");
				}
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(366, 371, 105, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(263, 183, 105, 47);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(263, 256, 105, 47);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("HKM BPO Services");
		lblNewLabel_2.setFont(new Font("Sitka Text", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(263, 71, 324, 122);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(366, 262, 252, 39);
		contentPane.add(passwordField);
		
		DispMsg = new JLabel("");
		DispMsg.setFont(new Font("Tahoma", Font.BOLD, 20));
		DispMsg.setBounds(366, 323, 252, 30);
		contentPane.add(DispMsg);
		setUndecorated(true);
	}
}
