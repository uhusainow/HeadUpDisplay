package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import at.ac.tgm.hit.uhusainow.headupdisplay.listener.DoubleClickListener;

public class MirrorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mirror);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.file_key), Context.MODE_PRIVATE);
        int zoneOne = sharedPref.getInt("1", 0);
        int zoneTwo = sharedPref.getInt("2", 0);
        int zoneThree = sharedPref.getInt("3", 0);

        ZoneHandler.setZone(this,1, zoneOne);
        ZoneHandler.setZone(this,2, zoneTwo);
        ZoneHandler.setZone(this,3, zoneThree);

        TextView zoneOneContent = findViewById(69420);
        TextView zoneTwoContent = findViewById(69421);
        TextView zoneThreeContent = findViewById(69422);

        if (zoneOneContent != null) {
            zoneOneContent.setScaleX(-1);
            zoneOneContent.setScaleY(-1);
        }

        if (zoneTwoContent != null) {
            zoneTwoContent.setScaleX(-1);
            zoneTwoContent.setScaleY(-1);
        }

        if (zoneThreeContent != null) {
            zoneThreeContent.setScaleX(-1);
            zoneThreeContent.setScaleY(-1);
        }

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                finish();
            }
        });

    }

}