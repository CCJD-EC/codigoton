package co.codigoton.dtos;

import java.util.ArrayList;

public class Table {
	
	private String name;
	private int clientType=0;
	private int geographicalCode=0;
	private int initialBalance=0;
	private int finalBalance=0;
	private boolean isEnableTable= true;
	private ArrayList<Client> clients;
	

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

	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) throws Exception {
		if (clients.size() <= 8) {
			
			this.clients = clients;	
		}else {
			throw new Exception("Has excedido el numero de clientes para esta mesa");
		}
	}
	
	public void addClients(Client client) throws Exception {
		if (clients.size() < 8) {
			
			this.clients.add(client);	
		}else {
			throw new Exception("Has excedido el numero de clientes para esta mesa");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public boolean isEnableTable() {
		return isEnableTable;
	}

	public void setEnableTable(boolean isEnableTable) {
		this.isEnableTable = isEnableTable;
	}

	
	public String toStringClients() {
		
		String contentOut = "<"+this.getName()+">/n";
		if (isEnableTable) {
			for (Client client : clients)
				contentOut += client.getCode()+",";		
		}else {
			contentOut += "CANCELADA";
		}
		
		return contentOut;
	}

}
