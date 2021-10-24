package co.codigoton.impl;

import java.sql.Connection;
import java.util.ArrayList;

import co.codigoton.dtos.Client;
import co.codigoton.dtos.Table;
import co.codigoton.utils.DBConnection;

public class FilterClients {

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
		
		return DBConnection.getFilterClients(dbConnection, query);
	}

}
