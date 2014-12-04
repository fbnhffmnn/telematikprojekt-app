package de.thwildau.telemetriedatasystemapp.data;

import android.content.res.Resources;
import de.thwildau.telemetriedatasystemapp.R;


public class NotificationTypeManager {

	private static NotificationTypeManager instance;
	
	private NotificationType[] types; 
	
	NotificationTypeManager(){
		setTypes(new NotificationType[]{
			new NotificationType(0, "Information", R.drawable.information),
			new NotificationType(1, "Warning", R.drawable.warning),
		});
	}
	
	public static synchronized NotificationTypeManager getInstance () {
		if (NotificationTypeManager.instance == null) {
			NotificationTypeManager.instance = new NotificationTypeManager ();
		}
		return NotificationTypeManager.instance;
	}
	
	public NotificationType getTypeByString(String str){
		for(NotificationType i : types){
			if(i.getTypeName().equals(str)){
				return i;
			}
		}
		return null;
	}

	public NotificationType getType(int i) {
		return types[i];
	}
	
	public NotificationType[] getTypes() {
		return types;
	}

	public void setTypes(NotificationType[] types) {
		this.types = types;
	}
}
