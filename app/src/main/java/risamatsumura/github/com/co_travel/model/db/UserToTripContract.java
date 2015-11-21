package risamatsumura.github.com.co_travel.model.db;

import android.provider.BaseColumns;

/**
 * Created by risam on 28/09/2015.
 */
public final class UserToTripContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private UserToTripContract() {}

    public static abstract class UserToTripEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_to_trip";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_TRIP_ID = "trip_id";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_USER_ID + TEXT_TYPE + COMMA_SEP +
                COLUMN_TRIP_ID + TEXT_TYPE +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
