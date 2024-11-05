package atm;

//Trang Menu
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class List {
	
	public List(Database database, User user) {
		JFrame frame = new JFrame("ATM");
		frame.setLayout(new BorderLayout());
		
		JLabel title1 = GUIConstants.jLabel("Welcome to ATM", 30);
                JLabel title2 = GUIConstants.jLabel("Customer ID: " + user.getID(), 30);
		JLabel title3 = GUIConstants.jLabel("Hello " + user.getFirstName() + " " + user.getLastName(), 30);
                
                JPanel titlePanel = new JPanel(new GridLayout(3, 1));
                titlePanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 15, 40));
                
                titlePanel.add(title1);
                titlePanel.add(title2);
                titlePanel.add(title3);
		frame.add(titlePanel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(15, 40, 40, 40));
		
		JButton deposit = GUIConstants.jButton("Deposit");
		deposit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Operation("Deposit", user, database);
			}
		});
		panel.add(deposit);
		
		JButton withdraw = GUIConstants.jButton("Withdraw");
		withdraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Operation("Withdraw", user, database);
			}
		});
		panel.add(withdraw);

                JButton transfer = GUIConstants.jButton("Transfer");
		transfer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Transfer(user, database);
			}
		});
		panel.add(transfer);
		
		JButton balance = GUIConstants.jButton("Balance");
		balance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Balance(user, database);
			}
		});
		panel.add(balance);
		
		JButton transactions = GUIConstants.jButton("Transactions");
		transactions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Transactions(database, user.getID());
			}
		});
		panel.add(transactions);
		
		JButton edit = GUIConstants.jButton("Edit My Data");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserData(false, database, user.getID());
			}
		});
		panel.add(edit);
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(800, 520);
		frame.setLocationRelativeTo(null);
                titlePanel.setBackground(GUIConstants.backgroundColor);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}

}
