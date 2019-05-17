package ceid.katefidis.smartit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
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
                    settings = new String[]{"Speed", "Pressure", "Timer"};

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
                                case 2:
                                    AlertDialog.Builder builder5 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater5 = context.getLayoutInflater();
                                    View view5=inflater5.inflate(R.layout.seekbar_dialog4,null);
                                    builder5.setTitle("Timer");
                                    builder5.setView(view5);
                                    final SeekBar seekBar5 = (SeekBar) view5.findViewById(R.id.seekBar1);
                                    final TextView seekProgress5 = (TextView) view5.findViewById(R.id.seekProgress);
                                    seekBar5.setProgress(deviceList.get(position).getOnline_time());
                                    seekProgress5.setText(deviceList.get(position).getOnline_time() + " seconds");
                                    seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress5.setText(progress + " seconds");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress5.setText(progressValue + " seconds");
                                        }
                                    });

                                    builder5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).setOnlineTime(seekBar5.getProgress());
                                            Toast.makeText(context, "Timer has been set to " + deviceList.get(position).getOnline_time() + " seconds!", Toast.LENGTH_SHORT).show();

                                            AlertDialog.Builder builderTimer = new AlertDialog.Builder(context);
                                            final AlertDialog timerDialog = builderTimer.create();
                                            timerDialog.setTitle("Timer");
                                            timerDialog.setMessage("The device will shutdown in 00:10");
                                            timerDialog.show();   //

                                            new CountDownTimer(deviceList.get(position).getOnline_time() * 1000, 1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    deviceList.get(position).setOnlineTime((int) (long)(millisUntilFinished/1000));
                                                    timerDialog.setMessage("The device will shutdown in "+ (millisUntilFinished/1000) + " seconds!");
                                                }

                                                @Override
                                                public void onFinish() {
                                                    deviceList.get(position).setOnlineTime(0);
                                                    deviceList.get(position).setEnabled(false);
                                                    notifyDataSetChanged(); //refresh list data
                                                    timerDialog.setMessage("The device has been shutdown!");
                                                    Toast.makeText(context, "Device #" + deviceList.get(position).getID() + " has been shutdown!", Toast.LENGTH_SHORT).show();
                                                }
                                            }.start();

                                        }
                                    });

                                    AlertDialog alertDialog5 = builder5.create();
                                    alertDialog5.show();

                                    break;
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            new AlertDialog.Builder(context)
                                    .setTitle("Remove Device")
                                    .setMessage("Do you want to remove this device?")
                                    .setNegativeButton("Cancel", null) // dismisses by default
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialog, int which) {
                                            deviceList.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    })
                                    .create()
                                    .show();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else if (deviceList.get(position).getSettings().equals("heating")) {
                    settings = new String[]{"Temperature", "Mode", "Timer", "Auto"};

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
                                    AlertDialog.Builder builder5 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater5 = context.getLayoutInflater();
                                    View view5=inflater5.inflate(R.layout.seekbar_dialog4,null);
                                    builder5.setTitle("Timer");
                                    builder5.setView(view5);
                                    final SeekBar seekBar5 = (SeekBar) view5.findViewById(R.id.seekBar1);
                                    final TextView seekProgress5 = (TextView) view5.findViewById(R.id.seekProgress);
                                    seekBar5.setProgress(deviceList.get(position).getOnline_time());
                                    seekProgress5.setText(deviceList.get(position).getOnline_time() + " seconds");
                                    seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress5.setText(progress + " seconds");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress5.setText(progressValue + " seconds");
                                        }
                                    });

                                    builder5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).setOnlineTime(seekBar5.getProgress());
                                            Toast.makeText(context, "Timer has been set to " + deviceList.get(position).getOnline_time() + " seconds!", Toast.LENGTH_SHORT).show();

                                            AlertDialog.Builder builderTimer = new AlertDialog.Builder(context);
                                            final AlertDialog timerDialog = builderTimer.create();
                                            timerDialog.setTitle("Timer");
                                            timerDialog.setMessage("The device will shutdown in 00:10");
                                            timerDialog.show();   //

                                            new CountDownTimer(deviceList.get(position).getOnline_time() * 1000, 1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    deviceList.get(position).setOnlineTime((int) (long)(millisUntilFinished/1000));
                                                    timerDialog.setMessage("The device will shutdown in "+ (millisUntilFinished/1000) + " seconds!");
                                                }

                                                @Override
                                                public void onFinish() {
                                                    deviceList.get(position).setOnlineTime(0);
                                                    deviceList.get(position).setEnabled(false);
                                                    notifyDataSetChanged(); //refresh list data
                                                    timerDialog.setMessage("The device has been shutdown!");
                                                    Toast.makeText(context, "Device #" + deviceList.get(position).getID() + " has been shutdown!", Toast.LENGTH_SHORT).show();
                                                }
                                            }.start();

                                        }
                                    });

                                    AlertDialog alertDialog5 = builder5.create();
                                    alertDialog5.show();

                                    break;
                                case 3:
                                    //automath epilogh, pairnei ta data twn sensorwn kai ginetai automath eisagwgh twn rithmisewn
                                    deviceList.get(position).getHeating().setTemperature(deviceList.get(position).getSensorData().getTemperature());
                                    Toast.makeText(context, "Automatic Temperature " + deviceList.get(position).getHeating().getTemperature() + "℃!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            new AlertDialog.Builder(context)
                                    .setTitle("Remove Device")
                                    .setMessage("Do you want to remove this device?")
                                    .setNegativeButton("Cancel", null) // dismisses by default
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialog, int which) {
                                            deviceList.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    })
                                    .create()
                                    .show();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

                } else {
                    settings = new String[]{"Color", "Hue", "Timer", "Auto"};

                    builder.setItems(settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // color
                                    AlertDialog.Builder builder0 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater = context.getLayoutInflater();
                                    View view=inflater.inflate(R.layout.seekbar_dialog3,null);
                                    builder0.setTitle("Color Picker");
                                    builder0.setView(view);
                                    final SeekBar seekBar0 = (SeekBar) view.findViewById(R.id.seekBar1);
                                    final TextView seekProgress0 = (TextView) view.findViewById(R.id.seekProgress);
                                    seekBar0.setProgress(deviceList.get(position).getLighting().getColorR());
                                    seekProgress0.setText("R: " + deviceList.get(position).getLighting().getColorR());
                                    seekBar0.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress0.setText("R: " + progress);
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress0.setText("R: " + progressValue );
                                        }
                                    });

                                    final SeekBar seekBar01 = (SeekBar) view.findViewById(R.id.seekBar2);
                                    final TextView seekProgress01 = (TextView) view.findViewById(R.id.seekProgress2);
                                    seekBar01.setProgress(deviceList.get(position).getLighting().getColorG());
                                    seekProgress01.setText("G: " + deviceList.get(position).getLighting().getColorG());
                                    seekBar01.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress01.setText("G: " + progress);
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress01.setText("G: " + progressValue);
                                        }
                                    });

                                    final SeekBar seekBar02 = (SeekBar) view.findViewById(R.id.seekBar3);
                                    final TextView seekProgress02 = (TextView) view.findViewById(R.id.seekProgress3);
                                    seekBar02.setProgress(deviceList.get(position).getLighting().getColorB());
                                    seekProgress02.setText("B: " + deviceList.get(position).getLighting().getColorB());
                                    seekBar02.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress02.setText("B: " + progress);
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress02.setText("B: " + progressValue);
                                        }
                                    });

                                    builder0.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).getLighting().setColorR(seekBar0.getProgress());
                                            deviceList.get(position).getLighting().setColorG(seekBar01.getProgress());
                                            deviceList.get(position).getLighting().setColorB(seekBar02.getProgress());
                                            Toast.makeText(context, "Color has been set to (" + deviceList.get(position).getLighting().getColorR()
                                                    + ", " + deviceList.get(position).getLighting().getColorG() + ", " + deviceList.get(position).getLighting().getColorB() + ")!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog0 = builder0.create();
                                    alertDialog0.show();
                                    break;
                                case 1: // hue
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater1 = context.getLayoutInflater();
                                    View view1=inflater1.inflate(R.layout.seekbar_dialog,null);
                                    builder1.setTitle("Hue");
                                    builder1.setView(view1);
                                    final SeekBar seekBar1 = (SeekBar) view1.findViewById(R.id.seekBar1);
                                    final TextView seekProgress1 = (TextView) view1.findViewById(R.id.seekProgress);
                                    seekBar1.setProgress(deviceList.get(position).getLighting().getHue());
                                    seekProgress1.setText(deviceList.get(position).getLighting().getHue() + " %");
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
                                            deviceList.get(position).getLighting().setHue(seekBar1.getProgress());
                                            Toast.makeText(context, "Hue has been set to " + seekBar1.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog1 = builder1.create();
                                    alertDialog1.show();
                                    break;
                                case 2:
                                    AlertDialog.Builder builder5 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater5 = context.getLayoutInflater();
                                    View view5=inflater5.inflate(R.layout.seekbar_dialog4,null);
                                    builder5.setTitle("Timer");
                                    builder5.setView(view5);
                                    final SeekBar seekBar5 = (SeekBar) view5.findViewById(R.id.seekBar1);
                                    final TextView seekProgress5 = (TextView) view5.findViewById(R.id.seekProgress);
                                    seekBar5.setProgress(deviceList.get(position).getOnline_time());
                                    seekProgress5.setText(deviceList.get(position).getOnline_time() + " seconds");
                                    seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress5.setText(progress + " seconds");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress5.setText(progressValue + " seconds");
                                        }
                                    });

                                    builder5.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).setOnlineTime(seekBar5.getProgress());
                                            Toast.makeText(context, "Timer has been set to " + deviceList.get(position).getOnline_time() + " seconds!", Toast.LENGTH_SHORT).show();

                                            AlertDialog.Builder builderTimer = new AlertDialog.Builder(context);
                                            final AlertDialog timerDialog = builderTimer.create();
                                            timerDialog.setTitle("Timer");
                                            timerDialog.setMessage("The device will shutdown in 00:10");
                                            timerDialog.show();   //

                                            new CountDownTimer(deviceList.get(position).getOnline_time() * 1000, 1000) {
                                                @Override
                                                public void onTick(long millisUntilFinished) {
                                                    deviceList.get(position).setOnlineTime((int) (long)(millisUntilFinished/1000));
                                                    timerDialog.setMessage("The device will shutdown in "+ (millisUntilFinished/1000) + " seconds!");
                                                }

                                                @Override
                                                public void onFinish() {
                                                    deviceList.get(position).setOnlineTime(0);
                                                    deviceList.get(position).setEnabled(false);
                                                    notifyDataSetChanged(); //refresh list data
                                                    timerDialog.setMessage("The device has been shutdown!");
                                                    Toast.makeText(context, "Device #" + deviceList.get(position).getID() + " has been shutdown!", Toast.LENGTH_SHORT).show();
                                                }
                                            }.start();

                                        }
                                    });

                                    AlertDialog alertDialog5 = builder5.create();
                                    alertDialog5.show();

                                    break;
                                case 3: // auto
                                    //automath epilogh, pairnei ta data twn sensorwn kai ginetai automath eisagwgh twn rithmisewn
                                    deviceList.get(position).getLighting().setHue(deviceList.get(position).getSensorData().getLighting());
                                    Toast.makeText(context, "Automatic Hue " + deviceList.get(position).getLighting().getHue() + "%!", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });

                    builder.setNeutralButton("Remove", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            new AlertDialog.Builder(context)
                                    .setTitle("Remove Device")
                                    .setMessage("Do you want to remove this device?")
                                    .setNegativeButton("Cancel", null) // dismisses by default
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override public void onClick(DialogInterface dialog, int which) {
                                            deviceList.remove(position);
                                            notifyDataSetChanged();
                                        }
                                    })
                                    .create()
                                    .show();

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