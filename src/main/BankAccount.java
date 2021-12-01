package main;
import java.util.Random;

public class BankAccount {

	private String type;
	private String name;
	private int id;
	private Double balance;
	private int age;
	private Double apr;
	
	BankAccount(final String type, final String name){
		this.type = type;
		this.name = name;
		Random rand = new Random();
		this.id = rand.nextInt(999999);
		this.balance = 0.00;

	}

	public String getType() {
		return this.type;
	}

	public int getId() {
		return this.id;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAge(final int age) {
		this.age = age;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setApr(final Double apr) {
		this.apr = apr;
	}
	
	public Double getApr() {
		return this.apr;
	}

}