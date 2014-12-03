package de.thwildau.telemetriedatasystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import de.thwildau.telemetriedatasystemapp.services.TDSNotificationService;

public class MainActivity extends Activity {
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Load.loadAct.finish();
		
        Intent i = new Intent(MainActivity.this, TDSNotificationService.class);
        i.putExtra("name", "SurvivingwithAndroid");       
        MainActivity.this.startService(i);
		
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
