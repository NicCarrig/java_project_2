package exception;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.time.LocalDateTime;

public class AutoException extends Exception {
	private int errNo;
	
	//errNo 1-99 - exception originated from model package
	//errNo 100-199 - exception originated from util package
	//errNo 200-299 - exception originated from adapter package
	//errNo 300-399 - exception originated from exception package
	//errNo 900-999 - exception originated from driver package
	
	public AutoException(int errNo) {
		super();
		this.errNo = errNo;
		fixError(errNo);
	}
	
	public void logError(String message) {
		//log exceptions with time stamps to a text file
		try {
			LocalDateTime date = LocalDateTime.now();
			FileWriter fileWriter = new FileWriter("error_log.txt", true);
			BufferedWriter bw = new BufferedWriter(fileWriter);
			bw.append(date + ": Error Number " + errNo + " " + message);
			bw.newLine();
			bw.close();
			fileWriter.close();
		}
		catch(Exception e) {
			
		}
	}
	
	public void fixError(int errNo) {
		//log error and fix it if possible
		//switch statement create new helper class object thats in package
		switch(errNo) {
			case 101:
				logError("Invalid file name");
				//fix error 1
				break;
			case 102:
				logError("Missing make/model name");
				//fix error 2
				break;
			case 103:
				logError("Missing option name");
				//fix error 3
				break;
			case 104:
				logError("Missing option price");
				//fix error 4
				break;
			case 105:
				logError("Missing submodel name");
				//fix error 5
				break;
			case 201:
				logError("No submodel with that name found");
				// fix error 6
				break;
			case 202:
				logError("No option with that name found");
				// fix error 6
				break;
			default:
				logError("Unknown Error");
		}
		
	}
	
	
}
