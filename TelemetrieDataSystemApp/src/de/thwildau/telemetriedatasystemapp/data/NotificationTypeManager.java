package de.thwildau.telemetriedatasystemapp.data;

import de.thwildau.telemetriedatasystemapp.R;

/**
 * Class to handle notification types
 * Singleton Pattern is used
 * @author Fabian
 *
 */
public class NotificationTypeManager {

	//instance
	private static NotificationTypeManager instance;
	//notification type array
	private NotificationType[] types; 
	
	/**
	 * constructor - point where types are defined
	 */
	NotificationTypeManager(){
		setTypes(new NotificationType[]{
			new NotificationType(0, "Unknown", R.drawable.event0),
			new NotificationType(1, "Object detected", R.drawable.event1),
			new NotificationType(2, "Driver is sleeping", R.drawable.event2),
			new NotificationType(3, "Human on road", R.drawable.event3),
			new NotificationType(4, "Potholes in the road", R.drawable.event4),
		});
	}
	 
	/**
	 * get Instance
	 * @return the singleton object
	 */
	public static synchronized NotificationTypeManager getInstance () {
		if (NotificationTypeManager.instance == null) {
			NotificationTypeManager.instance = new NotificationTypeManager ();
		}
		return NotificationTypeManager.instance;
	}
	
	/**
	 * method returns notification type object by name
	 * @param str - name of notification
	 * @return notification object
	 */
	public NotificationType getTypeByString(String str){
		for(NotificationType i : types){
			if(i.getTypeName().equals(str)){
				return i;
			}
		}
		return null;
	}

	/**
	 * method returns notification object by number
	 * @param i - number of notification
	 * @return notification object
	 */
	public NotificationType getType(int i) {
		try{
			return types[i];
		}catch(ArrayIndexOutOfBoundsException e) {
			return types[0];
		}
	}
	
	//------------------------------------------------------
	//getter and setter of the needed values 
	//------------------------------------------------------
	//getter and setter types
	public NotificationType[] getTypes() {
		return types;
	}

	public void setTypes(NotificationType[] types) {
		this.types = types;
	}
}
