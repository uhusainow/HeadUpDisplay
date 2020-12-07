package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import at.ac.tgm.hit.uhusainow.headupdisplay.UIUpdater;

public class VelocityOption extends Option {

    public static final int VELOCITY_ZONE_CONTENT_ID = 69420;

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
        content.setText("test");

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
        updateContent(zoneContentId);

    }

    @Override
    public void updateContent(int zoneContentId) {
        /*SpeedCommand speed = new SpeedCommand();
        speed.useImperialUnits(false);

        try {
            speed.run(super.getBluetoothSocket().getInputStream(), super.getBluetoothSocket().getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //final TextView content = super.getActivity().findViewById(zoneContentId);
        //content.setText(speed.getFormattedResult());
        //content.setText("Test" + Math.random());

        final int tmpZoneContentId = zoneContentId;
        super.setUpdater(new UIUpdater(new Runnable() {
            @Override
            public void run() {
                TextView test = (TextView) getActivity().findViewById(tmpZoneContentId);
                if (test.getText().toString().equals(DefaultOption.OPTION_DEFAULT_TEXT)) {
                    stopUpdater();
                } else {
                    test.setText("Test" + Math.random());
                }

            }
        }));

        super.getUpdater().startUpdates();

    }

}
