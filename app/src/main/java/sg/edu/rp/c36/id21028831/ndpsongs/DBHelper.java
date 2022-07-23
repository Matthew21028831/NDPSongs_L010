package sg.edu.rp.c36.id21028831.ndpsongs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "simpleSongs.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_NOTE = "note";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SONG = "song";
    private static final String COLUMN_SINGERS = "singers";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_STARS = "stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_NOTE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_SONG + " TEXT," + COLUMN_SINGERS +" TEXT," +COLUMN_YEAR+" TEXT,"+COLUMN_STARS+" TEXT) ";
        db.execSQL(createNoteTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN  module_name TEXT ");
//        onCreate(db);
    }

    public long insertNote(String song, String singer, String year, String stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG, song);
        values.put(COLUMN_SINGERS, singer);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STARS, stars);
        long result = db.insert(TABLE_NOTE, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
        return result;
    }

    public ArrayList<Note> getAllNotes() {
        ArrayList<Note> notes = new ArrayList();

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {COLUMN_ID, COLUMN_SONG, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STARS};
        Log.d("coloumn ran", "coloumn ran");
        Cursor cursor = db.query(TABLE_NOTE, columns, null, null,
                null, null, null, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String song = cursor.getString(1);
                        String singer=cursor.getString(2);
                        String year=cursor.getString(3);
                        String stars=cursor.getString(4);
                        Note note = new Note(id, song, singer, year, stars);
                        notes.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notes;
    }

    public int updateNote(Note data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SONG, data.getSong());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_NOTE, values, condition, args);
        db.close();
        return result;
    }

    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_NOTE, condition, args);
        db.close();
        return result;
    }



//    public ArrayList<Note> getAllNotes(String keyword) {
//        ArrayList<Note> notes = new ArrayList<Note>();
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columns= {COLUMN_ID, COLUMN_NOTE_CONTENT};
//        String condition = COLUMN_NOTE_CONTENT + " Like ?";
//        String[] args = { "%" +  keyword + "%"};
//        Cursor cursor = db.query(TABLE_NOTE, columns, condition, args,
//                null, null, null, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String noteContent = cursor.getString(1);
//                Note note = new Note(id, noteContent);
//                notes.add(note);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//        return notes;
//    }


}