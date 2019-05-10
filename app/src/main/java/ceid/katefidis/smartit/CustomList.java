package ceid.katefidis.smartit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<HomeDevice>{
    private final Activity context;
    private final ArrayList<HomeDevice> deviceList;
    //private final Integer[] imageId;
    public CustomList(Activity context,
                      ArrayList<HomeDevice> deviceList) {
        super(context, R.layout.list_single, deviceList);
        this.context = context;
        this.deviceList = deviceList;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        final Switch s = (Switch) rowView.findViewById(R.id.device_switch);
        //final ImageButton settings = rowView.findViewById(R.id.settings);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(deviceList.get(position).getID());
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

//        settings.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                //notifyDataSetChanged();
////                Intent i = new Intent(getContext(), ListSettingsActivity.class);
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                getContext().startActivity(i);
////                notifyDataSetChanged();
//                //((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
//                Log.i("Settings", "Settings of #" + deviceList.get(position).getID() + " has been oppened!");
//                // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setMessage("Look at this dialog!")
//                        .setCancelable(false)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                //do things
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.show();
//            }
//        });
        return rowView;
    }
}