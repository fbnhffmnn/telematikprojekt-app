package de.thwildau.telemetriedatasystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

/**
 * Class for "Load"-Activity
 * @author Fabian
 *
 */
public class Load extends Activity {

	//CountDownTimer for visibility
	private CountDownTimer countDownTimer;
	public static Activity loadAct; 	//needed for stopping this activity from another

	//Counter variables
	private final long startTime = 5 * 1000;
	private final long interval = 1 * 1000;
	
	/**
	 * Android Activity onCreate method
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		loadAct = this;
		countDownTimer = new MyCountDownTimer(startTime, interval);
		countDownTimer.start();
	}
	
	/**
	 * method to call main Activity "MainActivity"
	 */
	public void callMain(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);		
	}
	
	/**
	 * Subclass, that defines the CountDownTimer
	 * @author Fabian
	 *
	 */
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		/**
		 * onFinish method of the CountDownTimer, that calls the "callMain" method
		 */
		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			callMain();
		}

		/**
		 * not used!
		 */
		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
