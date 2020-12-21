package at.ac.tgm.hit.uhusainow.headupdisplay.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothConnection implements Serializable {
    public static final String BLEUTOOTH_DEVICE = "AirPods von Predator680";

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
                    System.out.println("searching");
                    /*if(bondedDevice.getName().equals(BluetoothConnection.BLEUTOOTH_DEVICE)){
                        System.out.println("found");
                        this.bluetoothSocket = bondedDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                        //this.bluetoothSocket.connect();
                        break;
                    } else {
                        throw new IOException();
                    }*/
                    System.out.println("Starting Bluetooth connection..");
                    if(bondedDevice.getName().equals(BluetoothConnection.BLEUTOOTH_DEVICE)) {
                        System.out.println("State: " + bondedDevice.getBondState());
                        try {
                            this.bluetoothSocket = bondedDevice.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                            this.bluetoothSocket.connect();
                        } catch (Exception e1) {
                            System.out.println("There was an error while establishing Bluetooth connection. Falling back..");
                            Class<?> clazz = this.bluetoothSocket.getRemoteDevice().getClass();
                            Class<?>[] paramTypes = new Class<?>[]{Integer.TYPE};
                            try {
                                Method m = clazz.getMethod("createRfcommSocket", paramTypes);
                                Object[] params = new Object[]{Integer.valueOf(1)};
                                BluetoothSocket sockFallback = (BluetoothSocket) m.invoke(this.bluetoothSocket.getRemoteDevice(), params);
                                sockFallback.connect();
                                this.bluetoothSocket = sockFallback;
                                System.out.println("Socket: " + this.bluetoothSocket.getRemoteDevice().getName() + " : " + this.bluetoothSocket.isConnected());
                            } catch (Exception e2) {
                                System.out.println("Couldn't fallback while establishing Bluetooth connection.");
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