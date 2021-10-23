
package co.codigoton.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import co.codigoton.dtos.Constants;

import java.util.logging.Level;
import java.util.logging.Logger;


public class APIConnection {
	
	// Variables globales 
	private static HttpResponse<String> apiConnection;
	private final static Logger LOGGER = Logger.getLogger(APIConnection.class.getName());
	
	//Metodo para desencriptar valor enviado como parametro
	public static String getDencyptedValue(String encryptedValue){
		String result = null;
		
		try {
			
			// Se realiza la concatenacion con la url
			String urlModified = Constants.url+encryptedValue;
						
			//Se realiza el request y se obtiene su resultado
			apiConnection = Unirest.get(urlModified).asString();
			result = apiConnection.getBody();
			
			LOGGER.log(Level.CONFIG,"Valor retornado "+result);
			
			return result.substring(1,result.length()-1);
			
		} catch (UnirestException e) {
			LOGGER.log(Level.CONFIG,"Error en la ejecucion del api "+ e.getMessage());
		}
		return result;
	
	}
	
	public static void main(String[] args) {
		System.out.print(getDencyptedValue("QzEwMjA5"));
	}

}
