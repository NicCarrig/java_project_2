/*
Nicholas Carrig	
CIS D035B
Assignment 2 
 */
package model;

import java.io.Serializable;
import java.util.Arrays;

public class Automobile implements Serializable {
	
	private String make;
	private String model;
	private double basePrice;
	private OptionSet opset[];

	
	// CLASS CONSTRUCTOR AND METHODS
	public Automobile(String make, String model, double basePrice, String[] options, int numOptionSets, int numberOfOptionsPerSet[]){
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		
		opset = new OptionSet[numOptionSets];
		
		//options is an array with each element being a string of a line in the text file
		//need to split the array into smaller arrays for each different submodel in the text file
		//each submodel should be broken up by "option set:" in the text file
		//numberOfOptionsPerSet hold the number of options given for an option set in the text file
		//numberOfOptionsPerSet does not count the "option set:" line
		int optionArrayIndex = 1;
		
		for(int i = 0; i < numOptionSets; i++) {
			String[] optionsInSet = Arrays.copyOfRange(options, optionArrayIndex, optionArrayIndex + numberOfOptionsPerSet[i]);
			opset[i] = new OptionSet(optionsInSet, numberOfOptionsPerSet[i]);
			optionArrayIndex += numberOfOptionsPerSet[i] + 1;   //move the array index to the next option set
																//add one so it skips the "option set:" line
		}

		
	}

	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	public String getSubmodel(int x) {
		String sub = opset[x].getSubmodel();
		return sub;
	}
	public void setSubmodel(int x, String submodelName) {
		opset[x].setSubmodel(submodelName);
	}
	public OptionSet getOneOptionSet(int x) {
		return opset[x];
	}
	
	
	public double calcTotalCost(int index) {
		//should get price for each option and add it to the base price
		double totalPrice = basePrice;
		int optionList = opset[index].getOptionsLength();
		
		for(int i = 0; i < optionList; i++) {
			totalPrice += opset[index].getOptionPrice(i);
		}		
		return totalPrice;
	}
	
	public void printCarInfo() {
		for(int i = 0; i < opset.length; i++) {
			
			double totalPrice = calcTotalCost(i);
			System.out.println("Make: " + make);
			System.out.println("Model: " + model);
			System.out.println("Base Price: " + basePrice);
			
			opset[i].printSubmodelInfo();
			System.out.printf("\tTotal: $%.2f\n", totalPrice);
			System.out.println();

		}
	}
	public void printOneSubmodelInfo(int index) {
		double totalPrice = calcTotalCost(index);
		
		System.out.println("Submodel " + (index+1) + " Info:");
		System.out.println("Make: " + make);
		System.out.println("Model: " + model);
		System.out.println("Base Price: " + basePrice);
			
		opset[index].printSubmodelInfo();
		System.out.printf("\tTotal: $%.2f\n", totalPrice);
		System.out.println();
	}
	
	public int findSubmodelIndex(String submodelName) {
		//linear search to find submodel name (not case sensitive) and return index in the opset array
		//returns -1 if not found
		int index = -1;
		
		for(int i = 0; i < opset.length; i++) {
			if( (submodelName.toLowerCase()).equals( (opset[i].getSubmodel()).toLowerCase()) ){
				index = i;
			}
		}
		
		return index;
	}
	
	public void setOneOptionPriceByName(int submodelIndex, String optionName, double price) {
		int optionIndex = opset[submodelIndex].findOptionNumber(optionName);
		opset[submodelIndex].setOptionPrice(price, optionIndex);
		
	}
	

}