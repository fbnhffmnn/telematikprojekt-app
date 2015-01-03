package de.thwildau.telemetriedatasystemapp.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle Message objects
 * Singleton Pattern is used
 * @author Fabian
 *
 */
public class MessageManager {
	
	//instance
	private static MessageManager instance;
	//needed values
	private List<TDSMessage> notifyList; 
	private int oldSizeOfList = 0;
	
	/**
	 * constructor
	 */
	MessageManager(){
		notifyList = new ArrayList<TDSMessage>();
	}
	
	/**
	 * get instance of messagemanager
	 * @return the singleton object of the class
	 */
	public static synchronized MessageManager getInstance () {
		if (MessageManager.instance == null) {
			MessageManager.instance = new MessageManager ();
		}
		return MessageManager.instance;
	}
	
	//------------------------------------------------------
	//getter and setter of the needed values 
	//------------------------------------------------------
	
	//setter message to notifylist
	public void addNotification(TDSMessage notify){
		notifyList.add(notify);
	}
	
	//clear notifylist
	public void clearList(){
		oldSizeOfList = notifyList.size();	// save previous list size
		notifyList.clear();
	}

	//getter size of notifylist
	public int sizeOfList(){
		return notifyList.size();
	}

	//getter and setter notifylist
	public List<TDSMessage> getNotifyList() {
		return notifyList;
	}

	public void setNotifyList(List<TDSMessage> notifyList) {
		this.notifyList = notifyList;
	}

	//getter notifylist as Array
	public TDSMessage[] getArrayFromNotifyList() {
		TDSMessage[] array = new TDSMessage[notifyList.size()];
		notifyList.toArray(array); // fill the array
		return array;
	}

	//getter and setter oldsizeoflist
	public int getOldSizeOfList() {
		return oldSizeOfList;
	}

	public void setOldSizeOfList(int oldSizeOfList) {
		this.oldSizeOfList = oldSizeOfList;
	}

}
