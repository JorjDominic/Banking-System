package Bank;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static Object[][] accounts;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private static Object[] loggedInAccount;
	private JTextField AmountField;
	private JTextField textField;
	private static JPanel View;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accounts = new Object[][] {
						{"123", "321", "Allen Lazatin", 1000},
						{"456", "654", "Gab Barbacena", 5000}
				};
					Menu frame = new Menu(accounts, loggedInAccount);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param accounts 
	 */
	
	public Menu(Object[][] accounts,Object[] loggedInAccount) {
		Menu.accounts = accounts;
		Menu.loggedInAccount = loggedInAccount;
		setTitle("Banking System");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(192, 192, 192));
		panel.setBounds(0, 0, 150, 321);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 0, 141, 58);
		panel.add(lblNewLabel);
		
		//VIEW BUTTON
		JButton btnNewButton = new JButton("View Account");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 tabbedPane.setSelectedIndex(0);
				 viewAccount(View, loggedInAccount);
				 tabbedPane.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 63, 130, 23);
		panel.add(btnNewButton);
		
		//DEPOSIT BUTTON 
		JButton btnDepositMoney = new JButton("Deposit Money");
		btnDepositMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 tabbedPane.setSelectedIndex(1);
				 tabbedPane.setVisible(true);
			}
		});
		btnDepositMoney.setBounds(10, 103, 130, 23);
		panel.add(btnDepositMoney);
		
		//WITHDRAW BUTTON
		JButton btnWithdrawMoney = new JButton("Withdraw Money");
		btnWithdrawMoney.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if ((int) loggedInAccount[3] == 0) {
		            JOptionPane.showMessageDialog(null, "Cannot withdraw from an account with zero balance.", "Error", JOptionPane.ERROR_MESSAGE);
		        } else {
		            View.removeAll();
		            tabbedPane.setSelectedIndex(2);
		            tabbedPane.setVisible(true);
		        }
		    }
		});
		btnWithdrawMoney.setBounds(10, 140, 130, 23);
		panel.add(btnWithdrawMoney);
		
		//TRANSFER MONEY 
		JButton btnTransferMoney = new JButton("Transfer Money");
		btnTransferMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if ((int) loggedInAccount[3] == 0) {
			            JOptionPane.showMessageDialog(null, "Cannot transfer from an account with zero balance.", "Error", JOptionPane.ERROR_MESSAGE);
			        } else {
				 tabbedPane.setSelectedIndex(3);
				 tabbedPane.setVisible(true);
			}}
		});
		btnTransferMoney.setBounds(10, 174, 130, 23);
		panel.add(btnTransferMoney);
		
		//LOG OUT BUTTON
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to log out?", "Confirmation", JOptionPane.YES_NO_OPTION);
		        if (choice == JOptionPane.YES_OPTION) {
		            dispose();
		            Login login = new Login();
		            login.setVisible(true); 
		        }
		    }
		});
		btnLogOut.setBounds(31, 287, 89, 23);
		panel.add(btnLogOut);
		tabbedPane.setBounds(148, -23, 426, 344);
		contentPane.add(tabbedPane);
		tabbedPane.setVisible(false);
		//VIEEW PANEL 
		View = new JPanel();
		tabbedPane.addTab("New tab", null, View, null);
		View.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("View Account Details");
		lblNewLabel_1.setBounds(10, 11, 160, 14);
		View.add(lblNewLabel_1);
	        
		//DEPOSIT PANEL
		JPanel Deposit = new JPanel();
		tabbedPane.addTab("New tab", null, Deposit, null);
		Deposit.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Deposit Money");
		lblNewLabel_2.setBounds(10, 11, 123, 14);
		Deposit.add(lblNewLabel_2);
		
		JLabel lblNewLabel_5 = new JLabel("Enter Amount");
		lblNewLabel_5.setBounds(10, 69, 148, 14);
		Deposit.add(lblNewLabel_5);
		
		AmountField = new JTextField();
		AmountField.setBounds(10, 94, 123, 20);
		Deposit.add(AmountField);
		AmountField.setColumns(10);
		
		JButton EnterAmountButton = new JButton("Enter");
		EnterAmountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int amount = Integer.parseInt(AmountField.getText());
			 depositMoney(amount, loggedInAccount, View);
			 AmountField.setText("");
			}
		});
		EnterAmountButton.setBounds(129, 148, 89, 23);
		Deposit.add(EnterAmountButton);
		
		
		//WITHDRAW PANEL
		JPanel WIthdraw = new JPanel();
		tabbedPane.addTab("New tab", null, WIthdraw, null);
		WIthdraw.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("WIthdraw Money");
		lblNewLabel_3.setBounds(10, 11, 142, 14);
		WIthdraw.add(lblNewLabel_3);
		
		JLabel lblNewLabel_6 = new JLabel("Enter Amount");
		lblNewLabel_6.setBounds(10, 69, 114, 14);
		WIthdraw.add(lblNewLabel_6);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 94, 123, 20);
		WIthdraw.add(textField);
		
		JButton EnterAmountButton_1 = new JButton("Enter");
		EnterAmountButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int amount = Integer.parseInt(textField.getText());
		        withdrawMoney(amount, loggedInAccount, View);
		        textField.setText("");
			}
		});
		EnterAmountButton_1.setBounds(131, 147, 89, 23);
		WIthdraw.add(EnterAmountButton_1);
		
		//TRANSFER PANEL
		JPanel Transfer = new JPanel();
		tabbedPane.addTab("New tab", null, Transfer, null);
		Transfer.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel(" Transfer Money");
		lblNewLabel_4.setBounds(10, 11, 129, 14);
		Transfer.add(lblNewLabel_4);
		
		JLabel lblNewLabel_7 = new JLabel("Enter Account Number ");
		lblNewLabel_7.setBounds(10, 73, 151, 14);
		Transfer.add(lblNewLabel_7);
		
		JLabel lblNewLabel_7_1 = new JLabel("Enter Amount ");
		lblNewLabel_7_1.setBounds(10, 130, 151, 14);
		Transfer.add(lblNewLabel_7_1);

        JTextField recipientAccNumField = new JTextField();
        recipientAccNumField.setBounds(10, 98, 86, 20);
        Transfer.add(recipientAccNumField);
        recipientAccNumField.setColumns(10);

        JTextField transferAmountField = new JTextField();
        transferAmountField.setColumns(10);
        transferAmountField.setBounds(10, 155, 86, 20);
        Transfer.add(transferAmountField);
		
		JButton btnNewButton_1 = new JButton("Enter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String recipientAccNum = recipientAccNumField.getText();
                int amount = Integer.parseInt(transferAmountField.getText());
                transferMoney(recipientAccNum, amount, loggedInAccount, accounts);
                recipientAccNumField.setText("");
                transferAmountField.setText("");
                // Refresh the view after transfer
                viewAccount(View, loggedInAccount);
			}
		});
		btnNewButton_1.setBounds(157, 189, 89, 23);
		Transfer.add(btnNewButton_1);
	}
	//VIEW ACCOUNT DETAILS METHOD 
	static void viewAccount(JPanel viewPanel, Object[] account) {
		viewPanel.removeAll();
		
		JLabel lblNewLabel_1 = new JLabel("View Account Details");
		lblNewLabel_1.setBounds(10, 11, 160, 14);
		View.add(lblNewLabel_1);
		
	    JLabel lblAccountNumber = new JLabel("Account Number: " + account[0]);
	    lblAccountNumber.setBounds(10, 54, 214, 14);
	    viewPanel.add(lblAccountNumber);

	    JLabel lblPinCode = new JLabel("Pin Code: " + account[1]);
	    lblPinCode.setBounds(10, 79, 214, 14);
	    viewPanel.add(lblPinCode);

	    JLabel lblAccountHolder = new JLabel("Account Holder: " + account[2]);
	    lblAccountHolder.setBounds(10, 104, 214, 14);
	    viewPanel.add(lblAccountHolder);

	    JLabel lblBalance = new JLabel("Balance: $" + account[3]);
	    lblBalance.setBounds(10, 129, 214, 14);
	    viewPanel.add(lblBalance);
	    
	    viewPanel.revalidate();
	    viewPanel.repaint();
	}
	//DEPOSIT METHOD 
	static void depositMoney(int amount, Object[] account, JPanel viewPanel) {
	    if (amount <= 0) {
	        JOptionPane.showMessageDialog(null, "Deposit amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int currentBalance = (int) account[3];
	    currentBalance += amount;
	    account[3] = currentBalance;
	    
	    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to deposit $" + amount + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
	    if (confirmation == JOptionPane.YES_OPTION) {
	        
	        viewAccount(viewPanel, account);
	        JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + currentBalance, "Success", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	//WITHDRAW METHOD 
	static void withdrawMoney(int amount, Object[] account, JPanel viewPanel) {
	    int currentBalance = (int) account[3];
	    if (currentBalance <= 0) {
	        JOptionPane.showMessageDialog(null, "Cannot withdraw from an account with zero balance.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (amount <= 0) {
	        JOptionPane.showMessageDialog(null, "Withdrawal amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (amount > currentBalance) {
	        JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to withdraw $" + amount + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
	    if (confirmation == JOptionPane.YES_OPTION) {
	        currentBalance -= amount;
	        account[3] = currentBalance;

	        
	        viewAccount(viewPanel, account);
	        JOptionPane.showMessageDialog(null, "Withdrawal successful. Remaining balance: $" + currentBalance, "Success", JOptionPane.INFORMATION_MESSAGE);
	    }
	}
	//TRANSFER METHOD 
	static void transferMoney(String recipientAccNum, int amount, Object[] senderAccount, Object[][] accounts) {
	    int senderCurrentBalance = (int) senderAccount[3];
	    if (senderCurrentBalance <= 0) {
	        JOptionPane.showMessageDialog(null, "Cannot transfer from an account with zero balance.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    int recipientIndex = -1;
		    for (int i = 0; i < accounts.length; i++) {
		        Object[] recipientAccount = accounts[i];
		        if (recipientAccNum.equals(recipientAccount[0])) {
		            recipientIndex = i;
		            break;
		        }
		    }
		    if (recipientIndex == -1) {
		        JOptionPane.showMessageDialog(null, "Recipient's account not found.", "Error", JOptionPane.ERROR_MESSAGE);
		        return;
		    }
		if (senderAccount[0].equals(recipientAccNum)) {
	        JOptionPane.showMessageDialog(null, "Sender's account number cannot be the same as the recipient's account number.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    if (amount <= 0) {
	        JOptionPane.showMessageDialog(null, "Transfer amount must be greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    if (amount > senderCurrentBalance) {
	        JOptionPane.showMessageDialog(null, "Insufficient funds.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    int confirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to transfer $" + amount + " to account number " + recipientAccNum + "?", "Confirmation", JOptionPane.YES_NO_OPTION);
	    if (confirmation == JOptionPane.NO_OPTION) {
	        JOptionPane.showMessageDialog(null, "Transfer cancelled.", "Information", JOptionPane.INFORMATION_MESSAGE);
	        return;
	    }
	    int senderIndex = -1;
	    for (int i = 0; i < accounts.length; i++) {
	        if (senderAccount == accounts[i]) {
	            senderIndex = i;
	            break;
	        }
	    }

	    if (senderIndex == -1) {
	        JOptionPane.showMessageDialog(null, "Sender's account not found.", "Error", JOptionPane.ERROR_MESSAGE);
	        return;
	    }


	    senderCurrentBalance -= amount;
	    senderAccount[3] = senderCurrentBalance;

	    int recipientCurrentBalance = (int) accounts[recipientIndex][3];
	    recipientCurrentBalance += amount;
	    accounts[recipientIndex][3] = recipientCurrentBalance;
	    String recipientName = (String) accounts[recipientIndex][2];
	    JOptionPane.showMessageDialog(null, "Transfer successful to "+ recipientName, "Success", JOptionPane.INFORMATION_MESSAGE);
	}}

