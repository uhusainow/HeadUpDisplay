package at.ac.tgm.hit.uhusainow.headupdisplay.options;

import android.app.Activity;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.MainActivity;
import at.ac.tgm.hit.uhusainow.headupdisplay.R;

public class DefaultOption extends Option {

    public static final String OPTION_DEFAULT_TEXT = "+";

    public DefaultOption(Activity activity) {
        super(activity, null);
    }

    @Override
    public void createContent(int zoneId, int zoneContentId, int option) {

        //Check if mainactivity
        if (!(super.getActivity() instanceof MainActivity)) return;

        //Remove current content
        super.removeCurrentContent(zoneContentId);

        //Edit attributes of content
        TextView content = new TextView(super.getActivity());
        content.setId(zoneContentId);
        content.setTextSize(36);
        content.setText(DefaultOption.OPTION_DEFAULT_TEXT);

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

    }

    @Override
    public void updateContent(int zoneContentId) {

    }

}
