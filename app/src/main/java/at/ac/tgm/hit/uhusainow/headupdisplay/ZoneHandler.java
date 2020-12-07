package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.SharedPreferences;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.DefaultOption;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.Option;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.VelocityOption;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.*;

public class ZoneHandler {

    private static BluetoothConnection bluetoothConnection;

    public static void setZone(Activity activity, int zone, int option) {

        BluetoothSocket bluetoothSocket = bluetoothConnection.getBluetoothSocket();

        Option usedOption = new DefaultOption(activity);

        switch (option){

            case 1:
                usedOption = new VelocityOption(activity, bluetoothSocket);
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
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
