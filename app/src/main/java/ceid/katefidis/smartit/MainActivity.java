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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
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

        BottomNavigationView navigation = findViewById(R.id.navigation);

        if(userType.equals("admin"))
        {
            navigation.getMenu().findItem(R.id.navigation_home).setTitle("Building");
            navigation.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_building_black_24dp);

        } else if(userType.equals("security")){
            navigation.getMenu().findItem(R.id.navigation_home).setTitle("Security");
            navigation.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_shield_black_24dp);
        } else {
            navigation.getMenu().findItem(R.id.navigation_home).setTitle("Home");
            navigation.getMenu().findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_black_24dp);
        }

        Log.d("UserType", userType);

        //Users object creation
        Admin admin = new Admin(1,"Emmanouil Katefidis", "katefidis@ceid.upatras.gr", "man0s", "12345", "6946948164");
        SecurityStaff security = new SecurityStaff(2, "Dionysis Papaspyros", "dpapaspyros@ceid.upatras.gr", "spyr0s", "12345", "6945612378");
        Tenant tenant = new Tenant(3,"Panagiotis Stavrinakis", "stavrinakis@ceid.upatras.gr", "pan0s", "12345", "6933404889");

        //Building creation
        final Building building = new Building("Panepisthmioupolh Patrwn", admin, security);

        //Apartment creation
        final Apartment apartment = new Apartment("5", tenant);

        //Add the apartment to the building
        building.addApartment(apartment);


            //when the user clicks the scan qr button
            Button adddeviceButton = (Button) findViewById(R.id.adddeviceButton);
            final CustomList customList = new CustomList(MainActivity.this, apartment.getDeviceList());
            final CustomMaintenanceList customMaintenanceList = new CustomMaintenanceList(MainActivity.this, building.getMaintanceDeviceList());
            final CustomSecurityList customSecurityList = new CustomSecurityList(MainActivity.this, building.getSecurityDeviceList());

            adddeviceButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if(userType.equals("tenant")) {
                        addDeviceTenant(view, apartment, customList);
                    } else {
                        addDeviceBuilding(view, userType, building, customMaintenanceList, customSecurityList);
                    }
                }
            });

            //when the user clicks the scan qr button
            Button scanqrButton = (Button) findViewById(R.id.QRcodescannerButton);

            scanqrButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    scanQR(view);
                }
            });

        final ListView lstItems;

        lstItems = (ListView)findViewById(R.id.lstItems);


        if(userType.equals("tenant"))
        {
            lstItems.setAdapter(customList);
        } else if (userType.equals("admin")){
            lstItems.setAdapter(customMaintenanceList);
        } else {
            lstItems.setAdapter(customSecurityList);
        }

//        lstItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                HomeDevice device = (HomeDevice) lstItems.getItemAtPosition(i);
//                Log.i("List", "You clicked on device " + device.getID());
//            }
//        });

