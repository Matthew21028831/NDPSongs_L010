package sg.edu.rp.c36.id21028831.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditSong extends AppCompatActivity {
    EditText editSong;
    EditText editSinger;
    EditText editRelease;
    Button btnUpdate;
    Button btnDelete;
    TextView goBack;
    Note data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);
        editSong=findViewById(R.id.editSong);
        editSinger=findViewById(R.id.editSinger);
        editRelease=findViewById(R.id.editSinger);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        goBack=findViewById(R.id.goBack);

        Intent i = getIntent();
        data = (Note) i.getSerializableExtra("data");

        editSong.setText(data.getSong());
        editSinger.setText(data.getSingers());
        editRelease.setText(data.getYear());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.updateNote(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteNote(data.getId());
            }
        });

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}