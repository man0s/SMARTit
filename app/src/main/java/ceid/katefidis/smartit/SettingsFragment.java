package ceid.katefidis.smartit;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.IntentCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        LinearLayout update = (LinearLayout) view.findViewById (R.id.updates);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "You have already the latest version!", Toast.LENGTH_LONG).show();
            }
        });

        LinearLayout signout = (LinearLayout) view.findViewById (R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //restart main activity
                Intent i = getContext().getPackageManager().getLaunchIntentForPackage( getContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Toast.makeText(getContext(), "You have been signed out!", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

}
