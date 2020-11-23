package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import io.github.macfja.obd2.Commander;
import io.github.macfja.obd2.command.livedata.VehicleSpeed;
import io.github.macfja.obd2.exception.ExceptionResponse;

import java.io.IOException;

/**
 * Class to create content for velocity
 */
public class VelocityOption extends Option{

    private Commander commander;

    /**
     * Initializing all attributes
     * @param activity
     */
    public VelocityOption(/*BluetoothSocket bs,*/ Activity activity){

        //this.commander = new Commander();
        super(activity);

        /*try {
            this.commander.setCommunicationInterface(bs.getOutputStream(), bs.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void createContent(int zoneId, int zoneTextId, int zoneContentId, int option) {

        //Get ConstraintLayout
        ConstraintLayout constraintLayout = super.getActivity().findViewById(R.id.constraintLayout);

        //Remove current content in zone
        if (super.getActivity().findViewById(zoneContentId) != null) constraintLayout.removeView(super.getActivity().findViewById(zoneContentId));

        //Check if no option selected

        //Create specific elements
        TextView tv = (TextView) super.getActivity().findViewById(zoneTextId);
        if (tv.getVisibility() != View.GONE) tv.setVisibility(View.GONE);

        //Edit attributes of content
        TextView test = new TextView(super.getActivity());
        /*try {
            test.setText(this.commander.sendCommand(new VehicleSpeed()) + "");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExceptionResponse exceptionResponse) {
            exceptionResponse.printStackTrace();
        }*/
        test.setText("Test erfolgreich" + Math.random());
        test.setId(zoneContentId);
        test.setTextSize(36);

        //Add content to layout
        constraintLayout.addView(test);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(zoneContentId,ConstraintSet.RIGHT,zoneId,ConstraintSet.RIGHT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.TOP,zoneId,ConstraintSet.TOP,0);
        constraintSet.connect(zoneContentId,ConstraintSet.LEFT,zoneId,ConstraintSet.LEFT,0);
        constraintSet.connect(zoneContentId,ConstraintSet.BOTTOM,zoneId,ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(constraintLayout);

    }

}
