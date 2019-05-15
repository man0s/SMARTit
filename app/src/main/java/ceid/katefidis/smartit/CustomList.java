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
import android.widget.LinearLayout;
import android.widget.SeekBar;
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
        final Switch device_switch = (Switch) rowView.findViewById(R.id.device_switch);
        //final ImageButton settings = rowView.findViewById(R.id.settings);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(deviceList.get(position).getID());
        device_switch.setChecked(deviceList.get(position).getEnabled());
        device_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                device_switch.setChecked(isChecked);
                deviceList.get(position).setEnabled(isChecked);
                if(isChecked) {
                    Log.i("Device", "Device #" + deviceList.get(position).getID() + " has been enabled!");
                    Toast.makeText(getContext(),"Device #" + deviceList.get(position).getID() + " has been enabled!", Toast.LENGTH_LONG).show();
                } else {
                    Log.i("Device", "Device #" + deviceList.get(position).getID() + " has been disabled!");
                    Toast.makeText(getContext(),"Device #" + deviceList.get(position).getID() + " has been disabled!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton settings_button = rowView.findViewById(R.id.device_settings);

        settings_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getContext(),"Buttoned", Toast.LENGTH_LONG).show();


                    // setup the alert builder
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Modify Settings");

                                        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                    // add a list

                    String[] settings = {};
                    if(deviceList.get(position).getSettings().equals("airconditioner"))
                    {
                        //settings = new String[]{deviceList.get(position).getID(), deviceList.get(position).getAirconditioner().getMode(), "camel", "sheep", "goat"};
                        settings = new String[]{"Temperature", "Mode", "Fan Direction", "Fan Intensity"};

                        builder.setItems(settings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // temperature
                                        AlertDialog.Builder builder0 = new AlertDialog.Builder(context);
                                        LayoutInflater inflater = context.getLayoutInflater();
                                        View view=inflater.inflate(R.layout.seekbar_dialog,null);
                                        builder0.setTitle("Temperature");
                                        builder0.setView(view);
                                        final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
                                        final TextView seekProgress = (TextView) view.findViewById(R.id.seekProgress);
                                        seekBar.setProgress(deviceList.get(position).getAirconditioner().getTemperature());
                                        seekProgress.setText(deviceList.get(position).getAirconditioner().getTemperature() + " ℃");
                                        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                            int progressValue;

                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                progressValue = progress;
                                                seekProgress.setText(progress + " ℃");
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {
                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {
                                                seekProgress.setText(progressValue + " ℃");
                                            }
                                        });

                                        builder0.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                deviceList.get(position).getAirconditioner().setTemperature(seekBar.getProgress());
                                                Toast.makeText(context, "Temperature has been set to " + seekBar.getProgress() + "℃!", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        AlertDialog alertDialog0 = builder0.create();
                                        alertDialog0.show();
                                        break;
                                    case 1: // mode

                                        // setup the alert builder
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                        builder1.setTitle("Choose a Mode");
                                        // add a radio button list
                                        String[] animals = {"Cool", "Normal", "Heat"};
                                        int checkedItem = 1; // normal
                                        if(deviceList.get(position).getAirconditioner().getMode().equals("cool"))
                                        {
                                            checkedItem = 0;
                                        } else if(deviceList.get(position).getAirconditioner().getMode().equals("heat"))
                                        {
                                            checkedItem = 2;
                                        }
                                        builder1.setSingleChoiceItems(animals, checkedItem, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                switch (which) {
                                                    case 0: // Cool
                                                        deviceList.get(position).getAirconditioner().setMode("cool");
                                                        break;
                                                     case 1: // normal
                                                        deviceList.get(position).getAirconditioner().setMode("normal");
                                                        break;
                                                    case 2: // heat
                                                        deviceList.get(position).getAirconditioner().setMode("heat");
                                                        break;
                                                }
                                            }
                                        });

                                        // add OK and Cancel buttons
                                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Toast.makeText(getContext(),"Mode has been set to "+ deviceList.get(position).getAirconditioner().getMode() +"!", Toast.LENGTH_LONG).show();
                                                // user clicked OK
                                            }
                                        });
                                        builder1.setNegativeButton("Cancel", null);

                                        // create and show the alert dialog
                                        AlertDialog dialog1 = builder1.create();
                                        dialog1.show();
                                        break;
                                    case 2: // fan direction
                                        AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                        LayoutInflater inflater2 = context.getLayoutInflater();
                                        View view2=inflater2.inflate(R.layout.seekbar_dialog2,null);
                                        builder2.setTitle("Fan Direction");
                                        builder2.setView(view2);
                                        final SeekBar seekBar2 = (SeekBar) view2.findViewById(R.id.seekBar1);
                                        final TextView seekProgress2 = (TextView) view2.findViewById(R.id.seekProgress);
                                        seekBar2.setProgress(deviceList.get(position).getAirconditioner().getDirection());
                                        seekProgress2.setText(deviceList.get(position).getAirconditioner().getDirection() + " °");
                                        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                            int progressValue;

                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                progressValue = progress;
                                                seekProgress2.setText(progress + " °");
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {
                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {
                                                seekProgress2.setText(progressValue + " °");
                                            }
                                        });

                                        builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                deviceList.get(position).getAirconditioner().setDirection(seekBar2.getProgress());
                                                Toast.makeText(context, "Fan Direction has been set to " + seekBar2.getProgress() + "°!", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        AlertDialog alertDialog2 = builder2.create();
                                        alertDialog2.show();
                                        break;
                                    case 3: // fan intensity
                                        AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
                                        LayoutInflater inflater3 = context.getLayoutInflater();
                                        View view3=inflater3.inflate(R.layout.seekbar_dialog,null);
                                        builder3.setTitle("Fan Intensity");
                                        builder3.setView(view3);
                                        final SeekBar seekBar3 = (SeekBar) view3.findViewById(R.id.seekBar1);
                                        final TextView seekProgress3 = (TextView) view3.findViewById(R.id.seekProgress);
                                        seekBar3.setProgress(deviceList.get(position).getAirconditioner().getIntensity());
                                        seekProgress3.setText(deviceList.get(position).getAirconditioner().getIntensity() + " %");
                                        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                            int progressValue;

                                            @Override
                                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                                progressValue = progress;
                                                seekProgress3.setText(progress + " %");
                                            }

                                            @Override
                                            public void onStartTrackingTouch(SeekBar seekBar) {
                                            }

                                            @Override
                                            public void onStopTrackingTouch(SeekBar seekBar) {
                                                seekProgress3.setText(progressValue + " %");
                                            }
                                        });

                                        builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                deviceList.get(position).getAirconditioner().setIntensityFan(seekBar3.getProgress());
                                                Toast.makeText(context, "Fan Intensity has been set to " + seekBar3.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        AlertDialog alertDialog3 = builder3.create();
                                        alertDialog3.show();
                                        break;
                                }
                            }
                        });

                        // create and show the alert dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();

                    } else if(deviceList.get(position).getSettings().equals("waterheater"))
                    {
                        settings = new String[]{"Temperature"};

                        builder.setItems(settings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // temperature
                                        break;
                                }
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        settings = new String[]{"Mode"};

                        builder.setItems(settings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0: // mode
                                        break;
                                }
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
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