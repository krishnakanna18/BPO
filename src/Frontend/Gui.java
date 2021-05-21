package Frontend;

import Backend.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class Gui extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
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
	public Gui() {
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
		
		JButton btnNewButton = new JButton("User Registration");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(351, 423, 185, 30);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Here");
				Admin t = new Admin();
				String username = textField.getText();
				String password = passwordField.getText();
				Integer status = t.login(username, password);
				if( status.equals(1))
				{
					System.out.println("Successfully Logged in");
				}
				else
				{
					System.out.println("Not Authorised");
				}
			}
		});
		btnNewButton_1.setBackground(new Color(127, 255, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(366, 336, 105, 30);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New User? Register Now");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(366, 376, 185, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserName");
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
		
		JButton btnCompanySignup = new JButton("Company Registration");
		btnCompanySignup.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCompanySignup.setBounds(566, 423, 185, 30);
		contentPane.add(btnCompanySignup);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(366, 262, 252, 39);
		contentPane.add(passwordField);
		setUndecorated(true);
	}
}
