package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.Option;
import at.ac.tgm.hit.uhusainow.headupdisplay.options.VelocityOption;
import org.w3c.dom.Text;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        int zoneOne = sp.getInt("1", 0);
        int zoneTwo = sp.getInt("2", 0);
        int zoneThree = sp.getInt("3", 0);

        setZone(1, zoneOne);
        setZone(2, zoneTwo);
        setZone(3, zoneThree);
        /*SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();*/

    }

    public void sendToOptions(View v) {

        Intent intent = new Intent(this, OptionsActivity.class);
        intent.putExtra("zone", v.getId());
        startActivityForResult(intent, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                int zone = data.getIntExtra("zone", -1);
                int option = data.getIntExtra("option", -1);
                setZone(zone, option);

            }
        }

    }

    public void setZone(int zone, int option) {

        /**
         * *****************************************************************************************************
         * *****************************************************************************************************
         * **************************************** Needs to be Changed ****************************************
         * ************************************* alternative for usedOption ************************************
         * *****************************************************************************************************
         * *****************************************************************************************************
         */
        Option usedOption = new Option(this);

        switch (option){

            case 1:
                usedOption = new VelocityOption(this);
                break;

            case 2:
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            case 6:
                break;

        }

        switch (zone) {
            case 1:
                usedOption.createContent(R.id.zoneOne, R.id.zoneOneText, 69420, option);
                break;
            case 2:
                usedOption.createContent(R.id.zoneTwo, R.id.zoneTwoText, 69421, option);
                break;
            case 3:
                usedOption.createContent(R.id.zoneThree, R.id.zoneThreeText, 69422, option);
                break;
        }

    }

    public void saveZone(int zone, int position){

        SharedPreferences sharedPref = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(zone+"", position);
        editor.commit();

    }


}