package de.thwildau.telemetriedatasystemapp.data;

import java.io.Serializable;

import de.thwildau.telemetriedatasystemapp.R;

import android.widget.ImageView;

public class NotificationType  implements iNotificationType,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int typeNr = 0;
	private String typeName = "";
	private int image = R.drawable.ic_launcher;
	
	NotificationType(int typeNr, String typeName, int image){
		this.typeNr = typeNr;
		this.typeName = typeName;
		this.image = image;
	}
	
	@Override
	public int getTypeNr() {
		return typeNr;
	}
	
	@Override
	public void setTypeNr(int typeNr) {
		this.typeNr = typeNr;
	}
	
	@Override
	public String getTypeName() {
		return typeName;
	}
	
	@Override
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public int getImage() {
		return image;
		
	}
	
	@Override
	public void setImage(int image) {
		this.image = image;		
	}

}
