package de.thwildau.telemetriedatasystemapp.data;

import java.io.Serializable;

import de.thwildau.telemetriedatasystemapp.R;

/**
 * Class for the notificationtype structure
 * @author Fabian
 *
 */
public class NotificationType  implements iNotificationType, Serializable  {
	
	/**
	 * for Serializable;
	 */
	private static final long serialVersionUID = 1L;
	
	// needed attributes
	private int typeNr = 0;
	private String typeName = "";
	private int image = R.drawable.ic_launcher;
	
	/**
	 * constructor
	 * @param typeNr - identifier of the notification type
	 * @param typeName - name of the notification 
	 * @param image - symbol  of the notification
	 */
	NotificationType(int typeNr, String typeName, int image){
		this.typeNr = typeNr;
		this.typeName = typeName;
		this.image = image;
	}
	
	//------------------------------------------------------
	//getter and setter of the needed values 
	//------------------------------------------------------
	//getter and setter typenr
	@Override
	public int getTypeNr() {
		return typeNr;
	}
	
	@Override
	public void setTypeNr(int typeNr) {
		this.typeNr = typeNr;
	}
	
	//getter and setter typename
	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	//getter and setter image
	@Override
	public int getImage() {
		return image;	
	}
	
	@Override
	public void setImage(int image) {
		this.image = image;		
	}

}
