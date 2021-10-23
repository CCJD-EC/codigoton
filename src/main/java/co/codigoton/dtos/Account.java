package co.codigoton.dtos;

public class Account {
	private int id;
	private int client_id;
	private double balance;
	

	public Account(int id, int client_id, double balance) {
		this.client_id = client_id;
		this.balance = balance;
	}


	public int getId() {
		return id;
	}


	public int getClient_id() {
		return client_id;
	}


	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
