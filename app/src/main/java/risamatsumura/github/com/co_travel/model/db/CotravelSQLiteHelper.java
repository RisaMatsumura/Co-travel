package risamatsumura.github.com.co_travel.model.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;

import java.util.Calendar;

import risamatsumura.github.com.co_travel.utils.DateFormatter;
import risamatsumura.github.com.co_travel.R;
import risamatsumura.github.com.co_travel.control.ImageHandler;

/**
 * Created by risam on 28/09/2015.
 */
public class CotravelSQLiteHelper extends SQLiteOpenHelper {

    private Context context;

    public CotravelSQLiteHelper(Context context) {
        super(context, DBContract.DATABASE_NAME, null, DBContract.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String table: DBContract.TABLES) {
            db.execSQL(table);
        }

        insertTestTrips(db);
        insertTestPhotos(db);
        insertTestPlaces(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TripContract.TripEntry.DELETE_TABLE);
        onCreate(db);
    }

    private void insertTestPhoto(SQLiteDatabase db, String title, byte[] image, Calendar date) {
        ContentValues cv = new ContentValues();
        cv.put(PhotoContract.PhotoEntry.COLUMN_TITLE, title);
        cv.put(PhotoContract.PhotoEntry.COLUMN_BINARY_DATA, image);
        cv.put(PhotoContract.PhotoEntry.COLUMN_DATE, DateFormatter.getTimeInMillis(date));
        db.insert(PhotoContract.PhotoEntry.TABLE_NAME, null, cv);
    }

    private void insertTestTrip(SQLiteDatabase db, String name, Calendar date, Calendar endDate) {
        ContentValues cv = new ContentValues();
        cv.put(TripContract.TripEntry.COLUMN_NAME, name);
        cv.put(TripContract.TripEntry.COLUMN_DATE, DateFormatter.getTimeInMillis(date));
        cv.put(TripContract.TripEntry.COLUMN_END_DATE, DateFormatter.getTimeInMillis(date));
        db.insert(TripContract.TripEntry.TABLE_NAME, null, cv);
    }

    private void insertTestPlace(SQLiteDatabase db, String marker, double latitude, double longitude) {
        ContentValues cv = new ContentValues();
        cv.put(PlaceContract.PlaceEntry.COLUMN_MARKER, marker);
        cv.put(PlaceContract.PlaceEntry.COLUMN_LAT, Double.toString(latitude));
        cv.put(PlaceContract.PlaceEntry.COLUMN_LONG, Double.toString(longitude));
        db.insert(PlaceContract.PlaceEntry.TABLE_NAME, null, cv);
    }

    public void insertTestPhotos(SQLiteDatabase db) {
        byte[] image;
        Calendar date = Calendar.getInstance();

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.trip_test_1));
        date.set(2000, 9, 24);
        insertTestPhoto(db, "Trip 1", image, date);

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.trip_test_2));
        date.set(2001, 9, 24);
        insertTestPhoto(db, "Trip 2", image, date);

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.trip_test_3));
        date.set(2002, 9, 24);
        insertTestPhoto(db, "Trip 3", image, date);

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.place_test_1));
        date.set(2000, 9, 24);
        insertTestPhoto(db, "Place 1", image, date);

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.place_test_2));
        date.set(2001, 9, 24);
        insertTestPhoto(db, "Place 2", image, date);

        image = ImageHandler.getBytes(BitmapFactory.decodeResource(context.getResources(), R.drawable.place_test_3));
        date.set(2002, 9, 24);
        insertTestPhoto(db, "Place 3", image, date);
    }

    public void insertTestTrips(SQLiteDatabase db) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(2000, 9, 24);
        endDate.set(2000, 9, 30);
        insertTestTrip(db, "Trip 1", startDate, endDate);

        startDate.set(2001, 9, 24);
        endDate.set(2001, 9, 30);
        insertTestTrip(db, "Trip 2", startDate, endDate);

        startDate.set(2003, 9, 24);
        endDate.set(2003, 9, 30);
        insertTestTrip(db, "Trip 3", startDate, endDate);
    }

    private void insertTestPlaces(SQLiteDatabase db) {

        insertTestPlace(db, "Place 1", 35.6814, 139.7661);
        insertTestPlace(db, "Place 2", 35.6844, 139.7631);
        insertTestPlace(db, "Place 3", 35.6814, 139.7691);
    }
}
