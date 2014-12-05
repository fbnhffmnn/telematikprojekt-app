package de.thwildau.telemetriedatasystemapp;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import de.thwildau.telemetriedatasystemapp.data.MessageManager;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;
import de.thwildau.telemetriedatasystemapp.services.NotificationArrayAdapter;

public class ReceiveMessages extends ListActivity {

	MessageManager msgMnmgr = MessageManager.getInstance();
	
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		TDSMessage[] values = msgMnmgr.getArrayFromNotifyList();
		// use custom layout
		NotificationArrayAdapter adapter = new NotificationArrayAdapter(this, values);
		setListAdapter(adapter);
		//adapter.notifyDataSetChanged();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TDSMessage item = (TDSMessage) getListAdapter().getItem(position);
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
