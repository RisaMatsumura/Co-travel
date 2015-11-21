package risamatsumura.github.com.co_travel.model.db;

import android.provider.BaseColumns;

/**
 * Created by risam on 28/09/2015.
 */
public final class UserContract {

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMMA_SEP = ",";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private UserContract() {}

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_USER_NAME = "user_name";
        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_PASSWORD = "password";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_USER_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_FIRST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_LAST_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_PASSWORD + TEXT_TYPE +" )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
