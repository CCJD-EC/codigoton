package co.codigoton.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import co.codigoton.dtos.Constants;
import co.codigoton.dtos.Table;

public class FileManagement {
	
	
	//Metodo para leer valores de entrada
    public static void readEntry() {
        File archivo = new File(Constants.pathEntryValues);
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = entrada.readLine();
            ArrayList<Table>  tables = new ArrayList<>();
            while(lectura != null){
            	System.out.println(lectura);
                lectura = entrada.readLine();
            }
            if(new String().contains("TC"))
            	new String().split(":");
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    //Metodo para generar archivo con valores de salida
    public static void writeOutput(String[][] content) {
        File archivo = new File(Constants.pathEntryValues);
        try {
            PrintWriter salida = new PrintWriter(archivo);
            for(int i = 0; i < content[0].length; i++) {
            	salida.println(content[0][i]);
            	for(String[] conte : content) {
            		salida.print(conte[i]);
            	}
            }
            salida.close();
            System.out.println("Se ha escrito al archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    
    
}
