package co.codigoton.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.codigoton.dtos.Constants;
import co.codigoton.dtos.Table;

public class FileManagement {
	
	
	//Metodo para leer valores de entrada
	 public static ArrayList<Table> readEntry() {
	        File file = new File(Constants.pathEntryValues);
	        ArrayList<Table>  tables = new ArrayList<>();
	        try {
	            BufferedReader entry = new BufferedReader(new FileReader(file));
	            String read = entry.readLine();
	            String readed = "";
	            while(read != null){
	            	readed = readed + "  " + read;       	       	
	            	read = entry.readLine();
	            }
	            String[] tablesAttributes = readed.split("<");
	            List<List<String>> listOfLists = new ArrayList<>();
	            for(int i = 0; i < tablesAttributes.length; i++) {
	            	String[] a = tablesAttributes[i].split("  ");
	            	listOfLists.add(Arrays.asList(a));
	            }
	            for(int i = 1; i < listOfLists.size(); i++) {	 
	            	Table table = new Table();
	            	for(int f = 0; f < listOfLists.get(i).size(); f++) {            		 
	            		 switch(listOfLists.get(i).get(f).split(":")[0]) {
	            		 	case "TC":
	            		 		table.setClientType(Integer.valueOf(listOfLists.get(i).get(f).split(":")[1]));
	            		 		break;
	            		 	case "UG":
	            		 		table.setGeographicalCode(Integer.valueOf(listOfLists.get(i).get(f).split(":")[1]));
	            		 		break;
	            		 	case "RI":
	            		 		table.setInitialBalance(Integer.valueOf(listOfLists.get(i).get(f).split(":")[1]));
	            		 		break;	            		 		
	            		 	case "RF":
	            		 		table.setFinalBalance(Integer.valueOf(listOfLists.get(i).get(f).split(":")[1]));
	            		 		break;
	            		 	default:
	            		 		table.setName(listOfLists.get(i).get(f).replace(">",""));
	            		 		break;
	            		 }
		            } 
	            	tables.add(table);            	
	            }
	            
	            for(Table conte : tables) {
	        		System.out.println(conte.getName());
	        	}
	            
	            entry.close();
	                     
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace(System.out);
	        } catch (IOException ex) {
	            ex.printStackTrace(System.out);
	        }
	        return tables;
	    }
    
    //Metodo para generar archivo con valores de salida
    public static void writeOutput(ArrayList<Table> content) {
        File archivo = new File(Constants.pathOutputValues);
        try {
            PrintWriter outP = new PrintWriter(archivo);
 
        	for(Table conte : content) {
        		outP.print(conte.getName());
        	}
            
            outP.close();
            System.out.println("Se ha escrito al archivo");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
}

