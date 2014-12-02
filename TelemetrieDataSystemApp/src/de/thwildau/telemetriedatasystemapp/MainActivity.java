package de.thwildau.telemetriedatasystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import de.thwildau.telemetriedatasystemapp.data.Connection;

public class MainActivity extends Activity {

	Connection connection = Connection.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Load.loadAct.finish();
		
		//test start
		Log.v("Server value=",connection.getServer());
		Integer meinInteger = new Integer(connection.getPort()); 
        String s = meinInteger.toString();
		Log.v("Port value=",s);
		Log.v("FahrzeugID value=",connection.getFahrzeugID());
		Log.v("Password value=",connection.getPassword());
		//test ende
		
		Button recevMess = (Button) findViewById(R.id.ButtonRecevMess);
		Button settings = (Button) findViewById(R.id.ButtonSetting);
		
		recevMess.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startSubActivity(ReceiveMessages.class);	
		    }
		});
		
		settings.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	startSubActivity(Settings.class);
		    }
		});
	}

	@SuppressWarnings("rawtypes")
	protected void startSubActivity(Class ClassToCall){
    	Intent intent = new Intent(this, ClassToCall);
		startActivity(intent);			
	}
}
