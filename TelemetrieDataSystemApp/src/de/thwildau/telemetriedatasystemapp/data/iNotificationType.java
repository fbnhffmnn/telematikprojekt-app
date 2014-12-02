package de.thwildau.telemetriedatasystemapp.data;

import android.widget.ImageView;

public interface iNotificationType {
	
	public int getTypeNr();
	
	public void setTypeNr(int typeNr);
	
	public String getTypeName();
	
	public void setTypeName(String typeName);
	
	public ImageView getImage();
	
	public void setImage(ImageView image);

}
