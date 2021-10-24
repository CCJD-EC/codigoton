package co.codigoton.impl;

import java.sql.Connection;
import java.util.ArrayList;

import co.codigoton.dtos.Client;
import co.codigoton.dtos.Table;
import co.codigoton.utils.DBConnection;
import co.codigoton.utils.FileManagement;

public class AssignClientsToTable {

	
	public static void main(String[] args) {
		
		//Variables Locales
		
		boolean isEnableTable = true;
		
		// Preparando coneccion
		
		Connection dbConn = DBConnection.getConnection();
		
		// Obtener la lista de mesas para asignar clientes
		
		ArrayList<Table> tables = new ArrayList<>();
		
		tables = FileManagement.readEntry();
		
		for (Table table : tables) {
			
			// Me devuelve la precalificacion de posibles clientes para la mesa
			ArrayList<Client> listClient = FilterClients.filterClient(dbConn, table);
			
			// Llenar mesa con clientes calificados
			try {

				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				// Revisa si la mesa esta habilitada: primer filtro
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
				
				//TODO. primer filtro
								
				listClient = FilterClients.getFilterClientsForCompany(listClient);
				
				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
				
				listClient = FilterClients.getFilterClientsForGender(listClient);
				
				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
				
				table.setClients(listClient);
				table.toStringClients();
			
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		//TODO ESCRITURA DE ARCHIVO
		
	}
	
	public static ArrayList<Client> getList8(ArrayList<Client> list){
		
		ArrayList<Client> list8 = new ArrayList<>();
		int i = 0;
		for (Client client : list) {
			if (i == 8)
				break;

			list8.add(client);
			i++;
		}

		
		return list8;
	}
}
