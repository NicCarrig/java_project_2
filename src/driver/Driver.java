/*
Nicholas Carrig	
CIS D035B
Assignment 2
 */
package driver;

import model.*;
import util.*;
import adapter.*;

public class Driver {

	public static void main(String [] args) {
		String fileName = "C:\\Users\\Niq\\eclipse-workspace\\Project_2\\test-car-2.txt";
		
		FileIO newCar = new FileIO(fileName);
		
		Automobile car1 = newCar.readFile();

		newCar.serializeVehicle(car1);
		
		System.out.println("Deserialized file: ");
		Automobile newCar1 = newCar.deserializeVehicle("Ford_Focus Wagon");
		newCar1.printCarInfo();
		newCar1.printOneSubmodelInfo(0);
		
		
//		CreateAuto a1 = new BuildAuto();
//		a1.buildAuto(fileName);
//		a1.printAuto("Ford");
//		
//		double newPrice = 1.1;
//		UpdateAuto a2 = new BuildAuto();
//		a2.updateSubmodelName("modelName", "submodel", "newName");
//		a2.updateOptionPrice("modelName", "submodel", "optionName", newPrice);

	}
	
}
