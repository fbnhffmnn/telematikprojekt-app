package de.thwildau.telemetriedatasystemapp;

import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;

public class MessageDetail extends Activity {

	TDSMessage msg;
	LinearLayout postionBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message_detail);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		msg = (TDSMessage) getIntent().getSerializableExtra("messageObj");
		
		
	    ImageView typeIcon = (ImageView) findViewById(R.id.detail_typeicon);
		TextView type = (TextView) findViewById(R.id.type);
		type.setText(msg.getType().getTypeName());
		
		TextView date = (TextView) findViewById(R.id.date);
		TextView time = (TextView) findViewById(R.id.time);
	    String dateStr = String.valueOf(msg.getDatum().get(Calendar.DAY_OF_MONTH));
	    dateStr += ".";
	    dateStr += String.valueOf(msg.getDatum().get(Calendar.MONTH));
	    dateStr += ".";
	    dateStr += String.valueOf(msg.getDatum().get(Calendar.YEAR));
	    date.setText(dateStr);
	    String timeStr = String.valueOf(msg.getDatum().get(Calendar.HOUR_OF_DAY));
	    timeStr += ":";
	    timeStr += String.valueOf(msg.getDatum().get(Calendar.MINUTE));
	    timeStr += ":";
	    timeStr += String.valueOf(msg.getDatum().get(Calendar.SECOND));
		time.setText(timeStr);

		TextView lng = (TextView) findViewById(R.id.lng);
		TextView lat = (TextView) findViewById(R.id.lat);
		lng.setText(String.valueOf(msg.getLongitude()));
		lat.setText(String.valueOf(msg.getLatitude()));
		
		
		postionBtn = (LinearLayout) findViewById(R.id.show_position);
		final Intent intent = new Intent(this, PositionView.class);
		intent.putExtra("lat", msg.getLatitude());
		intent.putExtra("lng", msg.getLongitude());
		String desc = msg.getType().getTypeName() + ", " + dateStr + " at " + timeStr;
		intent.putExtra("desc", desc);
		
		postionBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(intent);
			}
		});
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
