package ceid.katefidis.smartit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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
                if (deviceList.get(position).getSettings().equals("sprinkler")) {
                    settings = new String[]{"Speed", "Pressure"};

                    builder.setItems(settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // temperature
                                    AlertDialog.Builder builder0 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater = context.getLayoutInflater();
                                    View view=inflater.inflate(R.layout.seekbar_dialog,null);
                                    builder0.setTitle("Speed");
                                    builder0.setView(view);
                                    final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
                                    final TextView seekProgress = (TextView) view.findViewById(R.id.seekProgress);
                                    seekBar.setProgress(deviceList.get(position).getSprinkler().getSpeed());
                                    seekProgress.setText(deviceList.get(position).getSprinkler().getSpeed() + " %");
                                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress.setText(progress + " %");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress.setText(progressValue + " %");
                                        }
                                    });

                                    builder0.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).getSprinkler().setSpeed(seekBar.getProgress());
                                            Toast.makeText(context, "Speed has been set to " + seekBar.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog0 = builder0.create();
                                    alertDialog0.show();
                                    break;
                                case 1:
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater1 = context.getLayoutInflater();
                                    View view1=inflater1.inflate(R.layout.seekbar_dialog,null);
                                    builder1.setTitle("Pressure");
                                    builder1.setView(view1);
                                    final SeekBar seekBar1 = (SeekBar) view1.findViewById(R.id.seekBar1);
                                    final TextView seekProgress1 = (TextView) view1.findViewById(R.id.seekProgress);
                                    seekBar1.setProgress(deviceList.get(position).getSprinkler().getPressure());
                                    seekProgress1.setText(deviceList.get(position).getSprinkler().getPressure() + " %");
                                    seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress1.setText(progress + " %");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress1.setText(progressValue + " %");
                                        }
                                    });

                                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).getSprinkler().setPressure(seekBar1.getProgress());
                                            Toast.makeText(context, "Pressure has been set to " + seekBar1.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog1 = builder1.create();
                                    alertDialog1.show();
                                    break;
                            }
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (deviceList.get(position).getSettings().equals("heating")) {
                    settings = new String[]{"Temperature", "Mode", "Auto"};

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
                                    seekBar.setProgress(deviceList.get(position).getHeating().getTemperature());
                                    seekProgress.setText(deviceList.get(position).getHeating().getTemperature() + " ℃");
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
                                            deviceList.get(position).getHeating().setTemperature(seekBar.getProgress());
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
                                    String[] modes = {"Cool", "Normal", "Heat"};
                                    int checkedItem = 1; // normal
                                    if(deviceList.get(position).getHeating().getMode().equals("cool"))
                                    {
                                        checkedItem = 0;
                                    } else if(deviceList.get(position).getHeating().getMode().equals("heat"))
                                    {
                                        checkedItem = 2;
                                    }
                                    builder1.setSingleChoiceItems(modes, checkedItem, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            switch (which) {
                                                case 0: // Cool
                                                    deviceList.get(position).getHeating().setMode("cool");
                                                    break;
                                                case 1: // normal
                                                    deviceList.get(position).getHeating().setMode("normal");
                                                    break;
                                                case 2: // heat
                                                    deviceList.get(position).getHeating().setMode("heat");
                                                    break;
                                            }
                                        }
                                    });

                                    // add OK and Cancel buttons
                                    builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getContext(),"Mode has been set to "+ deviceList.get(position).getHeating().getMode() +"!", Toast.LENGTH_LONG).show();
                                            // user clicked OK
                                        }
                                    });
                                    builder1.setNegativeButton("Cancel", null);

                                    // create and show the alert dialog
                                    AlertDialog dialog1 = builder1.create();
                                    dialog1.show();
                                    break;
                                case 2:
                                    //automath epilogh, pairnei ta data twn sensorwn kai ginetai automath eisagwgh twn rithmisewn
                                    deviceList.get(position).getHeating().setTemperature(deviceList.get(position).getSensorData().getTemperature());
                                    Toast.makeText(context, "Automatic Temperature " + deviceList.get(position).getHeating().getTemperature() + "℃!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    settings = new String[]{"Color", "Hue", "Auto"};

                    builder.setItems(settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // color
                                    //colorpicker todo
                                    break;
                                case 1: // hue
                                    AlertDialog.Builder builder0 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater = context.getLayoutInflater();
                                    View view=inflater.inflate(R.layout.seekbar_dialog,null);
                                    builder0.setTitle("Hue");
                                    builder0.setView(view);
                                    final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
                                    final TextView seekProgress = (TextView) view.findViewById(R.id.seekProgress);
                                    seekBar.setProgress(deviceList.get(position).getLighting().getHue());
                                    seekProgress.setText(deviceList.get(position).getLighting().getHue() + " %");
                                    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress.setText(progress + " %");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress.setText(progressValue + " %");
                                        }
                                    });

                                    builder0.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).getLighting().setHue(seekBar.getProgress());
                                            Toast.makeText(context, "Hue has been set to " + seekBar.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog0 = builder0.create();
                                    alertDialog0.show();
                                    break;
                                case 2: // auto
                                    //automath epilogh, pairnei ta data twn sensorwn kai ginetai automath eisagwgh twn rithmisewn
                                    deviceList.get(position).getLighting().setHue(deviceList.get(position).getSensorData().getLighting());
                                    Toast.makeText(context, "Automatic Hue " + deviceList.get(position).getLighting().getHue() + "%!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
            });





        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}