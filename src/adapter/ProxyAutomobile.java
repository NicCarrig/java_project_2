package adapter;

import model.*;
import util.*;
import exception.*;

public abstract class ProxyAutomobile {
	
	private static Automobile a1;
	
	
	
	public void buildAuto(String fileName) {
		//create FileIO object and pass in name to constructor
		//set a1 for object.readfile()
		FileIO car = new FileIO(fileName);
		a1 = car.readFile();
	}
	
	public void printAuto(String modelName) {
		//use this to call a1's printCarInfo() method
		a1.printCarInfo();
	}
	
	public void updateSubmodelName(String modelName, String submodelName, String newName){
		//search submodel name and update it
		try {			
			FileIO car = new FileIO(modelName);
			a1 = car.deserializeVehicle(modelName);
			int submodelIndex = a1.findSubmodelIndex(submodelName);
			a1.setSubmodel(submodelIndex, newName);
			car.serializeVehicle(a1);
		}
		catch(Exception e) {
			fixErr(201);
		}
	}
	
	public void updateOptionPrice(String modelName, String submodelName, String optionName, double newPrice) {
		//search option within submodel and change it
		try {				
			FileIO car = new FileIO(modelName);
			a1 = car.deserializeVehicle(modelName);
			int submodelIndex = a1.findSubmodelIndex(submodelName);
			a1.setOneOptionPriceByName(submodelIndex, optionName, newPrice);
			car.serializeVehicle(a1);
		}
		catch(Exception e) {
			fixErr(202);
		}
	}
	
	public void fixErr(int errNo){
		AutoException fix = new AutoException(errNo);
	}

}
