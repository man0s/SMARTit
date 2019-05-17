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

public class CustomSecurityList extends ArrayAdapter<SecurityDevice> {
    private final Activity context;
    private final ArrayList<SecurityDevice> deviceList;
    //private final Integer[] imageId;
    public CustomSecurityList(Activity context,
                                 ArrayList<SecurityDevice> deviceList) {
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
                if (deviceList.get(position).getSettings().equals("camera")) {
                    settings = new String[]{"Zoom", "Rotation", "Playback"};

                    builder.setItems(settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // zoom
                                    AlertDialog.Builder builder0 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater = context.getLayoutInflater();
                                    View view = inflater.inflate(R.layout.seekbar_dialog, null);
                                    builder0.setTitle("Zoom");
                                    builder0.setView(view);
                                    final SeekBar seekBar = (SeekBar) view.findViewById(R.id.seekBar1);
                                    final TextView seekProgress = (TextView) view.findViewById(R.id.seekProgress);
                                    seekBar.setProgress(deviceList.get(position).getCamera().getZoom());
                                    seekProgress.setText(deviceList.get(position).getCamera().getZoom() + " %");
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
                                            deviceList.get(position).getCamera().setZoom(seekBar.getProgress());
                                            Toast.makeText(context, "Zoom has been set to " + seekBar.getProgress() + "%!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog0 = builder0.create();
                                    alertDialog0.show();
                                    break;
                                case 1: //rotation
                                    AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater2 = context.getLayoutInflater();
                                    View view2=inflater2.inflate(R.layout.seekbar_dialog2,null);
                                    builder2.setTitle("Rotation");
                                    builder2.setView(view2);
                                    final SeekBar seekBar2 = (SeekBar) view2.findViewById(R.id.seekBar1);
                                    final TextView seekProgress2 = (TextView) view2.findViewById(R.id.seekProgress);
                                    seekBar2.setProgress(deviceList.get(position).getCamera().getRotation());
                                    seekProgress2.setText(deviceList.get(position).getCamera().getRotation() + " °");
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
                                            deviceList.get(position).getCamera().setRotation(seekBar2.getProgress());
                                            Toast.makeText(context, "Camera Rotation has been set to " + seekBar2.getProgress() + "°!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog2 = builder2.create();
                                    alertDialog2.show();
                                    break;
                                case 2:
                                    AlertDialog.Builder builder3 = new AlertDialog.Builder(context);
                                    LayoutInflater inflater3 = context.getLayoutInflater();
                                    View view3=inflater3.inflate(R.layout.seekbar_dialog4,null);
                                    builder3.setTitle("Playback");
                                    builder3.setView(view3);
                                    final SeekBar seekBar3 = (SeekBar) view3.findViewById(R.id.seekBar1);
                                    final TextView seekProgress3 = (TextView) view3.findViewById(R.id.seekProgress);
                                    seekBar3.setProgress(deviceList.get(position).getCamera().getPlayback());
                                    seekProgress3.setText(deviceList.get(position).getCamera().getPlayback() + " seconds");
                                    seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                        int progressValue;

                                        @Override
                                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                            progressValue = progress;
                                            seekProgress3.setText(progress + " seconds");
                                        }

                                        @Override
                                        public void onStartTrackingTouch(SeekBar seekBar) {
                                        }

                                        @Override
                                        public void onStopTrackingTouch(SeekBar seekBar) {
                                            seekProgress3.setText(progressValue + " seconds");
                                        }
                                    });

                                    builder3.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            deviceList.get(position).getCamera().setPlayback(seekBar3.getProgress());
                                            Toast.makeText(context, "Playback has been set to " + seekBar3.getProgress() + " seconds!", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    AlertDialog alertDialog3 = builder3.create();
                                    alertDialog3.show();
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
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
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
                } else //einai doorlock
                {
                    settings = new String[]{"Lock Status"};

                    builder.setItems(settings, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case 0: // lockstatus
                                    if(!deviceList.get(position).getDoorlock().getLockpicked())
                                    {
                                        Toast.makeText(context, "Lock is secured.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Lock is lockpicked!", Toast.LENGTH_SHORT).show();
                                    }

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
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
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