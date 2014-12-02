package de.thwildau.telemetriedatasystemapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.thwildau.telemetriedatasystemapp.data.Notification;

public class NotificationArrayAdapter extends ArrayAdapter<Notification> {
	private final Context context;
	private final Notification[] values;
	
	public NotificationArrayAdapter(Context context, Notification[] values) {
		super(context, R.layout.activity_receive_messages, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.activity_receive_messages, parent, false);
	    TextView textViewfirst = (TextView) rowView.findViewById(R.id.firstLine);
	    TextView textViewsecond = (TextView) rowView.findViewById(R.id.secondLine);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    textViewfirst.setText(String.valueOf(values[position].getType().getTypeName()));
	    String date = String.valueOf(values[position].getDatum().get(Calendar.DAY_OF_MONTH));
	    date += ".";
	    date += String.valueOf(values[position].getDatum().get(Calendar.MONTH));
	    date += ".";
	    date += String.valueOf(values[position].getDatum().get(Calendar.YEAR));
	    date += "           ";
	    date += String.valueOf(values[position].getDatum().get(Calendar.HOUR_OF_DAY));
	    date += ":";
	    date += String.valueOf(values[position].getDatum().get(Calendar.MINUTE));
	    date += ":";
	    date += String.valueOf(values[position].getDatum().get(Calendar.SECOND));
	    textViewsecond.setText(date);
	    // Change the icon for Windows and iPhone
//	    String s = values[position];
//	    if (s.startsWith("Windows7") || s.startsWith("iPhone")
//	        || s.startsWith("Solaris")) {
//	      imageView.setImageResource(R.drawable.no);
//	    } else {
//	      imageView.setImageResource(R.drawable.ok);
//	    }

	    return rowView;
	}

}
