package de.thwildau.telemetriedatasystemapp;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Class for "PositionView"-Activity
 * @author Fabian
 *
 */
public class PositionView extends Activity {

	private GoogleMap mMap;
	double lat;
	double lng;
	String desc;
	
	/**
	 * Android Activity onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_position_view);
		
		//set back button in actionbar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//get data from parameter
		lat = getIntent().getDoubleExtra("lat", 0);
		lng = getIntent().getDoubleExtra("lng", 0);
		desc = getIntent().getStringExtra("desc");
		
		Log.v("lon value=",String.valueOf(lng));
		Log.v("lat value=",String.valueOf(lat));
		
		//create map fragment
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.addMarker(new MarkerOptions()
		        .position(new LatLng(lat, lng))
		        .title(desc));
		//scale camera to position
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 11));
	}
	
	/**
	 * method to finish activity by home button pressed
	 */
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
	
	/**
	 * method to finish activity on back button pressed
	 */
	@Override
	public void onBackPressed() {
		this.finish();
	}

}
