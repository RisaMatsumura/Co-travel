package risamatsumura.github.com.co_travel.model.db;

import android.provider.BaseColumns;

/**
 * Created by risam on 28/09/2015.
 */
public final class PlaceContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PlaceContract() {}

    public static abstract class PlaceEntry implements BaseColumns {
        public static final String TABLE_NAME = "places";
        public static final String COLUMN_MARKER = "marker";
        public static final String COLUMN_LAT = "latitude";
        public static final String COLUMN_LONG = "longitude";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_MARKER + TEXT_TYPE + COMMA_SEP +
                COLUMN_LAT + TEXT_TYPE + COMMA_SEP +
                COLUMN_LONG + TEXT_TYPE +" )";

        private static final String COLUMNS =
                " (" + COLUMN_MARKER + COMMA_SEP +
                        COLUMN_LAT + COMMA_SEP +
                        COLUMN_LONG +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] LIST_COLUMNS = {
                 _ID,
                COLUMN_MARKER,
                COLUMN_LAT,
                COLUMN_LONG,
        };
    }
}
