package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import at.ac.tgm.hit.uhusainow.headupdisplay.UIUpdater;
import com.github.pires.obd.commands.SpeedCommand;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class VelocityOption extends Option {

    public static final int VELOCITY_UPDATE_TIME = 2000;

    public VelocityOption(Activity activity, BluetoothSocket bluetoothSocket){

        super(activity, bluetoothSocket);

    }

    @Override
    public void createContent(int zoneId, int zoneContentId, int option) {

        //Remove current content
        super.removeCurrentContent(zoneContentId);

        //Edit attributes of content
        TextView content = new TextView(super.getActivity());
        content.setId(zoneContentId);
        content.setTextSize(36);

        //Add content to layout
        ConstraintLayout constraintLayout = super.getActivity().findViewById(R.id.constraintLayout);
        constraintLayout.addView(content);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(zoneContentId,ConstraintSet.RIGHT,zoneId,ConstraintSet.RIGHT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.TOP,zoneId,ConstraintSet.TOP,0);
        constraintSet.connect(zoneContentId,ConstraintSet.LEFT,zoneId,ConstraintSet.LEFT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.BOTTOM,zoneId,ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(constraintLayout);

        //Update Content
        super.setUpdater(new Timer());
        final int tmpZoneContentId = zoneContentId;
        super.getUpdater().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                TextView content = (TextView) getActivity().findViewById(tmpZoneContentId);
                if (content.getText().toString().equals(DefaultOption.OPTION_DEFAULT_TEXT)) {
                    stopUpdater();
                } else {
                    updateContent(tmpZoneContentId);
                }
                System.out.println("grrbahhh");
            }
        }, VelocityOption.VELOCITY_UPDATE_TIME, VelocityOption.VELOCITY_UPDATE_TIME);

    }

    @Override
    public void updateContent(int zoneContentId) {

        SpeedCommand speed = new SpeedCommand();
        speed.useImperialUnits(false);

        try {
            speed.run(super.getBluetoothSocket().getInputStream(), super.getBluetoothSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //final TextView content = super.getActivity().findViewById(zoneContentId);
        //content.setText(speed.getFormattedResult());
        final String kmh = speed.getFormattedResult();

        final int tmpZoneContentId = zoneContentId;
        super.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView test = (TextView) getActivity().findViewById(tmpZoneContentId);
                test.setText(kmh);
                //test.setText("Test" + Math.random());
            }
        });

    }

}
