package de.thwildau.telemetriedatasystemapp.data;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {
	
	private static MessageManager instance;
	
	private List<TDSMessage> notifyList; 
	private int oldSizeOfList = 0;
	
	MessageManager(){
		notifyList = new ArrayList<TDSMessage>();
	}
	
	public static synchronized MessageManager getInstance () {
		if (MessageManager.instance == null) {
			MessageManager.instance = new MessageManager ();
		}
		return MessageManager.instance;
	}
	
	public void addNotification(TDSMessage notify){
		notifyList.add(notify);
	}
	
	public void clearList(){
		oldSizeOfList = notifyList.size();
		notifyList.clear();
	}
	
	public int sizeOfList(){
		return notifyList.size();
	}

	public List<TDSMessage> getNotifyList() {
		return notifyList;
	}

	public void setNotifyList(List<TDSMessage> notifyList) {
		this.notifyList = notifyList;
	}
	
	public TDSMessage[] getArrayFromNotifyList() {
		TDSMessage[] array = new TDSMessage[notifyList.size()];
		notifyList.toArray(array); // fill the array
		return array;
	}

	public int getOldSizeOfList() {
		return oldSizeOfList;
	}

	public void setOldSizeOfList(int oldSizeOfList) {
		this.oldSizeOfList = oldSizeOfList;
	}

}
