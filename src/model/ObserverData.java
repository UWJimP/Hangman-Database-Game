package model;

/**
 * Interface to help identify what data is being transfered.
 *
 * @author Jim Phan phanjim2@hotmail.com
 * @version Aug 2, 2019
 */
public interface ObserverData {

	/**
	 * Get the type of data that was being sent.
	 * 
	 * @return The string data of what was sent.
	 */
	public String getType();
	
}
