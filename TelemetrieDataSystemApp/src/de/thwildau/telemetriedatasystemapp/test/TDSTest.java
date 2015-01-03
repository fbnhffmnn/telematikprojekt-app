package de.thwildau.telemetriedatasystemapp.test;

import java.util.Calendar;

import android.util.Log;
import de.thwildau.telemetriedatasystemapp.data.ConnectionData;
import de.thwildau.telemetriedatasystemapp.data.MessageManager;
import de.thwildau.telemetriedatasystemapp.data.NotificationTypeManager;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;

/**
 * Test Class 
 * is used to provide message data
 * only needed for testing, if there is no server access for testing
 * @author Fabian
 *
 */
public class TDSTest {
	
	// get data handler for setting data
	ConnectionData connection = ConnectionData.getInstance();
	NotificationTypeManager notificationMnmgr = NotificationTypeManager.getInstance();
	MessageManager msgMnmgr = MessageManager.getInstance();
	
	//define some geo positions
    double longitude = 13.512783;
    double latitude = 52.342052;
    double longitude1 = 13.40292;
    double latitude1 = 52.50452;
	
    /**
     * constructor
     * creates some message objects and set them to the message handler
     */
	public TDSTest(){
		Log.v("Server value=",connection.getServer());
		Integer meinInteger = Integer.valueOf(connection.getPort()); 
        String s = meinInteger.toString();
		Log.v("Port value=",s);
		Log.v("FahrzeugID value=",connection.getFahrzeugID());
		Log.v("Password value=",connection.getPassword());
		
		//create message 1
		TDSMessage n1 = new TDSMessage();
		n1.setLatitude(latitude);
		n1.setLongitude(longitude);
		n1.setDatum(Calendar.getInstance());
		n1.setType(notificationMnmgr.getType(0));
		
		//create message 2
		TDSMessage n2 = new TDSMessage();
		n2.setLatitude(latitude1);
		n2.setLongitude(longitude1);
		n2.setDatum(Calendar.getInstance());
		n2.setType(notificationMnmgr.getType(1));
		
		//create message 3
		TDSMessage n3 = new TDSMessage();
		n3.setLatitude(latitude);
		n3.setLongitude(longitude);
		n3.setDatum(Calendar.getInstance());
		n3.setType(notificationMnmgr.getType(1));

		//set messages to message handler
		msgMnmgr.addNotification(n1);
		msgMnmgr.addNotification(n2);
		msgMnmgr.addNotification(n3);
	}
}
