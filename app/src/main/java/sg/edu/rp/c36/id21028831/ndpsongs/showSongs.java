package sg.edu.rp.c36.id21028831.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class showSongs extends AppCompatActivity {

    ArrayList<Note> al;
    ListView lv;
    TextView btnGoBack;
    CustomAdapter caToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_songs);

        lv=findViewById(R.id.lv);
        btnGoBack=findViewById(R.id.btnGoBack);

        al = new ArrayList<Note>();
        caToDo=new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(caToDo);

        DBHelper dbh = new DBHelper(showSongs.this);
        al.clear();
        al.addAll(dbh.getAllNotes());
        caToDo.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Note data = al.get(position);
                Intent i = new Intent(showSongs.this,
                        EditSong.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}