package co.codigoton.impl;

import java.sql.Connection;
import java.util.ArrayList;

import co.codigoton.dtos.Client;
import co.codigoton.dtos.Table;
import co.codigoton.utils.DBConnection;

public class FilterClients {

	// Filtro de cada mesa para calificar clientes
	public static ArrayList<Client> filterClient(Connection dbConnection, Table table) {
		
		String	query = "select * from evalart_reto._filteTable where encrypt=0";
		
		if (table.getClientType() != 0) {
			query += " and type=" +table.getClientType();
		
		}
		
		if (table.getGeographicalCode() != 0) {
			query += " and location=" +table.getGeographicalCode();
		
		}
		
		if (table.getInitialBalance() > 0) {
			query += " and Tbalance >=" +table.getInitialBalance();
		}
		
		if (table.getFinalBalance() > 0) {
			query += " and Tbalance <=" +table.getFinalBalance();
		}
		
		query += " order by Tbalance DESC";
		
		return DBConnection.getFilterClients(dbConnection, query);
	}
	
	// Comprobar si el numero de clientes no sea menor a 4
	public static boolean hasTableCapacity(ArrayList<Client> clients) {
		
		if (clients.size() < 4) 
			return false;

	
		return true;
	}
	
	// Filtro de clientes de las misma compania 
	public static ArrayList<Client> getFilterClientsForCompany(ArrayList<Client> clients){
		
		// Variables locales
		ArrayList<Client> tempList = new ArrayList<>();
		int i = 0;
		
		// Cambio de estado a eliminado del cliente si es de la misma compania
		for (Client client : clients) {
			for (int j=i; j < clients.size(); j++) {
				if(client.getCompany().equalsIgnoreCase(clients.get(j).getCompany()) && client != clients.get(j))
					clients.get(j).setDeleted(true);

			}
			i++;
		}

		// Llenado del array para enviarlo
		for (Client client : clients)
			if (!client.isDeleted())
				tempList.add(client);
		
		return tempList;
		
		
	}
	
	
	// Filtro para poner igual numero de clientes del genero
	public static ArrayList<Client> getFilterClientsForGender(ArrayList<Client> clients){
		
		// Variables Locales
		ArrayList<Client> tempList = new ArrayList<>();
		int countMale=0, countFemale=0, i=0;
		int isMale = 1;
		
		//Cantidad de personas del mismo genero
		for (Client client : clients) {
			if (client.getMale() == 1) 
				countMale++;
			else 
				countFemale++;
		}
		
		// Igualamos la cantidad personas del mismo genero
		if (countMale < countFemale) {
			if (countMale > 4)
				countMale = 4;

			countFemale = countMale;
			
		} else if (countFemale < countMale)
		{
			if (countFemale > 4)
				countFemale = 4;
			countMale = countFemale;
		}
			
		// Validacion de los valores del mismo genero no sea igual		
		if (countMale != countFemale)
			return null;
		
		// Revisando cada cliente para ponerlos en el array temporal para enviarlo
		for (Client client : clients) {
			
			if (client.getMale() == 1 && countMale != 0) {
				
				countMale--;
				i++;
				tempList.add(client);
				continue;
			}
			
			if (client.getMale() == 0 && countFemale != 0) {
				
				countFemale--;
				i++;
				tempList.add(client);
				continue;
			}
			
			if(countMale == 0 || countFemale == 0)
			{		
				if (countMale == 0) 
					isMale = 0;
				else
					isMale = 1;
				
				for (int j=i; j < clients.size(); j++) {
					if(isMale == clients.get(j).getMale()) {
						if(!clients.get(j).isDeleted()) {
							tempList.add(clients.get(j));
							if (isMale == 1) 
								countMale--;
							else
								countFemale--;
							clients.get(j).setDeleted(true);
						}
						
						if(countMale == 0 && countFemale == 0)
							break;
					}
				}			
			}
			
			if(countMale == 0 && countFemale == 0)
				break;
			i++;
		}
			
		return tempList;
		
		
	}

}
