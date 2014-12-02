package de.thwildau.telemetriedatasystemapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class Load extends Activity {

	private CountDownTimer countDownTimer;
	public static Activity loadAct;

	private final long startTime = 5 * 1000;
	private final long interval = 1 * 1000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load);
		loadAct = this;
		countDownTimer = new MyCountDownTimer(startTime, interval);
		countDownTimer.start();

	}
	
	public void callMain(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);		
	}
	
	public class MyCountDownTimer extends CountDownTimer {
		public MyCountDownTimer(long startTime, long interval) {
			super(startTime, interval);
		}

		@Override
		public void onFinish() {
			// TODO Auto-generated method stub
			callMain();
		}

		@Override
		public void onTick(long arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
