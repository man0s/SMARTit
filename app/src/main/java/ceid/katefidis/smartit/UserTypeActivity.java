package ceid.katefidis.smartit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class UserTypeActivity extends Activity {

    private Button user_button;
    private Button admin_button;
    private Button security_button;
    private int return_code = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_type);


        user_button = (Button) findViewById(R.id.userButton);
        admin_button = (Button) findViewById(R.id.adminButton);
        security_button = (Button) findViewById(R.id.securityButton);

        user_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("User Type", "User");
                 return_code = -1;
                //go back to app
                Intent smartit = new Intent();
                setResult(return_code, smartit);
                //---close the activity---
                finish();
            }
        });

        admin_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("User Type", "Admin");
                return_code = 1;
                //go back to app
                Intent smartit = new Intent();
                setResult(return_code, smartit);
                //---close the activity---
                finish();
            }
        });

        security_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("User Type", "Security");
                return_code = 2;
                //go back to app
                Intent smartit = new Intent();
                setResult(return_code, smartit);
                //---close the activity---
                finish();
            }
        });


    }

}
