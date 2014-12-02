package de.thwildau.telemetriedatasystemapp.data;


public class NotificationTypeManager {

	private static NotificationTypeManager instance;
	
	private NotificationType[] types; 
	
	NotificationTypeManager(){
		setTypes(new NotificationType[]{
			new NotificationType(0, "Information", null),
			new NotificationType(1, "Warning", null),
		});
	}
	
	public static synchronized NotificationTypeManager getInstance () {
		if (NotificationTypeManager.instance == null) {
			NotificationTypeManager.instance = new NotificationTypeManager ();
		}
		return NotificationTypeManager.instance;
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
