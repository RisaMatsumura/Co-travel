package risamatsumura.github.com.co_travel.model.db;

import android.provider.BaseColumns;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by risam on 28/09/2015.
 */
public final class TripContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private TripContract() {}

    public static abstract class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_END_DATE = "end_date";
        public static final String COLUMN_DESCRIPTION = "description";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_DATE + TEXT_TYPE + COMMA_SEP +
                COLUMN_END_DATE + TEXT_TYPE + COMMA_SEP +
                COLUMN_DESCRIPTION + TEXT_TYPE +" )";

        private static final String COLUMNS =
                " (" + COLUMN_NAME + COMMA_SEP +
                        COLUMN_DATE + COMMA_SEP +
                        COLUMN_END_DATE + COMMA_SEP +
                        COLUMN_DESCRIPTION +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public static final String[] LIST_COLUMNS = {
                 _ID,
                COLUMN_NAME,
                COLUMN_DATE,
                COLUMN_END_DATE,
        };
    }
}
