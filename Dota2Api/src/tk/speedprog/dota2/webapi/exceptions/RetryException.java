package tk.speedprog.dota2.webapi.exceptions;

public class RetryException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5890634374773805278L;

	public RetryException(String msg) {
		super(msg);
	}
}
