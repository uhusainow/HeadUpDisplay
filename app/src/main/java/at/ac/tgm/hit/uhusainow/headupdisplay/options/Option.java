package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import at.ac.tgm.hit.uhusainow.headupdisplay.UIUpdater;

public abstract class Option {

    private Activity activity;
    private BluetoothSocket bluetoothSocket;
    private UIUpdater updater;

    public Option(Activity activity, BluetoothSocket bluetoothSocket) {
        this.activity = activity;
        this.bluetoothSocket = bluetoothSocket;
        this.updater = null;
    }

    public Activity getActivity() { return this.activity; }

    public abstract void createContent(int zoneId, int zoneContentId, int option);

    public abstract void updateContent(int zoneContentId);

    public void removeCurrentContent(int zoneContentId) {

        //Get ConstraintLayout
        ConstraintLayout constraintLayout = this.activity.findViewById(R.id.constraintLayout);

        //Remove current content in zone
        if (this.activity.findViewById(zoneContentId) != null) {
            System.out.println(((TextView)this.activity.findViewById(zoneContentId)).getText().toString());
            constraintLayout.removeView(this.activity.findViewById(zoneContentId));
        }

    }

    public BluetoothSocket getBluetoothSocket() { return this.bluetoothSocket; }

    public void setUpdater(UIUpdater updater) { this.updater = updater; }

    public UIUpdater getUpdater() { return this.updater; }

    /**
     * Doesnt work
     * Needs to be fixed
     */
    public void stopUpdater() {
        //this.getUpdater().stopUpdates();
        System.out.println("stop");
        this.updater = null;
    }

}
