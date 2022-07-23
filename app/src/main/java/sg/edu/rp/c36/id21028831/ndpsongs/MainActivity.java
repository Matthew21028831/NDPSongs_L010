package sg.edu.rp.c36.id21028831.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText enterSong;
    EditText enterSinger;
    EditText enterYear;
    RadioGroup rgRating;
    Button btnAdd;
    Button btnRetrieve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterSong=findViewById(R.id.enterSong);
        enterSinger=findViewById(R.id.enterSinger);
        enterYear=findViewById(R.id.enterYear);
        rgRating=findViewById(R.id.rgRating);
        btnAdd=findViewById(R.id.btnAdd);
        btnRetrieve=findViewById(R.id.btnRetrieve);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String songName = enterSong.getText().toString();
                String songSinger=enterSinger.getText().toString();
                String songYear=enterYear.getText().toString();
                String rating="";
                int checkedRadioId = rgRating.getCheckedRadioButtonId();
                if(checkedRadioId == R.id.oneStar){
                    rating="1";
                }
                else if(checkedRadioId == R.id.twoStar){
                    rating="2";

                }else if(checkedRadioId == R.id.threeStar){
                    rating="3";

                }else if(checkedRadioId == R.id.fourStar){
                    rating="4";

                }else if(checkedRadioId == R.id.fiveStar){
                    rating="5";
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(songName, songSinger, songYear, rating);

                if (inserted_id != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
//        DBHelper dbh = new DBHelper(MainActivity.this);
//        al.clear();
//        al.addAll(dbh.getAllNotes());
////                String filterText = enterSong.getText().toString().trim();
////                if(filterText.length() == 0) {
////                    al.addAll(dbh.getAllNotes());
////                }
////                else{
////                    al.addAll(dbh.getAllNotes(filterText));
////                }
//        aa.notifyDataSetChanged();

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, showSongs.class);
                startActivity(i);
                Log.d("activity started", "activity started");
            }
        });


    }
}