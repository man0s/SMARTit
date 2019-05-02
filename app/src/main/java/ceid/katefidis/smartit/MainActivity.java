package ceid.katefidis.smartit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    final Fragment homeFragment = new HomeFragment();
    final Fragment adddeviceFragment = new AddDeviceFragment();
    final Fragment settingsFragment = new SettingsFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = homeFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_adddevice:
                    fm.beginTransaction().hide(active).show(adddeviceFragment).commit();
                    active = adddeviceFragment;
                    return true;
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(homeFragment).commit();
                    active = homeFragment;
                    return true;
                case R.id.navigation_settings:
                    fm.beginTransaction().hide(active).show(settingsFragment).commit();
                    active = settingsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        //set home tab as default navigation item
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, settingsFragment, "3").hide(settingsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, adddeviceFragment, "2").hide(adddeviceFragment).commit();
        fm.beginTransaction().add(R.id.main_container,homeFragment, "1").commit();

//        boolean firstRun = settings.getBoolean("firstRun", true);
//        if ( firstRun )
//        {

            startActivityForResult(
                    new Intent(this, UserTypeActivity.class),
                    1);

 //       }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        if(resultCode == 1)
        {
            editor.putString("user_type", "admin");
        }
        else if(resultCode == 2)
        {
            editor.putString("user_type", "security");
        }
        else
        {
            editor.putString("user_type", "tenant");
        }
        //editor.putBoolean("firstRun", false);
        editor.apply();

        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String userType = preferences.getString("user_type", "tenant");

        Log.d("UserType", userType);

        if(userType.equals("tenant")){
            Tenant tenant = new Tenant(1,"Emmanouil Katefidis", "katefidis@ceid.upatras.gr", "man0s", "12345", "6946948164");
            final Apartment apartment = new Apartment("2016", tenant);

            //when the user clicks the scan qr button
            Button adddeviceButton = (Button) findViewById(R.id.adddeviceButton);

            adddeviceButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    addDeviceTenant(view, apartment);
                }
            });

            //when the user clicks the scan qr button
            Button scanqrButton = (Button) findViewById(R.id.QRcodescannerButton);

            scanqrButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    scanQR(view, userType);
                }
            });

        }




    }

    /** Called when the tenant taps the Add Device button */
    public void addDeviceTenant(View view, Apartment apartment) {
        TextInputEditText serialnumberText = findViewById(R.id.serialnumberInput);
        String serialNumber = serialnumberText.getText().toString();
        int timeStamp = (int) (new Date().getTime()/1000);
        Sensors sensor = new Sensors(timeStamp, 26, 85, 0);


        if(!apartment.searchDevice(serialNumber)){ //an h suskeuh den uparxei sthn lista me ta devices
            //sensor.pickEnviromentalData(); //eisagwgh live data apo to perivalon;
            apartment.addHomeDevice(new HomeDevice(serialNumber, 0, "Test Device", sensor, -1));
            String addText = "Device #" + serialNumber + " added!";
            Toast.makeText(MainActivity.this, addText, addText.length()).show();
            Log.i("info", "Device #" + serialNumber + " added!");
        } else { //alliws an uparxei hdh
            String addText = "Device #" + serialNumber + " already exists!";
            Toast.makeText(MainActivity.this, addText, addText.length()).show();
            Log.i("info", "Device #" + serialNumber + " already exists!");
        }



        //Call function that adds device to DB
        //if device already exists in DB show that
        //else if device added then successful info text
        //else not successful info text
    }

    /** Called when the user taps the Add Device button */
    public void addDevice(View view, String userType) {
        TextInputEditText serialnumberText = findViewById(R.id.serialnumberInput);
        String serialNumber = serialnumberText.getText().toString();
        String infoText = "Adding Device #" + serialNumber;
        Toast.makeText(MainActivity.this, infoText, infoText.length()).show();
        Log.i("info", "Adding Device #" + serialNumber);



        //Call function that adds device to DB
        //if device already exists in DB show that
        //else if device added then successful info text
        //else not successful info text
    }

    /** Called when the user taps the Scan QR Code button */
    public void scanQR(View view, String userType) {
        String infoText = "Scanning QR Code";
        Toast.makeText(MainActivity.this, infoText, infoText.length()).show();
        Log.i("info", infoText);

        //Call function that scans the QR Code
        //if the QR Code input is a serial number then add the device to DB
        //if device already exists in DB show that
        //else if device added then successful info text
        //else not successful info text
    }


}
