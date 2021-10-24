package co.codigoton.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.codigoton.dtos.Client;
import co.codigoton.dtos.Table;
import co.codigoton.utils.DBConnection;
import co.codigoton.utils.FileManagement;

public class AssignClientsToTable {

	// Variables Globales
		private final static Logger LOGGER = Logger.getLogger(AssignClientsToTable.class.getName());
	
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

				// Comprueba si tiene la cantidad adecuada de clientes
				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				// Revisa si la mesa esta habilitada: primer filtro
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
				
				//TODO segundo filtro
				
								
				// Llenado de la lista de clientes precalificados por compania
				listClient = FilterClients.getFilterClientsForCompany(listClient);
				
				// Comprueba si tiene la cantidad adecuada de clientes
				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				// Revisa si la mesa esta habilitada: tercer filtro
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
				
				// Llenado de la lista de clientes precalificados por genero
				listClient = FilterClients.getFilterClientsForGender(listClient);
				
				// Comprueba si tiene la cantidad adecuada de clientes
				isEnableTable = FilterClients.hasTableCapacity(listClient);
				
				// Revisa si la mesa esta habilitada: cuerto filtro
				if (!isEnableTable) {
					table.setEnableTable(isEnableTable);
					table.setClients(listClient);
					table.toStringClients();
					continue;
				}
			
				// Llenado de lista de clientes calificados para la mesa
				table.setClients(listClient);
				
				//Impresion de los clientes de la mesa
				table.toStringClients();
			
					
			} catch (Exception e) {
				LOGGER.log(Level.CONFIG,"Error en la desencripcion!! "+ e.getMessage());
				e.printStackTrace();
			}
			
			
		}
		
		//TODO ESCRITURA DE ARCHIVO
		
	}

}
