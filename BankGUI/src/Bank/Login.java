package Bank;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField AccNumberField;
	private JTextField PinCodeField;

	/**
	 * Launch the application.
	 */
	
	
	 static Object[][] accounts = {
	    		
	    		//ACCOUNT NUMBER - PINCODE - NAME - BALANCE
	            {"123", "321", "Allen Lazatin", 1000},
	            {"456", "654", "Gab Barbacena", 5000}
	    };
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("Login Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 330, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 11, 199, 42);
		contentPane.add(lblNewLabel);
		
		//ACCOUNT NUMBEEER
		JLabel lblNewLabel_1 = new JLabel("Account Number");
		lblNewLabel_1.setBounds(10, 98, 95, 14);
		contentPane.add(lblNewLabel_1);
		
		AccNumberField = new JTextField();
		AccNumberField.setBounds(123, 95, 86, 20);
		contentPane.add(AccNumberField);
		AccNumberField.setColumns(10);
		
		//PIN CODE 
		JLabel lblNewLabel_1_1 = new JLabel("Pin Code");
		lblNewLabel_1_1.setBounds(10, 140, 95, 14);
		contentPane.add(lblNewLabel_1_1);
		
		PinCodeField = new JTextField();
		PinCodeField.setColumns(10);
		PinCodeField.setBounds(123, 137, 86, 20);
		contentPane.add(PinCodeField);
		
		//BUTTON ENTEEER
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String AccountNumber = AccNumberField.getText();
				String PinCode = PinCodeField.getText();
				
				login(AccountNumber, PinCode);
			}
		});
		btnNewButton.setBounds(215, 157, 89, 23);
		contentPane.add(btnNewButton);
	}
	//LOGIN METHOD
	public void login(String accountNumber, String pinCode) {
	    if (accountNumber.isEmpty() || pinCode.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "ENTER YOUR ACCOUNT NUMBER AND/OR PIN CODE", "ERROR", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    boolean loggedIn = false;
	    for (Object[] account : accounts) {
	        if (accountNumber.equals(account[0]) && pinCode.equals(account[1])) {
	            loggedIn = true;
	            JOptionPane.showMessageDialog(null, "Welcome " + account[2], "Logged In", JOptionPane.INFORMATION_MESSAGE);
	            Object[] loggedInAccount = getLoggedInAccount(accountNumber);
	           
	            Menu menu = new Menu(accounts, loggedInAccount);
	            menu.setVisible(true);
	            dispose();
	            break;
	        }
	    }
	    
	    if (!loggedIn) {
	        JOptionPane.showMessageDialog(null, "INCORRECT ACCOUNT NUMBER OR PIN CODE", "ERROR", JOptionPane.ERROR_MESSAGE);
	    }
	}
	//LOGGED IN ACCOUNT ARRAY 
	private Object[] getLoggedInAccount(String accountNumber) {
	    for (Object[] account : accounts) {
	        if (accountNumber.equals(account[0])) {
	            return account;
	        }
	    }
	    return null; // Account not found
	}
	}


