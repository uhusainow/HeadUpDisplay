package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.BluetoothConnection;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.BluetoothDeviceNotSupported;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.BluetoothNotEnabled;
import at.ac.tgm.hit.uhusainow.headupdisplay.listener.DoubleClickListener;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            BluetoothConnection bluetoothConnection = new BluetoothConnection(BluetoothAdapter.getDefaultAdapter());
            ZoneHandler.setBluetoothConnection(bluetoothConnection);
        } catch (BluetoothDeviceNotSupported e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Bluetooth");
            alert.setMessage("Das Bluetooth-Device wird nicht unterstützt!");
            alert.setPositiveButton("OK", null);
            alert.show();
        } catch (BluetoothNotEnabled e) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        } catch (IOException e) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Bluetooth");
            alert.setMessage("Es ist ein Fehler bei der Verbindung mit dem Bleutooth-Device aufgetreten!");
            alert.setPositiveButton("OK", null);
            alert.show();
        }

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.file_key), Context.MODE_PRIVATE);
        int zoneOne = sharedPref.getInt("1", 0);
        int zoneTwo = sharedPref.getInt("2", 0);
        int zoneThree = sharedPref.getInt("3", 0);

        ZoneHandler.setZone(this,1, zoneOne);
        ZoneHandler.setZone(this,2, zoneTwo);
        ZoneHandler.setZone(this,3, zoneThree);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                Intent intent = new Intent(getApplicationContext(), MirrorActivity.class);
                startActivity(intent);
            }
        });

        /*SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();*/

    }

    public void sendToOptions(View v) {

        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra("zone", v.getId());
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                int zone = data.getIntExtra("zone", -1);
                int option = data.getIntExtra("option", -1);
                ZoneHandler.setZone(this, zone, option);

            }
        }

    }

}