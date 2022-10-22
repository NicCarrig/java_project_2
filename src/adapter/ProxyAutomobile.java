package adapter;

import model.*;
import util.*;

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
	
	public void updateSubmodelName(String modelName, String submodelName, String newName) {
		//search submodel name and update it
		FileIO car = new FileIO(modelName);
		a1 = car.deserializeVehicle(modelName);
		int submodelIndex = a1.findSubmodelIndex(submodelName);
		a1.setSubmodel(submodelIndex, newName);
		car.serializeVehicle(a1);
	}
	
	public void updateOptionPrice(String modelName, String submodelName, String optionName, double newPrice) {
		//search option within submodel and change it
		FileIO car = new FileIO(modelName);
		a1 = car.deserializeVehicle(modelName);
		int submodelIndex = a1.findSubmodelIndex(submodelName);
		a1.setOneOptionPriceByName(submodelIndex, optionName, newPrice);
		car.serializeVehicle(a1);
	}

}
