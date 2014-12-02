package de.thwildau.telemetriedatasystemapp.data;

import java.io.Serializable;
import java.util.Calendar;

import android.graphics.Bitmap;

public class TDSMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private NotificationType type;
	private Calendar datum;
	private double latitude;
	private double longitude;
	private Bitmap image;
	
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
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}


	
}
