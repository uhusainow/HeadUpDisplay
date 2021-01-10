package at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

public class BluetoothConnection implements Serializable {

    public static final ArrayList<String> BLUETOOTH_DEVICES = new ArrayList<>(Arrays.asList("CBT", "KONNWEI", "CAN OBDII", "OBDII"));

    private BluetoothAdapter bluetoothAdapter;
    private BluetoothSocket bluetoothSocket;

    public BluetoothConnection(BluetoothAdapter bluetoothAdapter) throws BluetoothDeviceNotSupported, BluetoothNotEnabled, IOException {
        this.bluetoothAdapter = bluetoothAdapter;
    }

    public void init() throws BluetoothDeviceNotSupported, BluetoothNotEnabled, IOException {

        this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if(bluetoothAdapter!=null) {

            if (bluetoothAdapter.isEnabled()) {

                Set<BluetoothDevice> devices = this.bluetoothAdapter.getBondedDevices();

                for(BluetoothDevice bondedDevice: devices) {

                    Log.d("BluetoothConnection", "Starting Bluetooth connection..");
                    if(BluetoothConnection.BLUETOOTH_DEVICES.contains(bondedDevice.getName())) {

                        System.out.println("State: " + bondedDevice.getBondState());
                        try {

                            this.bluetoothSocket = bondedDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                            this.bluetoothSocket.connect();

                        } catch (Exception e1) {

                            Log.e("BluetoothConnection", "There was an error while establishing Bluetooth connection. Falling back..", e1);
                            Class<?> clazz = this.bluetoothSocket.getRemoteDevice().getClass();
                            Class<?>[] paramTypes = new Class<?>[]{Integer.TYPE};

                            try {

                                Method m = clazz.getMethod("createRfcommSocket", paramTypes);
                                Object[] params = new Object[]{Integer.valueOf(1)};
                                BluetoothSocket sockFallback = (BluetoothSocket) m.invoke(this.bluetoothSocket.getRemoteDevice(), params);
                                sockFallback.connect();
                                this.bluetoothSocket = sockFallback;
                                Log.i("Socket-Info", this.bluetoothSocket.getRemoteDevice().getName() + " : " + this.bluetoothSocket.isConnected());

                            } catch (Exception e2) {

                                Log.e("BluetoothConnection", "Couldn't fallback while establishing Bluetooth connection.", e2);
                                throw new IOException(e2.getMessage());

                            }

                        }

                        break;
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