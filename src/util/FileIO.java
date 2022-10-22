/*
Nicholas Carrig	
CIS D035B
Assignment 2 
 */
package util;

import java.io.*;
import java.util.*;

import model.*;

public class FileIO {

	private String fName;
	
	public FileIO(String fName) {
		this.fName =fName;
	}
	
	public Automobile readFile() {
		int lineCount = 0;			//line number being read in from the text file
		String make = "";
		String model = "";
		double basePrice = 0.0;
		int optionCount = getOptionCount();				//gets the total number of lines in the file for options
		String [] options = new String[optionCount];	//stores those options into a string array
		int numOptionSets = getNumOptionSets();			//parses out how many different options sets are inside the file
//		int numOptionsPerSet[] = getNumberOfOptionsPerSet(options, numOptionSets);
//		//stores the number of options for each option set into an array
		
		
		try {
			FileReader file = new FileReader(fName);
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			
			while(!eof) {
				String line = buff.readLine(); 		//pulls in line from text file as a string
				if(line == null) {
					eof = true;
				}
				else {
					//first three lines of file will be make model and submodel
					//should probably handle in a parseName method
					switch(lineCount) {
					case 0:
						make = parseName(line);
						break;
					case 1:
						model = parseName(line);
						break;
					case 2:
						//third line should be base price only -> make parsePrice method
						basePrice = parsePrice(line);
						break;
					default:
						//line after base price will be submodel name
						//all lines after should be options in the format of (option type) (name of option or equipped/none) (price)
						//needs to push line string onto options array
						options[lineCount - 3] = line;					
					}
					lineCount++;
				}
			}
			buff.close();
			
			//check for inputs from file
//			printTextInputs(make, model, basePrice, optionCount, options);
		}
		catch(Exception e){
			System.out.println("Error:  " + e.toString());
		}
		//get number of options in each option set in the text file and store that number into an array to pass to the 
		//Automobile constructor, so the Automobile object can build the optionSet array
		int numOptionsPerSet[] = getNumberOfOptionsPerSet(options, numOptionSets);
		//stores the number of options for each option set into an array
		Automobile car = new Automobile(make, model, basePrice, options, numOptionSets, numOptionsPerSet);

		return car;
	}
	
//	TEXT FILE PARSE METHODS----------
	public String parseName(String line) {
		String tempName = "";
		String[] result = line.split("\\s");
		
		for(int i=1; i<result.length; i++) {
			//start i at 1 to skip the first word in text file (description in text file)
			tempName += result[i];
			if(i != result.length -1) {
				//if its not the last word of the line, add a space
				tempName += " ";
			}
		}				
		return tempName;
	}
	public double parsePrice(String line) {
		//string should only contain "basePrice XXXX.XX", where XXXX is the base price amount
		String[] result = line.split("\\s");
		double tempPrice = Double.parseDouble(result[1]);
		return tempPrice;
	}
	
	public int getOptionCount() {
		int lineCount = 0;
		 try {
			 FileReader file = new FileReader(fName);
			 BufferedReader buff = new BufferedReader(file);
			 boolean eof = false;
			 
			 while (!eof) {
				 String line = buff.readLine();
				 if (line == null) {
					 eof = true;
				 }
				 else {
					 lineCount++;
				 }
			 }
			 buff.close();
		 }
		 catch (Exception e) {
			 System.out.println("Error:  " + e.toString());
		 }
		 return lineCount-3;		//first 3 lines are make/model/price
	}
	public int getNumOptionSets() {
		//maybe return int array and add the number of options to each element and then return the array
		// use array.length() - 1 in the rad file method instead of returning the value
		int optSetCount = 0;
		try {
			 FileReader file = new FileReader(fName);
			 BufferedReader buff = new BufferedReader(file);
			 boolean eof = false;
			 
			 while (!eof) {
				 String line = buff.readLine();
				 if (line == null) {
					 eof = true;
				 }
				 else if(line.equals("option set:")){
					 optSetCount++;
				 }
			 }
			 buff.close();
		 }
		 catch (Exception e) {
			 System.out.println("Error:  " + e.toString());
		 }
//		System.out.println("option Sets: " + optSetCount);
		return optSetCount;
	}
	public int[] getNumberOfOptionsPerSet(String[] options, int numOptionSets) {
		int numberOfOptionsPerSet[] = new int[numOptionSets];
		int index = 0;
		int optionCount = 0;
		
		//expected first line should be "option set:"
		//for loop should count each line that is not 
		for(int i = 1; i < options.length; i++) {
			if(options[i].equals("option set:")) {
				//if the string is the "option set:" dividing line then store the current count of options into the array
				//then reset the count and move to the next array index
				numberOfOptionsPerSet[index] = optionCount;
				optionCount = 0;
				index++;
			}
			else if(i == options.length-1) {
				//if its the last line in the array there won't be another "option set:" line
				//add the current option before setting the element in the array
				optionCount++;
				numberOfOptionsPerSet[index] = optionCount;
			}
			else {
				optionCount++;
			}
		}
		
//		System.out.println("Options in each set: ");
//		for(int i = 0; i < numberOfOptionsPerSet.length; i++) {
//			System.out.printf("%d ", numberOfOptionsPerSet[i]);
//		}
		
		return numberOfOptionsPerSet;
	}
	
	
	//should be used to test file input only
//	public void printTextInputs(String make, String model, double basePrice, int optionCount, String[] options) {
//		System.out.println("Make: " + make);
//		System.out.println("Model: " + model);
//		System.out.println("Base Price: " + basePrice);
//		for(int i = 0; i < optionCount; i++) {
//			System.out.println(options[i]);
//		}
//	}
	
	public void serializeVehicle(Automobile car) {
//		String fileName = car.getMake() + "_" + car.getModel() + "_" + car.getSubmodel() + ".txt";	
//		String fileName = car.getMake() + "_" + car.getModel() + "_" + car.getSubmodel();
		String fileName = car.getMake() + "_" + car.getModel();
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(car);
			
			out.close();
		}
		catch(Exception e) {
			System.out.println("Error:  " + e.toString());
		}
	}	
	
	public Automobile deserializeVehicle(String fileName) {
		Automobile car = null;
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			car = (Automobile)in.readObject();			
			
			in.close();
		}
		catch(Exception e) {
			System.out.println("Error:  " + e.toString());
		}
		return car;
	}
}