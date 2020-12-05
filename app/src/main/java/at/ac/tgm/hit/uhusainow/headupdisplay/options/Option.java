package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import at.ac.tgm.hit.uhusainow.headupdisplay.MainActivity;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;
import at.ac.tgm.hit.uhusainow.headupdisplay.UIUpdater;

public class Option {

    //Default option for zone
    public static final int DEFAULT_OPTION = 0;

    private Activity activity;
    private UIUpdater updater;

    /**
     * Initializing all attributes
     * @param activity
     */
    public Option(Activity activity) {
        this.activity = activity;
        this.updater = null;
    }

    /**
     * Returns the activity
     * @return the activity
     */
    public Activity getActivity() { return this.activity; }

    public void setUpdater(UIUpdater updater) { this.updater = updater; }

    public UIUpdater getUpdater() { return this.updater; }

    /**
     *
     * @param zoneId
     * @param zoneTextId
     * @param zoneContentId
     * @param option
     */
    public void createContent(int zoneId, int zoneTextId, int zoneContentId, int option) {

        removeCurrentContent(zoneContentId);

        //Check if no option selected and set default content visible
        if (getActivity() instanceof MainActivity) {
            TextView tv = (TextView) this.activity.findViewById(zoneTextId);
            tv.setVisibility(View.VISIBLE);
        }

    }

    public void removeCurrentContent(int zoneContentId) {

        //Get ConstraintLayout
        ConstraintLayout constraintLayout = this.activity.findViewById(R.id.constraintLayout);

        //Remove current content in zone
        if (this.activity.findViewById(zoneContentId) != null) constraintLayout.removeView(this.activity.findViewById(zoneContentId));

        if (updater != null) updater.stopUpdates();

    }

}
