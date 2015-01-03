package de.thwildau.telemetriedatasystemapp.data;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Class that defines a Message, specially it is called TDSMessage (Telemetrie Data System Message)
 * @author Fabian
 *
 */
public class TDSMessage implements Serializable {

	/**
	 * for Serializable
	 */
	private static final long serialVersionUID = 1L;
	
	//needed values 
	private NotificationType type;
	private Calendar datum;
	private double latitude;
	private double longitude;
	private byte[] image;
	
	//------------------------------------------------------
	//getter and setter of the needed values 
	//------------------------------------------------------
	
	//getter and setter type
	public NotificationType getType() {
		return type;
	}
	public void setType(NotificationType type) {
		this.type = type;
	}
	
	//getter and setter datum
	public Calendar getDatum() {
		return datum;
	}
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
		
	//getter and setter position
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	//getter  and setter image
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}


	
}
