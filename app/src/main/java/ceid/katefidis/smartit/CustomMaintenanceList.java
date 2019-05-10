package ceid.katefidis.smartit;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomMaintenanceList extends ArrayAdapter<MaintenanceDevice> {
    private final Activity context;
    private final ArrayList<MaintenanceDevice> deviceList;
    //private final Integer[] imageId;
    public CustomMaintenanceList(Activity context,
                                 ArrayList<MaintenanceDevice> deviceList) {
        super(context, R.layout.list_single, deviceList);
        this.context = context;
        this.deviceList = deviceList;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(deviceList.get(position).getID());
        final Switch s = (Switch) rowView.findViewById(R.id.device_switch);
        s.setChecked(deviceList.get(position).getEnabled());
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                s.setChecked(isChecked);
                deviceList.get(position).setEnabled(isChecked);
                if(isChecked) {
                    Log.i("Device", "Device #" + deviceList.get(position).getID() + " has been enabled!");
                } else {
                    Log.i("Device", "Device #" + deviceList.get(position).getID() + " has been disabled!");
                }
            }
        });
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}