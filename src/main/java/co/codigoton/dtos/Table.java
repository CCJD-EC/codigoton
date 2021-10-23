package co.codigoton.dtos;

public class Table {
	
	private String name = "";
	private int clientType = 0;
	private int geographicalCode = 0;
	private int initialBalance = 0;
	private int finalBalance = 0;
	private Client[] clients = new Client[8];
	

	public int getClientType() {
		return clientType;
	}

	public void setClientType(int clientType) {
		this.clientType = clientType;
	}

	public int getGeographicalCode() {
		return geographicalCode;
	}
	
	public void setGeographicalCode(int geographicalCode) {
		this.geographicalCode = geographicalCode;
	}

	public int getInitialBalance() {
		return initialBalance;
	}

	public void setInitialBalance(int initialBalance) {
		this.initialBalance = initialBalance;
	}

	public int getFinalBalance() {
		return finalBalance;
	}
	
	public void setFinalBalance(int finalBalance) {
		this.finalBalance = finalBalance;
	}

	public Client[] getClients() {
		return clients;
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	
	
	

}
