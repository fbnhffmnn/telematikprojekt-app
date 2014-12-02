package de.thwildau.telemetriedatasystemapp;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import de.thwildau.telemetriedatasystemapp.data.MessageManager;
import de.thwildau.telemetriedatasystemapp.data.Notification;
import de.thwildau.telemetriedatasystemapp.data.NotificationManager;

public class ReceiveMessages extends ListActivity {

	NotificationManager notificationMnmgr = NotificationManager.getInstance();
	MessageManager msgMnmgr = MessageManager.getInstance();
	
    double longitude = 13.512783;
    double latitude = 52.342052;
    double longitude1 = 13.40292;
    double latitude1 = 52.50452;
	
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		//TEST START
		Notification n1 = new Notification();
		n1.setLatitude(latitude);
		n1.setLongitude(longitude);
		n1.setDatum(Calendar.getInstance());
		n1.setType(notificationMnmgr.getType(0));
		
		Notification n2 = new Notification();
		n2.setLatitude(latitude1);
		n2.setLongitude(longitude1);
		n2.setDatum(Calendar.getInstance());
		n2.setType(notificationMnmgr.getType(1));
		
		Notification n3 = new Notification();
		n3.setLatitude(latitude);
		n3.setLongitude(longitude);
		n3.setDatum(Calendar.getInstance());
		n3.setType(notificationMnmgr.getType(1));
		//TEST END
		
		msgMnmgr.addNotification(n1);
		msgMnmgr.addNotification(n2);
		msgMnmgr.addNotification(n3);
		
		Notification[] values = msgMnmgr.getArrayFromNotifyList();
		// use custom layout
		NotificationArrayAdapter adapter = new NotificationArrayAdapter(this, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Notification item = (Notification) getListAdapter().getItem(position);
		//Toast.makeText(this, item.getFirstRow() + " selected", Toast.LENGTH_LONG).show();
    	Intent intent = new Intent(this, MessageDetail.class);
    	intent.putExtra("messageObj",item);
		startActivity(intent);	
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; goto parent activity.
	            this.finish();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public void onBackPressed() {
		this.finish();
	}
}
