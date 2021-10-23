package co.codigoton.utils;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.codigoton.dtos.Constants;


public class DBConneccion {

	// Variables Globales
	private final static Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
		
	//Metodo para obtener la coneccion a la base de datos
	private static Connection getConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			dbConnection = DriverManager.getConnection("jdbc:mysql://"+Constants.host+":"+
						Constants.port+"/"+Constants.databaseName,Constants.user,Constants.password);
			
			return dbConnection;
		
		} catch (ClassNotFoundException|SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en la coneccion a la base!! "+ e.getMessage());
			System.out.println(e);
		}
		
		return dbConnection;
	}
	
	//Metodo para obtener los datos de la cuenta
	public static void getAccounts() {
		
		Connection dbConnection = getConnection();
		Statement statemnt;
		
		try {

			statemnt = dbConnection.createStatement();
			ResultSet rst= statemnt.executeQuery("select * from evalart_reto.account limit 10");  
			while(rst.next())  
				System.out.println(rst.getString(1)+"  "+rst.getString(2)+"  "+rst.getString(3));  
			dbConnection.close();  
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en ejecucion de la consulta!! "+ e.getMessage());
		}  
	}
	
	//Metodo para obtener los datos del cliente
	public static void getClients() {
		
		Connection dbConnection = getConnection();
		Statement statemnt;
		ResultSet rst;
		
		try {
			statemnt = dbConnection.createStatement();
			rst= statemnt.executeQuery("select * from evalart_reto.account limit 10");  
			while(rst.next())  
				System.out.println(rst.getInt(1)+"  "+rst.getString(2)+"  "+rst.getString(3));  
			dbConnection.close();  
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en ejecucion de la consulta!! "+ e.getMessage());
		}  
	}
	
}
