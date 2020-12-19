package at.ac.tgm.hit.uhusainow.headupdisplay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class OptionsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Intent intent = getIntent();

        /*String[] textString = getResources().getStringArray(R.array.options);
        int[] drawableIds = {R.drawable.img_id_row1, R.drawable.img_id_row2, R.drawable.img_id_row3, R.drawable.img_id_row4};

        CustomAdapter adapter = new CustomAdapter(this,  textString, drawableIds);


        listView1 = (ListView)findViewById(R.id.menuList);
        listView1.setAdapter(adapter);*/


        int zoneNumber = intent.getIntExtra("zone", 0);
        switch (zoneNumber){
            case R.id.zoneOne:
                zoneNumber = 1;
                break;
            case R.id.zoneTwo:
                zoneNumber = 2;
                break;
            case R.id.zoneThree:
                zoneNumber = 3;
                break;
        }

        TextView title = findViewById(R.id.title);
        title.setText("Zone " + zoneNumber);

        final int finalZoneNumber = zoneNumber;

        ListView list = findViewById(R.id.options);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(), "position " + position + "\nid " + id + "\nzoneNumber " + finalZoneNumber, Toast.LENGTH_SHORT).show();
                sendSelection(position, finalZoneNumber);
        }});

    }

    public void sendSelection(int position, int zoneNumber){

        Intent intent = new Intent();
        intent.putExtra("option", position);
        intent.putExtra("zone", zoneNumber);
        setResult(RESULT_OK, intent);
        finish();

    }

}