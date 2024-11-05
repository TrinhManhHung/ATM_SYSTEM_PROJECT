package atm;
//Khai báo các thuộc tính của đối tượng User
public class User {
	
	private int ID;
	private String firstName;
	private String lastName;
	private String birthDate;
	private String email;
	private String phoneNumber;
	private int PINCode;
	private double balance;
	
	public User() {
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public int getPINCode() {
		return PINCode;
	}
	
	public void setPINCode(int PINCode) {
		this.PINCode = PINCode;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

}
