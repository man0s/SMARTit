package ceid.katefidis.smartit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        //set home tab as default navigation item
        navigation.setSelectedItemId(R.id.navigation_home);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container, settingsFragment, "3").hide(settingsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, adddeviceFragment, "2").hide(adddeviceFragment).commit();
        fm.beginTransaction().add(R.id.main_container,homeFragment, "1").commit();
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

    /** Called when the user taps the Add Device button */
    public void addDevice(View view) {
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

}
