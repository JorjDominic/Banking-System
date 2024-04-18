package Conditions;
import java.util.Scanner;

class BankAccount {
	
    static Object[][] accounts = {
    		
    		//ACCOUNT NUMBER - PINCODE - NAME - BALANCE
            {"123", "321", "Allen Lazatin", 1000},
            {"456", "654", "Gab Barbacena", 5000}
    };
    //MAIN METHOD 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String accNum, pinCode;

        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.println("Welcome to Bank Account System");
            System.out.println("LOGIN PAGE");
            System.out.println("PLEASE ENTER YOUR ACCOUNT NUMBER");

            accNum = input.nextLine();

            System.out.println("PLEASE ENTER YOUR PIN CODE");

            pinCode = input.nextLine();

            for (Object[] account : accounts) {
                if (accNum.equals(account[0]) && pinCode.equals(account[1])) {
                    System.out.println("Login Successful! Welcome, " + account[2]);
                    menu(input, account, accounts);
                    loggedIn = true;
                    break;
                }
            }

            if (!loggedIn) {
                System.out.println("Invalid Account Number or PIN Code. Please try again.");
            }
        }
    }
    //MENU METHOD 
    static void menu(Scanner input, Object[] account, Object[][] accounts) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("");
            System.out.println("WELCOME");
            System.out.println("1-VIEW ACCOUNT");
            System.out.println("2-WITHDRAW MONEY");
            System.out.println("3-DEPOSIT MONEY");
            System.out.println("4-TRANSFER MONEY");
            System.out.println("5-LOGOUT");

            System.out.println("Please select an option:");

            int option = input.nextInt();

            switch (option) {
                case 1:
                    viewAccount(account);
                    break;
                case 2:
                    withdrawMoney(input, account);
                    break;
                case 3:
                    depositMoney(input, account);
                    break;
                case 4:
                    transferMoney(input, account, accounts);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");

            }
        }
        main(null);
    }
    //VIEW ACCOUNT METHOD 
    static void viewAccount(Object[] account) {
        System.out.println("Account Holder: " + account[2]);
        System.out.println("Balance: $" + account[3]);
    }
    //WITHDRAW MONEY METHOD 
    static void withdrawMoney(Scanner input, Object[] account) {
        int currentBalance = (int) account[3];
        if (currentBalance <= 0) {
            System.out.println("Cannot withdraw from an account with zero balance.");
            return;
        }

        System.out.println("Enter amount to withdraw:");
        int amount = input.nextInt();
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return;
        }

        if (amount > currentBalance) {
            System.out.println("Insufficient funds.");
            return;
        }
        currentBalance -= amount;
        account[3] = currentBalance;
        System.out.println("Withdrawal successful. Remaining balance: $" + currentBalance);
    }
    //DEPOSIT MONEY METHOD 
    static void depositMoney(Scanner input, Object[] account) {
        System.out.println("Enter amount to deposit:");
        int amount = input.nextInt();
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return;
        }

        int currentBalance = (int) account[3];
        currentBalance += amount;
        account[3] = currentBalance;
        System.out.println("Deposit successful. New balance: $" + currentBalance);
    }
    //TRANSFER MONEY METHOD 
    static void transferMoney(Scanner input, Object[] senderAccount, Object[][] accounts) {
        int senderCurrentBalance = (int) senderAccount[3];
        if (senderCurrentBalance <= 0) {
            System.out.println("Cannot transfer from an account with zero balance.");
            return;
        }
        System.out.println("Enter amount to transfer:");
        int amount = input.nextInt();
        if (amount <= 0) {
            System.out.println("Transfer amount must be greater than 0.");
            return;
        }
        if (amount > senderCurrentBalance) {
            System.out.println("Insufficient funds.");
            return;
        }

        System.out.println("Enter recipient's account number:");
        String recipientAccNum = input.next();

        int senderIndex = -1;
        for (int i = 0; i < accounts.length; i++) {
            if (senderAccount == accounts[i]) {
                senderIndex = i;
                break;
            }
        }

        if (senderIndex == -1) {
            System.out.println("Sender's account not found.");
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
            System.out.println("Recipient's account not found.");
            return;
        }
        senderCurrentBalance -= amount;
        senderAccount[3] = senderCurrentBalance;

        int recipientCurrentBalance = (int) accounts[recipientIndex][3];
        recipientCurrentBalance += amount;
        accounts[recipientIndex][3] = recipientCurrentBalance;

        System.out.println("Transfer successful.");
    }
}