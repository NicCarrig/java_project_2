package exception;

public class AutoException extends Exception {
	private int errNo;
	
	public AutoException(int errNo) {
		super();
		this.errNo = errNo;
		fixError(errNo);
	}
	
	public void fixError(int errNo) {
		//switch statement create new helper class object thats in package
		
	}
	
}
