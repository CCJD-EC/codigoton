package co.codigoton.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.codigoton.dtos.Client;
import co.codigoton.dtos.Constants;


public class DBConnection {

	// Variables Globales
	private final static Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
		
	//Metodo para obtener la coneccion a la base de datos
	private static Connection getConnection() {
		Connection dbConnection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");  
			dbConnection = DriverManager.getConnection("jdbc:mysql://"+Constants.host+":"+
						Constants.port+"/"+Constants.databaseName,Constants.user,Constants.password);
			
			//Se crea estructura de la tabla temporal para el programa
			createTempTable(dbConnection);
			
			return dbConnection;
		
		} catch (ClassNotFoundException|SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en la coneccion a la base!! "+ e.getMessage());
			System.out.println(e);
		}
		
		return dbConnection;
	}
	
	//Metodo para obtener datos de un query
	public static ArrayList<Client> getFilterClients(Connection dbConnection, String query) {
		Statement statemnt;
		ResultSet rst;
		ArrayList<Client> listClient = new ArrayList<>();
		
		try {
			statemnt = dbConnection.createStatement();
			rst= statemnt.executeQuery(query);  
			while(rst.next())  
				listClient.add(new Client(rst.getFloat(2),rst.getString(3), 
								rst.getInt(4),rst.getInt(5),
								rst.getString(6),rst.getString(7),
								rst.getInt(8)));
			return listClient;			
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en ejecucion de la consulta!! "+ e.getMessage());
		}
		
		return listClient;
	}
	
	//Metodo para obtener los datos del cliente
	public static void getClients(Connection dbConnection) {
		Statement statemnt;
		ResultSet rst;
		
		try {
			statemnt = dbConnection.createStatement();
			rst= statemnt.executeQuery(Constants.queryClient);  
			while(rst.next())  
				System.out.println(rst.getInt(1)+"  "+rst.getString(2)+"  "+rst.getString(3));  
			
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en ejecucion de la consulta!! "+ e.getMessage());
		}  
	}
	
	// Metodo para actualizar loas codigos encriptados
	private static void upgradeCodeEncripted(Connection dbConnection) {
		
		Statement statemnt;
		ResultSet rst;
		
		try {
			
			statemnt = dbConnection.createStatement();
			rst= statemnt.executeQuery(Constants.queryEncrypClient);
			
			while(rst.next()) 
				if (rst.getInt(8) == 1) {
					updateRegister("update _filteTable set code=\""+APIConnection.getDencyptedValue(rst.getString(3))
					+ ",encrypt = 0 "
					+ "\" where client_id="+rst.getInt(1)+";", dbConnection);
					
				}
				
			
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en la desencripcion!! "+ e.getMessage());
			System.out.println(e);
		}
	}
	
	
	// Metodo para actualizar 
	private static void updateRegister(String Query, Connection dbConnection) {
		
		Statement statemnt;
		
		try {
			
			statemnt = dbConnection.createStatement();
			statemnt.executeUpdate(Query);
			
		} catch (SQLException e) {
			LOGGER.log(Level.CONFIG,"Error en la desencripcion!! "+ e.getMessage());
			System.out.println(e);
		}
	}
	
	
	//Metodo para crear tabla temporal con los datos a implementar
			public static void createTempTable(Connection dbConnection) {
				

				Statement statemnt;
				
				try {

					statemnt = dbConnection.createStatement();
					String stm = "DROP TEMPORARY TABLE IF EXISTS _filteTable;\n";
					String stm2 = "CREATE TEMPORARY TABLE _filteTable \n"
							    + "select client_id, sum(balance) as Tbalance, \n"
					            +"code, male, type, location,company,encrypt \n"
							    +"from evalart_reto.account a, evalart_reto.client c \n"
					            +"where a.client_id = c.id \n"
							    +"GROUP by client_id;";
			
					statemnt.execute(stm);
					statemnt.execute(stm2);
					
				} catch (SQLException e) {
					LOGGER.log(Level.CONFIG,"Error en la creacion de la tabla!! "+ e.getMessage());
				}  
			}

	public static void main(String[] args) {
		Connection a = DBConnection.getConnection();
		upgradeCodeEncripted(a);
		getClients(a);
	}
	
}
