package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.MainActivity;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;

public class Option {

    //Default option for zone
    public static final int DEFAULT_OPTION = 0;

    private Activity activity;

    /**
     * Initializing all attributes
     * @param activity
     */
    public Option(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the activity
     * @return the activity
     */
    public Activity getActivity() { return this.activity; }

    /**
     *
     * @param zoneId
     * @param zoneTextId
     * @param zoneContentId
     * @param option
     */
    public void createContent(int zoneId, int zoneTextId, int zoneContentId, int option) {

        //Get ConstraintLayout
        ConstraintLayout constraintLayout = this.activity.findViewById(R.id.constraintLayout);

        //Remove current content in zone
        if (this.activity.findViewById(zoneContentId) != null) constraintLayout.removeView(this.activity.findViewById(zoneContentId));

        //Check if no option selected and set default content visible
        TextView tv = (TextView) this.activity.findViewById(zoneTextId);
        tv.setVisibility(View.VISIBLE);

    };

}
