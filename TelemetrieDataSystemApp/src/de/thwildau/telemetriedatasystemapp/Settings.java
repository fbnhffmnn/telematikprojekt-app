package de.thwildau.telemetriedatasystemapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import de.thwildau.telemetriedatasystemapp.data.ConnectionData;

/**
 * Class for "Settings"-Activity
 * @author Fabian
 *
 */
public class Settings extends Activity {

	ConnectionData connection = ConnectionData.getInstance();

	EditText settingServer;
	EditText settingPort;
	EditText settingFahrzeugID;
	EditText settingPassword;

	/**
	 * Android Activity onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		//set back button in actionbar
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		//get button element
		Button save = (Button) findViewById(R.id.settingsSave);

		//get gui elements
		settingServer = (EditText) findViewById(R.id.settingsServer);
		settingPort = (EditText) findViewById(R.id.settingsPort);
		settingFahrzeugID = (EditText) findViewById(R.id.settingsFahrzeug);
		settingPassword = (EditText) findViewById(R.id.settingsPassword);

		//set user data from connection manager
		settingServer.setText(connection.getServer());
		settingPort.setText(String.valueOf(connection.getPort()));
		settingFahrzeugID.setText(connection.getFahrzeugID());
		settingPassword.setText(connection.getPassword());

		//save button listener
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				save(MainActivity.class);
			}
		});
	}

	/**
	 * method to save user input data to connection manager and return to "MainActivity"
	 * @param ClassToCall - Activity class that should be called
	 */
	@SuppressWarnings("rawtypes")
	protected void save(Class ClassToCall) {
		Intent intent = new Intent(this, ClassToCall);
		connection.setServer(settingServer.getText().toString());
		connection.setPort(Integer.valueOf(settingPort.getText().toString()));
		connection.setFahrzeugID(settingFahrzeugID.getText().toString());
		connection.setPassword(settingPassword.getText().toString());
		startActivity(intent);
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
