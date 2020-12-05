package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.MainActivity;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import at.ac.tgm.hit.uhusainow.headupdisplay.UIUpdater;
import com.github.pires.obd.commands.SpeedCommand;
import org.w3c.dom.Text;

import java.io.IOException;


/**
 * Class to create content for velocity
 */
public class VelocityOption extends Option{

    private BluetoothSocket bluetoothSocket;

    /**
     * Initializing all attributes
     * @param activity
     */
    public VelocityOption(BluetoothSocket bluetoothSocket, Activity activity){

        super(activity);
        this.bluetoothSocket = bluetoothSocket;

    }

    @Override
    public void createContent(int zoneId, int zoneTextId, int zoneContentId, int option) {

        super.removeCurrentContent(zoneContentId);

        //Create specific elements
        if (super.getActivity() instanceof MainActivity) {
            TextView tv = (TextView) super.getActivity().findViewById(zoneTextId);
            if (tv.getVisibility() != View.GONE) tv.setVisibility(View.GONE);
        }

        //Edit attributes of content
        TextView test = new TextView(super.getActivity());
        test.setId(zoneContentId);
        test.setTextSize(36);

        //Add content to layout
        ConstraintLayout constraintLayout = super.getActivity().findViewById(R.id.constraintLayout);
        constraintLayout.addView(test);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(zoneContentId,ConstraintSet.RIGHT,zoneId,ConstraintSet.RIGHT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.TOP,zoneId,ConstraintSet.TOP,0);
        constraintSet.connect(zoneContentId,ConstraintSet.LEFT,zoneId,ConstraintSet.LEFT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.BOTTOM,zoneId,ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(constraintLayout);

        //Update Content
        final int tmpZoneContentId = zoneContentId;
        super.setUpdater(new UIUpdater(new Runnable() {
            @Override
            public void run() {
                setSpeed(tmpZoneContentId);
            }
        }));
        super.getUpdater().startUpdates();

    }

    public void setSpeed(int zoneContentId) {

        /*SpeedCommand speed = new SpeedCommand();
        speed.useImperialUnits(false);

        try {
            speed.run(bluetoothSocket.getInputStream(),bluetoothSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        TextView content = super.getActivity().findViewById(zoneContentId);
        //content.setText(speed.getFormattedResult());
        content.setText("Test" + Math.random());

    }

}
