package sg.edu.rp.c36.id21028831.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    ArrayList<Note> al;
    ListView lv;
    ArrayAdapter<Note> aa;

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

        lv=findViewById(R.id.lv);

        al = new ArrayList<Note>();
        aa = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(MainActivity.this,
                        EditSong.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

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

                String data=String.format("\nSong Name: %s\nSinger Name: %s\nSong Year: %s\nSong Rating: %s", songName, songSinger, songYear, rating);
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertNote(data);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllNotes());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllNotes());
//                String filterText = enterSong.getText().toString().trim();
//                if(filterText.length() == 0) {
//                    al.addAll(dbh.getAllNotes());
//                }
//                else{
//                    al.addAll(dbh.getAllNotes(filterText));
//                }
                aa.notifyDataSetChanged();
            }
        });

    }
}