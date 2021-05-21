package Frontend;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Backend.Admin;
import Backend.Company;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private final JTextField userField = new JTextField();
	private JLabel lblUserRegistration_1;
	private JLabel lblUserRegistration_2;
	private JLabel lblUserRegistration_6;
	private JButton btnNewButton;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JLabel signupMsg;
	private JComboBox companyBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setTitle("SignUp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 25, 956, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(135, 206, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		userField.setBounds(337, 151, 381, 42);
		contentPane.add(userField);
		userField.setColumns(10);
		

		
		JLabel lblUserRegistration = new JLabel("User Registration");
		lblUserRegistration.setBounds(414, 58, 266, 56);
		lblUserRegistration.setFont(new Font("Lucida Sans", Font.PLAIN, 25));
		contentPane.add(lblUserRegistration);
		
		lblUserRegistration_1 = new JLabel("Username");
		lblUserRegistration_1.setBounds(106, 146, 209, 42);
		lblUserRegistration_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_1);
		
		lblUserRegistration_2 = new JLabel("Company");
		lblUserRegistration_2.setBounds(106, 230, 209, 42);
		lblUserRegistration_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_2);
		
		lblUserRegistration_6 = new JLabel("Password");
		lblUserRegistration_6.setBounds(106, 392, 209, 42);
		lblUserRegistration_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblUserRegistration_6);
		
		btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(337, 472, 85, 37);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Admin x = new Admin();
				
				String username = userField.getText();
				String password = passwordField.getText();
				String companyName = companyBox.getSelectedItem().toString();
				String roleText = comboBox.getSelectedItem().toString();
				boolean role = false;
				if(roleText.equals("Manager"))
				{
					role = true;
				}
				int status = x.register(username, password, companyName, role);
				
				if( status == 1)
				{
					signupMsg.setText("Signed up Successfully");
				}
				else
				{
					signupMsg.setText("Sign up failed");
				}
					
				//System.out.println(username+ password+ companyName+ address+ role);
				}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(337, 397, 381, 42);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Role");
		lblNewLabel.setBounds(106, 327, 84, 37);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel);
		

		comboBox = new JComboBox();
		comboBox.setBounds(337, 327, 381, 37);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Manager", "Employee"}));
		contentPane.add(comboBox);
		
		signupMsg = new JLabel("");
		signupMsg.setBounds(337, 548, 381, 42);
		contentPane.add(signupMsg);
		
		Company c = new Company();
		List<String> companyList = c.getCompanies();

		String[] companies = companyList.toArray(new String[0]);
		companyBox = new JComboBox();
		companyBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		companyBox.setModel(new DefaultComboBoxModel(companies));
		companyBox.setBounds(337, 230, 381, 42);
		contentPane.add(companyBox);
		setUndecorated(true);
	}
}
