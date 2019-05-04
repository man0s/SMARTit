package ceid.katefidis.smartit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        //ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(deviceList.get(position).getID());
        //imageView.setImageResource(imageId[position]);
        return rowView;
    }
}