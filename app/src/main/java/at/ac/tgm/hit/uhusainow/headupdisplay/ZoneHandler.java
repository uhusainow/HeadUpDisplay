package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.*;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.*;

public class ZoneHandler {

    private static BluetoothConnection bluetoothConnection;

    public static void setZone(Activity activity, int zone, int option) {

        /*SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.file_key), Context.MODE_PRIVATE);
        int zoneOne = sharedPref.getInt("1", 0);
        int zoneTwo = sharedPref.getInt("2", 0);
        int zoneThree = sharedPref.getInt("3", 0);
        if (zone != 0) {
            if (zoneOne == zone || zoneTwo == zone || zoneThree == zone) {
                Toast.makeText(activity, "Modul bereits in Verwendung!", Toast.LENGTH_LONG).show();
                return;
            }
        }*/

        BluetoothSocket bluetoothSocket = bluetoothConnection.getBluetoothSocket();
        if(bluetoothSocket == null){
            System.out.println("Fehler 69420");
            return;
        }

        Option usedOption = new DefaultOption(activity);

        switch (option){

            case 1:
                usedOption = new VelocityOption(activity, bluetoothSocket);
                break;

            case 2:
                usedOption = new FuelOption(activity, bluetoothSocket);
                break;

            case 3:
                usedOption = new RPMOption(activity, bluetoothSocket);
                break;

            case 4:
                usedOption = new ThrottlePositionOption(activity, bluetoothSocket);
                break;

            case 5:
                usedOption = new AmbientTempOption(activity, bluetoothSocket);
                break;

        }

        switch (zone) {
            case 1:
                usedOption.createContent(R.id.zoneOne, 69420, option);
                break;
            case 2:
                usedOption.createContent(R.id.zoneTwo, 69421, option);
                break;
            case 3:
                usedOption.createContent(R.id.zoneThree, 69422, option);
                break;
        }

        saveZone(activity, zone, option);

    }

    public static void saveZone(Activity activity, int zone, int option){

        SharedPreferences sharedPref = activity.getSharedPreferences(activity.getString(R.string.file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(zone+"", option);
        editor.commit();

    }

    public static void setBluetoothConnection(BluetoothConnection bc) {
        bluetoothConnection = bc;
    }

}
