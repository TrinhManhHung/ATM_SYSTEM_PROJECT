
package atm;

//Trang xử lý chức năng chuyển tiền 
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


/**
 *
 * @author HUNG
 */
public class Transfer {
	
	public Transfer(User user, Database database) {
		JFrame frame = new JFrame("ATM");
		frame.setLayout(new BorderLayout());
		
		JLabel title = GUIConstants.jLabel("Transfer", 30);
		title.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		frame.add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
		
                panel.add(GUIConstants.jLabel("ID:", 23));
                JTextField id = GUIConstants.jTextField();
		panel.add(id);
                panel.add(GUIConstants.jLabel("Amount:", 23));
                JTextField amount = GUIConstants.jTextField();
                panel.add(amount);
		
		JButton cancel = GUIConstants.jButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panel.add(cancel);
		
		JButton confirm = GUIConstants.jButton("Confirm");
		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { //mo ngoac
                            try{
                                int idReceived = Integer.parseInt(id.getText()); 
				double amountIn = Double.parseDouble(amount.getText());
                                if(!UsersDatabase.checkExistUser(idReceived, database)){
                                    JOptionPane.showMessageDialog(null, "Not Valid ID!");
                                }
                                else if (idReceived == user.getID()) {
                                    JOptionPane.showMessageDialog(null, "Cannot transfer money to yourself!");
                                }
                                else{
                                    User userReceived = UsersDatabase.getUserByID(idReceived, database);
                                    if(user.getBalance() < amountIn){
                                        JOptionPane.showMessageDialog(null, "Not Enough Money! Please Again!");
                                    }
                                    else if(amountIn <= 0){
                                        JOptionPane.showMessageDialog(null, "Money must be positive!");
                                    }
                                    else{
                                        //Xứ lý giao dịch + số dư người gửi
                                        int idTrans = TransactionsDatabase.getNextID(database);
                                        Transaction transaction1 = new Transaction(amountIn*-1, user);
                                        transaction1.setID(idTrans);
                                        transaction1.setType("Transfer Out");
                                        TransactionsDatabase.saveTransaction(transaction1, database);
                                        user.setBalance(user.getBalance()-amountIn);
                                        UsersDatabase.updateUserData(user, database);
                                        //Xử lý giao dịch + số dư người nhận
                                        Transaction transaction2 = new Transaction(amountIn, userReceived);
                                        transaction2.setID(idTrans);
                                        transaction2.setType("Transfer In");
                                        TransactionsDatabase.saveTransaction(transaction2, database);
                                        userReceived.setBalance(userReceived.getBalance()+amountIn);
                                        UsersDatabase.updateUserData(userReceived, database);
                                        JOptionPane.showMessageDialog(null, "Operation done successfully");
                                        frame.dispose();
                                    }
                                }
                            }
                            catch (NumberFormatException u){
                                JOptionPane.showMessageDialog(null, "Invalid Number!");
                            }
			}//dong ngoac
		});
		panel.add(confirm);
		
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setSize(600, 350);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(GUIConstants.backgroundColor);
		frame.setVisible(true);
	}

}
