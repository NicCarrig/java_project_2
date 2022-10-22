package model;

import java.io.Serializable;

public class OptionSet implements Serializable {
	private String submodel;
	private Option opt[];
	
	public OptionSet (String[] n, int size){
		//first element in option array is submodel name
		String submodelArr[] = n[0].split("\\s");
		submodel = submodelArr[1];
		//initialize new array for just the options
		opt = new Option[size - 1];
		for(int i = 1; i < size; i++) {
			opt[i-1] = new Option(n[i]);
		}
		
	}
	
	//INNER CLASS
	protected class Option implements Serializable {
		private String name;
		private String value;
		private double price;
		
		public Option(String n){
			//string in 
			//parse name of option, text value, and price of option
			String[] result = n.split("\\s");
			String tempValue = "";
			
			name = result[0];
			price = Double.parseDouble(result[result.length-1]);
			
			//get all elements between the first and last
			for(int i = 1; i < result.length-1; i++) {
				tempValue += result[i];
				if(i != result.length-2) {
					tempValue += " ";
				}
			}
			
			value = tempValue;
		}

		protected String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		
		protected String getValue() {
			return value;
		}
		protected void setValue(String value) {
			this.value = value;
		}

		protected double getPrice() {
			return price;
		}
		protected void setPrice(double price) {
			this.price = price;
		}
		
		protected void printOptionInfo() {
			System.out.printf("%s: %s - $%.2f", name, value, price);
		}
					
	}

	//OUTER CLASS METHODS
	protected String getSubmodel() {
		return submodel;
	}
	protected void setSubmodel(String submodel) {
		this.submodel = submodel;
	}
	protected String getOptionName(int i) {
		return opt[i].getName();
	}
	protected void setOptionName(String newName, int i) {
		opt[i].setName(newName);
	}
	protected double getOptionPrice(int i) {
		return opt[i].getPrice();
	}
	protected void setOptionPrice(double newPrice, int i) {
		opt[i].setPrice(newPrice);
	}
	protected Option getOneOption(int i) {
		return opt[i];
	}
	protected Option[] getAllOptions() {
		return opt;
	}
	protected int getOptionsLength() {
		return opt.length;
	}
	
	protected void printSubmodelInfo() {
		System.out.println("Submodel: " + submodel);
		for(int i = 0; i < opt.length; i++) {
			opt[i].printOptionInfo();
			System.out.println();
		}
	}
	protected void printOneOptionInfo(int i) {
		opt[i].printOptionInfo();
		System.out.println();
	}
	
	protected int findOptionNumber(String optionName) {
		//linear search to find option by name and return its index in the option array
		// returns -1 if the option is not found
		int optNum = -1;
		
		for(int i = 0; i < opt.length; i++) {
			if(optionName.equals(opt[i].getName())) {
				optNum = i;
				//return optNum;
			}
		}		
		return optNum;
	}
			
}
