package sg.edu.rp.c36.id21028831.ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Note> versionList;

    public CustomAdapter(Context context, int resource, ArrayList<Note> objects){
        super(context, resource, objects);

        parent_context=context;
        layout_id=resource;
        versionList=objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvSong = rowView.findViewById(R.id.textViewSong);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvStars = rowView.findViewById(R.id.textViewStars);

        // Obtain the Android Version information based on the position
        Note currentVersion = versionList.get(position);

        // Set values to the TextView to display the corresponding information
        tvSong.setText(currentVersion.getSong());
        tvSinger.setText(currentVersion.getSingers());
        tvYear.setText(currentVersion.getYear());
        tvStars.setText(currentVersion.getStars());

        return rowView;
    }

}
