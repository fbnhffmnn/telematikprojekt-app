package de.thwildau.telemetriedatasystemapp.data;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
	
	private static MessageManager instance;
	
	private List<Notification> notifyList; 
	
	MessageManager(){
		notifyList = new ArrayList<Notification>();
	}
	
	public static synchronized MessageManager getInstance () {
		if (MessageManager.instance == null) {
			MessageManager.instance = new MessageManager ();
		}
		return MessageManager.instance;
	}
	
	public void addNotification(Notification notify){
		notifyList.add(notify);
	}

	public List<Notification> getNotifyList() {
		return notifyList;
	}

	public void setNotifyList(List<Notification> notifyList) {
		this.notifyList = notifyList;
	}
	
	public Notification[] getArrayFromNotifyList() {
		Notification[] array = new Notification[notifyList.size()];
		notifyList.toArray(array); // fill the array
		return array;
	}

}
