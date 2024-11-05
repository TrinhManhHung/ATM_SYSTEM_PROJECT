//Các thao tác dữ liệu transactions với database (set, get, ...)
package atm;

import java.sql.*;
import java.util.*;

public class TransactionsDatabase {
    
    private static ArrayList<Transaction> getAllTransactions(Database database){
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<Integer> usersIDs = new ArrayList<>();
        String select = "SELECT * FROM `transactions`;";
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            while (rs.next()){
                Transaction x = new Transaction();
                x.setID(rs.getInt("ID"));
                x.setAmount(rs.getDouble("Amount"));
                x.setDateTime(rs.getString("DateTime"));
                usersIDs.add(rs.getInt("UserID"));
                transactions.add(x);
            }
            for(int i =0; i <usersIDs.size(); i++){
                User user = UsersDatabase.getUserByID(usersIDs.get(i), database);
                transactions.get(i).setUser(user);
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }
    
    public static int getNextID (Database database){
        int ID = 1;
        ArrayList<Transaction> transactions = getAllTransactions(database);
        int size = transactions.size();
        if(size != 0){
            Transaction last  = transactions.get(size - 1);
            ID = last.getID() + 1;
        }
        return ID;
    }
    
    public static void saveTransaction(Transaction t, Database database){
        String insert = "INSERT INTO `transactions`(`ID`, `Amount`, `Type`, `DateTime`, `UserID`)"
               + " VALUES ('" + t.getID() + "','" + t.getAmount() + "','" + t.getType() + "','" + t.getDateTime() + "','" + t.getUser().getID() + "');";
        
        try{
            database.getStatement().execute(insert);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public static String[][] getUserTransactions (Database database, int id){
        ArrayList<Transaction> transactions = new ArrayList<>();
        String select = "SELECT * FROM `transactions` WHERE `UserID` = " + id + " ;";
        try{
            ResultSet rs = database.getStatement().executeQuery(select);
            while(rs.next()){
		Transaction t = new Transaction();
                t.setID(rs.getInt("ID"));
                t.setType(rs.getString("Type"));
                t.setAmount(rs.getDouble("Amount"));
                t.setDateTime(rs.getString("DateTime"));
                transactions.add(t);
                }

                User user = UsersDatabase.getUserByID(id, database);
                for (Transaction t : transactions) {
                        t.setUser(user);
                }
        } catch(SQLException e){
            e.printStackTrace();
        }
        
        String[][] data = new String[transactions.size()][5];
        for(int i =0; i <transactions.size(); i++){
            data[i][0] = String.valueOf(transactions.get(i).getID());
            data[i][1] = transactions.get(i).getType();
            data[i][2] = String.valueOf(Math.abs(transactions.get(i).getAmount()));
            data[i][3] = transactions.get(i).getDate();
            data[i][4] = transactions.get(i).getTime();
        }
        return data;
    }
}
