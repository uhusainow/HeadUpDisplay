package at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class BluetoothConnection implements Serializable {
    public static final String BLEUTOOTH_DEVICE = "KENNWEI";

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;

    public BluetoothConnection(BluetoothAdapter bluetoothAdapter) throws BluetoothDeviceNotSupported, BluetoothNotEnabled, IOException {
        this.bluetoothAdapter = bluetoothAdapter;
        this.init();
    }

    private void init() throws BluetoothDeviceNotSupported, BluetoothNotEnabled, IOException {
        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice device = null;

        if(bluetoothAdapter!=null) {
            if (bluetoothAdapter.isEnabled()) {
                Set<BluetoothDevice> devices = this.bluetoothAdapter.getBondedDevices();

                for(BluetoothDevice bondedDevice: devices) {
                    //System.out.println("DEVICE: " + bondedDevice.getName());
                    if(bondedDevice.getName().equals(BluetoothConnection.BLEUTOOTH_DEVICE) ||
                            bondedDevice.getName().equals("CAN OBDII") ||
                            bondedDevice.getName().equals("CBT")){
                        this.bluetoothSocket = bondedDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                        this.bluetoothSocket.connect();
                        break;
                    } else {
                        throw new IOException();
                    }
                }
            } else {
                throw new BluetoothNotEnabled();
            }
        } else {
            throw new BluetoothDeviceNotSupported();
        }
    }

    public BluetoothSocket getBluetoothSocket() {
        return this.bluetoothSocket;
    }
}