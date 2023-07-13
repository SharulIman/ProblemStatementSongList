package sg.edu.rp.c346.id22018526.songlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "songlist.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_SONGS = "songs";

    private static final String COLUMN_ID = "_id";

    private static final String COLUMN_TITLE = "_title";

    private static final String COLUMN_SINGERS = "_singers";

    private static final String COLUMN_YEAR = "_year";

    private static final String COLUMN_STAR = "_stars";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONGS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGERS + " TEXT,"
                + COLUMN_YEAR + "INTEGER,"
                + COLUMN_STAR + " INTEGER )";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SONGS);
        onCreate(db);

    }
    public void insertSong(String title, String singers,int year, int stars){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_SINGERS, singers);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_STAR, stars);
        long result = db.insert(TABLE_SONGS, null, values);
        db.close();
        Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
    }
    public int updateNote(Song data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, data.getTitle());
        values.put(COLUMN_SINGERS, data.getSingers());
        values.put(COLUMN_YEAR, data.getYear());
        values.put(COLUMN_STAR, data.getStar());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_SONGS, values, condition, args);
        db.close();
        return result;
    }
    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONGS, condition, args);
        db.close();
        return result;
    }
    public ArrayList<Song> getAllSongs(int keyword) {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STAR};
        String condition = COLUMN_STAR + " Like ?";
        String[] args = { "%" +  keyword + "%"};
        Cursor cursor = db.query(TABLE_SONGS, columns, condition, args,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(title,singers,year,star );
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }
    public ArrayList<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<Song>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_SINGERS, COLUMN_YEAR, COLUMN_STAR};
        Cursor cursor = db.query(TABLE_SONGS, columns, null, null,
                null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singers = cursor.getString(2);
                int year = cursor.getInt(3);
                int star = cursor.getInt(4);
                Song song = new Song(title,singers,year,star );
                songs.add(song);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;
    }


}
