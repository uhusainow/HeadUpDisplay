package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.Option;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.VelocityOption;
import at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth.*;

import java.io.IOException;

public class ZoneHandler {

    private static BluetoothConnection bluetoothConnection;

    private static Option oldOption;

    public static void setZone(Activity activity, int zone, int option) {

        BluetoothSocket bluetoothSocket = bluetoothConnection.getBluetoothSocket();

        /**
         * *****************************************************************************************************
         * *****************************************************************************************************
         * **************************************** Needs to be Changed ****************************************
         * ************************************* alternative for usedOption ************************************
         * *****************************************************************************************************
         * *****************************************************************************************************
         */
        Option usedOption = new Option(activity);
        boolean test = true;

        switch (option){

            case 1:
                usedOption = new VelocityOption(bluetoothSocket, activity);
                test = false;
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
                usedOption.createContent(R.id.zoneOne, R.id.zoneOneText, 69420, option);
                break;
            case 2:
                usedOption.createContent(R.id.zoneTwo, R.id.zoneTwoText, 69421, option);
                break;
            case 3:
                usedOption.createContent(R.id.zoneThree, R.id.zoneThreeText, 69422, option);
                break;
        }

        if(test && oldOption != null && oldOption.getUpdater() != null) oldOption.getUpdater().stopUpdates();
        oldOption = usedOption;

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
