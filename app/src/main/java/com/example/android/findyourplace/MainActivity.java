package com.example.android.findyourplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {
    private TextView getPlace1;
int PLACE_PICKER=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPlace1=(TextView)findViewById(R.id.getPlace);
        getPlace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                Intent intent = null;
                try {
                    intent=intentBuilder.build(MainActivity.this);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent,PLACE_PICKER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PLACE_PICKER){
            if(resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                String addr = String.format("Place: %s",place.getAddress());
                getPlace1.setText(addr);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
