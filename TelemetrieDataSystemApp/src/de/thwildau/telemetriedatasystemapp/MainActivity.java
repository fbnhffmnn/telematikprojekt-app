package de.thwildau.telemetriedatasystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import de.thwildau.telemetriedatasystemapp.data.ConnectionData;
import de.thwildau.telemetriedatasystemapp.services.TDSNotificationService;

/**
 * Class for "MainActivity"-Activity
 * @author Fabian
 *
 */
public class MainActivity extends Activity {
	
	ConnectionData connection = ConnectionData.getInstance();
	Button service = null;
	/**
	 * Android Activity onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Load.loadAct.finish();		//stop previous activity
		
		//define next activities
        Intent i = new Intent(MainActivity.this, TDSNotificationService.class);
        i.putExtra("name", "MainActivity");       
        MainActivity.this.startService(i);
		
        //get buttons from layout
		Button recevMess = (Button) findViewById(R.id.ButtonRecevMess);
		service = (Button) findViewById(R.id.ButtonService);
		Button settings = (Button) findViewById(R.id.ButtonSetting);
		
		//set listener
		recevMess.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startSubActivity(ReceiveMessages.class);	
		    }
		});
		service.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	if(connection.getService() == true){
		    		service.setText("Server Connection: OFF");
		    		connection.setService(false);
		    	}else{
		    		service.setText("Server Connection: ON");
		    		connection.setService(true);
		    	}
		    }
		});
		settings.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startSubActivity(Settings.class);
		    }
		});
	}

	/**
	 * method to start next activity from button
	 * @param ClassToCall - Activity class that should be called
	 */
	@SuppressWarnings("rawtypes")
	protected void startSubActivity(Class ClassToCall){
    	Intent intent = new Intent(this, ClassToCall);
		startActivity(intent);			
	}
}
