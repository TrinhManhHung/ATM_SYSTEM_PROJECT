
package atm;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int ID;
    private double amount;
    private String type;
    private LocalDateTime dateTime;
    private User user;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
    //METHOD
    public Transaction(double amount, User user){
        this.amount = amount;
        this.user = user;
        dateTime = LocalDateTime.now();
    }
    
    public Transaction(){
        
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    public String getDateTime() {
        return formatter.format(dateTime);
    }

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return DateTimeFormatter.ofPattern("yyyy-dd-MM").format(dateTime);
    }

    public String getTime() {
        return DateTimeFormatter.ofPattern("HH:mm").format(dateTime);
    }

}
