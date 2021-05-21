package Frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class CompanyReg extends JFrame {

	private JPanel contentPane;
	private final JTextField textField = new JTextField();
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblUserRegistration_1;
	private JLabel lblUserRegistration_2;
	private JLabel lblUserRegistration_4;
	private JLabel lblUserRegistration_5;
	private JLabel lblUserRegistration_7;
	private JPasswordField passwordField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignIn frame = new UserSignIn();
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
	public CompanyReg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 956, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		textField.setBounds(337, 146, 381, 42);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(337, 230, 381, 42);
		textField_1.setColumns(10);
		contentPane.add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(337, 335, 519, 111);
		textField_3.setColumns(10);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(337, 512, 381, 42);
		textField_4.setColumns(10);
		contentPane.add(textField_4);
		
		JLabel lblUserRegistration = new JLabel("Company Registration");
		lblUserRegistration.setBounds(333, 58, 347, 56);
		lblUserRegistration.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
		contentPane.add(lblUserRegistration);
		
		lblUserRegistration_1 = new JLabel("Name");
		lblUserRegistration_1.setBounds(106, 146, 209, 42);
		lblUserRegistration_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_1);
		
		lblUserRegistration_2 = new JLabel("Company Name");
		lblUserRegistration_2.setBounds(106, 230, 209, 42);
		lblUserRegistration_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_2);
		
		lblUserRegistration_4 = new JLabel("Address");
		lblUserRegistration_4.setBounds(106, 335, 209, 42);
		lblUserRegistration_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_4);
		
		lblUserRegistration_5 = new JLabel("E-mail id");
		lblUserRegistration_5.setBounds(106, 507, 209, 42);
		lblUserRegistration_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_5);
		
		lblUserRegistration_7 = new JLabel("Password");
		lblUserRegistration_7.setBounds(106, 628, 209, 42);
		lblUserRegistration_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_7);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(337, 633, 381, 42);
		contentPane.add(passwordField);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(377, 761, 85, 37);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnNewButton);
		setUndecorated(true);
	}
}
