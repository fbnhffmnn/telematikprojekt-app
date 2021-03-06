package de.thwildau.telemetriedatasystemapp.services;

import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.thwildau.telemetriedatasystemapp.R;
import de.thwildau.telemetriedatasystemapp.data.TDSMessage;

/**
 * Class defines the layout of the received messages list
 * extends the array adapter
 * @author Fabian
 *
 */
public class NotificationArrayAdapter extends ArrayAdapter<TDSMessage> {
	private final Context context;
	private final TDSMessage[] values;
	
	/**
	 * constructor
	 * @param context
	 * @param values - array of TDSMessage objects
	 */
	public NotificationArrayAdapter(Context context, TDSMessage[] values) {
		super(context, R.layout.activity_receive_messages, values);
		this.context = context;
		this.values = values;
	}

	/**
	 * override predefined getView method
	 * is used to define the layout of the single list entries of the array 
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    View rowView = inflater.inflate(R.layout.activity_receive_messages, parent, false);
	    TextView textViewfirst = (TextView) rowView.findViewById(R.id.firstLine);
	    TextView textViewsecond = (TextView) rowView.findViewById(R.id.secondLine);
	    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	    imageView.setImageResource(values[position].getType().getImage());
	    textViewfirst.setText(String.valueOf(values[position].getType().getTypeName()));
	    String date = String.valueOf(values[position].getDatum().get(Calendar.DAY_OF_MONTH));
	    date += ".";
	    date += String.valueOf(values[position].getDatum().get(Calendar.MONTH)+1);
	    date += ".";
	    date += String.valueOf(values[position].getDatum().get(Calendar.YEAR));
	    date += "           ";
	    date += String.valueOf(values[position].getDatum().get(Calendar.HOUR_OF_DAY));
	    date += ":";
	    date += String.valueOf(values[position].getDatum().get(Calendar.MINUTE));
	    date += ":";
	    date += String.valueOf(values[position].getDatum().get(Calendar.SECOND));
	    textViewsecond.setText(date);
	    return rowView;
	}
}
