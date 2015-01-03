package de.thwildau.telemetriedatasystemapp.data;

/**
 * Interface for the notification types
 * @author Fabian
 *
 */
public interface iNotificationType {
	
	public int getTypeNr();
	
	public void setTypeNr(int typeNr);
	
	public String getTypeName();
	
	public void setTypeName(String typeName);
	
	public int getImage();
	
	public void setImage(int image);

}
