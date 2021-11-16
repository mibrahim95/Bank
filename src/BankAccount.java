import java.util.Random;

public class BankAccount {

	private String accountType;
	private String name;
	private int accountId;
	private Double balance;
	private int age;
	private Double apr;
	
	BankAccount(String accountType, String name){
		this.accountType = accountType;
		this.name = name;
		Random rand = new Random();
		this.accountId = rand.nextInt(999999);
		this.balance = 0.00;

	}

	public String getAccountType() {
		return accountType;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setApr(Double apr) {
		this.apr = apr;
	}
	
	public Double getApr() {
		return this.apr;
	}

}