//        final ImageButton settings = lstItems.getSelectedView().findViewById(R.id.settings);
//        settings.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Log.i("EPITELOUS", "PATHITHIKE");
////                Intent i = new Intent(getContext(), DevicePreference.class);
//                //((AppCompatActivity) context).getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
//            }
//        });





    }

    /** Called when the tenant taps the Add Device button */
    public void addDeviceTenant(View view, Apartment apartment, CustomList customList) {
        TextInputEditText serialnumberText = findViewById(R.id.serialnumberInput);
        String serialNumber = serialnumberText.getText().toString();
        int timeStamp = (int) (new Date().getTime()/1000);
        Sensors sensor = new Sensors(timeStamp, 26, 85, 0);



        if(!apartment.searchDevice(serialNumber)){ //an h suskeuh den uparxei sthn lista me ta devices
            //sensor.pickEnviromentalData(); //eisagwgh live data apo to perivalon;

            //Elegxos apo remote server gia to an uparxei to sygkekrimeno serial kai lhpsh twn stoixeiwn(px onoma) ths sugkekrimenhs suskeus

            HomeDevice device = new HomeDevice(serialNumber, 0, serialNumber, sensor, -1, true);

            //lipsi apo ton server ti syskeuh antistoixei to sygkektimeno serialnumber..

            //An to serialnumberarxizei me 1, einai Aircondition
            if(serialNumber.charAt(0) == '1'){
                device.setSettings("airconditoner");
                //An to serialnumberarxizei me 2, einai Waterheater
            } else if (serialNumber.charAt(0) == '2') {
                device.setSettings("waterheater");
                //alliws einai cleaningrobot
            } else device.setSettings("cleaningrobot");

            apartment.addHomeDevice(device);
            customList.notifyDataSetChanged(); //refresh

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

    /** Called when the tenant taps the Add Device button */
    public void addDeviceBuilding(View view, String userType, Building building, CustomMaintenanceList customMaintenanceList, CustomSecurityList customSecurityList) {
        TextInputEditText serialnumberText = findViewById(R.id.serialnumberInput);
        String serialNumber = serialnumberText.getText().toString();
        int timeStamp = (int) (new Date().getTime()/1000);
        Sensors sensor = new Sensors(timeStamp, 26, 85, 0);


        if(userType.equals("admin")) {
            if (!building.searchDevice(serialNumber)) { //an h suskeuh den uparxei sthn lista me ta devices
                //sensor.pickEnviromentalData(); //eisagwgh live data apo to perivalon;

                //Elegxos apo remote server gia to an uparxei to sygkekrimeno serial kai lhpsh twn stoixeiwn(px onoma) ths sugkekrimenhs suskeus

                MaintenanceDevice device  = new MaintenanceDevice(serialNumber, 0, serialNumber, sensor, -1, true);

                //An to serialnumberarxizei me 1, einai Aircondition
                if(serialNumber.charAt(0) == '1'){
                    device.setSettings("sprinkler");
                    //An to serialnumberarxizei me 2, einai Waterheater
                } else if (serialNumber.charAt(0) == '2') {
                    device.setSettings("heating");
                    //alliws einai cleaningrobot
                } else device.setSettings("lighting");

                building.addMaintenceDevice(device);
                customMaintenanceList.notifyDataSetChanged(); //refresh list
                String addText = "Device #" + serialNumber + " added!";
                Toast.makeText(MainActivity.this, addText, addText.length()).show();
                Log.i("info", "Device #" + serialNumber + " added!");
            } else { //alliws an uparxei hdh
                String addText = "Device #" + serialNumber + " already exists!";
                Toast.makeText(MainActivity.this, addText, addText.length()).show();
                Log.i("info", "Device #" + serialNumber + " already exists!");
            }
        } else {
            if (!building.searchSecDevice(serialNumber)) { //an h suskeuh den uparxei sthn lista me ta devices
                //sensor.pickEnviromentalData(); //eisagwgh live data apo to perivalon;

                //Elegxos apo remote server gia to an uparxei to sygkekrimeno serial kai lhpsh twn stoixeiwn(px onoma) ths sugkekrimenhs suskeus

                SecurityDevice device = new SecurityDevice(serialNumber, 0, serialNumber, sensor, -1, true);

                //An to serialnumberarxizei me 1, einai camera
                if(serialNumber.charAt(0) == '1'){
                    device.setSettings("camera");
                    //Alliws, einai doorlock
                } else {
                    device.setSettings("doorlock");
                }

                building.addSecurityDevice(device);
                customSecurityList.notifyDataSetChanged(); //refresh list
                String addText = "Device #" + serialNumber + " added!";
                Toast.makeText(MainActivity.this, addText, addText.length()).show();
                Log.i("info", "Device #" + serialNumber + " added!");
            } else { //alliws an uparxei hdh
                String addText = "Device #" + serialNumber + " already exists!";
                Toast.makeText(MainActivity.this, addText, addText.length()).show();
                Log.i("info", "Device #" + serialNumber + " already exists!");
            }
        }

        //Call function that adds device to DB
        //if device already exists in DB show that
        //else if device added then successful info text
        //else not successful info text
    }

    /** Called when the user taps the Scan QR Code button */
    public void scanQR(View view) {
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
