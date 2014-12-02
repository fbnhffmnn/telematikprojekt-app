package de.thwildau.telemetriedatasystemapp.data;


public class NotificationManager {

	private static NotificationManager instance;
	
	private NotificationType[] types; 
	
	NotificationManager(){
		setTypes(new NotificationType[]{
			new NotificationType(0, "Information", null),
			new NotificationType(1, "Warning", null),
		});
	}
	
	public static synchronized NotificationManager getInstance () {
		if (NotificationManager.instance == null) {
			NotificationManager.instance = new NotificationManager ();
		}
		return NotificationManager.instance;
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
